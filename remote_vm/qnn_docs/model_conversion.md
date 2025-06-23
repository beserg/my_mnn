

Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
TensorFlow Model Conversion
TensorFlow Model Conversion
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Machine Learning frameworks have specific formats for storing neural network models. Qualcomm® Neural Processing SDK supports these various models by converting them to a framework neutral deep learning container (DLC) format. The DLC file is used by the Qualcomm® Neural Processing SDK runtime for execution of the neural network.

A trained TensorFlow model consists of either:

A frozen TensorFlow model (pb file) OR

A pair of checkpoint and graph meta files

A SavedModel directory (Tensorflow 2.x)

The snpe-tensorflow-to-dlc tool converts a frozen TensorFlow model or a graph meta file into an equivalent Qualcomm® Neural Processing SDK DLC file. The following command will convert an Inception v3 TensorFlow model into a Qualcomm® Neural Processing SDK DLC file.

snpe-tensorflow-to-dlc --input_network $SNPE_ROOT/examples/Models/InceptionV3/tensorflow/inception_v3_2016_08_28_frozen.pb \
                       --input_dim input "1,299,299,3" --out_node "InceptionV3/Predictions/Reshape_1" \
                       --output_path inception_v3.dlc
* When using converter tools in Windows PowerShell, make sure a virtual environment with the required packages is activated and execute the converter script via python, as shown in the following example.
(venv-3.10) > python snpe-tensorflow-to-dlc <options>
The Inception v3 model files can be obtained by following the Getting Inception v3 tutorial.

TensorFlow Graph and Qualcomm® Neural Processing SDK Layer Mapping

Qualcomm® Neural Processing SDK, like many other neural network runtime engines, uses layers as building blocks to define the structure of neural networks. TensorFlow on the other hand, defines a neural network as a graph of nodes and a layer is defined as a set of nodes within the graph.

With this in mind, in order to properly convert a TensorFlow graph into a Qualcomm® Neural Processing SDK DLC file the following requirements must be met when defining a TensorFlow graph:

All nodes belonging to a layer must be defined in a unique TensorFlow scope.

A node can only belong to a single layer.

More information about graph compatibility can be found in the TensorFlow Graph Compatibility chapter.

Previous

Model Conversion

Next

Tensorflow Graph Compatibility

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close



Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Tensorflow Graph Compatibility
Tensorflow Graph Compatibility
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Layer to Graph Node Compatibility

The sections below describe what topologies of Tensorflow graph operations are compatible with each of the Qualcomm® Neural Processing SDK supported layers.

../images/node_labels.png
Batch Normalization

Tensorflow Reference

../images/node_batchnorm.png
Convolution

Tensorflow Reference
../images/node_conv1.png
../images/node_conv2.png
Concatenation

Tensorflow Reference

../images/node_concat.png
Deconvolution/Transposed Convolution

Tensorflow Reference

../images/node_deconv.png
ElementWise Sum/Mul/Max

Tensorflow Reference Sum
Tensorflow Reference Mul
Tensorflow Reference Max
../images/node_eltwise.png
Fully Connected

Tensorflow Reference

../images/node_fullyconnected.png
Local Response Normalization

Tensorflow Reference
../images/node_lrn.png
Pooling Avg/Max

Tensorflow Avg Pooling Reference
Tensorflow Max Pooling Reference
../images/node_pooling.png
Sigmoid/Relu/TanH/Elu

Tensorflow Relu Reference
Tensorflow Sigmoid Reference
Tensorflow TanH Reference
Tensorflow Elu Reference
../images/node_activations.png
SoftMax

Tensorflow SoftMax Reference
../images/node_softmax.png
PReLu

TFLearn PReLU Reference
../images/node_prelu.png
Slice

Tensorflow Slice Reference
../images/node_slice.png
Reshape

Tensorflow Reshape Reference
../images/node_reshape.png
Previous

TensorFlow Model Conversion

Next

TFLite Model Conversion

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
TFLite Model Conversion
TFLite Model Conversion
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Machine Learning frameworks have specific formats for storing neural network models. Qualcomm® Neural Processing SDK supports these various models by converting them to a framework neutral deep learning container (DLC) format. The DLC file is used by the Qualcomm® Neural Processing SDK runtime for execution of the neural network.

A trained Tensorflow model can be converted to a TFLte model (.tflite) file using the instructions at https://www.tensorflow.org/lite/convert#python_api_

The snpe-tflite-to-dlc tool converts a TFLite model into an equivalent Qualcomm® Neural Processing SDK DLC file. The following command will convert an Inception v3 TFLite model into a Qualcomm® Neural Processing SDK DLC file.

snpe-tflite-to-dlc --input_network inception_v3.tflite
                   --input_dim input "1,299,299,3"
                   --output_path inception_v3.dlc
The Inception v3 model files can be obtained from https://tfhub.dev/tensorflow/lite-model/inception_v3/1/default/1

Note:

To check the list of currently supported TFlite Ops, see Op Support Table.

Qualcomm® Neural Processing SDK and TFlite Converter currently only support float input data types.

There are some known issues with certain older versions of MLIR based TFLite converter that can lead to failure loading the model.

Previous

Tensorflow Graph Compatibility

Next

PyTorch Model Conversion

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
PyTorch Model Conversion
PyTorch Model Conversion
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Machine Learning frameworks have specific formats for storing neural network models. Qualcomm® Neural Processing SDK supports these various models by converting them to a framework neutral deep learning container (DLC) format. The DLC file is used by the Qualcomm® Neural Processing SDK runtime for execution of the neural network.

The snpe-pytorch-to-dlc tool converts a PyTorch TorchScript model into an equivalent Qualcomm® Neural Processing SDK DLC file. The following command will convert an ResNet18 PyTorch model into a Qualcomm® Neural Processing SDK DLC file.

snpe-pytorch-to-dlc --input_network resnet18.pt
                    --input_dim input "1,3,224,224"
                    --output_path resnet18.dlc
A trained PyTorch model can be converted to TorchScript model (.pt) file, the tutorial at https://pytorch.org/tutorials/advanced/cpp_export.html#converting-to-torch-script-via-tracing

Following code can be used to convert a pretrained PyTorch ResNet18 model to TorchScript (.pt) model.

import torch
import torchvision.models as models
resnet18_model = models.resnet18()
input_shape = [1, 3, 224, 224]
input_data = torch.randn(input_shape)
script_model = torch.jit.trace(resnet18_model, input_data)
script_model.save("resnet18.pt")
Note:

To check the list of currently supported PyTorch Ops, see Op Support Table.

Qualcomm® Neural Processing SDK and PyTorch Converter currently only support float input data types.

Previous

TFLite Model Conversion

Next

ONNX Model Conversion

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
ONNX Model Conversion
ONNX Model Conversion
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Machine Learning frameworks have specific formats for storing neural network models. Qualcomm® Neural Processing SDK supports these various models by converting them to a framework neutral deep learning container (DLC) format. The DLC file is used by the Qualcomm® Neural Processing SDK runtime for execution of the neural network. Qualcomm® Neural Processing SDK includes a tool, “snpe-onnx-to-dlc”, for converting models serialized in the ONNX format to DLC.

Converting Models from ONNX to DLC

The snpe-onnx-to-dlc tool converts a serialized ONNX model to an equivalent DLC representation.

With the ONNX alexnet model obtained by following the instructions in https://github.com/onnx/models/blob/main/validated/vision/classification/alexnet/README.md, the following command will produce a DLC representation of alexnet:

snpe-onnx-to-dlc --input_network models/bvlc_alexnet/bvlc_alexnet/model.onnx
                 --output_path bvlc_alexnet.dlc
* When using converter tools in Windows PowerShell, make sure a virtual environment with the required packages is activated and execute the converter script via python, as shown in the following example.
(venv-3.10) > python snpe-onnx-to-dlc <options>
Note:

Information about the ops, versions, and parameters Qualcomm® Neural Processing SDK supports can be found at Supported ONNX Ops.

Neither snpe-onnx-to-dlc nor the Qualcomm® Neural Processing SDK runtime support symbolic tensor shape variables. See Network Resizing for information on resizing Qualcomm® Neural Processing SDK networks at initialization.

In general, Qualcomm® Neural Processing SDK determines the data types for tensors and operations based upon the needs of the runtime and builder parameters. Data types specified by the ONNX model will usually be ignored.

If the model contains ONNX functions, converter always does inlining of function nodes.

Previous

PyTorch Model Conversion

Next

Quantizing a Model

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close



Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Quantizing a Model
Quantizing a Model
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Each of the snpe-framework-to-dlc conversion tools convert non-quantized models into a non-quantized DLC file. Quantizing requires another step. The snpe-dlc-quantize tool is used to quantize the model to one of supported fixed point formats.

For example, the following command will convert an Inception v3 DLC file into a quantized Inception v3 DLC file.

snpe-dlc-quantize --input_dlc inception_v3.dlc --input_list image_file_list.txt
                  --output_dlc inception_v3_quantized.dlc
The image list specifies paths to raw image files used for quantization. See snpe-dlc-quantize for more details.

The tool requires the batch dimension of the DLC input file to be set to 1 during model conversion. The batch dimension can be changed to a different value for inference, by resizing the network during initialization.

For details on the quantization algorithm, and information on when to use a quantized model, see Quantized vs Non-Quantized Models.

Input data for quantization

To properly calculate the ranges for the quantization parameters, a representative set of input data needs to be used as input into snpe-dlc-quantize.

Experimentation shows that providing 5-10 input data examples in the input_list for snpe-dlc-quantize is usually sufficient, and definitely practical for quick experiments. For more robust quantization results, we recommend providing 50-100 examples of representative input data for the given model use case, without using data from the training set. The representative input data set ideally should include all input data modalities which represent/produce all the output types/classes of the model, preferably with several input data examples per output type/class.

In Supported Network Layers, we have listed the layers/ops that are guaranteed to be quantized successfully. For other layers/ops no guarantees can be made.

Previous

ONNX Model Conversion

Next

Offline Graph Caching for DSP Runtime on HTP

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close



Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Offline Graph Caching for DSP Runtime on HTP
Offline Graph Caching for DSP Runtime on HTP
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Qualcomm® Neural Processing SDK DSP runtime on targets having Hexagon Tensor Processor (HTP) supports offline graph caching feature which helps to prepare the backend graph on Linux x86-64 platform. This helps to reduce the init time and directly loads the cache on device while executing the model.

The workflow change for Qualcomm® Neural Processing SDK Users:

model conversions using snpe-<framework>-to-dlc

model quantization using snpe-dlc-quant

model offline graph cache preparation using snpe-dlc-graph-prepare

model execution on target using snpe-net-run or custom application

../images/create_htp_cache.png
The DLC Quantize in the above image consists of 2 step process i.e first to quantize the model and then generating the offline cache. snpe-dlc-graph-prepare tool is used to generate a DLC cache blob for the Qualcomm® Neural Processing SDK HTP runtime after the DLC is quantized by snpe-dlc-quant tool. snpe-dlc-graph-prepare tool can also be used with the float model to generate the cache for HTP FP16 runtime.

For example, the following commands convert an Inception v3 DLC file into a quantized Inception v3 DLC file, and generates the HTP graph cache and stores in the DLC.

snpe-dlc-quant --input_dlc inception_v3.dlc --input_list image_file_list.txt --output_dlc inception_v3_quantized.dlc

snpe-dlc-graph-prepare --input_dlc inception_v3_quantized.dlc --output_dlc inception_v3_quantized_cache.dlc --htp_socs sm8750
Running snpe-dlc-graph-prepare triggers generation of HTP graph on the model provided, and adding the generated cache to HTP records into the DLC. If the HTP compiler cannot process/compile any section of the network, snpe-dlc-graph-prepare issues an error message.

snpe-dlc-graph-prepare can help to re-prepare the offline cache for the graph quickly using same/different version of Qualcomm® Neural Processing SDK, without re-doing the quantization step which may take significant time if the input dataset is huge.

Similarly, snpe-dlc-quantize tool uses enable_htp option to generate a DLC cache blob for the Qualcomm® Neural Processing SDK HTP runtime as part of the quantization process.

For example, the following command converts an Inception v3 DLC file into a quantized Inception v3 DLC file, and generates the HTP graph cache and stores in the DLC.

snpe-dlc-quantize --input_dlc inception_v3.dlc --input_list image_file_list.txt --output_dlc inception_v3_quantized.dlc --enable_htp --htp_socs sm8750
Notes:

The offline prepared graph cache and the SNPE object at runtime must specify the same graph outputs. If at runtime the same graph outputs are not as specified in the prepared graph, the prepared graph is considered invalid and will be ignored. Then, graph preparation will done at runtime (called online prepare) thereby rejecting the cache blob in DLC leading to apparent increase in init time in this situation.

In order to enable CPU fallback for offline prepare, the DSP subnet that precedes the CPU subnet needs to have all output tensors that are inputs into the subsequent subnets marked as graph outputs.

The graph outputs are specified to the snpe-dlc-graph-prepare tool either as:

Output Layer Names (--set_output_layers) in which case all output tensors for that layer are considered graph outputs. Or as

Output Tensor Names (--set_output_tensors) if not all outputs from the layer are considered graph outputs

An example would be if there was an intermediate layer, for which one (or more) of its output tensors should be considered as a graph output. By default, the tool will choose all output tensors from the last layer in the serialized graph.

Outputs to the graph can be specified using an optional input_list to snpe-dlc-graph-prepare as well. To specify Output Layer Names to snpe-dlc-graph-prepare, a special line starting with “#” is added into the input_list argument that specifies the layer name(s):

#<output_op_name>[<space><output_op_name>]*
Alternately, to specify Output Tensor Names to snpe-dlc-graph-prepare, a special line starting with “%” is added into the input_list argument that specifies the output tensor name(s):

%<output_tensor_name>[<space><output_tensor_name>]*
To specify the Output Tensor Names at runtime:

For snpe-net-run, one must pass the names using the --set_output_tensors argument on the command line Syntax: [ --set_output_tensors=<val> ] Specifies a comma separated list of tensors to be output after execution.

When using the API - use this SNPE Builder API Snpe_SNPEBuilder_SetOutputTensors() / SNPEBuilder::setOutputTensors() to specify the same output tensor names.

A cache record created for a particular SoC can run on another SoC. Such interoperability is governed by the VTCM size and the DSP architecture of prepared and running SoCs. HTP Offline cache compatibility follows these empirical rules:

A cache generated for a newer DSP Arch cannot run an SoC with a lower DSP Arch. For example a cache record generated for v69 device (say sm8450) will not run on a v68 device (like sm8350) even if the cache was prepared with 2MB vtcm.

For the same DSP Arch, a cache prepared for one SoC can run on another SoC if the prepared with VTCM is less or equals the VTCM of running SoC.

A cache generated for v68 or v69 device will not run on a v73 device.

The --optimization_level command line option in the snpe-dlc-graph-prepare tool has some inherent tradeoffs and non-deterministic behavior:

Default optimization level is 2. Higher optimization levels incur longer offline prepare time but yields more optimal graph and hence faster execution time for most graphs.

Level 3 should provide more optimal graph in most cases, but can also result in less optimal graph in some cases.

Level 3 might yield a larger HTP offline cache record size and hence can lead to possible degradation on initialization time.

Offline graph prepare using snpe-dlc-quantize will be deprecated in future. Currently, snpe-dlc-quantize is used to support legacy work flow. It is recommended to migrate to snpe-dlc-graph-prepare for offline htp graph cache blob preparation.

Note that Output Tensor Names is not supported on the AIP runtime for legacy HTA SOCs.

It is possible to cache resized networks by making use of the –input_name and –input_dimensions arguments or use the Snpe_SNPEBuilder_SetInputDimensions API. Cache records are sensitive to the set of input dimensions they were prepared with. Multiple cache records with the same record identifier may coexist if they were prepared with differing input dimensions. During execution, a cache record may only be used if the input dimensions during execution match those used to prepare the cache record. This also applies to online prepare using both the net-run arguments (–input_name and –input_dimensions) as well as the API for resizing input tensor dimensions (Snpe_SNPEBuilder_SetInputDimensions API).

For example, assume a hypothetical network with one input whose original dimensions are 1x3x4x5. If the user resizes this input to 2x3x4x5 during cache preparation and attempts to subsequently run inference without also resizing that input to 2x3x4x5, then this otherwise compatible cache record will be rejected on the grounds of mismatching input dimensions.

Previous

Quantizing a Model

Next

Qairt Converter

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Qairt Converter
FAQs
Qairt Converter
Updated: Jun 11, 2025 80-63442-2 Rev: BL
The qairt-converter tool converts a model from one of ONNX/TensorFlow/TFLite/PyTorch framework to a DLC. The DLC contains the model in a Qualcomm graph format to support inference on Qualcomm’s AI accelerator cores. A new prefix qairt for Qualcomm AI Runtime signifies that this converter can be used with both the Qualcomm Neural Processing SDK API as well as the Qualcomm AI Engine Direct API. The converter automatically detects the proper framework based on the source model extension.

Supported frameworks and file types are:

Framework

File Type

Onnx

*.onnx

TensorFlow

*.pb

TFLite

*.tflite

PyTorch

*.pt

Basic Conversion
Basic conversion has only one required argument --input_network, which is the path to the source framework model. The source model can either be float model or quantized model, qairt-converter will convert it to corresponding DLC, retaining the precision and datatype of the tensors. Some frameworks may require additional arguments that are otherwise listed as optional. Please check the help text at qairt-converter for more details.

../images/qairt_basic_conversion.png
Onnx Conversion

Current ONNX Conversion supports upto ONNX Opset 21.

$ qairt-converter --input_network model.onnx
Tensorflow Conversion

Tensorflow additionally requires --source_model_input_shape and --out_tensor_node arguments. --source_model_input_shape is for specifying the list of all the input names and dimensions to the network model. --out_tensor_node is for specifying the network model’s output tensor name/s.

$ qairt-converter \
      --input_network inception_v3_2016_08_28_frozen.pb \
      --source_model_input_shape input 1,299,299,3 \
      --out_tensor_node InceptionV3/Predictions/Reshape_1
In the above example, the model inception_v3_2016_08_28_frozen.pb has input named input with dimensions (1,299,299,3), and output tensor with name InceptionV3/Predictions/Reshape_1.

Input/Output Layouts
The default input and output layouts in the converted graph are the same as per the source model. This behavior differs from the legacy converter which would modify the input and (optionally) the output layout to the spatial first format. An example single layer Onnx model (spatial last) is shown below.

../images/qairt-conversion-layout-comparison.png
Input/Output Customization using YAML
Note

This feature allows user to specify their desired input/output tensor layout for the converted model.

Users can provide a YAML configuration file to simplify using different input and output configurations using the --config command-line option. All configurations in the YAML are optional. If an option is provided in the YAML configuration and an equivalent option is provided on the command line, the command line option takes precedence over the one provided in the configuration file. The YAML configuration schema is shown below.

Name: Name of the input or output tensor present in the model that needs to be customized

Src Model Parameters

These are mandatory if a certain equivalent desired configuration is specified.

DataType: Data type of the tensor in source model.

Layout: Tensor layout in the source model. Valid values are:

NCDHW

NDHWC

NCHW

NHWC

NFC

NCF

NTF

TNF

NF

NC

F

where

N = Batch

C = Channels

D = Depth

H = Height

W = Width

F = Feature

T = Time

Desired Model Parameters

DataType: Desired data type of the tensor in the converted model. Valid values are float32, float16, uint8, int8 datatypes.

Layout: Desired tensor layout of the converted model. Same valid values as source layout.

Shape: Tensor shape/dimension in the converted model. Valid values are comma separated dimension values, i.e., (a,b,c,d).

Color Conversion: Tensor color encoding in the converted model. Valid values are BGR, RGB, RGBA, ARGB32, NV21, and NV12.

QuantParams: Required when the desired model data type is a quantized data type. Has two subfields: Scale and Offset.

Scale: Scale of the buffer as a float value.

Offset: Offset value as an integer.

Optional: During calls to graph execute, the client can use optional I/O tensors to signal to the backend which tensors to be optionally provided/produced. Valid values are True, False.

The --dump_config_template option of qairt-converter saves the IO configuration file for the user to update. Pass the --dump_config_template option to the qairt-converter to save the IO configuration file at the specified location.

../images/qairt_io_config.png
$ qairt-converter \
      --input_network model.onnx \
      --dump_config_template <output_folder>/io_config.yaml
Here is an example io_config.yaml, where both input and output tensor layout is converted from NCF format in source model to NFC format in the converted model.

Converted Graph:
- Output Tensors: ['output']

Input Tensor Configuration:
  # Input 1
  - Name: 'input'
    Src Model Parameters:
        DataType:
        Layout: NCF
        Shape:
    Desired Model Parameters:
        DataType:
        Layout: NFC
        Color Conversion:
        QuantParams:
          Scale:
          Offset:

Output Tensor Configuration:
  # Output 1
  - Name: 'output'
    Src Model Parameters:
        DataType:
        Layout: NCF
    Desired Model Parameters:
        DataType:
        Layout: NFC
        QuantParams:
          Scale:
          Offset:
$ qairt-converter \
      --input_network model.onnx \
      --config io_config.yaml
QAT encodings
QAT encodings are quantization-aware training encodings which are present in the source network model. They can be present in the following form in the source network model.

FakeQuant Nodes: There can be FakeQuant nodes in the source network model. This nodes simulate the quantize-dequantize operations and use parameters like scale and zero-points to map the floating point values to quantized values and back. During conversion this nodes will be removed and corresponding encodings are applied to generate a quantized or mixed precision DLC output.

../images/qairt_fakequant.png
Quantization overrides: Tensor output encodings can be associated with the output tensors in the source network model via overrides. The quantization overrides for the tensors(output, weights, bias, activations) in the source network model can be provided to the qairt-converter with a JSON file using the --quantization_overrides command-line option. When the overrides option is specified, qairt-converter produces a fully quantized or mixed precision graph depending on the overrides by applying encoding overrides, propagate encodings across data invariant Ops and fallback the missing tensors in float datatype.

Quant-Dequant Nodes: There can be Quant-Dequant(QDQ) nodes present in the source network model. The Quant nodes convert floating-point values to lower precision values typically integers to reduce model’s memory footprint and improving inference time. The Dequant do the opposite and convert from lower precision values to floating-point values for getting higher precision for certain operations. During conversion this nodes will be removed and corresponding encodings are applied to generate a quantized or mixed precision DLC output.

../images/qairt_qdq.png
Note

Inference fails for CPU and DSP runtimes if QAT encodings contain 16-bit.

Float model Usecases
Float bitwidth conversions

Users can convert float source model between float bitwidth 16 and 32 using the --float_bitwidth flag to the qairt-converter tool.

../images/qairt_float_conversion.png
For converting a source model with all float32 tensors to float16 tensor use --float_bitwidth 16.

Note

Float bitwidth 32 is the default bitwidth for float source model conversion.

Float bitwidth 16 is the default bitwidth for source model with quantization encodings or overrides

$ qairt-converter --input_network model.onnx \
      --float_bitwidth 16
For converting a source model with all float16 tensors to float32 tensor use --float_bitwidth 32.

$ qairt-converter --input_network model.onnx \
      --float_bitwidth 32
Float16 Conversion with Float32 bias

To generate a float16 graph with the bias still in float32, an additional --float_bias_bitwidth 32 flag can be passed.

$ qairt-converter --input_network model.onnx \
    --float_bitwidth 16 \
    --float_bias_bitwidth 32
Quantization overrides Usecases
Float mixed precision conversion

User can provide overrides to qairt-converter to floating point source model to a mixed float precision (float16 and float32) model. For example, if the source model has all tensors with float32 precision and user wants to change precision of some tensors to float16, override file should contain names of the tensor with type as float16.

../images/qairt_override_float_mp.png
$ qairt-converter \
  --input_network model.onnx \
  --quantization_overrides <path to json>/overrides.json
Quant conversion

User can also convert a float source model or mixed precision source model to a quantized model using quantization overrides. The qairt-converter will generate a fully quantized or mixed precision graph based on the overrides provided.

../images/qairt_override_float_mp_quant.png
$ qairt-converter \
  --input_network model.onnx \
  --quantization_overrides <path to json>/overrides.json
Overrides to Float conversion

User can convert a source model with overrides to float to run on floating point runtimes i.e. QNN-GPU and QNN-CPU using the command-line option --export_format=DLC_STRIP_QUANT.

Note

This might result in loss of accuracy.

../images/qairt_override_strip_quant.png
$ qairt-converter \
      --input_network model.onnx \
      --quantization_overrides <path to json>/overrides.json \
      --export_format=DLC_STRIP_QUANT
Quantized model Usecases
Quant model conversion

User can now convert a quantized model in a single step using the qairt-converter without any additional steps.

../images/qairt_quant_conversion.png
$ qairt-converter \
      --input_network quant_model.onnx
Quant to Float conversion

User can convert a quantized source model to float to run on floating point runtimes i.e. QNN-GPU and QNN-CPU using the command-line option --export_format=DLC_STRIP_QUANT.

Note

This might result in loss of accuracy.

../images/qairt_quant_strip_quant.png
$ qairt-converter --input_network quant_model.onnx \
    --export_format=DLC_STRIP_QUANT
Quant-Dequant(QDQ) model Usecases
QDQ model conversion

User can now convert a Quant-Dequant source model to quantized model in a single step using the qairt-converter without any additional steps.

../images/qairt_qdq_conversion.png
$ qairt-converter \
      --input_network quant_dequant_model.onnx
QDQ to Float conversion

User can convert a Quant-Dequant source model to float to run on floating point runtimes i.e. QNN-GPU and QNN-CPU using the command-line option --export_format=DLC_STRIP_QUANT.

../images/qairt_qdq_strip_quant.png
Note

This might result in loss of accuracy.

$ qairt-converter --input_network model.onnx \
    --export_format=DLC_STRIP_QUANT
DryRun
Use the --dry_run option to evaluate the model without actually converting any ops. This returns unsupported ops/attributes and unused inputs/outputs.

FAQs
How is QAIRT Converter different from Legacy Converters?

Single converter vs independent framework converters

The qairt-converter is a single converter tool supporting conversion for all supported frameworks based on the model extension while legacy converters had different framework specific tools.

Changed some optional arguments as default behavior

The default input and output layouts in the Converted graph will be same as in the Source graph. The legacy ONNX and Pytorch converters may not always retain the input and output layouts from Source graph.

Removed deprecated arguments

Deprecated arguments on the legacy converters are not enabled on the new converter.

Renamed some arguments for clarity

The –input_encoding argument is renamed to –input_color_encoding. Framework-specific arguments have the framework name present. eg- –define_symbol is renamed to –onnx_define_symbol, –show_unconsumed_nodes is renamed to –tf_show_unconsumed_nodes, –signature_name is renamed to –tflite_signature_name.

DLC as the Converter output file format

The QAIRT Converter uses DLC as export format similar to SNPE.

What about the changes to Quantization?

qairt-quantizer is a standalone tool for quantization like snpe-dlc-quant. Please refer to qairt-quantizer for more information and usage details.

Will the Converted model be any different with QAIRT converter compared to Legacy Converter?

The result of the QAIRT Converter will be different from the result of Legacy Converters in terms of the input/output layout.

Legacy converters will by default modify the input tensors to Spatial First (e.g. NHWC) layout. This means for Frameworks like ONNX, where the predominant layout is Spatial Last (e.g. NCHW), the input/output layout is different between the source model and the converted model.

Since QAIRT Converter preserves the source layouts be default, the QAIRT-converted graphs in case of many ONNX/Pytorch models will be different from the Legacy-converted graphs.

The QAIRT Converter will be enhanced in a future release to support the same layouts as the legacy converters.

Previous

Offline Graph Caching for DSP Runtime on HTP

Next

Qairt Quantizer

On this page
Basic Conversion
Input/Output Layouts
Input/Output Customization using YAML
QAT encodings
Float model Usecases
Quantization overrides Usecases
Quantized model Usecases
Quant-Dequant(QDQ) model Usecases
DryRun
FAQs
Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Qairt Quantizer
Qairt Quantizer
Updated: Jun 11, 2025 80-63442-2 Rev: BL
The qairt-converter tool now converts non-quantized models into a non-quantized or quantized DLC file depending on the overrides provided during the Converter step. qairt-quantizer now can be used to quantize all the tensors which are missing encodings during qairt-converter step (fill in the gaps) or can be used to calibrate the provided encodings through a list of images. The qairt-quantizer tool is used to quantize the model to one of supported fixed point formats.

For example, the following command will convert an Inception v3 DLC file into a quantized Inception v3 DLC file.

$ qairt-quantizer --input_dlc inception_v3.dlc \
                  --input_list image_file_list.txt \
                  --output_dlc inception_v3_quantized.dlc
To properly calculate the ranges for the quantization parameters, a representative set of input data needs to be used as input into qairt-quantizer using the --input_list parameter. The --input_list specifies paths to raw image files to be used for calibration during quantization. For details refer to --input_list argument in qnn-net-run for supported input formats (in order to calculate output activation encoding information for all layers, do not include the line which specifies desired outputs).

The tool requires the batch dimension of the DLC input file to be set to 1 during model conversion. The batch dimension can be changed to a different value for inference, by resizing the network during initialization.

Additional details
qairt-quantizer is majorly similar to snpe-dlc-quant with the following differences:

qairt-quantizer can now be used to generate encodings using calibration dataset provided via the --input_list flag for the tensors for the following scenarios:

Fill in the gaps: If any tensor is missing encoding during the qairt-converter step i.e. the tensors for which override is not specified in --quantization_overrides or source model encodings (QAT).

If encodings is not specified for all the tensors via overrides or QAT encodings.

The external overrides and source model encodings (QAT) are now applied during qairt-converter stage by default. So the quantizer options to ignore the overrides and source model encodings, --ignore_encodings (legacy) and --ignore_quantization_overrides are now no-op.

An alternative option is to the --export_format=DLC_STRIP_QUANT flag of qairt-converter, when specified the converter will ignore/remove all the encodings in the source model and output float model which can be recalibrated using qairt-quantizer and --input_list flag.

Another alternative for using this feature is through qairt-quantizer options --input_list and --ignore_quantization_overrides``in combination which signals the quantizer to ignores all the encodings applied during conversion and generates encodings using the calibration dataset provided via ``--input_list.

The float fallback feature controlled via command-line option --enable_float_fallback, present as --float_fallback in legacy quantizers is also a no-op for qairt-quantizer and can be skipped. The float fallback was added to produce a fully quantized or mixed precision graph by applying encoding overrides or source model encodings, by propagating encodings across data invariant Ops and falling back the missing tensors to float datatype. To simplify the steps, this is taken care during qairt-converter. qairt-converter applies the overrides and encodings, and the tensors which are missing encodings will fall back to the default float datatype.

To summarize, qairt-quantizer command-line arguments --ignore_quantization_overrides, and --enable_float_fallback are now no-op, and are applied by default during qairt-converter step itself.

Note

--enable_float_fallback and --input_list are mutually exclusive options. One of them is mandatory argument for quantizer.

Outputs can be specified for qairt-quantizer by modifying the input_list in the following ways:

#<output_layer_name>[<space><output_layer_name>]
%<output_tensor_name>[<space><output_tensor_name>]
<input_layer_name>:=<input_layer_path>[<space><input_layer_name>:=<input_layer_path>]
Note: Output tensors and layers can be specified individually, but when specifying both, the order shown must be used to specify each.

qairt-quantizer also supports quantization using AIMET, inplace of default Quantizer, when --use_aimet_quantizer command line option is provided. To use AIMET Quantizer, run the setup script to create AIMET specific environment, by executing the following command

$ source {SNPE_ROOT}/bin/aimet_env_setup.sh --env_path <path where AIMET venv needs to be created> \
                                            --aimet_sdk_tar <AIMET Torch SDK tarball>
Advance AIMET algorithms- AdaRound and AMP is also supported in qairt-quantizer. The user needs to provide a YAML config file through the command line option --config and specify the algorithm “adaround” or “amp” through --apply_algorithms along with --use_aimet_quantizer flag.

The template of the YAML file for AMP is shown below:

aimet_quantizer:
   datasets:
       <dataset_name>:
           dataloader_callback: '<path/to/unlabled/dataloader/callback/function>'
           dataloader_kwargs: {arg1: val, arg2: val2}

   amp:
       dataset: <dataset_name>,
       candidates:  [[[8, 'int'], [16, 'int']], [[16, 'float'], [16, 'float']]],
       allowed_accuracy_drop: 0.02
       eval_callback_for_phase2: '<path/to/evaluator/callback/function>'
dataloader_callback is used to set the path of a callback function which returns labeled dataloader of type torch.DataLoader. The data should be in source network input format. dataloader_kwargs is an optional dictionary through which the user can provide keyword arguments of the above defined callback function. dataset is used to specify the name of the dataset that has been defined above. candidates is list of lists for all possible bitwidth values for activations and parameters. allowed_accuracy_drop is used to specify the maximum allowed drop in accuracy from FP32 baseline. The pareto front curve is plotted only till the point where the allowable accuracy drop is met. eval_callback_for_phase2 is used to set the path of the evaluator function which takes predicted value batch as the first argument and ground truth batch as the second argument and returns calculated metric float value.

The template of the YAML file for AdaRound is shown below:

aimet_quantizer:
    datasets:
        <dataset_name>:
            dataloader_callback: '<path/to/unlabled/dataloader/callback/function>'
            dataloader_kwargs: {arg1: val, arg2: val2}

    adaround:
        dataset: <dataset_name>
        num_batches: 1
dataloader_callback is used to set the path of a callback function which returns unlabeled dataloader of type torch.DataLoader. The data should be in source network input format. dataloader_kwargs is an optional dictionary through which the user can provide keyword arguments of the above defined callback function. dataset is used to specify the name of the dataset that has been defined above. num_batches is used to specify the number of batches to be used for adaround iteration.

AdaRound can also run in default mode, without config file, by just passing “adaround” in the command line option --apply_algorithms along with --use_aimet_quantizer flag. This flow uses the data provided through the input_list option to take rounding decisions.

Note:
AIMET Torch Tarball naming convention should be as follows - aimetpro-release-<VERSION (optionally with build ID)>.torch-<cpu/gpu>-.*.tar.gz. For example, aimetpro-release-x.xx.x.torch-xxx-release.tar.gz.

Once the setup script is run, ensure that AIMET_ENV_PYTHON environment variable is set to <AIMET virtual environment path>/bin/python

Minimum AIMET version supported is, AIMET-1.33.0

Previous

Qairt Converter

Next

Model Tips

On this page
Additional details
Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Model Tips
Using MobilenetSSD
Using MobilenetSSD
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Tensorflow MobilenetSSD model

Tensorflow Mobilenet SSD frozen graphs come in a couple of flavors. The standard frozen graph and a quantization aware frozen graph. The following example uses a quantization aware frozen graph to ensure accurate results on the Qualcomm® Neural Processing SDK runtimes.

Prerequisites

The quantization aware model conversion process was tested using Tensorflow v1.11 however other versions may also work. The CPU version of Tensorflow was used to avoid out of memory issues observed across various GPU cards during conversion.

Setup the Tensorflow Object Detection Framework

The quantization aware model is provided as a TFLite frozen graph. However Qualcomm® Neural Processing SDK requires a Tensorflow frozen graph (.PB). To convert the quantized model, the object detection framework is used to export to a Tensorflow frozen graph. Follow these steps to clone the object detection framework:

mkdir ~/tfmodels
cd ~/tfmodels
git clone https://github.com/tensorflow/models.git
Checkout a tested object detection framework commit (SHA)

git checkout ad386df597c069873ace235b931578671526ee00
Follow third party instructions to setup the Tensorflow object detection framework

Download the quantization aware model

A specific version of the Tensorflow MobilenetSSD model has been tested: ssd_mobilenet_v2_quantized_300x300_coco_2019_01_03.tar.gz

wget http://download.tensorflow.org/models/object_detection/ssd_mobilenet_v2_quantized_300x300_coco_2019_01_03.tar.gz
After downloading the model extract the contents to a directory.

tar xzvf ssd_mobilenet_v2_quantized_300x300_coco_2019_01_03.tar.gz
Export a trained graph from the object detection framework

Follow these instructions to export the Tensorflow graph:

https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/exporting_models.md

or modify and execute this sample script

Create this file, export_train.sh, using your favorite editor. Modify the paths to the correct directory location of the downloaded quantization aware model files.

#!/bin/bash
INPUT_TYPE=image_tensor
PIPELINE_CONFIG_PATH=<path_to>/ssd_mobilenet_v2_quantized_300x300_coco_2019_01_03/pipeline.config
TRAINED_CKPT_PREFIX=<path_to>/ssd_mobilenet_v2_quantized_300x300_coco_2019_01_03/model.ckpt
EXPORT_DIR=<path_to>/exported
pushd ~/tfmodels/models/tfmodels/research
python3 object_detection/export_inference_graph.py \
--input_type=${INPUT_TYPE} \
--pipeline_config_path=${PIPELINE_CONFIG_PATH} \
--trained_checkpoint_prefix=${TRAINED_CKPT_PREFIX} \
--output_directory=${EXPORT_DIR}
popd
Make the script executable

chmod u+x export_train.sh
Run the script

./export_train.sh
This should generate a frozen graph in <path_to>/exported/frozen_inference_graph.pb

Convert the frozen graph using the snpe-tensorflow-to-dlc converter.

snpe-tensorflow-to-dlc --input_network <path_to>/exported/frozen_inference_graph.pb --input_dim Preprocessor/sub 1,300,300,3 --out_node detection_classes --out_node detection_boxes --out_node detection_scores ---output_path mobilenet_ssd.dlc --allow_unconsumed_nodes
After Qualcomm® Neural Processing SDK conversion you should have a mobilenet_ssd.dlc that can be loaded and run in the Qualcomm® Neural Processing SDK runtimes.

The output layers for the model are:

Postprocessor/BatchMultiClassNonMaxSuppression

add

The output buffer names are:

(classes) detection_classes:0 (+1 index offset)

(classes) Postprocessor/BatchMultiClassNonMaxSuppression_classes (0 index offset)

(boxes) Postprocessor/BatchMultiClassNonMaxSuppression_boxes

(scores) Postprocessor/BatchMultiClassNonMaxSuppression_scores


Running the model in |Qualcomm(R)| Neural Processing SDK

The following are limitations and suggestions for running DLC model in Qualcomm® Neural Processing SDK:

Batch dimension > 1 is not supported.

DetectionOutput layer is supported on CPU runtime processor only. To run the model using different runtime processor, such as GPU or DSP, CPU fallback mode must be enabled in Runtime List (see Snpe_SNPEBuilder_SetRuntimeProcessorOrder() description in Qualcomm® Neural Processing SDK API). If using snpe-net-run tool, use –runtime_order option

Configure DetectionOutput layer reasonably. Performance of DetectionOutput layer (i.e. processing time) is function of layer parameters: top_k, keep_top_k and confidence_threshold. For example, top_k parameters have practically exponential impact on processing time; e.g. top_k=100 will result in much smaller processing time vs. top_k=1000. Smaller confidence_threshold will result in larger number of boxes to output, and vice versa.

Resizing input dimensions at SNPE object creation/build time is not allowed. Note that input dimensions are embedded into DLC model during conversion, but in some cases can be overridden via Snpe_SNPEBuilder_SetInputDimensions() (see description in Qualcomm® Neural Processing SDK API) at SNPE object creation/build time. Due to PriorBox layer folding in the model converter, input/network resizing is not possible.

Previous

Model Tips

Next

Using DeepLabv3

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close


Products

Developer

Support

Company



Workspace

Developer Workspace


Loading...
Bring your ideas to life by saving your favorite products, comparing specifications and sharing with your team to work collaboratively.

0 Projects

Sort


Newest
NewestOldestRecently updatedLeast recently updated

Create a Project
You do not have any projects yet. Start building your Workspace.

Neural Processing SDK
Legal notice

Network Models
Model Conversion
Model Tips
Using DeepLabv3
Using DeepLabv3
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Tensorflow DeepLabv3 model

A specific version of the Tensorflow DeepLabv3 model has been tested: deeplabv3_mnv2_pascal_train_aug_2018_01_29.tar. This version of DeepLabv3 uses MobileNet-v2 as the backbone and has been pretrained on the Pascal VOC 2012 dataset.

Download the model.

wget http://download.tensorflow.org/models/deeplabv3_mnv2_pascal_train_aug_2018_01_29.tar.gz
After downloading the model extract the contents to a directory.

tar xzvf deeplabv3_mnv2_pascal_train_aug_2018_01_29.tar.gz
Convert the model using the snpe-tensorflow-to-dlc converter.

snpe-tensorflow-to-dlc --input_network deeplabv3_mnv2_pascal_train_aug/frozen_inference_graph.pb --input_dim sub_7 1,513,513,3 --out_node ArgMax --output_path deeplabv3.dlc
The output layer for the model is:

ArgMax

The output buffer names is:

(Segmentation Map) ArgMax:0.raw

Preprocessing Input Images

Qualcomm® Neural Processing SDK does not support the preprocessing done within the DeepLabv3 model. Preprocessing must be done offline before the images are run. In the preprocessing phase, images must be resized to 513x513x3 and the pixels must be normalized to be between -1 to 1.

The following steps need to be performed on all input images in this exact order:

Calculate the resize ratio and target size of the image using the following:

resize_ratio = 513.0 / max(width, height)
target_size = (int(resize_ratio * width), int(resize_ratio * height))
Convert the image to the target_size, using an Anti-alias resampling filter. This will make the longer dimension of the image to be 513 and the other dimension will be smaller than 513.

Pad the smaller dimension with the mean value of 128 to produce an image of 513x513x3.

Convert the image to type float32.

Multiply the image elementwise with 0.00784313771874.

Elementwise subtract 1.0 from the image.

Running the model in Qualcomm® Neural Processing SDK

The following are limitations and suggestions for running DLC model in Qualcomm® Neural Processing SDK:

Some operations in the model are supported on CPU runtime processor only. To run the model using different runtime processor, such as GPU or DSP, CPU fallback mode must be enabled in Runtime List (see Snpe_SNPEBuilder_SetRuntimeProcessorOrder() description in Qualcomm® Neural Processing SDK API). If using snpe-net-run tool, use –runtime_order option

Postprocessing Output Segmentation Maps

Running DeepLabv3 with Qualcomm® Neural Processing SDK will produce an output segmentation map of size 513x513x1 where every element is an integer that represents a class (e.g. 0=background, etc.). However the output of Qualcomm® Neural Processing SDK still has the padding applied in the preprocessing step. This padding must be cropped out and the image should be resized to the orginal size.

The following steps should be taken in order to get the same dimensions as the original image:

Crop off the padding that was applied to the shorter dimension in the pre-processing step. The ratio of the dimensions of the segmentation map should now be the same as the original image.

Resize the segmentation map to the height and width of the original image.

Previous

Using MobilenetSSD

Next

Input Data and Preprocessing

Light
Dark
Auto
Qualcomm relentlessly innovates to deliver intelligent computing everywhere, helping the world tackle some of its most important challenges. Our leading-edge AI, high performance, low-power computing, and unrivaled connectivity deliver proven solutions that transform major industries. At Qualcomm, we are engineering human progress.
Quick links
Products
Support
Partners
Contact us
Developer
Company info
About us
Careers
Investors
News & media
Our businesses
Email Subscriptions
Stay connected
Get the latest Qualcomm and industry information delivered to your inbox.

Subscribe
Manage your subscription
Terms of Use
Privacy
Cookie Policy
Accessibility Statement
Cookie Settings
Language: English (US)
Languages
English ( United States )
简体中文 ( China )
© Qualcomm Technologies, Inc. and/or its affiliated companies.

Snapdragon and Qualcomm branded products are products of Qualcomm Technologies, Inc. and/or its subsidiaries. Qualcomm patented technologies are licensed by Qualcomm Incorporated.

Note: Certain services and materials may require you to accept additional terms and conditions before accessing or using those items.

References to "Qualcomm" may mean Qualcomm Incorporated, or subsidiaries or business units within the Qualcomm corporate structure, as applicable.

Qualcomm Incorporated includes our licensing business, QTL, and the vast majority of our patent portfolio. Qualcomm Technologies, Inc., a subsidiary of Qualcomm Incorporated, operates, along with its subsidiaries, substantially all of our engineering, research and development functions, and substantially all of our products and services businesses, including our QCT semiconductor business.

Materials that are as of a specific date, including but not limited to press releases, presentations, blog posts and webcasts, may have been superseded by subsequent events or disclosures.

Nothing in these materials is an offer to sell or license any of the services or materials referenced herein.


Provide Feedback
Close
