//
//  SoftmaxExecution.cpp
//  MNN
//
//  Created by MNN on 2019/02/28.
//  Copyright © 2018, Alibaba Group Holding Limited
//

#include "backend/opencl/execution/image/SoftmaxExecution.hpp"
#include "core/Macro.h"
#include "backend/opencl/core/OpenCLRunningUtils.hpp"

namespace MNN {
namespace OpenCL {

SoftmaxExecution::SoftmaxExecution(const std::vector<Tensor *> &inputs, int axis, const MNN::Op *op, Backend *backend)
    : CommonExecution(backend, op) {
    mAxis          = axis;
    mOpenCLBackend = static_cast<OpenCLBackend *>(backend);
    auto kernel = mOpenCLBackend->getOpenCLRuntime()->buildKernel("softmax", "softmax_channel", {"-DSOFTMAX_LOCAL_SIZE=512"}, mOpenCLBackend->getPrecision());
    mMaxWorkGroupSize = static_cast<uint32_t>(mOpenCLBackend->getOpenCLRuntime()->getMaxWorkGroupSize(kernel));
}

bool SoftmaxExecution::buildSoftmaxKernel(int localSize) {
    auto runtime = mOpenCLBackend->getOpenCLRuntime();
    std::set<std::string> buildOptions;
    buildOptions.emplace("-DSOFTMAX_LOCAL_SIZE=" + std::to_string(localSize));
    std::string kernelName;
    if (mAxis == 1) {
        mUnits[0].kernel           = runtime->buildKernel("softmax", "softmax_channel", buildOptions, mOpenCLBackend->getPrecision());
    } else if (mAxis == 2) {
        mUnits[0].kernel           = runtime->buildKernel("softmax", "softmax_height", buildOptions, mOpenCLBackend->getPrecision());
    } else {
        MNN_ASSERT(mAxis == 3);
        mUnits[0].kernel           = runtime->buildKernel("softmax", "softmax_width", buildOptions, mOpenCLBackend->getPrecision());
    }
    mMaxWorkGroupSize = static_cast<uint32_t>(runtime->getMaxWorkGroupSize(mUnits[0].kernel));
    return true;
}

int SoftmaxExecution::getLocalSize(int size, int maxGroupSize){
    int local_size = 1;
    while(local_size * 2 <= maxGroupSize && local_size * 2 <= size){
        local_size *= 2;
    }
    return local_size;
}

ErrorCode SoftmaxExecution::onEncode(const std::vector<Tensor *> &inputs, const std::vector<Tensor *> &outputs) {
    mUnits.resize(1);
    auto &unit = mUnits[0];
    auto MaxLocalSize = std::min(std::min(mOpenCLBackend->getOpenCLRuntime()->getMaxWorkItemSizes()[0], mMaxWorkGroupSize), static_cast<uint32_t>(512));
    Tensor *input  = inputs[0];
    Tensor *output = outputs[0];
    
    const auto dims = input->buffer().dimensions;
    int inside  = 1;
    int outside = 1;
    int channel = 1;
    for (int i = 0; i < mAxis; ++i) {
        outside *= input->length(i);
    }
    channel = input->length(mAxis);
    for (int i = mAxis + 1; i < dims; ++i) {
        inside *= input->length(i);
    }
    std::vector<int> inputShape  = tensorShapeFormat(input);
    std::vector<int> outputShape = tensorShapeFormat(output);

    const int inputBatch    = inputShape.at(0);
    const int inputHeight   = inputShape.at(1);
    const int inputWidth    = inputShape.at(2);
    const int inputChannels = inputShape.at(3);
    
    const int outputBatch    = outputShape.at(0);
    const int outputHeight   = outputShape.at(1);
    const int outputWidth    = outputShape.at(2);
    const int outputChannels = outputShape.at(3);

    const int channelBlocks  = UP_DIV(outputChannels, 4);
    const int remainChannels = channelBlocks * 4 - outputChannels;
    int shape[] = {outputBatch, channelBlocks, outputHeight, outputWidth};
    int localSize = getLocalSize(channel, MaxLocalSize);
    if(localSize < 4){
        localSize = 1;
    }
    std::vector<uint32_t> mGlobalWorkSize{1, 1, 1};
    if(inputBatch == outside && channel == inputChannels && inside == inputWidth * inputHeight){
        mAxis = 1;
        localSize = getLocalSize(channelBlocks, MaxLocalSize);
        mGlobalWorkSize = {(uint32_t)(localSize), (uint32_t)outputWidth, (uint32_t)outputHeight * outputBatch};
    }else if(inputBatch * inputChannels == outside && channel == inputHeight && inside == inputWidth){
        mAxis = 2;
        mGlobalWorkSize = {(uint32_t)(localSize), (uint32_t)channelBlocks*outputWidth, (uint32_t)outputBatch};
    }else if(inputBatch * inputChannels * inputHeight == outside && channel == inputWidth && inside == 1){
        mAxis = 3;
        mGlobalWorkSize = {(uint32_t)(localSize), (uint32_t)channelBlocks, (uint32_t)outputBatch*outputHeight};
    }
    buildSoftmaxKernel(localSize);
    std::vector<uint32_t> mLocalWorkSize{(uint32_t)(localSize), 1, 1, 1};
    
    cl_int ret = CL_SUCCESS;
    uint32_t idx    = 0;
    ret |= unit.kernel->get().setArg(idx++, mGlobalWorkSize[0]);
    ret |= unit.kernel->get().setArg(idx++, mGlobalWorkSize[1]);
    ret |= unit.kernel->get().setArg(idx++, mGlobalWorkSize[2]);

    ret |= unit.kernel->get().setArg(idx++, openCLImage(input));
    ret |= unit.kernel->get().setArg(idx++, openCLImage(output));
    ret |= unit.kernel->get().setArg(idx++, remainChannels);
    ret |= unit.kernel->get().setArg(idx++, shape);
    MNN_CHECK_CL_SUCCESS(ret, "setArg SoftmaxExecution");
    if(localSize == 1){
        mLocalWorkSize = localWS3DDefault(mGlobalWorkSize, mMaxWorkGroupSize, mOpenCLBackend->getOpenCLRuntime(), "softmax", unit.kernel, mOpenCLBackend->getCLTuneLevel(), "softmax").first;
    }
    mOpenCLBackend->recordKernel3d(unit.kernel, mGlobalWorkSize, mLocalWorkSize);
    unit.globalWorkSize = {mGlobalWorkSize[0], mGlobalWorkSize[1], mGlobalWorkSize[2]};
    unit.localWorkSize  = {mLocalWorkSize[0], mLocalWorkSize[1], mLocalWorkSize[2]};
    return NO_ERROR;
}

class SoftmaxCreator : public OpenCLBackend::Creator {
public:
    virtual Execution *onCreate(const std::vector<Tensor *> &inputs, const std::vector<Tensor *> &outputs,
                                const MNN::Op *op, Backend *backend) const override {
        auto dimType = inputs[0]->getDimensionType();
        if (dimType == Tensor::TENSORFLOW && inputs[0]->dimensions() == 4) {
            int index[4] = {0, 2, 3, 1};
            auto axis = op->main_as_Axis()->axis();
            if (axis < 0) {
                axis = inputs[0]->dimensions() + axis;
            }

            axis = index[axis];
            //1 : channel //2 : height
            if (1 == axis || 2 == axis || 3 == axis) {
                return new SoftmaxExecution(inputs, axis, op, backend);
            }
            return nullptr;
        } else {
            auto axis = op->main_as_Axis()->axis();
            if (axis < 0) {
                axis = inputs[0]->dimensions() + axis;
            }

            if (1 == axis || 2 == axis || 3 == axis) {
                return new SoftmaxExecution(inputs, axis, op, backend);
            }
            return nullptr;
        }
    }
};

REGISTER_OPENCL_OP_CREATOR(SoftmaxCreator, OpType_Softmax, IMAGE);
} // namespace OpenCL
} // namespace MNN
