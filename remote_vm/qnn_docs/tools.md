

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

Tools
Analysis
snpe-platform-validator
Tools
Updated: Jun 11, 2025 80-63442-2 Rev: BL
This page describes the SDK tools and features for Linux/Android and Windows developers.
Category

Tool

Developer

Linux/Android

Windows

Ubuntu

WSL x86_64 (*)

Device

WSL x86_64 (*)

Windows x86_64

Windows on Snapdragon

Model Conversion

snpe-onnx-to-dlc

Yes

Yes

Yes

Yes

Yes**

snpe-pytorch-to-dlc

Yes

Yes

Yes

snpe-tensorflow-to-dlc

Yes

Yes

Yes

Yes

Yes**

snpe-tflite-to-dlc

Yes

Yes

Yes

qairt-converter

Yes

Yes

Yes

Yes

Yes**

Model Preparation

snpe-dlc-graph-prepare

Yes

Yes

Yes

Yes

Yes**

snpe-dlc-quant

Yes

Yes

Yes

Yes

Yes

snpe-dlc-quantize

Yes

Yes

Yes

snpe-udo-package-generator

Yes

Yes

Yes

qairt-quantizer

Yes

Yes

Yes

Yes

Yes

Execution

snpe-net-run

Yes

Yes

Yes

Yes

Yes

Yes

snpe-parallel-run

Yes

Yes

Yes

Yes

Yes

snpe-throughput-net-run

Yes

Yes

Yes

Yes

Yes

Yes

Analysis

snpe-diagview

Yes

Yes

Yes

Yes

snpe-dlc-diff

Yes

Yes

Yes

Yes

Yes**

snpe-dlc-info

Yes

Yes

Yes

Yes

Yes**

snpe-dlc-viewer

Yes

Yes

Yes

Yes

Yes**

snpe-platform-validator

Yes

Yes

snpe-platform-validator-py

Yes

snpe_bench.py

Yes

Yes

qairt-dlc-diff

Yes

Yes

Yes

Yes

Yes**

qairt-dlc-info

Yes

Yes

Yes

Yes

Yes**

Architecture Checker (Experimental)

Yes

Yes

Yes

Yes

Yes**

Accuracy Debugger (Experimental)

Yes

Yes***

Yes

Note

* Binary used in Windows WSL x86_64 is from Ubuntu, related installation documentation please reference to Qualcomm (R) Neural Processing SDK Setup
* For ARM64X in Windows, only snpe-net-run and snpe-throughput-net-run are supported.
** Requires the python scripts and the executables from the Windows x86_64 binary folder.
*** snpe-accuracy-debugger on Windows x86 system is tested only for CPU runtime currently.
Note

* When using converter tools in Windows PowerShell, make sure a virtual environment with the required packages is actived and converters are executed via python, as shown in the following example.
(venv-3.10) > python snpe-onnx-to-dlc <options>
Note

TFlite conversion using qairt-converter is not supported for Windows x86_64 and Windows on Snapdragon due to TVM library dependency.

Model Conversion
snpe-onnx-to-dlc
snpe-onnx-to-dlc converts a serialized ONNX model into a DLC file. Current ONNX Conversion supports upto ONNX Opset 21. .. code:: fragment

usage: snpe-onnx-to-dlc [–out_node OUT_NAMES] [–input_type INPUT_NAME INPUT_TYPE]
[–input_dtype INPUT_NAME INPUT_DTYPE] [–input_encoding …] [–input_layout INPUT_NAME INPUT_LAYOUT] [–custom_io CUSTOM_IO] [–preserve_io [PRESERVE_IO [PRESERVE_IO …]]] [–dry_run [DRY_RUN]] [-d INPUT_NAME INPUT_DIM] [-n] [-b BATCH] [-s SYMBOL_NAME VALUE] [–dump_custom_io_config_template DUMP_CUSTOM_IO_CONFIG_TEMPLATE] [–quantization_overrides QUANTIZATION_OVERRIDES] [–keep_quant_nodes] [–disable_batchnorm_folding] [–expand_lstm_op_structure] [–keep_disconnected_nodes] –input_network INPUT_NETWORK [-h] [–debug [DEBUG]] [-o OUTPUT_PATH] [–copyright_file COPYRIGHT_FILE] [–float_bitwidth FLOAT_BITWIDTH] [–float_bw FLOAT_BW] [–float_bias_bw FLOAT_BIAS_BW] [–model_version MODEL_VERSION] [–validation_target RUNTIME_TARGET PROCESSOR_TARGET] [–strict] [–udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS …]] [–op_package_lib OP_PACKAGE_LIB] [–converter_op_package_lib CONVERTER_OP_PACKAGE_LIB] [-p PACKAGE_NAME | –op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS …]]

Script to convert ONNX model into a DLC file.

required arguments:
--input_network INPUT_NETWORK, -i INPUT_NETWORK
Path to the source framework model.

optional arguments:
--out_node OUT_NAMES, --out_name OUT_NAMES
Name of the graph’s output Tensor Names. Multiple output names should be provided separately like:

–out_name out_1 –out_name out_2

–input_type INPUT_NAME INPUT_TYPE, -t INPUT_NAME INPUT_TYPE
Type of data expected by each input op/layer. Type for each input is |default| if not specified. For example: “data” image.Note that the quotes should always be included in order to handle special characters, spaces,etc. For multiple inputs specify multiple –input_type on the command line. Eg:

–input_type “data1” image –input_type “data2” opaque

These options get used by DSP runtime and following descriptions state how input will be handled for each option. Image: Input is float between 0-255 and the input’s mean is 0.0f and the input’s max is 255.0f. We will cast the float to uint8ts and pass the uint8ts to the DSP. Default: Pass the input as floats to the dsp directly and the DSP will quantize it. Opaque: Assumes input is float because the consumer layer(i.e next layer) requires it as float, therefore it won’t be quantized. Choices supported:

image default opaque

–input_dtype INPUT_NAME INPUT_DTYPE
The names and datatype of the network input layers specified in the format [input_name datatype], for example:

‘data’ ‘float32’

Default is float32 if not specified Note that the quotes should always be included in order to handlespecial characters, spaces, etc. For multiple inputs specify multiple –input_dtype on the command line like:

–input_dtype ‘data1’ ‘float32’ –input_dtype ‘data2’ ‘float32’

–input_encoding INPUT_ENCODING [INPUT_ENCODING …], -e INPUT_ENCODING [INPUT_ENCODING …]
Usage: –input_encoding “INPUT_NAME” INPUT_ENCODING_IN [INPUT_ENCODING_OUT] Input encoding of the network inputs. Default is bgr. e.g.

–input_encoding “data” rgba

Quotes must wrap the input node name to handle special characters, spaces, etc. To specify encodings for multiple inputs, invoke –input_encoding for each one. e.g.

–input_encoding “data1” rgba –input_encoding “data2” other

Optionally, an output encoding may be specified for an input node by providing a second encoding. The default output encoding is bgr. e.g.

–input_encoding “data3” rgba rgb

Input encoding types:
image color encodings: bgr,rgb, nv21, nv12, … time_series: for inputs of rnn models; other: not available above or is unknown.

Supported encodings:
bgr rgb rgba argb32 nv21 nv12 time_series other

–input_layout INPUT_NAME INPUT_LAYOUT, -l INPUT_NAME INPUT_LAYOUT
Layout of each input tensor. If not specified, it will use the default based on the Source Framework, shape of input and input encoding. Accepted values are-

NCDHW, NDHWC, NCHW, NHWC, NFC, NCF, NTF, TNF, NF, NC, F, NONTRIVIAL

N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature, T = Time NDHWC/NCDHW used for 5d inputs NHWC/NCHW used for 4d image-like inputs NFC/NCF used for inputs to Conv1D or other 1D ops NTF/TNF used for inputs with time steps like the ones used for LSTM op NF used for 2D inputs, like the inputs to Dense/FullyConnected layers NC used for 2D inputs with 1 for batch and other for Channels (rarely used) F used for 1D inputs, e.g. Bias tensor NONTRIVIAL for everything elseFor multiple inputs specify multiple –input_layout on the command line. Eg:

–input_layout “data1” NCHW –input_layout “data2” NCHW

Note: This flag does not set the layout of the input tensor in the converted DLC. Please use –custom_io for that.

--custom_io CUSTOM_IO
Use this option to specify a yaml file for custom IO.

–preserve_io [PRESERVE_IO [PRESERVE_IO …]]
Use this option to preserve IO layout and datatype. The different ways of using this option are as follows:

–preserve_io layout <space separated list of names of inputs and

outputs of the graph>
–preserve_io datatype <space separated list of names of inputs and

outputs of the graph> To preserve specific layers or datatypes specify the string (layout or datatype) in the command to indicate that the converter needs to preserve the layout or datatype, e.g., --preserve_io layout input1 input2 output1 --preserve_io datatype input1 input2 output1 To preserve the layout and/or datatype for all inputs and outputs of the graph (optional):

–preserve_io layout –preserve_io datatype

To preserve both layout and datatypes for all IO tensors pass the option as follows:
–preserve_io

Note: Only one of the above usages are allowed at a time. Note: –custom_io gets higher precedence than –preserve_io.

–dry_run [DRY_RUN]
Evaluates the model without actually converting any ops, and returns unsupported ops/attributes as well as unused inputs and/or outputs if any. Leave empty or specify “info” to see dry run as a table, or specify “debug” to show more detailed messages only”

-d INPUT_NAME INPUT_DIM, –input_dim INPUT_NAME INPUT_DIM
The name and dimension of all the input buffers to the network specified in the format [input_name comma-separated-dimensions], for example: ‘data’ 1,224,224,3. Note that the quotes should always be included in order to handle special characters, spaces, etc. NOTE: This feature works only with Onnx 1.6.0 and above

-n, --no_simplification
Do not attempt to simplify the model automatically. This may prevent some models from properly converting when sequences of unsupported static operations are present.

-b BATCH, --batch BATCH
The batch dimension override. This will take the first dimension of all inputs and treat it as a batch dim, overriding it with the value provided here. For example: –batch 6 will result in a shape change from [1,3,224,224] to [6,3,224,224]. If there are inputs without batch dim this should not be used and each input should be overridden independently using -d option for input dimension overrides.

-s SYMBOL_NAME VALUE, –define_symbol SYMBOL_NAME VALUE
This option allows overriding specific input dimension symbols. For instance you might see input shapes specified with variables such as : data: [1,3,height,width] To override these simply pass the option as: –define_symbol height 224 –define_symbol width 448 which results in dimensions that look like: data: [1,3,224,448]

--dump_custom_io_config_template DUMP_CUSTOM_IO_CONFIG_TEMPLATE
Dumps the yaml template for Custom I/O configuration. This file canbe edited as per the custom requirements and passed using the option –custom_ioUse this option to specify a yaml file to which the custom IO config template is dumped.

–disable_batchnorm_folding –expand_lstm_op_structure

Enables optimization that breaks the LSTM op to equivalent math ops

--keep_disconnected_nodes
Disable Optimization that removes Ops not connected to the main graph. This optimization uses output names provided over commandline OR inputs/outputs extracted from the Source model to determine the main graph

-h, --help
show this help message and exit

–debug [DEBUG] Run the converter in debug mode. -o OUTPUT_PATH, –output_path OUTPUT_PATH

Path where the converted Output model should be saved.If not specified, the converter model will be written to a file with same name as the input model

--copyright_file COPYRIGHT_FILE
Path to copyright file. If provided, the content of the file will be added to the output model.

--float_bitwidth FLOAT_BITWIDTH
Use the –float_bitwidth option to select the bitwidth to use when using float for parameters(weights/bias) and activations for all ops or specific Op (via encodings) selected through encoding, either 32 (default) or 16.

--float_bw FLOAT_BW
Note: –float_bw is deprecated, use –float_bitwidth.

--float_bias_bw FLOAT_BIAS_BW
Use the –float_bias_bw option to select the bitwidth to use for the float bias tensor

--model_version MODEL_VERSION
User-defined ASCII string to identify the model, only first 64 bytes will be stored

–validation_target RUNTIME_TARGET PROCESSOR_TARGET
A combination of processor and runtime target against which model will be validated. Choices for RUNTIME_TARGET:

{cpu, gpu, dsp}.

Choices for PROCESSOR_TARGET:
{snapdragon_801, snapdragon_820, snapdragon_835}.

If not specified, will validate model against {snapdragon_820, snapdragon_835} across all runtime targets.

--strict
If specified, will validate in strict mode whereby model will not be produced if it violates constraints of the specified validation target. If not specified, will validate model in permissive mode against the specified validation target.

–udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS …], -udo CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS …]
Path to the UDO configs (space separated, if multiple)

Quantizer Options:
--quantization_overrides QUANTIZATION_OVERRIDES
Use this option to specify a json file with parameters to use for quantization. These will override any quantization data carried from conversion (eg TF fake quantization) or calculated during the normal quantization process. Format defined as per AIMET specification.

--keep_quant_nodes
Use this option to keep activation quantization nodes in the graph rather than stripping them.

Custom Op Package Options:
–op_package_lib OP_PACKAGE_LIB, -opl OP_PACKAGE_LIB
Use this argument to pass an op package library for quantization. Must be in the form <op_package_lib_path:interfaceProviderName> and be separated by a comma for multiple package libs

–converter_op_package_lib CONVERTER_OP_PACKAGE_LIB, -cpl CONVERTER_OP_PACKAGE_LIB
Path to converter op package library compiled by the OpPackage generator.

-p PACKAGE_NAME, --package_name PACKAGE_NAME
A global package name to be used for each node in the Model.cpp file. Defaults to Qnn header defined package name

–op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS …], -opc CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS …]
Path to a Qnn Op Package XML configuration file that contains user defined custom operations.

Note: Only one of: {‘op_package_config’, ‘package_name’} can be specified

For more information, see ONNX Model Conversion

Additional details:
input_layout argument:

Used with TF2Onnx or Keras2Onnx models when the input layout is NHWC. Onnx converter assumes that 4D inputs to the model are used by CNNs and are in NCHW format. For Keras2Onnx or TF2Onnx models, where the input is NHWC followed most likely by a transpose to NCHW, the converter will fail to successfully convert and optimize the model without the use of this argument.

snpe-pytorch-to-dlc
snpe-pytorch-to-dlc converts a serialized PyTorch model into a DLC file.

usage: snpe-pytorch-to-dlc -d INPUT_NAME INPUT_DIM [--out_node OUT_NAMES]
                           [--input_type INPUT_NAME INPUT_TYPE]
                           [--input_dtype INPUT_NAME INPUT_DTYPE] [--input_encoding  ...]
                           [--input_layout INPUT_NAME INPUT_LAYOUT] [--custom_io CUSTOM_IO]
                           [--preserve_io [PRESERVE_IO [PRESERVE_IO ...]]] [--dump_relay DUMP_RELAY]
                           [--quantization_overrides QUANTIZATION_OVERRIDES] [--keep_quant_nodes]
                           [--disable_batchnorm_folding] [--expand_lstm_op_structure]
                           [--keep_disconnected_nodes]
                           --input_network INPUT_NETWORK [-h] [--debug [DEBUG]] [-o OUTPUT_PATH]
                           [--copyright_file COPYRIGHT_FILE] [--float_bitwidth FLOAT_BITWIDTH]
                           [--float_bw FLOAT_BW] [--float_bias_bw FLOAT_BIAS_BW]
                           [--model_version MODEL_VERSION]
                           [--validation_target RUNTIME_TARGET PROCESSOR_TARGET] [--strict]
                           [--udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]
                           [--op_package_lib OP_PACKAGE_LIB]
                           [--converter_op_package_lib CONVERTER_OP_PACKAGE_LIB]
                           [-p PACKAGE_NAME | --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]


Script to convert PyTorch model into DLC

required arguments:
    -d INPUT_NAME INPUT_DIM, --input_dim INPUT_NAME INPUT_DIM
                        The names and dimensions of the network input layers specified in the format
                        [input_name comma-separated-dimensions], for example:
                            'data' 1,3,224,224
                        Note that the quotes should always be included in order to handle special
                        characters, spaces, etc. For multiple inputs specify multiple --input_dim on the command line like:
                            --input_dim 'data1' 1,3,224,224 --input_dim 'data2' 1,50,100,3
    --input_network INPUT_NETWORK, -i INPUT_NETWORK
                        Path to the source framework model.

optional arguments:
    --out_node OUT_NAMES, --out_name OUT_NAMES
                        Name of the graph's output Tensor Names. Multiple output names should be
                        provided separately like:
                            --out_name out_1 --out_name out_2
    --input_type INPUT_NAME INPUT_TYPE, -t INPUT_NAME INPUT_TYPE
                        Type of data expected by each input op/layer. Type for each input is
                        |default| if not specified. For example: "data" image.Note that the quotes
                        should always be included in order to handle special characters, spaces,etc.
                        For multiple inputs specify multiple --input_type on the command line.
                        Eg:
                           --input_type "data1" image --input_type "data2" opaque
                        These options get used by DSP runtime and following descriptions state how
                        input will be handled for each option.
                        Image:
                        Input is float between 0-255 and the input's mean is 0.0f and the input's
                        max is 255.0f. We will cast the float to uint8ts and pass the uint8ts to the
                        DSP.
                        Default:
                        Pass the input as floats to the dsp directly and the DSP will quantize it.
                        Opaque:
                        Assumes input is float because the consumer layer(i.e next layer) requires
                        it as float, therefore it won't be quantized.
                        Choices supported:
                           image
                           default
                           opaque
    --input_dtype INPUT_NAME INPUT_DTYPE
                        The names and datatype of the network input layers specified in the format
                        [input_name datatype], for example:
                            'data' 'float32'
                        Default is float32 if not specified.
                        Note that the quotes should always be included in order to handle special
                        characters, spaces, etc.
                        For multiple inputs specify multiple --input_dtype on the command line like:
                            --input_dtype 'data1' 'float32' --input_dtype 'data2' 'float32'
  --input_encoding INPUT_ENCODING [INPUT_ENCODING ...], -e INPUT_ENCODING [INPUT_ENCODING ...]
                        Usage:     --input_encoding "INPUT_NAME" INPUT_ENCODING_IN
                        [INPUT_ENCODING_OUT]
                        Input encoding of the network inputs. Default is bgr.
                        e.g.
                           --input_encoding "data" rgba
                        Quotes must wrap the input node name to handle special characters,
                        spaces, etc. To specify encodings for multiple inputs, invoke
                        --input_encoding for each one.
                        e.g.
                            --input_encoding "data1" rgba --input_encoding "data2" other
                        Optionally, an output encoding may be specified for an input node by
                        providing a second encoding. The default output encoding is bgr.
                        e.g.
                            --input_encoding "data3" rgba rgb
                        Input encoding types:
                             image color encodings: bgr,rgb, nv21, nv12, ...
                            time_series: for inputs of rnn models;
                            other: not available above or is unknown.
                        Supported encodings:
                            bgr
                            rgb
                            rgba
                            argb32
                            nv21
                            nv12
                            time_series
                            other
    --input_layout INPUT_NAME INPUT_LAYOUT, -l INPUT_NAME INPUT_LAYOUT
                        Layout of each input tensor. If not specified, it will use the default
                        based on the Source Framework, shape of input and input encoding.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, NFC, NCF, NTF, TNF, NF, NC, F, NONTRIVIAL
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature, T = Time
                        NDHWC/NCDHW used for 5d inputs
                        NHWC/NCHW used for 4d image-like inputs
                        NFC/NCF used for inputs to Conv1D or other 1D ops
                        NTF/TNF used for inputs with time steps like the ones used for LSTM op
                        NF used for 2D inputs, like the inputs to Dense/FullyConnected layers
                        NC used for 2D inputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D inputs, e.g. Bias tensor
                        NONTRIVIAL for everything elseFor multiple inputs specify multiple
                        --input_layout on the command line.
                        Eg:
                            --input_layout "data1" NCHW --input_layout "data2" NCHW
                        Note: This flag does not set the layout of the input tensor in the converted DLC.
    --custom_io CUSTOM_IO
                        Use this option to specify a yaml file for custom IO.
    --preserve_io [PRESERVE_IO [PRESERVE_IO ...]]
                        Use this option to preserve IO layout and datatype. The different ways of
                        using this option are as follows:
                            --preserve_io layout <space separated list of names of inputs and
                        outputs of the graph>
                            --preserve_io datatype <space separated list of names of inputs and
                        outputs of the graph>
                        In this case, user should also specify the string - layout or datatype in
                        the command to indicate that converter needs to
                        preserve the layout or datatype. e.g.
                           --preserve_io layout input1 input2 output1
                           --preserve_io datatype input1 input2 output1
                        Optionally, the user may choose to preserve the layout and/or datatype for
                        all the inputs and outputs of the graph.
                        This can be done in the following two ways:
                            --preserve_io layout
                            --preserve_io datatype
                        Additionally, the user may choose to preserve both layout and datatypes for
                        all IO tensors by just passing the option as follows:
                            --preserve_io
                        Note: Only one of the above usages are allowed at a time.
                        Note: --custom_io gets higher precedence than --preserve_io.
    --dump_relay DUMP_RELAY
                        Dump Relay ASM and Params at the path provided with the argument
                        Usage: --dump_relay <path_to_dump>
    --disable_batchnorm_folding
    --expand_lstm_op_structure
                        Enables optimization that breaks the LSTM op to equivalent math ops
    --keep_disconnected_nodes
                        Disable Optimization that removes Ops not connected to the main graph.
                        This optimization uses output names provided over commandline OR
                        inputs/outputs extracted from the Source model to determine the main graph
    -h, --help            show this help message and exit
    -o OUTPUT_PATH, --output_path OUTPUT_PATH
                        Path where the converted Output model should be saved.If not specified, the
                        converter model will be written to a file with same name as the input model
    --copyright_file COPYRIGHT_FILE
                        Path to copyright file. If provided, the content of the file will be added
                        to the output model.
    --float_bitwidth FLOAT_BITWIDTH
                        Use the --float_bitwidth option to select the bitwidth to use when using
                        float for parameters(weights/bias) and activations for all ops  or specific
                        Op (via encodings) selected through encoding, either 32 (default) or 16.
    --float_bw FLOAT_BW
                        Note: --float_bw is deprecated, use --float_bitwidth.
    --float_bias_bw FLOAT_BIAS_BW
                        Use the --float_bias_bw option to select the bitwidth to use for the float
                        bias tensor
    --model_version MODEL_VERSION
                        User-defined ASCII string to identify the model, only first 64 bytes will be
                        stored
    --validation_target RUNTIME_TARGET PROCESSOR_TARGET
                        A combination of processor and runtime target against which model will be
                        validated.
                        Choices for RUNTIME_TARGET:
                           {cpu, gpu, dsp}.
                        Choices for PROCESSOR_TARGET:
                           {snapdragon_801, snapdragon_820, snapdragon_835}.
                        If not specified, will validate model against {snapdragon_820,
                        snapdragon_835} across all runtime targets.
    --strict            If specified, will validate in strict mode whereby model will not be
                        produced if it violates constraints of the specified validation target. If
                        not specified, will validate model in permissive mode against the specified
                        validation target.
    --udo_config_paths  UDO_CONFIG_PATHS [UDO_CONFIG_PATHS ...], -udo UDO_CONFIG_PATHS
                        [UDO_CONFIG_PATHS ...]
                            Path to the UDO configs (space separated, if multiple)

Quantizer Options:
    --quantization_overrides QUANTIZATION_OVERRIDES
                        Use this option to specify a json file with parameters to use for
                        quantization. These will override any quantization data carried from
                        conversion (eg TF fake quantization) or calculated during the normal
                        quantization process. Format defined as per AIMET specification.
    --keep_quant_nodes  Use this option to keep activation quantization nodes in the graph rather
                        than stripping them.

Custom Op Package Options:
    --op_package_lib OP_PACKAGE_LIB, -opl OP_PACKAGE_LIB
                        Use this argument to pass an op package library for quantization. Must be in
                        the form <op_package_lib_path:interfaceProviderName> and be separated by a
                        comma for multiple package libs
    --converter_op_package_lib CONVERTER_OP_PACKAGE_LIB, -cpl CONVERTER_OP_PACKAGE_LIB
                        Path to converter op package library compiled by the OpPackage generator.
    -p PACKAGE_NAME, --package_name PACKAGE_NAME
                        A global package name to be used for each node in the Model.cpp file.
                        Defaults to Qnn header defined package name
    --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...], -opc CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]
                        Path to a Qnn Op Package XML configuration file that contains user defined
                        custom operations.

Note: Only one of: {'package_name', 'op_package_config'} can be specified
For more information, see PyTorch Model Conversion


snpe-tensorflow-to-dlc
snpe-tensorflow-to-dlc converts a TensorFlow model into a DLC file.

usage: snpe-tensorflow-to-dlc -d INPUT_NAME INPUT_DIM --out_node OUT_NAMES
                              [--input_type INPUT_NAME INPUT_TYPE]
                              [--input_dtype INPUT_NAME INPUT_DTYPE] [--input_encoding  ...]
                              [--input_layout INPUT_NAME INPUT_LAYOUT] [--custom_io CUSTOM_IO]
                              [--preserve_io [PRESERVE_IO [PRESERVE_IO ...]]]
                              [--show_unconsumed_nodes] [--saved_model_tag SAVED_MODEL_TAG]
                              [--saved_model_signature_key SAVED_MODEL_SIGNATURE_KEY]
                              [--quantization_overrides QUANTIZATION_OVERRIDES] [--keep_quant_nodes]
                              [--disable_batchnorm_folding] [--expand_lstm_op_structure]
                              [--keep_disconnected_nodes]
                              --input_network INPUT_NETWORK [-h] [--debug [DEBUG]] [-o OUTPUT_PATH]
                              [--copyright_file COPYRIGHT_FILE] [--float_bitwidth FLOAT_BITWIDTH]
                              [--float_bw FLOAT_BW] [--float_bias_bw FLOAT_BIAS_BW]
                              [--model_version MODEL_VERSION]
                              [--validation_target RUNTIME_TARGET PROCESSOR_TARGET] [--strict]
                              [--udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]
                              [--op_package_lib OP_PACKAGE_LIB]
                              [--converter_op_package_lib CONVERTER_OP_PACKAGE_LIB]
                              [-p PACKAGE_NAME | --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]

Script to convert TF model into DLC.

required arguments:
    -d INPUT_NAME INPUT_DIM, --input_dim INPUT_NAME INPUT_DIM
                        The names and dimensions of the network input layers specified in the format
                        [input_name comma-separated-dimensions], for example:
                            'data' 1,224,224,3
                        Note that the quotes should always be included in order to handle special
                        characters, spaces, etc.
                        For multiple inputs specify multiple --input_dim on the command line like:
                            --input_dim 'data1' 1,224,224,3 --input_dim 'data2' 1,50,100,3
    --out_node OUT_NAMES, --out_name OUT_NAMES
                        Name of the graph's output Tensor Names. Multiple output names should be
                        provided separately like:
                            --out_name out_1 --out_name out_2
    --input_network INPUT_NETWORK, -i INPUT_NETWORK
                        Path to the source framework model.

optional arguments:
    --input_type INPUT_NAME INPUT_TYPE, -t INPUT_NAME INPUT_TYPE
                        Type of data expected by each input op/layer. Type for each input is
                        |default| if not specified. For example: "data" image.Note that the quotes
                        should always be included in order to handle special characters, spaces,etc.
                        For multiple inputs specify multiple --input_type on the command line.
                        Eg:
                           --input_type "data1" image --input_type "data2" opaque
                        These options get used by DSP runtime and following descriptions state how
                        input will be handled for each option.
                        Image:
                        Input is float between 0-255 and the input's mean is 0.0f and the input's
                        max is 255.0f. We will cast the float to uint8ts and pass the uint8ts to the
                        DSP.
                        Default:
                        Pass the input as floats to the dsp directly and the DSP will quantize it.
                        Opaque:
                        Assumes input is float because the consumer layer(i.e next layer) requires
                        it as float, therefore it won't be quantized.
                        Choices supported:
                           image
                           default
                           opaque
    --input_dtype INPUT_NAME INPUT_DTYPE
                        The names and datatype of the network input layers specified in the format
                        [input_name datatype], for example:
                            'data' 'float32'
                        Default is float32 if not specified
                        Note that the quotes should always be included in order to handlespecial
                        characters, spaces, etc.
                        For multiple inputs specify multiple --input_dtype on the command line like:
                            --input_dtype 'data1' 'float32' --input_dtype 'data2' 'float32'
    --input_encoding INPUT_ENCODING [INPUT_ENCODING ...], -e INPUT_ENCODING [INPUT_ENCODING ...]
                        Usage:     --input_encoding "INPUT_NAME" INPUT_ENCODING_IN
                        [INPUT_ENCODING_OUT]
                        Input encoding of the network inputs. Default is bgr.
                        e.g.
                           --input_encoding "data" rgba
                        Quotes must wrap the input node name to handle special characters,
                        spaces, etc. To specify encodings for multiple inputs, invoke
                        --input_encoding for each one.
                        e.g.
                            --input_encoding "data1" rgba --input_encoding "data2" other
                        Optionally, an output encoding may be specified for an input node by
                        providing a second encoding. The default output encoding is bgr.
                        e.g.
                            --input_encoding "data3" rgba rgb
                        Input encoding types:
                            image color encodings: bgr,rgb, nv21, nv12, ...
                            time_series: for inputs of rnn models;
                            other: not available above or is unknown.
                        Supported encodings:
                            bgr
                            rgb
                            rgba
                            argb32
                            nv21
                            nv12
                            time_series
                            other
    --input_layout INPUT_NAME INPUT_LAYOUT, -l INPUT_NAME INPUT_LAYOUT
                        Layout of each input tensor. If not specified, it will use the default
                        based on the Source Framework, shape of input and input encoding.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, NFC, NCF, NTF, TNF, NF, NC, F, NONTRIVIAL
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature, T = Time
                        NDHWC/NCDHW used for 5d inputs
                        NHWC/NCHW used for 4d image-like inputs
                        NFC/NCF used for inputs to Conv1D or other 1D ops
                        NTF/TNF used for inputs with time steps like the ones used for LSTM op
                        NF used for 2D inputs, like the inputs to Dense/FullyConnected layers
                        NC used for 2D inputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D inputs, e.g. Bias tensor
                        NONTRIVIAL for everything elseFor multiple inputs specify multiple
                        --input_layout on the command line.
                        Eg:
                            --input_layout "data1" NCHW --input_layout "data2" NCHW
                        Note: This flag does not set the layout of the input tensor in the converted DLC.
    --custom_io CUSTOM_IO
                        Use this option to specify a yaml file for custom IO.
    --preserve_io [PRESERVE_IO [PRESERVE_IO ...]]
                        Use this option to preserve IO layout and datatype. The different ways of
                        using this option are as follows:
                            --preserve_io layout <space separated list of names of inputs and
                        outputs of the graph>
                            --preserve_io datatype <space separated list of names of inputs and
                        outputs of the graph>
                        In this case, user should also specify the string - layout or datatype in
                        the command to indicate that converter needs to
                        preserve the layout or datatype. e.g.
                           --preserve_io layout input1 input2 output1
                           --preserve_io datatype input1 input2 output1
                        Optionally, the user may choose to preserve the layout and/or datatype for
                        all the inputs and outputs of the graph.
                        This can be done in the following two ways:
                            --preserve_io layout
                            --preserve_io datatype
                        Additionally, the user may choose to preserve both layout and datatypes for
                        all IO tensors by just passing the option as follows:
                            --preserve_io
                        Note: Only one of the above usages are allowed at a time.
                        Note: --custom_io gets higher precedence than --preserve_io.
    --show_unconsumed_nodes
                        Displays a list of unconsumed nodes, if there any are found. Nodes which are
                        unconsumed do not violate the structural fidelity of the generated graph.
    --saved_model_tag SAVED_MODEL_TAG
                        Specify the tag to seletet a MetaGraph from savedmodel. ex:
                        --saved_model_tag serve. Default value will be 'serve' when it is not
                        assigned.
    --saved_model_signature_key SAVED_MODEL_SIGNATURE_KEY
                        Specify signature key to select input and output of the model. ex:
                        --saved_model_signature_key serving_default. Default value will be
                        'serving_default' when it is not assigned
    --disable_batchnorm_folding
    --expand_lstm_op_structure
                        Enables optimization that breaks the LSTM op to equivalent math ops
    --keep_disconnected_nodes
                        Disable Optimization that removes Ops not connected to the main graph.
                        This optimization uses output names provided over commandline OR
                        inputs/outputs extracted from the Source model to determine the main graph
    -h, --help          show this help message and exit
    --debug [DEBUG]     Run the converter in debug mode.
    -o OUTPUT_PATH, --output_path OUTPUT_PATH
                        Path where the converted Output model should be saved.If not specified, the
                        converter model will be written to a file with same name as the input model
    --copyright_file COPYRIGHT_FILE
                        Path to copyright file. If provided, the content of the file will be added
                        to the output model.
    --float_bitwidth FLOAT_BITWIDTH
                        Use the --float_bitwidth option to select the bitwidth to use when using
                        float for parameters(weights/bias) and activations for all ops  or specific
                        Op (via encodings) selected through encoding, either 32 (default) or 16.
    --float_bw FLOAT_BW
                        Note: --float_bw is deprecated, use --float_bitwidth.
    --float_bias_bw FLOAT_BIAS_BW
                        Use the --float_bias_bw option to select the bitwidth to use for the float
                        bias tensor
    --model_version MODEL_VERSION
                        User-defined ASCII string to identify the model, only first 64 bytes will be
                        stored
    --validation_target RUNTIME_TARGET PROCESSOR_TARGET
                        A combination of processor and runtime target against which model will be
                        validated.
                        Choices for RUNTIME_TARGET:
                           {cpu, gpu, dsp}.
                        Choices for PROCESSOR_TARGET:
                           {snapdragon_801, snapdragon_820, snapdragon_835}.
                        If not specified, will validate model against {snapdragon_820,
                        snapdragon_835} across all runtime targets.
    --strict            If specified, will validate in strict mode whereby model will not be
                        produced if it violates constraints of the specified validation target. If
                        not specified, will validate model in permissive mode against the specified
                        validation target.
    --udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...], -udo CUSTOM_OP_CONFIG_PATHS
                        [CUSTOM_OP_CONFIG_PATHS ...]
                        Path to the UDO configs (space separated, if multiple)

Quantizer Options:
    --quantization_overrides QUANTIZATION_OVERRIDES
                        Use this option to specify a json file with parameters to use for
                        quantization. These will override any quantization data carried from
                        conversion (eg TF fake quantization) or calculated during the normal
                        quantization process. Format defined as per AIMET specification.
    --keep_quant_nodes  Use this option to keep activation quantization nodes in the graph rather
                        than stripping them.

Custom Op Package Options:
    --op_package_lib OP_PACKAGE_LIB, -opl OP_PACKAGE_LIB
                        Use this argument to pass an op package library for quantization. Must be in
                        the form <op_package_lib_path:interfaceProviderName> and be separated by a
                        comma for multiple package libs
    --converter_op_package_lib CONVERTER_OP_PACKAGE_LIB, -cpl CONVERTER_OP_PACKAGE_LIB
                        Path to converter op package library compiled by the OpPackage generator.
    -p PACKAGE_NAME, --package_name PACKAGE_NAME
                        A global package name to be used for each node in the Model.cpp file.
                        Defaults to Qnn header defined package name
    --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...], -opc CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]
                        Path to a Qnn Op Package XML configuration file that contains user defined
                        custom operations.

Note: Only one of: {'package_name', 'op_package_config'} can be specified
For more information, see TensorFlow Model Conversion
Additional details:
input_network argument:

The converter supports a single frozen graph .pb file, a path to a pair of graph meta and checkpoint files, or the path to a SavedModel directory (TF 2.x).

If you are using the TensorFlow Saver to save your graph during training, 3 files will be generated as described below:

<model-name>.meta

<model-name>

checkpoint

The converter –input_network option specifies the path to the graph meta file. The converter will also use the checkpoint file to read the graph nodes parameters during conversion. The checkpoint file must have the same name without the .meta suffix.

This argument is required.

input_dim argument:

Specifies the input dimensions of the graph’s input node(s)

The converter requires a node name along with dimensions as input from which it will create an input layer by using the node output tensor dimensions. When defining a graph, there is typically a placeholder name used as input during training in the graph. The placeholder tensor name is the name you must use as the argument. It is also possible to use other types of nodes as input, however the node used as input will not be used as part of a layer other than the input layer.

Multiple Inputs

Networks with multiple inputs must provide –input_dim INPUT_NAME INPUT_DIM, one for each input node.

This argument is required.

out_node argument:

The name of the last node in your TensorFlow graph which will represent the output layer of your network.

Multiple Outputs

Networks with multiple outputs must provide several –out_node arguments, one for each output node.

output_path argument:

Specifies the output DLC file name.

This argument is optional. If not provided the converter will create a DLC file with the same name as the graph file name, with a .dlc file extension.

saved_model_tag:

For Tensorflow 2.x networks, this option allows a MetaGraph to be selected from the SavedModel specified by input_network.

This argument is optional and defaults to “serve”.

saved_model_signature:

For Tensorflow 2.x networks, this option specifies the signature key for selecting inputs and outputs of a Tensorflow 2.x SavedModel.

This argument is optional and defaults to “serving_default”.

SavedModel is the default model format in TensorFlow 2 and can been supported in Qualcomm® Neural Processing SDK TensorFlow Converter now.


snpe-tflite-to-dlc
snpe-tflite-to-dlc converts a TFLite model into a DLC file.

usage: snpe-tflite-to-dlc [-d INPUT_NAME INPUT_DIM] [--signature_name SIGNATURE_NAME]
                          [--out_node OUT_NAMES] [--input_type INPUT_NAME INPUT_TYPE]
                          [--input_dtype INPUT_NAME INPUT_DTYPE] [--input_encoding  ...]
                          [--input_layout INPUT_NAME INPUT_LAYOUT] [--custom_io CUSTOM_IO]
                          [--preserve_io [PRESERVE_IO [PRESERVE_IO ...]]] [--dump_relay DUMP_RELAY]
                          [--quantization_overrides QUANTIZATION_OVERRIDES]
                          [--keep_quant_nodes] [--disable_batchnorm_folding]
                          [--expand_lstm_op_structure]
                          [--keep_disconnected_nodes] --input_network INPUT_NETWORK [-h]
                          [--debug [DEBUG]] [-o OUTPUT_PATH] [--copyright_file COPYRIGHT_FILE]
                          [--float_bitwidth FLOAT_BITWIDTH] [--float_bw FLOAT_BW]
                          [--float_bias_bw FLOAT_BIAS_BW] [--model_version MODEL_VERSION]
                          [--validation_target RUNTIME_TARGET PROCESSOR_TARGET] [--strict]
                          [--udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]
                          [--op_package_lib OP_PACKAGE_LIB]
                          [--converter_op_package_lib CONVERTER_OP_PACKAGE_LIB]
                          [-p PACKAGE_NAME | --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]

Script to convert TFLite model into DLC

required arguments:
    --input_network INPUT_NETWORK, -i INPUT_NETWORK
                        Path to the source framework model.

optional arguments:
    -d INPUT_NAME INPUT_DIM, --input_dim INPUT_NAME INPUT_DIM
                        The names and dimensions of the network input layers specified in the format
                        [input_name comma-separated-dimensions], for example:
                            'data' 1,224,224,3
                        Note that the quotes should always be included in order to handlespecial
                        characters, spaces, etc.
                        For multiple inputs specify multiple --input_dim on the command line like:
                            --input_dim 'data1' 1,224,224,3 --input_dim 'data2' 1,50,100,3
    --signature_name SIGNATURE_NAME, -sn SIGNATURE_NAME
                        Specifies a specific subgraph signature to convert.
    --out_node OUT_NAMES, --out_name OUT_NAMES
                        Name of the graph's output Tensor Names. Multiple output names should be
                        provided separately like:
                            --out_name out_1 --out_name out_2
    --input_type INPUT_NAME INPUT_TYPE, -t INPUT_NAME INPUT_TYPE
                        Type of data expected by each input op/layer. Type for each input is
                        |default| if not specified. For example: "data" image.Note that the quotes
                        should always be included in order to handle special characters, spaces,etc.
                        For multiple inputs specify multiple --input_type on the command line.
                        Eg:
                           --input_type "data1" image --input_type "data2" opaque
                        These options get used by DSP runtime and following descriptions state how
                        input will be handled for each option.
                        Image:
                        Input is float between 0-255 and the input's mean is 0.0f and the input's
                        max is 255.0f. We will cast the float to uint8ts and pass the uint8ts to the
                        DSP.
                        Default:
                        Pass the input as floats to the dsp directly and the DSP will quantize it.
                        Opaque:
                        Assumes input is float because the consumer layer(i.e next layer) requires
                        it as float, therefore it won't be quantized.
                        Choices supported:
                           image
                           default
                           opaque
    --input_dtype INPUT_NAME INPUT_DTYPE
                        The names and datatype of the network input layers specified in the format
                        [input_name datatype], for example:
                            'data' 'float32'
                        Default is float32 if not specified
                        Note that the quotes should always be included in order to handlespecial
                        characters, spaces, etc.
                        For multiple inputs specify multiple --input_dtype on the command line like:
                            --input_dtype 'data1' 'float32' --input_dtype 'data2' 'float32'
    --input_encoding INPUT_ENCODING [INPUT_ENCODING ...], -e INPUT_ENCODING [INPUT_ENCODING ...]
                        Usage:     --input_encoding "INPUT_NAME" INPUT_ENCODING_IN
                        [INPUT_ENCODING_OUT]
                        Input encoding of the network inputs. Default is bgr.
                        e.g.
                           --input_encoding "data" rgba
                        Quotes must wrap the input node name to handle special characters,
                        spaces, etc. To specify encodings for multiple inputs, invoke
                        --input_encoding for each one.
                        e.g.
                            --input_encoding "data1" rgba --input_encoding "data2" other
                        Optionally, an output encoding may be specified for an input node by
                        providing a second encoding. The default output encoding is bgr.
                        e.g.
                            --input_encoding "data3" rgba rgb
                        Input encoding types:
                             image color encodings: bgr,rgb, nv21, nv12, ...
                            time_series: for inputs of rnn models;
                            other: not available above or is unknown.
                        Supported encodings:
                            bgr
                            rgb
                            rgba
                            argb32
                            nv21
                            nv12
                            time_series
                            other
    --input_layout INPUT_NAME INPUT_LAYOUT, -l INPUT_NAME INPUT_LAYOUT
                        Layout of each input tensor. If not specified, it will use the default
                        based on the Source Framework, shape of input and input encoding.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, NFC, NCF, NTF, TNF, NF, NC, F, NONTRIVIAL
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature, T = Time
                        NDHWC/NCDHW used for 5d inputs
                        NHWC/NCHW used for 4d image-like inputs
                        NFC/NCF used for inputs to Conv1D or other 1D ops
                        NTF/TNF used for inputs with time steps like the ones used for LSTM op
                        NF used for 2D inputs, like the inputs to Dense/FullyConnected layers
                        NC used for 2D inputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D inputs, e.g. Bias tensor
                        NONTRIVIAL for everything elseFor multiple inputs specify multiple
                        --input_layout on the command line.
                        Eg:
                            --input_layout "data1" NCHW --input_layout "data2" NCHW
                        Note: This flag does not set the layout of the input tensor in the converted DLC.
    --custom_io CUSTOM_IO
                        Use this option to specify a yaml file for custom IO.
    --preserve_io [PRESERVE_IO [PRESERVE_IO ...]]
                        Use this option to preserve IO layout and datatype. The different ways of
                        using this option are as follows:
                            --preserve_io layout <space separated list of names of inputs and
                        outputs of the graph>
                            --preserve_io datatype <space separated list of names of inputs and
                        outputs of the graph>
                        In this case, user should also specify the string - layout or datatype in
                        the command to indicate that converter needs to
                        preserve the layout or datatype. e.g.
                           --preserve_io layout input1 input2 output1
                           --preserve_io datatype input1 input2 output1
                        Optionally, the user may choose to preserve the layout and/or datatype for
                        all the inputs and outputs of the graph.
                        This can be done in the following two ways:
                            --preserve_io layout
                            --preserve_io datatype
                        Additionally, the user may choose to preserve both layout and datatypes for
                        all IO tensors by just passing the option as follows:
                            --preserve_io
                        Note: Only one of the above usages are allowed at a time.
                        Note: --custom_io gets higher precedence than --preserve_io.
    --dump_relay DUMP_RELAY
                        Dump Relay ASM and Params at the path provided with the argument
                        Usage: --dump_relay <path_to_dump>
    --disable_batchnorm_folding
    --expand_lstm_op_structure
                        Enables optimization that breaks the LSTM op to equivalent math ops
    --keep_disconnected_nodes
                        Disable Optimization that removes Ops not connected to the main graph.
                        This optimization uses output names provided over commandline OR
                        inputs/outputs extracted from the Source model to determine the main graph
    -h, --help          show this help message and exit
    --debug [DEBUG]     Run the converter in debug mode.
    -o OUTPUT_PATH, --output_path OUTPUT_PATH
                        Path where the converted Output model should be saved.If not specified, the
                        converter model will be written to a file with same name as the input model
    --copyright_file COPYRIGHT_FILE
                        Path to copyright file. If provided, the content of the file will be added
                        to the output model.
    --float_bitwidth FLOAT_BITWIDTH
                        Use the --float_bitwidth option to select the bitwidth to use when using
                        float for parameters(weights/bias) and activations for all ops  or specific
                        Op (via encodings) selected through encoding, either 32 (default) or 16.
    --float_bw FLOAT_BW
                        Note: --float_bw is deprecated, use --float_bitwidth.
    --float_bias_bw FLOAT_BIAS_BW
                        Use the --float_bias_bw option to select the bitwidth to use for the float
                        bias tensor
    --model_version MODEL_VERSION
                        User-defined ASCII string to identify the model, only first 64 bytes will be
                        stored
    --validation_target RUNTIME_TARGET PROCESSOR_TARGET
                        A combination of processor and runtime target against which model will be
                        validated.
                        Choices for RUNTIME_TARGET:
                           {cpu, gpu, dsp}.
                        Choices for PROCESSOR_TARGET:
                           {snapdragon_801, snapdragon_820, snapdragon_835}.
                        If not specified, will validate model against {snapdragon_820,
                        snapdragon_835} across all runtime targets.
    --strict            If specified, will validate in strict mode whereby model will not
                        be produced if it violates constraints of the specified validation target. If
                        not specified, will validate model in permissive mode against the specified
                        validation target.
    --udo_config_paths CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...], -udo CUSTOM_OP_CONFIG_PATHS
                        [CUSTOM_OP_CONFIG_PATHS ...]
                        Path to the UDO configs (space separated, if multiple)

Quantizer Options:
    --quantization_overrides QUANTIZATION_OVERRIDES
                        Use this option to specify a json file with parameters to use for
                        quantization. These will override any quantization data carried from
                        conversion (eg TF fake quantization) or calculated during the normal
                        quantization process. Format defined as per AIMET specification.
    --keep_quant_nodes  Use this option to keep activation quantization nodes in the graph rather
                        than stripping them.

Custom Op Package Options:
    --op_package_lib OP_PACKAGE_LIB, -opl OP_PACKAGE_LIB
                        Use this argument to pass an op package library for quantization. Must be in
                        the form <op_package_lib_path:interfaceProviderName> and be separated by a
                        comma for multiple package libs
    --converter_op_package_lib CONVERTER_OP_PACKAGE_LIB, -cpl CONVERTER_OP_PACKAGE_LIB
                        Path to converter op package library compiled by the OpPackage generator.
    -p PACKAGE_NAME, --package_name PACKAGE_NAME
                        A global package name to be used for each node in the Model.cpp file.
                        Defaults to Qnn header defined package name
    --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...], -opc CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]
                        Path to a Qnn Op Package XML configuration file that contains user defined
                        custom operations.

Note: Only one of: {'package_name', 'op_package_config'} can be specified
For more information, see TFLite Model Conversion
Additional details:
input_network argument:

The converter supports a single .tflite file.

The converter –input_network option specifies the path to the .tflite file.

This argument is required.

input_dim argument:

Specifies the input dimensions of the graph’s input node(s)

The converter requires a node name along with dimensions as input from which it will create an input layer by using the node output tensor dimensions. When defining a graph, there is typically a placeholder name used as input during training in the graph. The placeholder tensor name is the name you must use as the argument. It is also possible to use other types of nodes as input, however the node used as input will not be used as part of a layer other than the input layer.

Multiple Inputs

Networks with multiple inputs must provide –input_dim INPUT_NAME INPUT_DIM, one for each input node.

This argument is optional.

output_path argument:

Specifies the output DLC file name.

This argument is optional. If not provided the converter will create a DLC file with the same name as the tflite file name, with a .dlc file extension.


qairt-converter
The qairt-converter tool converts a model from the one of Onnx/TensorFlow/TFLite/PyTorch framework to a DLC file representing the QNN graph format that can enable inference on Qualcomm AI IP/HW. The converter auto detects the framework based on the source model extension. Current ONNX Conversion supports upto ONNX Opset 21.

Basic command line usage looks like:

usage: qairt-converter [--source_model_input_shape INPUT_NAME INPUT_DIM]
                       [--out_tensor_node OUT_NAMES]
                       [--source_model_input_datatype INPUT_NAME INPUT_DTYPE]
                       [--source_model_input_layout INPUT_NAME INPUT_LAYOUT]
                       [--desired_input_layout INPUT_NAME DESIRED_INPUT_LAYOUT]
                       [--source_model_output_layout OUTPUT_NAME OUTPUT_LAYOUT]
                       [--desired_output_layout OUTPUT_NAME DESIRED_OUTPUT_LAYOUT]
                       [--desired_input_color_encoding [ ...]]
                       [--preserve_io_datatype [PRESERVE_IO_DATATYPE ...]]
                       [--dump_config_template DUMP_IO_CONFIG_TEMPLATE] [--config IO_CONFIG]
                       [--dry_run [DRY_RUN]] [--enable_framework_trace] [--gguf_config GGUF_CONFIG]
                       [--quantization_overrides QUANTIZATION_OVERRIDES]
                       [--lora_weight_list LORA_WEIGHT_LIST] [--onnx_skip_simplification]
                       [--onnx_override_batch BATCH] [--onnx_define_symbol SYMBOL_NAME VALUE]
                       [--onnx_validate_models] [--onnx_summary]
                       [--onnx_perform_sequence_construct_optimizer] [--tf_summary]
                       [--tf_override_batch BATCH] [--tf_disable_optimization]
                       [--tf_show_unconsumed_nodes] [--tf_saved_model_tag SAVED_MODEL_TAG]
                       [--tf_saved_model_signature_key SAVED_MODEL_SIGNATURE_KEY]
                       [--tf_validate_models] [--tflite_signature_name SIGNATURE_NAME]
                       [--dump_exported_onnx] --input_network INPUT_NETWORK [--debug [DEBUG]]
                       [--output_path OUTPUT_PATH] [--copyright_file COPYRIGHT_FILE]
                       [--float_bitwidth FLOAT_BITWIDTH] [--float_bias_bitwidth FLOAT_BIAS_BITWIDTH]
                       [--set_model_version MODEL_VERSION] [--export_format EXPORT_FORMAT]
                       [--converter_op_package_lib CONVERTER_OP_PACKAGE_LIB]
                       [--package_name PACKAGE_NAME | --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]]
                       [-h] [--target_backend BACKEND] [--target_soc_model SOC_MODEL]

required arguments:
  --input_network INPUT_NETWORK, -i INPUT_NETWORK
                        Path to the source framework model.

optional arguments:
  --source_model_input_shape INPUT_NAME INPUT_DIM, -s INPUT_NAME INPUT_DIM
                        The name and dimension of all the input buffers to the network specified in
                        the format [input_name comma-separated-dimensions],
                        for example: --source_model_input_shape 'data' 1,224,224,3.
                        Note that the quotes should always be included in order to handle special
                        characters, spaces, etc.
                        NOTE: Required for TensorFlow and PyTorch. Optional for Onnx and Tflite
                        In case of Onnx, this feature works only with Onnx 1.6.0 and above
  --out_tensor_node OUT_NAMES, --out_tensor_name OUT_NAMES
                        Name of the graph's output Tensor Names. Multiple output names should be
                        provided separately like:
                            --out_tensor_name out_1 --out_tensor_name out_2
                        NOTE: Required for TensorFlow. Optional for Onnx, Tflite and PyTorch
  --source_model_input_datatype INPUT_NAME INPUT_DTYPE
                        The names and datatype of the network input layers specified in the format
                        [input_name datatype], for example:
                            'data' 'float32'
                        Default is float32 if not specified
                        Note that the quotes should always be included in order to handlespecial
                        characters, spaces, etc.
                        For multiple inputs specify multiple --source_model_input_datatype on the
                        command line like:
                            --source_model_input_datatype 'data1' 'float32'
                        --source_model_input_datatype 'data2' 'float32'
  --source_model_input_layout INPUT_NAME INPUT_LAYOUT
                        Layout of each input tensor. If not specified, it will use the default based
                        on the Source Framework, shape of input and input encoding.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, HWIO, OIHW, NFC, NCF, NTF, TNF, NF, NC, F
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature,
                        T = Time, I = Input, O = Output
                        NDHWC/NCDHW used for 5d inputs
                        NHWC/NCHW used for 4d image-like inputs
                        HWIO/IOHW used for Weights of Conv Ops
                        NFC/NCF used for inputs to Conv1D or other 1D ops
                        NTF/TNF used for inputs with time steps like the ones used for LSTM op
                        NF used for 2D inputs, like the inputs to Dense/FullyConnected layers
                        NC used for 2D inputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D inputs, e.g. Bias tensor
                        For multiple inputs specify multiple --source_model_input_layout on the
                        command line.
                        Eg:
                            --source_model_input_layout "data1" NCHW --source_model_input_layout
                        "data2" NCHW
  --desired_input_layout INPUT_NAME DESIRED_INPUT_LAYOUT
                        Desired Layout of each input tensor. If not specified, it will use the
                        default based on the Source Framework, shape of input and input encoding.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, HWIO, OIHW, NFC, NCF, NTF, TNF, NF, NC, F
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature,
                        T = Time, I = Input, O = Output
                        NDHWC/NCDHW used for 5d inputs
                        NHWC/NCHW used for 4d image-like inputs
                        HWIO/IOHW used for Weights of Conv Ops
                        NFC/NCF used for inputs to Conv1D or other 1D ops
                        NTF/TNF used for inputs with time steps like the ones used for LSTM op
                        NF used for 2D inputs, like the inputs to Dense/FullyConnected layers
                        NC used for 2D inputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D inputs, e.g. Bias tensor
                        For multiple inputs specify multiple --desired_input_layout on the command
                        line.
                        Eg:
                            --desired_input_layout "data1" NCHW --desired_input_layout "data2" NCHW
  --source_model_output_layout OUTPUT_NAME OUTPUT_LAYOUT
                        Layout of each output tensor. If not specified, it will use the default
                        based on the Source Framework, shape of input and input encoding.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, HWIO, OIHW, NFC, NCF, NTF, TNF, NF, NC, F
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature, T =
                        Time
                        NDHWC/NCDHW used for 5d inputs
                        NHWC/NCHW used for 4d image-like inputs
                        NFC/NCF used for inputs to Conv1D or other 1D ops
                        NTF/TNF used for inputs with time steps like the ones used for LSTM op
                        NF used for 2D inputs, like the inputs to Dense/FullyConnected layers
                        NC used for 2D inputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D inputs, e.g. Bias tensor
                        For multiple inputs specify multiple --source_model_output_layout on the
                        command line.
                        Eg:
                            --source_model_output_layout "data1" NCHW --source_model_output_layout
                        "data2" NCHW
  --desired_output_layout OUTPUT_NAME DESIRED_OUTPUT_LAYOUT
                        Desired Layout of each output tensor. If not specified, it will use the
                        default based on the Source Framework.
                        Accepted values are-
                            NCDHW, NDHWC, NCHW, NHWC, HWIO, OIHW, NFC, NCF, NTF, TNF, NF, NC, F
                        N = Batch, C = Channels, D = Depth, H = Height, W = Width, F = Feature, T =
                        Time
                        NDHWC/NCDHW used for 5d outputs
                        NHWC/NCHW used for 4d image-like outputs
                        NFC/NCF used for outputs to Conv1D or other 1D ops
                        NTF/TNF used for outputs with time steps like the ones used for LSTM op
                        NF used for 2D outputs, like the outputs to Dense/FullyConnected layers
                        NC used for 2D outputs with 1 for batch and other for Channels (rarely used)
                        F used for 1D outputs, e.g. Bias tensor
                        For multiple outputs specify multiple --desired_output_layout on the command
                        line.
                        Eg:
                            --desired_output_layout "data1" NCHW --desired_output_layout "data2"
                        NCHW
  --desired_input_color_encoding [ ...], -e [ ...]
                        Usage:     --input_color_encoding "INPUT_NAME" INPUT_ENCODING_IN
                        [INPUT_ENCODING_OUT]
                        Input encoding of the network inputs. Default is bgr.
                        e.g.
                           --input_color_encoding "data" rgba
                        Quotes must wrap the input node name to handle special characters,
                        spaces, etc. To specify encodings for multiple inputs, invoke
                        --input_color_encoding for each one.
                        e.g.
                            --input_color_encoding "data1" rgba --input_color_encoding "data2" other
                        Optionally, an output encoding may be specified for an input node by
                        providing a second encoding. The default output encoding is bgr.
                        e.g.
                            --input_color_encoding "data3" rgba rgb
                        Input encoding types:
                            image color encodings: bgr,rgb, nv21, nv12, ...
                            time_series: for inputs of rnn models;
                            other: not available above or is unknown.
                        Supported encodings:
                           bgr
                           rgb
                           rgba
                           argb32
                           nv21
                           nv12
  --preserve_io_datatype [PRESERVE_IO_DATATYPE ...]
                        Use this option to preserve IO datatype. The different ways of using this
                        option are as follows:
                            --preserve_io_datatype <space separated list of names of inputs and
                        outputs of the graph>
                        e.g.
                            --preserve_io_datatype input1 input2 output1
                        The user may choose to preserve the datatype for all the inputs and outputs
                        of the graph.
                            --preserve_io_datatype
                        Note: --config gets higher precedence than --preserve_io_datatype.
  --dump_config_template DUMP_IO_CONFIG_TEMPLATE
                        Dumps the yaml template for I/O configuration. This file can be edited as
                        per the custom requirements and passed using the option --configUse this
                        option to specify a yaml file to which the IO config template is dumped.
  --config IO_CONFIG    Use this option to specify a yaml file for input and output options.
  --dry_run [DRY_RUN]   Evaluates the model without actually converting any ops, and returns
                        unsupported ops/attributes as well as unused inputs and/or outputs if any.
  --enable_framework_trace
                        Use this option to enable converter to trace the op/tensor change
                        information.
                        Currently framework op trace is supported only for ONNX converter.
  --gguf_config GGUF_CONFIG
                        This is an optional argument that can be used when input network is a GGUF
                        File.It specifies the path to the config file for building GenAI model.(the
                        config.json file generated when saving the huggingface model)
  --debug [DEBUG]       Run the converter in debug mode.
  --output_path OUTPUT_PATH, -o OUTPUT_PATH
                        Path where the converted Output model should be saved.If not specified, the
                        converter model will be written to a file with same name as the input model
  --copyright_file COPYRIGHT_FILE
                        Path to copyright file. If provided, the content of the file will be added
                        to the output model.
  --float_bitwidth FLOAT_BITWIDTH
                        Use the --float_bitwidth option to convert the graph to the specified float
                        bitwidth, either 32 (default) or 16.
  --float_bias_bitwidth FLOAT_BIAS_BITWIDTH
                        Use the --float_bias_bitwidth option to select the bitwidth to use for float
                        bias tensor, either 32 or 16 (default '0' if not provided).
  --set_model_version MODEL_VERSION
                        User-defined ASCII string to identify the model, only first 64 bytes will be
                        stored
  --export_format EXPORT_FORMAT
                        DLC_DEFAULT (default)
                        - Produce a Float graph given a Float Source graph
                        - Produce a Quant graph given a Quant Source graph
                        DLC_STRIP_QUANT
                        - Produce a Float Graph with discarding Quant data
  -h, --help            show this help message and exit

Custom Op Package Options:
  --converter_op_package_lib CONVERTER_OP_PACKAGE_LIB, -cpl CONVERTER_OP_PACKAGE_LIB
                        Absolute path to converter op package library compiled by the OpPackage
                        generator. Must be separated by a comma for multiple package libraries.
                        Note: Order of converter op package libraries must follow the order of xmls.
                        Ex1: --converter_op_package_lib absolute_path_to/libExample.so
                        Ex2: -cpl absolute_path_to/libExample1.so,absolute_path_to/libExample2.so
  --package_name PACKAGE_NAME, -p PACKAGE_NAME
                        A global package name to be used for each node in the Model.cpp file.
                        Defaults to Qnn header defined package name
  --op_package_config CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...], -opc CUSTOM_OP_CONFIG_PATHS [CUSTOM_OP_CONFIG_PATHS ...]
                        Path to a Qnn Op Package XML configuration file that contains user defined
                        custom operations.

Quantizer Options:
  --quantization_overrides QUANTIZATION_OVERRIDES, -q QUANTIZATION_OVERRIDES
                        Use this option to specify a json file with parameters to use for
                        quantization. These will override any quantization data carried from
                        conversion (eg TF fake quantization) or calculated during the normal
                        quantization process. Format defined as per AIMET specification.

LoRA Converter Options:
  --lora_weight_list LORA_WEIGHT_LIST
                        Path to a file specifying a list of tensor names that should be updateable.

Onnx Converter Options:
  --onnx_skip_simplification, -oss
                        Do not attempt to simplify the model automatically. This may prevent some
                        models from
                        properly converting  when sequences of unsupported static operations are
                        present.
  --onnx_override_batch BATCH
                        The batch dimension override. This will take the first dimension of all
                        inputs and treat it as a batch dim, overriding it with the value provided
                        here. For example:
                        --onnx_override_batch 6
                        will result in a shape change from [1,3,224,224] to [6,3,224,224].
                        If there are inputs without batch dim this should not be used and each input
                        should be overridden independently using -s option for input dimension
                        overrides.
  --onnx_define_symbol SYMBOL_NAME VALUE
                        This option allows overriding specific input dimension symbols. For instance
                        you might see input shapes specified with variables such as :
                        data: [1,3,height,width]
                        To override these simply pass the option as:
                        --onnx_define_symbol height 224 --onnx_define_symbol width 448
                        which results in dimensions that look like:
                        data: [1,3,224,448]
  --onnx_validate_models
                        Validate the original ONNX model against optimized ONNX model.
                        Constant inputs with all value 1s will be generated and will be used
                        by both models and their outputs are checked against each other.
                        The % average error and 90th percentile of output differences will be
                        calculated for this.
                        Note: Usage of this flag will incur extra time due to inference of the
                        models.
  --onnx_summary        Summarize the original onnx model and optimized onnx model.
                        Summary will print the model information such as number of parameters,
                        number of operators and their count, input-output tensor name, shape and
                        dtypes.
  --onnx_perform_sequence_construct_optimizer
                        This option allows optimization on SequenceConstruct Op.
                        When SequenceConstruct op is one of the outputs of the graph, it removes
                        SequenceConstruct op and makes its inputs as graph outputs to replace the
                        original output of SequenceConstruct.
  --tf_summary          Summarize the original TF model and optimized TF model.
                        Summary will print the model information such as number of parameters,
                        number of operators and their count, input-output tensor name, shape and
                        dtypes.

TensorFlow Converter Options:
  --tf_override_batch BATCH
                        The batch dimension override. This will take the first dimension of all
                        inputs and treat it as a batch dim, overriding it with the value provided
                        here. For example:
                        --tf_override_batch 6
                        will result in a shape change from [1,224,224,3] to [6,224,224,3].
                        If there are inputs without batch dim this should not be used and each input
                        should be overridden independently using -s option for input dimension
                        overrides.
  --tf_disable_optimization
                        Do not attempt to optimize the model automatically.
  --tf_show_unconsumed_nodes
                        Displays a list of unconsumed nodes, if there any are found. Nodeswhich are
                        unconsumed do not violate the structural fidelity of thegenerated graph.
  --tf_saved_model_tag SAVED_MODEL_TAG
                        Specify the tag to seletet a MetaGraph from savedmodel. ex:
                        --saved_model_tag serve. Default value will be 'serve' when it is not
                        assigned.
  --tf_saved_model_signature_key SAVED_MODEL_SIGNATURE_KEY
                        Specify signature key to select input and output of the model. ex:
                        --tf_saved_model_signature_key serving_default. Default value will be
                        'serving_default' when it is not assigned
  --tf_validate_models  Validate the original TF model against optimized TF model.
                        Constant inputs with all value 1s will be generated and will be used
                        by both models and their outputs are checked against each other.
                        The % average error and 90th percentile of output differences will be
                        calculated for this.
                        Note: Usage of this flag will incur extra time due to inference of the
                        models.

PyTorch Converter Options:
  --dump_exported_onnx  Dump the exported Onnx model from input Torchscript model

Tflite Converter Options:
  --tflite_signature_name SIGNATURE_NAME
                        Use this option to specify a specific Subgraph signature to convert

Backend Options:
  --target_backend BACKEND
                        Use this option to specify the backend on which the model needs to run.
                        Providing this option will generate a graph optimized for the given backend
                        and this graph may not run on other backends. The default backend is HTP.
                        Supported backends are CPU,GPU,DSP,HTP,HTA,LPAI.
  --target_soc_model SOC_MODEL
                        Use this option to specify the SOC on which the model needs to run.
                        This can be found from SOC info of the device and it starts with strings
                        such as SDM, SM, QCS, IPQ, SA, QC, SC, SXR, SSG, STP, QRB, or AIC.
                        NOTE: --target_backend option must be provided to use --target_soc_model
                        option.

Note: Only one of: {'package_name', 'op_package_config'} can be specified Note: Only one of:
{'package_name', 'op_package_config'} can be specified

Model Preparation
snpe-dlc-graph-prepare
snpe-dlc-graph-prepare is used to perform offline graph preparation on quantized dlcs to run on DSP/HTP runtimes.

Command Line Options:
  [ -h, --help ]        Displays this help message.
  [ --version ]         Displays version information.
  [ --verbose ]         Enable verbose user messages.
  [ --quiet ]           Disables some user messages.
  [ --silent ]          Disables all but fatal user messages.
  [ --debug=<val> ]     Sets the debug log level.
  [ --debug1 ]          Enables level 1 debug messages.
  [ --debug2 ]          Enables level 2 debug messages.
  [ --debug3 ]          Enables level 3 debug messages.
  [ --log-mask=<val> ]  Sets the debug log mask to set the log level for one or more areas.
                        Example: ".*=USER_ERROR, .*=INFO, NDK=DEBUG2, NCC=DEBUG3"
  [ --log-file=<val> ]  Overrides the default name for the debug log file.
  [ --log-dir=<val> ]   Overrides the default directory path where debug log files are written.
  [ --log-file-include-hostname ]
                        Appends the name of this host to the log file name.
  --input_dlc=<val>     Path to the dlc container containing the model for which graph cache
                        should be generated. This argument is required.
  [ --output_dlc=<val> ]
                        Path at which the cached data included model container should be written.
                        If this argument is omitted, the quantized model will be written at
                        <input_model_name>_cached.dlc.
  [ --set_output_tensors=<val> ]
                        Specifies a comma separated list of tensors to be output after execution
                        without whitespace.
  [ --set_output_layers=<val> ]
                        Specifies a comma separated list of layers whose output buffers should be
                        output after execution, without whitespace.
  [ --input_list=<val> ]
                        Path to a file specifying input images as passed to snpe-net-run. Only
                        the graph output buffers information specified in the input list (line starting
                        with # or %, if any) will be used. Paths to the input images will be ignored
  [ --htp_socs=<val> ]  Specify SoC(s) to generate HTP Offline Cache for. SoCs are specified with an
                        ASIC identifier, in a comma seperated list without whitespace.
                        For example --htp_socs sm8350,sm8450,sm8550,sm8650,qcs6490,qcs8550.
                        This flag and --htp_archs are mutually exclusive.
                        Default ASIC identifier: sm8650
  [ --htp_archs=<val> ]
                        Specify DSP Architecture(s) to generate general HTP Offline Cache for.
                        Architectures are specified with an ASIC identifier, in a comma seperated list
                        without whitespace. For example, --htp_archs v68,v73. This flag cannot be
                        coupled with --htp_socs or --vtcm_override
  [ --vtcm_override=<val> ]
                        Specify a single value representing the VTCM size in MB for the generated HTP Offline Caches.
                        For example, --vtcm_override 4. When set to 0, the SoC maximum VTCM size is used and if cache
                        compatibility mode is set to STRICT the maximum value is checked. This flag can be used with
                        --htp_socs to override the default SOC VTCM size setting
  [ --optimization_level=<val> ]
                        Specify an optimization level. Valid values are 1, 2 and 3. Default is 2. Higher optimization levels incur
                        longer offline prepare time but yields more optimal graph and hence faster execution time for most graphs
  [ --buffer_data_type=<val> ]
                        Sets data type of IO buffers during prepare. Data Type can be the following:
                        float32, fixedPoint8, fixedPoint16. Arguments should be formatted as follows:
                        --buffer_data_type buffer_name1=buffer_name1_data_type
                        --buffer_data_type buffer_name2=buffer_name2_data_type
                        (Note: deprecated)
  [ --overwrite_cache_records ]
                        Allow this tool to overwrite over any cache record that exactly matches the requested SoC(s).
                        Default behavior is to skip (re)generating cache records when a matching cache already exists
  [ --use_float_io ]    Prepare quantized HTP Graph to operate with floating point inputs/outputs (Note: deprecated)
  [ --htp_dlbc=<val> ]  Specify Deep Learning Bandwidth Compression (DLBC) for this HTP graph. The default setting is OFF.
                        To turn on, specify it as --htp_dlbc=true
  [ --num_hvx_threads=<val> ]
                        Specify the number of HVX threads to reserve for this HTP graph. Must be greater than 0.
  [ --input_name=<val> ]
                        Specifies the name of input for which dimensions are specified.
  [ --input_dimensions=<val> ]
                        Specifies new dimensions for input whose name is specified in input_name. e.g. "1,224,224,3".
                        For multiple inputs, specify --input_name and --input_dimensions multiple times.
  [ --memorymapped_buffer_hint=<val> ]
                        Specifies memory-mapped buffers hint. The default setting is OFF.
                        To turn on, specify it as --memorymapped_buffer_hint=true
  [ --udo_package_path=<val> ]
                        Use this option to specify path to the Registration Library for UDO Package(s). Usage is:
                        --udo_package_path=<path_to_reg_lib>
                        Optionally, user can provide multiple packages as a comma-separated list.
                        This option must be specified for Networks with UDO. All UDO's in Network must have host executable CPU Implementation
For detailed information on how to use the tool, please refer to Offline Graph Caching for DSP Runtime on HTP

snpe-dlc-quant
snpe-dlc-quant converts non-quantized DLC models into quantized DLC models.

Command Line Options:
    [ -h,--help ]         Displays this help message.
    [ --version ]         Displays version information.
    [ --verbose ]         Enable verbose user messages.
    [ --quiet ]           Disables some user messages.
    [ --silent ]          Disables all but fatal user messages.
    [ --debug=<val> ]     Sets the debug log level.
    [ --debug1 ]          Enables level 1 debug messages.
    [ --debug2 ]          Enables level 2 debug messages.
    [ --debug3 ]          Enables level 3 debug messages.
    [ --log-mask=<val> ]  Sets the debug log mask to set the log level for one or more areas.
                        Example: ".*=USER_ERROR, .*=INFO, NDK=DEBUG2, NCC=DEBUG3"
    [ --log-file=<val> ]  Overrides the default name for the debug log file.
    [ --log-dir=<val> ]   Overrides the default directory path where debug log files are written.
    [ --log-file-include-hostname ]
                        Appends the name of this host to the log file name.
    [ --input_dlc=<val> ]
                        Path to the dlc container containing the model for which fixed-point encoding
                        metadata should be generated. This argument is required.
    [ --input_list=<val> ]
                        Path to a file specifying the trial inputs. This file should be a plain text file,
                        containing one or more absolute file paths per line. These files will be taken to constitute
                        the trial set. Each path is expected to point to a binary file containing one trial input
                        in the 'raw' format, ready to be consumed by the tool without any further modifications.
                        This is similar to how input is provided to snpe-net-run application.
    [ --no_weight_quantization ]
                        Note: Deprecated.
    [ --output_dlc=<val> ]
                        Path at which the metadata-included quantized model container should be written.
                        If this argument is omitted, the quantized model will be written at <unquantized_model_name>_quantized.dlc.
    [ --use_enhanced_quantizer ]
                        Note: Deprecated; use --param_quantizer and/or --act_quantizer.
                        Use the enhanced quantizer feature when quantizing the model.  Regular quantization determines the range using the actual
                        values of min and max of the data being quantized.  Enhanced quantization uses an algorithm to determine optimal range.  It can be
                        useful for quantizing models that have long tails in the distribution of the data being quantized.
    [ --use_adjusted_weights_quantizer ]
                        Note: Deprecated; use --param_quantizer.
                        Use the adjusted tf quantizer for quantizing the weights only. This might be helpful for improving the accuracy of some models,
                        such as denoise model as being tested. This option is only used when quantizing the weights with 8 bit.
    [ --optimizations=<val> ]
                        Note: Deprecated; use --algorithms.
                        Enables new optimization algorithms. Usage is:
                            --optimizations <algo_name1> --optimizations <algo_name2>
                        Available optimization algorithms are:
                        "cle" - Cross layer equalization includes a number of methods for equalizing weights
                        and biases across layers in order to rectify imbalances that cause quantization errors.
    [ --algorithms=<val> ]
                        Enables new optimization algorithms. Usage is:
                            --algorithms <algo_name1> --algorithms <algo_name2>
                        Available optimization algorithms are:
                        "cle" - Cross layer equalization includes a number of methods for equalizing weights
                        and biases across layers in order to rectify imbalances that cause quantization errors.
    [ --override_params ]
                        Use this option to override quantization parameters when quantization was provided from the original source framework (eg TF fake quantization).
                        Note: Quantizer throws an error if overridden encodings contain unsupported bitwidths.
    [ --use_encoding_optimizations ]
                        Note: Deprecated.
    [ --udo_package_path=<val> ]
                        Specifies the path to the registration library for UDO package(s). Usage is:
                            --udo_package_path=<path_to_reg_lib>
                        You can (optionally) provide multiple packages as a comma-separated list.
                        This option must be specified for networks with UDO. All UDO's in a network must have a host-executable CPU implementation.
    [ --use_symmetric_quantize_weights ]
                        Note: Deprecated, use --param_quantizer.
                        Use the symmetric quantizer feature when quantizing the weights of the model. It makes sure min and max have the
                        same absolute values about zero. Symmetrically quantized data will also be stored as int#_t data such that the offset is always 0.
    [ --use_native_dtype ]
                        Note: This option is deprecated, use --use_native_input_files option in future.
                          Use this option to indicate how to read input files,
                           1. float (default): reads inputs as floats and quantizes if necessary based on quantization parameters in the model.
                           2. native:          reads inputs assuming the data type to be native to the model. For ex., uint8_t.
    [ --use_native_input_files ]
                        Use this option to indicate how to read input files,
                           1. float (default): reads inputs as floats and quantizes if necessary based on quantization parameters in the model.
                           2. native:          reads inputs assuming the data type to be native to the model. For ex., uint8_t.
    [ --use_native_output_files ]
                        Use this option to indicate the data type of the output files,
                           1. float (default): generates the output file as float data.
                           2. native:          generates the output file as datatype native to the source model. i.e. uint8_t.
    [ --float_fallback ]
                        Enables fallback to floating point (FP) instead of fixed point.
                        This option can be paired with --float_bitwidth to indicate the bitwidth for FP (by default 32).
                        If this option is enabled, then input list must not be provided and --override_params must be provided.
                        The external quantization file (encoding file) might be missing quantization parameters for some interim tensors.
                        First it will try to fill the gaps by propagating across math-invariant functions. If the quantization parameters are
                        still missing, it applies fallback to nodes to floating point
    [ --param_quantizer=<val> ]
                        Indicates the weight/bias quantizer to use. Optional and must be followed by one of the following options:
                        "tf": Uses the real min/max of the data and specified bitwidth (default).
                        "enhanced": Uses an algorithm useful for quantizing models with long tails present in the weight distribution.
                        "symmetric": Ensures min and max have the same absolute values about zero. Data will be stored as int#_t data such that the offset is always 0.
    [ --act_quantizer=<val> ]
                        Indicates the activation quantizer to use. Optional and must be followed by one of the following options:
                        "tf": Uses the real min/max of the data and specified bitwidth (default).
                        "enhanced": Uses an algorithm useful for quantizing models with long tails present in the weight distribution.
                        "symmetric": Ensures min and max have the same absolute values about zero. Data will be stored as int#_t data such that the offset is always 0.
    [ --bitwidth=<val> ]
                        Note: Deprecated.
                        Selects the bitwidth to use when quantizing the weights/activations/biases; 8 (default) or 16.
                        Cannot be mixed with --weights_bitwidth or --act_bitwidth or --bias_bitwidth.
    [ --weights_bitwidth=<val> ]
                        Selects the bitwidth to use when quantizing the weights; either 4, 8 (default) or 16.
                        8w/16a is only supported by HTA currently.
                        Cannot be mixed with --bitwidth.
    [ --act_bitwidth=<val> ]
                        Selects the bitwidth to use when quantizing the activations; either 8 (default) or 16.
                        8w/16a is only supported by HTA currently.
                        Cannot be mixed with --bitwidth.
    [ --float_bitwidth=<val> ]
                        Selects the bitwidth to use when using float for parameters (weights/biases) and activations for
                        all ops or a specific op (via encodings) selected through encoding; either 32 (default) or 16.
    [ --bias_bitwidth=<val> ]
                        Selects the bitwidth to use when quantizing the biases; either 8 (default) or 32.
                        Using 32-bit biases may sometimes provide a small improvement in accuracy.
                        Cannot be mixed with --bitwidth.
    [ --float_bias_bitwidth=<val> ]
                        Specifies the bitwidth for float bias tensors; either 32 or 16.
                        If not provided and bias is overridden to float in the quantizer, the overriding float tensor's bitwidth will be used.
    [ --axis_quant ]    Note: Deprecated; use --use_per_channel_quantization.
                        Selects per-axis-element quantization for the weights and biases of certain layer types.
                        Only Convolution, Deconvolution, and FullyConnected are supported.
    [ --use_per_channel_quantization ]
                        Selects per-axis-element quantization for the weights and biases of certain layer types.
                        Only Convolution, Deconvolution, and FullyConnected are supported.
    [ --use_per_row_quantization ]
                        Enables row wise quantization of Matmul and FullyConnected ops.
    [ --enable_per_row_quantized_bias ]
                        Enables row wise quantization of bias for FullyConnected op, when weights are per-row quantized.
    [ --restrict_quantization_steps=<val> ]
                        Specifies the number of steps to use for computing quantization encodings such that scale = (max - min) / number of quantization steps.
                        The option should be passed as a comma separated pair of hexadecimal string minimum and maximum values,
                        i.e., --restrict_quantization_steps "MIN,MAX".
                        Note that this is a hexadecimal string literal and not a signed integer, to supply a negative value an explicit minus sign is required,
                        e.g., --restrict_quantization_steps "-0x80,0x7F" indicates an example 8-bit range.
                              --restrict_quantization_steps "-0x8000,0x7F7F" indicates an example 16-bit range.
                        This option only applies to symmetric parameter quantization.


Description:
Generate 8 or 16 bit TensorFlow style fixed point weight and activations encodings for a floating point DLC.

Additional details:
For specifying input_list, refer to input_list argument in snpe-net-run for supported input formats (in order to calculate output activation encoding information for all layers, do not include the line which specifies desired outputs).

The tool requires the batch dimension of the DLC input file to be set to 1 during the original model conversion step.

An example of quantization using snpe-dlc-quant can be found in the C/C++ Tutorial section: Running the Inception v3 Model. For details on quantization see Quantized vs Non-Quantized Models.

Outputs can be specified for snpe-dlc-quant by modifying the input_list in the following ways:

#<output_layer_name>[<space><output_layer_name>]
%<output_tensor_name>[<space><output_tensor_name>]
<input_layer_name>:=<input_layer_path>[<space><input_layer_name>:=<input_layer_path>]
…
Note: Output tensors and layers can be specified individually, but when specifying both, the order shown must be used to specify each.

When using the Qualcomm® Neural Processing SDK API:

Any output layers specified when snpe-dlc-quant was called, need to be specified using the Snpe_SNPEBuilder_SetOutputLayers() function.

Any output tensors specified when snpe-dlc-quant was called, need to be specified using the Snpe_SNPEBuilder_SetOutputTensors() function.


snpe-dlc-quantize
snpe-dlc-quantize converts non-quantized DLC models into quantized DLC models.

Command Line Options:
    [ -h,--help ]         Displays this help message.
    [ --version ]         Displays version information.
    [ --verbose ]         Enable verbose user messages.
    [ --quiet ]           Disables some user messages.
    [ --silent ]          Disables all but fatal user messages.
    [ --debug=<val> ]     Sets the debug log level.
    [ --debug1 ]          Enables level 1 debug messages.
    [ --debug2 ]          Enables level 2 debug messages.
    [ --debug3 ]          Enables level 3 debug messages.
    [ --log-mask=<val> ]  Sets the debug log mask to set the log level for one or more areas.
                        Example: ".*=USER_ERROR, .*=INFO, NDK=DEBUG2, NCC=DEBUG3"
    [ --log-file=<val> ]  Overrides the default name for the debug log file.
    [ --log-dir=<val> ]   Overrides the default directory path where debug log files are written.
    [ --log-file-include-hostname ]
                        Appends the name of this host to the log file name.
    [ --input_dlc=<val> ]
                        Path to the dlc container containing the model for which fixed-point encoding
                        metadata should be generated. This argument is required.
    [ --input_list=<val> ]
                        Path to a file specifying the trial inputs. This file should be a plain text file,
                        containing one or more absolute file paths per line. These files will be taken to constitute
                        the trial set. Each path is expected to point to a binary file containing one trial input
                        in the 'raw' format, ready to be consumed by the tool without any further modifications.
                        This is similar to how input is provided to snpe-net-run application.
    [ --no_weight_quantization ]
                        Note: Deprecated.
    [ --output_dlc=<val> ]
                        Path at which the metadata-included quantized model container should be written.
                        If this argument is omitted, the quantized model will be written at <unquantized_model_name>_quantized.dlc.
    [ --enable_htp ]      Pack HTP information in quantized DLC.
    [ --htp_socs=<val> ]  Specify SoC to generate HTP Offline Cache for.
                        SoCs are specified with an ASIC identifier, in a comma separated list.
                        For example, --htp_socs sm8650
    [ --overwrite_cache_records ]
                        Overwrite HTP cache records present in the DLC.
    [ --use_float_io ]
                        Pack HTP information in quantized DLC (Note: deprecated).
    [ --use_enhanced_quantizer ]
                        Note: Deprecated; use --param_quantizer and/or --act_quantizer.
                        Use the enhanced quantizer feature when quantizing the model.  Regular quantization determines the range using the actual
                        values of min and max of the data being quantized.  Enhanced quantization uses an algorithm to determine optimal range.  It can be
                        useful for quantizing models that have long tails in the distribution of the data being quantized.
    [ --use_adjusted_weights_quantizer ]
                        Note: Deprecated; use --param_quantizer.
                        Use the adjusted tf quantizer for quantizing the weights only. This might be helpful for improving the accuracy of some models,
                        such as denoise model as being tested. This option is only used when quantizing the weights with 8 bit.

    [ --optimizations=<val> ]
                        Note: Deprecated; use --algorithms.
                        Enables new optimization algorithms. Usage is:
                            --optimizations <algo_name1> --optimizations <algo_name2>
                        Available optimization algorithms are:
                        "cle" - Cross layer equalization includes a number of methods for equalizing weights
                        and biases across layers in order to rectify imbalances that cause quantization errors.
    [ --algorithms=<val> ]
                        Enables new optimization algorithms. Usage is:
                            --algorithms <algo_name1> --algorithms <algo_name2>
                        Available optimization algorithms are:
                        "cle" - Cross layer equalization includes a number of methods for equalizing weights
                        and biases across layers in order to rectify imbalances that cause quantization errors.
    [ --override_params ]
                        Use this option to override quantization parameters when quantization was provided from the original source framework (eg TF fake quantization).
                        Note: Quantizer throws an error if overridden encodings contain unsupported bitwidths.
    [ --use_encoding_optimizations ]
                        Note: Deprecated.
                        Use this option to enable quantization encoding optimizations. This can reduce requantization in the graph and may improve accuracy for some models.
    [ --udo_package_path=<val> ]
                        Specifies the path to the registration library for UDO package(s). Usage is:
                            --udo_package_path=<path_to_reg_lib>
                        You can (optionally) provide multiple packages as a comma-separated list.
                        This option must be specified for networks with UDO. All UDO's in a network must have a host-executable CPU implementation.
    [ --use_symmetric_quantize_weights ]
                        Note: Deprecated, use --param_quantizer.
                        Use the symmetric quantizer feature when quantizing the weights of the model. It makes sure min and max have the
                        same absolute values about zero. Symmetrically quantized data will also be stored as int#_t data such that the offset is always 0.
    [ --use_native_dtype ]
                        Note: This option is deprecated, use --use_native_input_files option in future.
                          Use this option to indicate how to read input files,
                           1. float (default): reads inputs as floats and quantizes if necessary based on quantization parameters in the model.
                           2. native:          reads inputs assuming the data type to be native to the model. For ex., uint8_t.
    [ --use_native_input_files ]
                        Use this option to indicate how to read input files,
                           1. float (default): reads inputs as floats and quantizes if necessary based on quantization parameters in the model.
                           2. native:          reads inputs assuming the data type to be native to the model. For ex., uint8_t.
    [ --use_native_output_files ]
                        Use this option to indicate the data type of the output files,
                           1. float (default): generates the output file as float data.
                           2. native:          generates the output file as datatype native to the source model. i.e. uint8_t.
    [ --float_fallback ]
                        Enables fallback to floating point (FP) instead of fixed point.
                        This option can be paired with --float_bitwidth to indicate the bitwidth for FP (by default 32).
                        If this option is enabled, then input list must not be provided and --override_params must be provided.
                        The external quantization file (encoding file) might be missing quantization parameters for some interim tensors.
                        First it will try to fill the gaps by propagating across math-invariant functions. If the quantization parameters are
                        still missing, it applies fallback to nodes to floating point
    [ --param_quantizer=<val> ]
                        Indicates the weight/bias quantizer to use. Optional and must be followed by one of the following options:
                        "tf": Uses the real min/max of the data and specified bitwidth (default).
                        "enhanced": Uses an algorithm useful for quantizing models with long tails present in the weight distribution.
                        "symmetric": Ensures min and max have the same absolute values about zero. Data will be stored as int#_t data such that the offset is always 0.
    [ --act_quantizer=<val> ]
                        Indicates the activation quantizer to use. Optional and must be followed by one of the following options:
                        "tf": Uses the real min/max of the data and specified bitwidth (default).
                        "enhanced": Uses an algorithm useful for quantizing models with long tails present in the weight distribution.
                        "symmetric": Ensures min and max have the same absolute values about zero. Data will be stored as int#_t data such that the offset is always 0.
    [ --bitwidth=<val> ]
                        Note: Deprecated.
                        Selects the bitwidth to use when quantizing the weights/activations/biases; 8 (default) or 16.
                        Cannot be mixed with --weights_bitwidth or --act_bitwidth or --bias_bitwidth.
    [ --weights_bitwidth=<val> ]
                        Selects the bitwidth to use when quantizing the weights; either 4, 8 (default) or 16.
                        8w/16a is only supported by HTA currently.
                        Cannot be mixed with --bitwidth.
    [ --act_bitwidth=<val> ]
                        Selects the bitwidth to use when quantizing the activations; either 8 (default) or 16.
                        8w/16a is only supported by HTA currently.
                        Cannot be mixed with --bitwidth.
    [ --float_bitwidth=<val> ]
                        Selects the bitwidth to use when using float for parameters (weights/biases) and activations for
                        all ops or a specific op (via encodings) selected through encoding; either 32 (default) or 16.
    [ --bias_bitwidth=<val> ]
                        Selects the bitwidth to use when quantizing the biases; either 8 (default) or 32.
                        Using 32-bit biases may sometimes provide a small improvement in accuracy.
                        Cannot be mixed with --bitwidth.
    [ --float_bias_bitwidth=<val> ]
                        Specifies the bitwidth for float bias tensors; either 32 or 16.
                        If not provided and bias is overridden to float in the quantizer, the overriding float tensor's bitwidth will be used.
    [ --axis_quant ]    Note: Deprecated; use --use_per_channel_quantization.
                        Selects per-axis-element quantization for the weights and biases of certain layer types.
                        Only Convolution, Deconvolution, and FullyConnected are supported.
    [ --use_per_channel_quantization ]
                        Selects per-axis-element quantization for the weights and biases of certain layer types.
                        Only Convolution, Deconvolution, and FullyConnected are supported.
    [ --use_per_row_quantization ]
                        Enables row wise quantization of Matmul and FullyConnected ops.
    [ --enable_per_row_quantized_bias ]
                        Enables row wise quantization of bias for FullyConnected op, when weights are per-row quantized.
    [ --restrict_quantization_steps=<val> ]
                        Specifies the number of steps to use for computing quantization encodings such that scale = (max - min) / number of quantization steps.
                        The option should be passed as a comma separated pair of hexadecimal string minimum and maximum values,
                        i.e., --restrict_quantization_steps "MIN,MAX".
                        Note that this is a hexadecimal string literal and not a signed integer, to supply a negative value an explicit minus sign is required,
                        e.g., --restrict_quantization_steps "-0x80,0x7F" indicates an example 8-bit range.
                              --restrict_quantization_steps "-0x8000,0x7F7F" indicates an example 16-bit range.
                        This option only applies to symmetric parameter quantization.


Description:
Generate 8 or 16 bit TensorFlow style fixed point weight and activations encodings for a floating point DLC model.

Additional details:
For specifying input_list, refer to input_list argument in snpe-net-run for supported input formats (in order to calculate output activation encoding information for all layers, do not include the line which specifies desired outputs).

The tool requires the batch dimension of the DLC input file to be set to 1 during the original model conversion step.

An example of quantization using snpe-dlc-quantize can be found in the C/C++ Tutorial section: Running the Inception v3 Model. For details on quantization see Quantized vs Non-Quantized Models.

Using snpe-dlc-quantize is mandatory for running on HTA.

Using snpe-dlc-quantize is mandatory for running on DSP runtime on Snapdragon 865. It is recommended that offline cache generation be used. It is specified by using –enable_htp option for snpe-dlc-quantize.

When using offline cache generation for HTP, the same input(s) tensors or layers and output(s) tensors or layers should be specified when using snpe-dlc-quantize and to run inference on the model using Qualcomm® Neural Processing SDK APIs or snpe-net-run. Not doing so will cause the cache to be invalidated, and graph initialization will take longer.

Outputs can be specified for snpe-dlc-quantize by modifying the input_list in the following ways:

#<output_layer_name>[<space><output_layer_name>]
%<output_tensor_name>[<space><output_tensor_name>]
<input_layer_name>:=<input_layer_path>[<space><input_layer_name>:=<input_layer_path>]
…
Note: Output tensors and layers can be specified individually, but when specifying both, the order shown must be used to specify each.

When running a model with an offline generated cache using snpe-net-run:

Any output layers specified when snpe-dlc-quantize was called, need to be specified using the input list as shown in the input_list argument to snpe-net-run.

Any output tensors specified when snpe-dlc-quantized was called, need to be specified using the –set_output_tensors argument to snpe-net-run. Refer to snpe-net-run for documentation.

When using the Qualcomm® Neural Processing SDK API:

Any output layers specified when snpe-dlc-quantize was called, need to be specified using the Snpe_SNPEBuilder_SetOutputLayers() function.

Any output tensors specified when snpe-dlc-quantize was called, need to be specified using the Snpe_SNPEBuilder_SetOutputTensors() function.


snpe-udo-package-generator
DESCRIPTION:
------------
This tool generates a UDO (User Defined Operation) package using a
user provided config file.

USAGE:
------------
snpe-udo-package-generator [-h] --config_path CONFIG_PATH [--debug]
                                    [--output_path OUTPUT_PATH] [-f]
OPTIONAL ARGUMENTS:
-------------------
    -h, --help            show this help message and exit
    --debug               Returns debugging information from generating the package
    --output_path OUTPUT_PATH, -o OUTPUT_PATH
                        Path where the package should be saved
    -f, --force-generation
                        This option will delete the existing package
                        Note appropriate file permissions must be set to use
                        this option.

REQUIRED_ARGUMENTS:
-------------------
    --config_path CONFIG_PATH, -p CONFIG_PATH
                        The path to a config file that defines a UDO.
qairt-quantizer
The qairt-quantizer tool converts non-quantized DLC models into quantized DLC models.

Basic command line usage looks like:

usage: qairt-quantizer --input_dlc INPUT_DLC [--output_dlc OUTPUT_DLC] [--input_list INPUT_LIST]
                       [--enable_float_fallback] [--apply_algorithms ALGORITHMS [ALGORITHMS ...]]
                       [--bias_bitwidth BIAS_BITWIDTH] [--act_bitwidth ACT_BITWIDTH]
                       [--weights_bitwidth WEIGHTS_BITWIDTH] [--float_bitwidth FLOAT_BITWIDTH]
                       [--float_bias_bitwidth FLOAT_BIAS_BITWIDTH] [--ignore_quantization_overrides]
                       [--use_per_channel_quantization] [--use_per_row_quantization]
                       [--enable_per_row_quantized_bias]
                       [--preserve_io_datatype [PRESERVE_IO_DATATYPE ...]]
                       [--use_native_input_files] [--use_native_output_files]
                       [--restrict_quantization_steps ENCODING_MIN, ENCODING_MAX]
                       [--keep_weights_quantized] [--adjust_bias_encoding]
                       [--act_quantizer_calibration ACT_QUANTIZER_CALIBRATION]
                       [--param_quantizer_calibration PARAM_QUANTIZER_CALIBRATION]
                       [--act_quantizer_schema ACT_QUANTIZER_SCHEMA]
                       [--param_quantizer_schema PARAM_QUANTIZER_SCHEMA]
                       [--percentile_calibration_value PERCENTILE_CALIBRATION_VALUE]
                       [--use_aimet_quantizer] [--op_package_lib OP_PACKAGE_LIB]
                       [--dump_encoding_json] [--config CONFIG_FILE] [--export_stripped_dlc] [-h]
                       [--target_backend BACKEND] [--target_soc_model SOC_MODEL] [--debug [DEBUG]]

required arguments:
  --input_dlc INPUT_DLC, -i INPUT_DLC
                        Path to the dlc container containing the model for which fixed-point
                        encoding metadata should be generated. This argument is required

optional arguments:
  --output_dlc OUTPUT_DLC, -o OUTPUT_DLC
                        Path at which the metadata-included quantized model container should be
                        written.If this argument is omitted, the quantized model will be written at
                        <unquantized_model_name>_quantized.dlc
  --input_list INPUT_LIST, -l INPUT_LIST
                        Path to a file specifying the input data. This file should be a plain text
                        file, containing one or more absolute file paths per line. Each path is
                        expected to point to a binary file containing one input in the "raw" format,
                        ready to be consumed by the quantizer without any further preprocessing.
                        Multiple files per line separated by spaces indicate multiple inputs to the
                        network. See documentation for more details. Must be specified for
                        quantization. All subsequent quantization options are ignored when this is
                        not provided.
  --enable_float_fallback, -f
                        Use this option to enable fallback to floating point (FP) instead of fixed
                        point.
                        This option can be paired with --float_bitwidth to indicate the bitwidth for
                        FP (by default 32).
                        If this option is enabled, then input list must not be provided and
                        --ignore_quantization_overrides must not be provided.
                        The external quantization encodings (encoding file/FakeQuant encodings)
                        might be missing quantization parameters for some interim tensors.
                        First it will try to fill the gaps by propagating across math-invariant
                        functions. If the quantization params are still missing,
                        then it will apply fallback to nodes to floating point.
  --apply_algorithms ALGORITHMS [ALGORITHMS ...]
                        Use this option to enable new optimization algorithms. Usage is:
                        --apply_algorithms <algo_name1> ... The available optimization algorithms
                        are: "cle" - Cross layer equalization includes a number of methods for
                        equalizing weights and biases across layers in order to rectify imbalances
                        that cause quantization errors.
  --bias_bitwidth BIAS_BITWIDTH
                        Use the --bias_bitwidth option to select the bitwidth to use when quantizing
                        the biases, either 8 (default) or 32.
  --act_bitwidth ACT_BITWIDTH
                        Use the --act_bitwidth option to select the bitwidth to use when quantizing
                        the activations, either 8 (default) or 16.
  --weights_bitwidth WEIGHTS_BITWIDTH
                        Use the --weights_bitwidth option to select the bitwidth to use when
                        quantizing the weights, either 4, 8 (default) or 16.
  --float_bitwidth FLOAT_BITWIDTH
                        Use the --float_bitwidth option to select the bitwidth to use for float
                        tensors,either 32 (default) or 16.
  --float_bias_bitwidth FLOAT_BIAS_BITWIDTH
                        Use the --float_bias_bitwidth option to select the bitwidth to use when
                        biases are in float, either 32 or 16 (default '0' if not provided).
  --ignore_quantization_overrides
                        Use only quantizer generated encodings, ignoring any user or model provided
                        encodings.
                        Note: Cannot use --ignore_quantization_overrides with
                        --quantization_overrides (argument of Qairt Converter)
  --use_per_channel_quantization
                        Use this option to enable per-channel quantization for convolution-based op
                        weights.
                        Note: This will only be used if built-in model Quantization-Aware Trained
                        (QAT) encodings are not present for a given weight.
  --use_per_row_quantization
                        Use this option to enable rowwise quantization of Matmul and FullyConnected
                        ops.
  --enable_per_row_quantized_bias
                        Enables row wise quantization of bias for FullyConnected op,
                        when weights are per-row quantized.
  --preserve_io_datatype [PRESERVE_IO_DATATYPE ...]
                        Use this option to preserve IO datatype. The different ways of using this
                        option are as follows:
                            --preserve_io_datatype <space separated list of names of inputs and
                        outputs of the graph>
                        e.g.
                           --preserve_io_datatype input1 input2 output1
                        The user may choose to preserve the datatype for all the inputs and outputs
                        of the graph.
                            --preserve_io_datatype
  --use_native_input_files
                        Boolean flag to indicate how to read input files.
                        If not provided, reads inputs as floats and quantizes if necessary based on
                        quantization parameters in the model. (default)
                        If provided, reads inputs assuming the data type to be native to the model.
                        For ex., uint8_t.
  --use_native_output_files
                        Boolean flag to indicate the data type of the output files
                        If not provided, outputs the file as floats. (default)
                        If provided, outputs the file that is native to the model. For ex., uint8_t.
  --restrict_quantization_steps ENCODING_MIN, ENCODING_MAX
                        Specifies the number of steps to use for computing quantization encodings
                        such that scale = (max - min) / number of quantization steps.
                        The option should be passed as a space separated pair of hexadecimal string
                        minimum and maximum valuesi.e. --restrict_quantization_steps "MIN MAX".
                        Please note that this is a hexadecimal string literal and not a signed
                        integer, to supply a negative value an explicit minus sign is required.
                        E.g.--restrict_quantization_steps "-0x80 0x7F" indicates an example 8 bit
                        range,
                            --restrict_quantization_steps "-0x8000 0x7F7F" indicates an example 16
                        bit range.
                        This argument is required for 16-bit Matmul operations.
  --keep_weights_quantized
                        Use this option to keep the weights quantized even when the output of the op
                        is in floating point. Bias will be converted to floating point as per the
                        output of the op. Required to enable wFxp_actFP configurations according to
                        the provided bitwidth for weights and activations
                        Note: These modes are not supported by all runtimes. Please check
                        corresponding Backend OpDef supplement if these are supported
  --adjust_bias_encoding
                        Use --adjust_bias_encoding option to modify bias encoding and weight
                        encoding to ensure that the bias value is in the range of the bias encoding.
                        This option is only applicable for per-channel quantized weights.
                        NOTE: This may result in clipping of the weight values
  --act_quantizer_calibration ACT_QUANTIZER_CALIBRATION
                        Specify which quantization calibration method to use for activations
                        supported values: min-max (default), sqnr, entropy, mse, percentile
                        This option can be paired with --act_quantizer_schema to override the
                        quantization
                        schema to use for activations otherwise default schema(asymmetric) will be
                        used
  --param_quantizer_calibration PARAM_QUANTIZER_CALIBRATION
                        Specify which quantization calibration method to use for parameters
                        supported values: min-max (default), sqnr, entropy, mse, percentile
                        This option can be paired with --param_quantizer_schema to override the
                        quantization
                        schema to use for parameters otherwise default schema(asymmetric) will be
                        used
  --act_quantizer_schema ACT_QUANTIZER_SCHEMA
                        Specify which quantization schema to use for activations
                        supported values: asymmetric (default), symmetric, unsignedsymmetric
  --param_quantizer_schema PARAM_QUANTIZER_SCHEMA
                        Specify which quantization schema to use for parameters
                        supported values: asymmetric (default), symmetric, unsignedsymmetric
  --percentile_calibration_value PERCENTILE_CALIBRATION_VALUE
                        Specify the percentile value to be used with Percentile calibration method
                        The specified float value must lie within 90 and 100, default: 99.99
  --use_aimet_quantizer
                        Use AIMET for Quantization instead of QNN IR quantizer
  --op_package_lib OP_PACKAGE_LIB, -opl OP_PACKAGE_LIB
                        Use this argument to pass an op package library for quantization. Must be in
                        the form <op_package_lib_path:interfaceProviderName> and be separated by a
                        comma for multiple package libs
  --dump_encoding_json  Use this argument to dump encoding of all the tensors in a json file
  --config CONFIG_FILE, -c CONFIG_FILE
                        Use this argument to pass the path of the config YAML file with quantizer
                        options
  --export_stripped_dlc
                        Use this argument to export a DLC which strips out data not needed for graph
                        composition
  -h, --help            show this help message and exit
  --debug [DEBUG]       Run the quantizer in debug mode.

Backend Options:
  --target_backend BACKEND
                        Use this option to specify the backend on which the model needs to run.
                        Providing this option will generate a graph optimized for the given backend
                        and this graph may not run on other backends. The default backend is HTP.
                        Supported backends are CPU,GPU,DSP,HTP,HTA,LPAI.
  --target_soc_model SOC_MODEL
                        Use this option to specify the SOC on which the model needs to run.
                        This can be found from SOC info of the device and it starts with strings
                        such as SDM, SM, QCS, IPQ, SA, QC, SC, SXR, SSG, STP, QRB, or AIC.
                        NOTE: --target_backend option must be provided to use --target_soc_model
                        option.
Execution
snpe-net-run
snpe-net-run loads a DLC file, loads the data for the input tensor(s), and executes the network on the specified runtime.

DESCRIPTION:
------------
Tool that loads and executes a neural network using the SDK API.


REQUIRED ARGUMENTS:
-------------------
--container  <FILE> Path to the DL container containing the network.
--input_list <FILE> Path to a file listing the inputs for the network.
                    Optionally the file can have "#" starting line to specify the layer names or "%" to specify the output tensor
                    names for which output tensor files are to be produced. For more details about the input_list file format,
                    please refer to SDK html documentation (docs/general/tools.html#snpe-net-run input_list argument).


OPTIONAL ARGUMENTS:
-------------------
--use_gpu           Use the GPU runtime for SNPE. Default float32 math and float16 storage.
--use_dsp           Use the DSP fixed point runtime for SNPE. Data & Math: 8bit fixed point Tensorflow style format.
--use_aip           Use the AIP fixed point runtime for SNPE. Data & Math: 8bit fixed point Tensorflow style format.
--debug             Specifies that output from all layers of the network
                    will be saved.
--output_dir=<val>
                    The directory to save output to. Defaults to ./output
--storage_dir=<val>
                    The directory to store metadata files
--encoding_type=<val>
                    Specifies the encoding type of input file. Valid settings are "nv21".
                    Cannot be combined with --userbuffer*.
--use_native_input_files
                    Specifies to consume the input file(s) in their native data type(s).
                    Must be used with --userbuffer_xxx.
--use_native_output_files
                    Specifies to write the output file(s) in their native data type(s).
                    Must be used with --userbuffer_xxx.
--userbuffer_auto
                    Specifies to use userbuffer for input and output, with auto detection of types enabled.
                    Must be used with user specified buffer. Cannot be combined with --encoding_type.
--userbuffer_float
                    Specifies to use userbuffer for inference, and the input type is float.
                    Cannot be combined with --encoding_type.
--userbuffer_floatN=<val>
                    Specifies to use userbuffer for inference, and the input type is float 16 or float 32.
                    Cannot be combined with --encoding_type.
--userbuffer_tf8      Specifies to use userbuffer for inference, and the input type is tf8exact0.
                    Cannot be combined with --encoding_type.
--userbuffer_tfN=<val>
                    Overrides the userbuffer output used for inference, and the output type is tf8exact0 or tf16exact0.
                    Must be used with user specified buffer.
--userbuffer_float_output
                    Overrides the userbuffer output used for inference, and the output type is float. Must be used with user
                    specified buffer.
--userbuffer_floatN_output=<val>
                    Overrides the userbuffer output used for inference, and the output type is float 16 or float 32. Must be used with user
                    specified buffer.
--userbuffer_tfN_output=<val>
                    Overrides the userbuffer output used for inference, and the output type is tf8exact0 or tf16exact0.
                    Must be used with user specified buffer.
--userbuffer_tf8_output
                    Overrides the userbuffer output used for inference, and the output type is tf8exact0.
--userbuffer_uintN_output=<val>
                    Overrides the userbuffer output used for inference, and the output type is Uint N. Must be used with user
                    specified buffer.
--userbuffer_memorymapped
                    Specifies to use memory-mapped (zero-copy) user buffer. Must be used with --userbuffer_float or
                    --userbuffer_tf8 or userbuffer_tfN or userbuffer_auto etc. Cannot be combined with --encoding_type.
                    Note: Passing this option will turn all input and output userbuffers into memory mapped buffer.
--static_min_max  Specifies to use quantization parameters from the model instead of
                    input specific quantization. Used in conjunction with --userbuffer_tf8.
--resizable_dim=<val>
                    Specifies the maximum number that resizable dimensions can grow into.
                    Used as a hint to create UserBuffers for models with dynamic sized outputs. Should be a
                    positive integer and is not applicable when using ITensor.
--userbuffer_glbuffer
                    [EXPERIMENTAL]  Specifies to use userbuffer for inference, and the input source is OpenGL buffer.
                    Cannot be combined with --encoding_type.
                    GL buffer mode is only supported on Android OS.
--data_type_map=<val>
                    Sets data type of IO buffers during prepare.
                    Arguments should be provided in the following format:
                    --data_type_map buffer_name1=buffer_name1_data_type --data_type_map buffer_name2=buffer_name2_data_type
                    Data Type can have the following values: float16, float32, fixedPoint8, fixedPoint16, int8, int16, int32, int64, uint8, uint16, uint32, uint64, bool8
                    Note: Must use this option with --tensor_mode.
--tensor_mode=<val>
                    Sets type of tensor to use.
                    Arguments should be provided in the following format:
                    --tensor_mode itensor
                    Data Type can have the following values: userBuffer, itensor
--perf_profile=<val>
                    Specifies perf profile to set. Valid settings are "low_balanced" , "balanced" , "default",
                    "high_performance" ,"sustained_high_performance", "burst", "low_power_saver", "power_saver",
                    "high_power_saver", "extreme_power_saver", and "system_settings".
--perf_config_yaml  Specifies the path to the yaml file containing the perf profile settings.
--profiling_level=<val>
                    Specifies the profiling level.  Valid settings are "off", "basic", "moderate", "detailed", and "linting".
                    Default is detailed.
--enable_cpu_fallback
                    Enables cpu fallback functionality. Defaults to disable mode.
--input_name=<val>
                    Specifies the name of graph and the name of input for which dimensions are specified
                    e.g. --input_name="<graph name> <input name>"
                    graph name is specified in snpe-dlc-info and defaults to the name of the first graph in the DLC.
--input_dimensions=<val>
                    Specifies new dimensions for input whose name is specified in input_name. e.g. --input_dimension 1,224,224,3
                    For multiple inputs, specify --input_name="<graph name> <input name>" and --input_dimensions multiple times."
--gpu_mode=<val>  Specifies gpu operation mode. Valid settings are "default", "float16".
                    default = float32 math and float16 storage (equiv. use_gpu arg).
                    float16 = float16 math and float16 storage.
--enable_init_cache
                    Enable init caching mode to accelerate the network building process. Defaults to disable.
--platform_options=<val>
                    Specifies value to pass as platform options.
--priority_hint=<val>
                    Specifies hint for priority level.  Valid settings are "low", "normal", "normal_high", "high". Defaults to normal.
                    Note: "normal_high" is only available on DSP.
--inferences_per_duration=<val>
                    Specifies the number of inferences in specific duration (in seconds). e.g. "10,20".
--runtime_order=<val>
                    Specifies the order of precedence for runtimes.
                    Valid values are: cpu (Snapdragon CPU), gpu_float16 (Adreno GPU), gpu (Adreno GPU), aip (Snapdragon HTA+HVX), and dsp (Hexagon DSP).
                    This option cannot be passed when any variant of --use_<RUNTIME> is used.
--set_output_tensors=<val>
                    Optionally, Specifies a comma separated list of tensors to be output after execution.
                    If using Multi Graph DLC, use --set_output_tensors for each graph.
                    e.g --set_output_tensors="graphA tensorA1,tensorA2" --set_output_tensors="graphB tensorB1,tensorB2"
                    graph name is specified in snpe-dlc-info and defaults to the name of the first graph in the DLC.
--set_unconsumed_as_output
                    Sets all unconsumed tensors as outputs.
                    aip_fixed8_tf (Snapdragon HTA+HVX) = Data & Math: 8bit fixed point Tensorflow style format
                    cpu (Snapdragon CPU)               = Same as cpu_float32
                    gpu (Adreno GPU)                   = Same as gpu_float32_16_hybrid
                    dsp (Hexagon DSP)                  = Same as dsp_fixed8_tf
                    aip (Snapdragon HTA+HVX)           = Same as aip_fixed8_tf
--udo_package_path=<val>
                    Path to the registration library for UDO package(s).
                    Optionally, user can provide multiple packages as a comma-separated list.
--duration=<val>    Specified the duration of the run in seconds. Loops over the input_list until this amount of time has transpired.
--dbglogs
--timeout=<val>     Execution terminated when exceeding time limit (in microseconds). Only valid for HTP (dsp v68+) runtime.
--userlogs=<val>    Specifies the user level logging as level,<optional logPath>.
                    Valid values are: "warn", "verbose", "info", "error", "fatal"
--cache_compatibility_mode=<val>
                    Specifies the cache compatibility check mode; valid values are: "permissive" (default), "strict", and "always_generate_new".
                    Only valid for HTP (dsp v68+) runtime.
--validate_cache    Perform an additional validation step just before building SNPE to check the validity of the selected cache record in the DLC.
                    Upon success, app will proceed as usual. On validation failure, the app will report the validation error before exiting.
--graph_init=<val>
                    List of comma seperated graphs in the current DLC that is set to be inited.
--graph_execute=<val>
                    List of comma seperated graphs in the current DLC that is set to be executed.
--help              Show this help message.
--version           Show SDK Version Number.
This binary outputs raw output tensors into the output folder by default. Examples of using snpe-net-run can be found in Running the Inception v3 Model tutorial.
Additional details:
Running batched inputs:

snpe-net-run is able to automatically batch the input data. The batch size is indicated in the model container (DLC file) but can also be set using the “input_dimensions” argument passed to snpe-net-run. Users do not need to batch their input data. If the input data is not batch, the input size needs to be a multiple of the size of the input data files. snpe-net-run would group the provided inputs into batches and pad the incomplete batches (if present) with zeros.

In the example below, the model is set to accept batches of three inputs. So, the inputs are automatically grouped together to form batches by snpe-net-run and padding is done to the final batch. Note that there are five output files generated by snpe-net-run:

 …
Processing DNN input(s):
cropped/notice_sign.raw
cropped/trash_bin.raw
cropped/plastic_cup.raw
Processing DNN input(s):
cropped/handicap_sign.raw
cropped/chairs.raw
Applying padding
input_list argument:

snpe-net-run can take multiple input files as input data per iteration, and specify multiple output names, in an input list file formated as below:

#<output_name>[<space><output_name>]
<input_layer_name>:=<input_layer_path>[<space><input_layer_name>:=<input_layer_path>]
…
The first line starting with a “#” specifies the output layers’ names. If there is more than one output, a whitespace should be used as a delimiter. Following the first line, you can use multiple lines to supply input files, one line per iteration, and each line only supply one layer. If there is more than one input per line, a whitespace should be used as a delimiter.

Here is an example, where the layer names are “Input_1” and “Input_2”, and inputs are located in the path “Placeholder_1/real_input_inputs_1/”. Its input list file should look like this:

#Output_1 Output_2
Input_1:=Placeholder_1/real_input_inputs_1/0-0#e6fb51.rawtensor Input_2:=Placeholder_1/real_input_inputs_1/0-1#8a171b.rawtensor
Input_1:=Placeholder_1/real_input_inputs_1/1-0#67c965.rawtensor Input_2:=Placeholder_1/real_input_inputs_1/1-1#54f1ff.rawtensor
Input_1:=Placeholder_1/real_input_inputs_1/2-0#b42dc6.rawtensor Input_2:=Placeholder_1/real_input_inputs_1/2-1#346a0e.rawtensor
Similar to above a first line starting with “%” specifies the output tensor names

Note: If the batch dimension of the model is greater than 1, the number of batch elements in the input file has to either match the batch dimension specified in the DLC or it has to be one. In the latter case, snpe-net-run will combine multiple lines into a single input tensor.

Running AIP Runtime:

AIP Runtime requires a DLC which was quantized, and HTA sections were generated offline.

AIP Runtime does not support debug_mode

AIP Runtime requires a DLC with all the layers partitioned to HTA to support batched inputs

Set cache compatibility mode:

A DLC can include more than one cache record and users can set the compatibility mode to check whether the best cache record is optimal for the device. The available modes indicate binary cache compatibility as follows.

permissive – Compatible if it could run on the device.

strict – Compatible if it could run on the device and fully utilize hardware capability.

always_generate_new – Always incompatible; SNPE will generate a new cache.


snpe-parallel-run
snpe-parallel-run loads a DLC file, loads the data for the input tensor(s), and executes the network on the specified runtime. This app is similar to snpe-net-run, but is able to run multiple threads of inference on the same network for benchmarking purposes.

DESCRIPTION:
------------
Tool that loads and executes one or more neural networks on different threads with optional asynchronous input/output processing using SDK APIs.


REQUIRED ARGUMENTS:
-------------------
--container  <FILE>   Path to the DL container containing the network.
--input_list <FILE>   Path to a file listing the inputs for the network.
--perf_profile <VAL>
                      Specifies perf profile to set. Valid settings are "balanced" , "default" , "high_performance" , "sustained_high_performance" , "burst" , "power_saver", "low_power_saver", "high_power_saver", "extreme_power_saver", "low_balanced", and "system_settings".
                      NOTE: "balanced" and "default" are the same.  "default" is being deprecated in the future.
--cpu_fallback        Enables cpu fallback functionality. Valid settings are "false", "true".
--runtime_order <VAL,VAL,VAL,..>
                      Specifies the order of precedence for runtime e.g cpu,gpu etc. Valid values are: cpu, gpu, gpu_float16, dsp, aip. This option cannot be passed when any variant of --use_<RUNTIME> is used.
--use_cpu             Use the CPU runtime for SNPE (Snapdragon CPU). Data & Math: float 32bit. Only one --use_<RUNTIME> option is needed.
--use_gpu             Use the GPU float32 runtime (Adreno GPU). Data: float 16bit Math: float 32bit.
--use_gpu_fp16        Use the GPU float16 runtime (Adreno GPU). Data: float 16bit Math: float 16bit.
--use_dsp             Use the DSP fixed point runtime (Hexagon DSP). Data & Math: 8bit fixed point Tensorflow style format.
--use_aip             Use the AIP fixed point runtime (Snapdragon HTA+HVX). Data & Math: 8bit fixed point Tensorflow style format.


OPTIONAL ARGUMENTS:
-------------------
--userbuffer_float    Specifies to use userbuffer for inference, and the input type is float.
--userbuffer_tf8      Specifies to use userbuffer for inference, and the input type is tf8exact0.
--userbuffer_auto     Specifies to use userbuffer with automatic input and output type detection for inference.
--use_native_input_files
                      Specifies to consume the input file(s) in their native data type(s).
                      Must be used with --userbuffer_xxx.
--use_native_output_files
                      Specifies to write the output file(s) in their native data type(s).
                      Must be used with --userbuffer_xxx.
--input_name <INPUT_NAME>
                      Specifies the name of input for which dimensions are specified.
--input_dimensions <INPUT_DIM>
                      Specifies new dimensions for input whose name is specified in input_name. e.g. "1,224,224,3".
--output_dir <DIR>    The directory to save result files
--static_min_max      Specifies to use quantization parameters from the model instead of
                      input specific quantization. Used in conjunction with --userbuffer_tf8.
--userbuffer_float_output
                      Overrides the userbuffer output used for inference, and the output type is float.
                      Must be used with user specified buffer.
--userbuffer_tf8_output
                      Overrides the userbuffer output used for inference, and the output type is tf8exact0.
                      Must be used with user specified buffer.
--userbuffer_memorymapped
                      Specifies to use memory-mapped (zero-copy) user buffer. Must be used with --userbuffer_float or
                      --userbuffer_tf8 or userbuffer_tfN or userbuffer_auto etc. Cannot be combined with --encoding_type.
                      Note: Passing this option will turn all input and output userbuffers into memory mapped buffer.
--userbuffer_memorymapped_shared
                      Specifies to use memory-mapped (zero-copy) user buffer with shared memory chunk.
                      Must be used with --userbuffer_float or --userbuffer_tf8 or userbuffer_tfN or
                      userbuffer_auto etc. Cannot be combined with --encoding_type or --userbuffer_memorymapped.
                      Note: Passing this option will turn all input and output userbuffers into memory mapped buffer
--enable_init_cache   Enable init caching mode to accelerate the network building process. Defaults to disable.
--profiling_level     Specifies the profiling level.  Valid settings are "off", "basic", "moderate", "detailed", and "linting".
                      Default is off.
--platform_options    Specifies value to pass as platform options.  Valid settings: "HtaDLBC:ON/OFF", "unsignedPD:ON/OFF".
--platform_options_local
                      Specifies the value to pass for the current SNPE instance. Valid settings: "HtpDLBC:ON/OFF", "HtaDLBC:ON/OFF;HtpDLBC:ON/OFF".
--set_output_tensors  Specifies a comma separated list of tensors to be output after execution.
--userlogs <VAL>      Specifies the user level logging as level,<optional logPath>.
--version             Show SDK Version Number.
--help                Show this help message.
Additional details:
Required runtime argument:

For the required arguments pertaining to runtime specification, either –runtime_order OR –use_cpu OR –use_gpu etc. needs to be specified. The following example demonstrates an equivalent command using either of these options.

snpe-parallel-run --container container.dlc --input_list input_list.txt
--perf_profile burst --cpu_fallback true --use_dsp --use_gpu --userbuffer_auto
is equivalent to

snpe-parallel-run --container container.dlc --input_list input_list.txt
--perf_profile burst --cpu_fallback true --runtime_order dsp,gpu --userbuffer_auto
Spawning multiple threads:

snpe-parallel-run is able to create multiple threads to execute identical inference passes.

In the example below, the given command has the required arguments for container and input list given. After these 2 options, the remaining options form a repeating sequence that corresponds to each thread. In this example, we have varied the runtimes specified for each thread (one for dsp, another for gpu, and the last one for dsp).

snpe-parallel-run --container container.dlc --input_list input_list.txt
--perf_profile burst --cpu_fallback true --use_dsp --userbuffer_auto
--perf_profile burst --cpu_fallback true --use_gpu --userbuffer_auto
--perf_profile burst --cpu_fallback true --use_dsp --userbuffer_auto
When this command is executed, the following section of output is observed:

...
Processing DNN input(s):
input.raw
PSNPE start executing...
runtimes: dsp_fixed8_tf gpu_float32_16_hybrid dsp_fixed8_tf - Mode :0- Number of images processed: x
    Build time: x seconds.
...
Note that the number of runtimes listed corresponds to the number of threads specified, as well as the order in which those threads were specified.


snpe-throughput-net-run
snpe-throughput-net-run concurrently runs multiple instances of SNPE for a certain duration of time and measures inference throughput. Each instance of SNPE can have its own model, designated runtime and performance profile. Please note that the –duration parameter is common for all instances of SNPE created.

DESCRIPTION:
------------
Tool to load and execute concurrent SNPE objects using the SDK API.


REQUIRED ARGUMENTS:
-------------------
    --container  <FILE>              Path to the DL container containing the network.
    --duration   <VAL>               Duration of time (in seconds) to run network execution.
    --use_cpu                        Use the CPU runtime for SNPE (Snapdragon CPU). Data & Math: float 32bit. Only one --use_<RUNTIME> option is needed.
    --use_gpu                        Use the GPU float32 runtime (Adreno GPU). Data: float 16bit Math: float 32bit.
    --use_gpu_fp16                   Use the GPU float16 runtime (Adreno GPU). Data: float 16bit Math: float 16bit.
    --use_dsp                        Use the DSP fixed point runtime (Hexagon DSP). Data & Math: 8bit fixed point Tensorflow style format.
    --use_aip                        Use the AIP fixed point runtime (Snapdragon HTA+HVX). Data & Math: 8bit fixed point Tensorflow style format.
    --perf_profile <VAL>             Specifies perf profile to set. Valid settings are "balanced" , "default" , "high_performance" ,
                                     "sustained_high_performance" , "burst" , "power_saver", "low_power_saver", "high_power_saver", "extreme_power_saver", "low_balanced", and "system_settings".
                                     NOTE: "balanced" and "default" are the same.  "default" is being deprecated in the future.
    --perf_config_yaml <VAL>         Specifies the path to the yaml config file containing the perf configs.
    --runtime_order <VAL,VAL,VAL,..> Specifies the order of precedence for runtime e.g cpu,gpu etc. Valid values are: cpu, gpu, gpu_float16, dsp, aip. This option cannot be passed when any variant of --use_<RUNTIME> is used.

OPTIONAL ARGUMENTS:
-------------------
    --debug                             Specifies that output from all layers of the network
                                        will be saved.
    --userbuffer_auto                   Specifies to use userbuffer for input and output, with auto detection of types enabled.
                                        Must be used with user specified buffer.
    --userbuffer_float                  Specifies to use userbuffer for inference, and the input type is float.
                                        Must be used with user specified buffer.
    --userbuffer_floatN                 Specifies to use userbuffer for inference, and the input type is float16 or float32.
                                        Must be used with user specified buffer.
    --userbuffer_tf8                    Specifies to use userbuffer for inference, and the input type is tf8exact0.
                                        Must be used with user specified buffer.
    --userbuffer_tfN                    Specifies to use userbuffer for inference, and the input type is tf8exact0 or tf16exact0.
                                        Must be used with user specified buffer.
    --userbuffer_memorymapped           Specifies to use memory-mapped (zero-copy) user buffer. Must be used with --userbuffer_float or
                                        --userbuffer_tf8 or userbuffer_tfN or userbuffer_auto etc. Cannot be combined with --encoding_type.
                                        Note: Passing this option will turn all input and output userbuffers into memory mapped buffer.
    --userbuffer_float_output           Overrides the userbuffer output used for inference, and the output type is float.
                                        Must be used with user specified buffer.
    --userbuffer_floatN_output          Overrides the userbuffer output used for inference, and the output type is float16 or float32.
                                        Must be used with user specified buffer.
    --userbuffer_tf8_output             Overrides the userbuffer output used for inference, and the output type is tf8exact0.
                                        Must be used with user specified buffer.
    --userbuffer_tfN_output             Overrides the userbuffer output used for inference, and the output type is tf8exact0 or tf16exact0.
                                        Must be used with user specified buffer.
    --storage_dir <DIR>                 The directory to store metadata files
    --version                           Show SDK Version Number.
    --iterations <VAL>                  Number of times to iterate through entire input list
    --verbose                           Print more debug information.
    --skip_execute                      Don't do execution (just graph build/teardown)
    --enable_cpu_fallback               Enables cpu fallback functionality. Defaults to disable mode.
    --json  <FILE>                      Generated JSON report.
    --input_raw <FILE>                  Path to raw inputs for the network, seperated by ",".
    --fixed_fps <VAL>                   Fix fps so as to control system loading, total FPS will be limited to around <VAL> Ex: 30,20,0(free run)
    --udo_package_path <VAL,VAL>        Path to UDO package with registration library for UDOs.
                                        Optionally, user can provide multiple packages as a comma-separated list.
    --enable_init_cache                 Enable init caching mode to accelerate the network building process. Defaults to disable.
    --platform_options <VAL>            Specifies value to pass as platform options for all SNPE instances.
    --platform_options_local <VAL>      Specifies the value to pass as per SNPE instance platform options for the current SNPE instance.
                                        if --platform_options is specified then it overwrites the global platform options for the current SNPE instance.
    --priority_hint <VAL>               Specifies hint for priority level. Valid settings are "low", "normal", "normal_high", "high". Defaults to normal.
                                        Note: "normal_high" is only available on DSP.
    --groupDuration <VAL>               Duration (in ms) of execution before next sleep.(Optional)
    --groupSleep <VAL>                  Sleep interval (in ms) after execution of group.(Optional)
    --set_output_layers <VAL>           Optionally, user can provide a comma separated list of layers to be output after execution.
                                        If using multi-graph DLC, provide <graph name> <comma separated layers> in double quotes.
                                        It should be defined for all instances or none at all.
                                        Use empty string for instances that doesn't need any layer outputs.
                                        e.g --set_output_layers "graphA layer1,layer2,layer3"
    --set_output_tensors <VAL>          Optionally, user can provide a comma separated list of tensors to be output after execution.
                                        If using multi-graph DLC, provide <graph name> <comma separated tensors> in double quotes.
                                        It should be defined for all instances or none at all.
                                        Use empty string for instances that doesn't need any layer outputs.
                                        e.g --set_output_tensors "graphA tensor1,tensor2,tensor3"
    --userlogs=<val>                    Specifies the user level logging as level,<optional logPath>.
                                        Valid values are: "warn", "verbose", "info", "error", "fatal"
    --enable_cpu_fxp                    Enable the fixed point execution on CPU runtime.
    --model_name <VAL>                  To add the model name to the logs (Optional)
    --cache_compatibility_mode=<val>    Specifies the cache compatibility check mode; valid values are: "permissive" (default), "strict", and "always_generate_new".
                                        Only valid for HTP (dsp v68+) runtime.
    --validate_cache                    Perform an additional validation step just before building SNPE to check the validity of the selected cache record in the DLC.
                                        Upon success, app will proceed as usual. On validation failure, the app will report the validation error before exiting.
    --graph_init                        Optionally, Specifies a comma separated list of specified graphs in the current DLC that is set to be inited.
                                        e.g --graph_init graph1, graph2, graph3
    --graph_execute                     Optionally, Specifies a comma separated list of specified graphs in the current DLC that is set to be executed.
                                        e.g --graph_execute graph1, graph2, graph3
    --help                              Show this help message.

Analysis
snpe-diagview
snpe-diagview loads a DiagLog file generated by snpe-net-run whenever it operates on input tensor data. The DiagLog file contains timing information information for each layer as well as the entire forward propagate time. If the run uses an input list of input tensors, the timing info reported by snpe-diagview is an average over the entire input set.

The snpe-net-run generates a file called “SNPEDiag_0.log”, “SNPEDiag_1.log” … , “SNPEDiag_n.log”, where n corresponds to the nth iteration of the snpe-net-run execution.

usage: snpe-diagview --input_log DIAG_LOG [-h] [--output CSV_FILE]

Reads a diagnostic log and output the contents to stdout

required arguments:
    --input_log     DIAG_LOG
                Diagnostic log file (required)
optional arguments:
    --output        CSV_FILE
                Output CSV file with all diagnostic data (optional)

    --chrometrace   CHROMETRACE_FILE
                Output chrometrace JSON filename (w/out extension) for logs made using profiling level linting (optional)
The output generated has timings collected at different layers of the stack. Below is the description of the timing markers:

(Please note: Certain backends like DSP or GPU sometimes splits an op into multiple ops in the backend. Sometimes the backends fuse multiple ops into one. Hence a detailed profiling log can display mismatching no of ops and layer mapping.)

../images/snpe_diagview_timing_diagram.png
snpe-dlc-diff
snpe-dlc-diff compares two DLCs and by default outputs some of the following differences in them in a tabular format:

unique layers between the two DLCs

parameter differences in common layers

differences in dimensions of buffers associated with common layers

weight differences in common layers

output tensor names differences in common layers

unique records between the two DLCs (currently checks for AIP records only)

usage: snpe-dlc-diff [-h] -i1 INPUT_DLC_ONE -i2 INPUT_DLC_TWO [-c] [-l] [-p]
                        [-d] [-w] [-o] [-i] [-x] [-s SAVE]

required arguments:
    -i1 INPUT_DLC_ONE, --input_dlc_one INPUT_DLC_ONE
                        path to the first dl container archive
    -i2 INPUT_DLC_TWO, --input_dlc_two INPUT_DLC_TWO
                        path to the second dl container archive

optional arguments:
    -h, --help            show this help message and exit
    -c, --copyrights      compare copyrights between models
    -l, --layers          compare unique layers between models
    -p, --parameters      compare parameter differences between identically
                        named layers
    -d, --dimensions      compare dimension differences between identically
                        named layers
    -w, --weights         compare weight differences between identically named
                        layers.
    -o, --outputs         compare output_tensor name differences names between
                        identically named layers
    -i, --diff_by_id      Overrides the default comparison strategy for diffing
                        2 models components. By default comparison is made
                        between identically named layers. With this option the
                        models are ordered by id and diff is done in order as
                        long as no more than 1 consecutive layers have
                        different layer types.
    -x, --hta             compare HTA records differences in Models
    -s SAVE, --save SAVE  Save the output to a csv file. Specify a target file
                        path.

snpe-dlc-info
snpe-dlc-info outputs layer information from a DLC file, which provides information about the network model.

usage: snpe-dlc-info [-h] -i INPUT_DLC [-s SAVE]

required arguments:
    -i INPUT_DLC, --input_dlc INPUT_DLC
                        path to a DLC file

optional arguments:
    -s SAVE, --save SAVE
                        Save the output to a csv file. Specify a target file path.

snpe-dlc-viewer
snpe-dlc-viewer visualizes the network structure of a DLC in a web browser.

usage: snpe-dlc-viewer [-h] -i INPUT_DLC [-s]

required arguments:
    -i INPUT_DLC, --input_dlc INPUT_DLC
                        Path to a DLC file

optional arguments:
    -s, --save            Save HTML file. Specify a file name and/or target save path
    -h, --help            Shows this help message and exits
Additional details:

The DLC viewer tool renders the specified network DLC in HTML format that may be viewed on a web browser. On installations that support a native web browser, a browser instance is opened on which the network is automatically rendered. Users can optionally save the HTML content anywhere on their systems and open on a chosen web browser independently at a later time.

Features:

Graph-based representation of network model with nodes depicting layers and edges depicting buffer connections.

Colored legend to indicate layer types.

Zoom and drag options available for ease of visualization.

Tool-tips upon mouse hover to describe detailed layer parameters.

Sections showing metadata from DLC records

Supported browsers:

Google Chrome

Firefox

Internet Explorer on Windows

Microsoft Edge Browser on Windows

Safari on Mac


snpe-platform-validator
DESCRIPTION:
------------
snpe-platform-validator is a tool to check the capabilities of a device. This tool runs on the device,
rather than on the host, and requires a few additional files to be pushed to the device besides its own executable.
Additional details below.


REQUIRED ARGUMENTS:
-------------------
    --runtime <RUNTIME>   Specify the runtime to validate. <RUNTIME> : gpu, dsp, aip, all.

OPTIONAL ARGUMENTS:
-------------------
    --coreVersion         Query the runtime core descriptor.
    --libVersion          Query the runtime core library API.
    --testRuntime         Run diagnostic tests on the specified runtime.
    --targetPath <DIR>    The directory to save output on the device. Defaults to /data/local/tmp/platformValidator/output.
    --debug               Turn on verbose logging.
    --help                Show this help message.
Additional details:
Files needed to be pushed to device (Please note, we have to push the Stub.so and Skel.so of appropriate DSP architecture version, e.g., v68, v73) :

// Android
bin/aarch64-android/snpe-platform-validator
lib/aarch64-android/libSnpeHtpV73CalculatorStub.so
lib/aarch64-android/libSnpeHtpV73Stub.so
lib/hexagon-${DSP_ARCH}/unsigned/libCalculator_skel.so
lib/hexagon-${DSP_ARCH}/unsigned/libSnpeHtpV73Skel.so

// Windows
bin/aarch64-windows-msvc/snpe-platform-validator.exe
lib/aarch64-windows-msvc/calculator_htp.dll
lib/aarch64-windows-msvc/SnpeHtpV73Stub.dll
lib/hexagon-${DSP_ARCH}/unsigned/libCalculator_skel.so
lib/hexagon-${DSP_ARCH}/unsigned/libSnpeHtpV73Skel.so
example: for pushing aarch64-android variant to /data/local/tmp/platformValidator

adb push $SNPE_ROOT/bin/aarch64-android/snpe-platform-validator /data/local/tmp/platformValidator/bin/snpe-platform-validator
adb push $SNPE_ROOT/lib/aarch64-android/ /data/local/tmp/platformValidator/lib
adb push $SNPE_ROOT/lib/dsp /data/local/tmp/platformValidator/dsp

snpe-platform-validator-py
DESCRIPTION:
------------
snpe-platform-validator is a tool to check the capabilities of a device. The output is saved in a CSV file in the
"Output" directory, in a csv format. Basic logs are also displayed on the console.

REQUIRED ARGUMENTS:
-------------------
    --runtime <RUNTIME>      Specify the runtime to validate. <RUNTIME> : gpu, dsp, aip, all.
    --directory <ARTIFACTS>  Path to the root of the unpacked SDK directory containing the executable and library files.

OPTIONAL ARGUMENTS:
-------------------
    --buildVariant <VARIANT>      Specify the build variant (e.g: aarch64-android) to be validated.
    --deviceId                    Uses the device for running the adb command. Defaults to first device in the adb devices list.
    --coreVersion                 Outputs the version of the runtime that is present on the target.
    --libVersion                  Outputs the library version of the runtime that is present on the target.
    --testRuntime                 Run diagnostic tests on the specified runtime.
    --targetPath <PATH>           The path to be used on the device. Defaults to /data/local/tmp/platformValidator
                                NOTE that this directory will be deleted before proceeding with validation.
    --remoteHost <REMOTEHOST>     Run on remote host through remote adb server. Defaults to localhost.
    --debug                       Set to turn on debug log.

snpe_bench.py
python script snpe_bench.py runs a DLC neural network and collects benchmark performance information.

usage: snpe_bench.py [-h] -c CONFIG_FILE [-o OUTPUT_BASE_DIR_OVERRIDE]
                        [-v DEVICE_ID_OVERRIDE] [-r HOST_NAME] [-a]
                        [-t DEVICE_OS_TYPE_OVERRIDE] [-d] [-s SLEEP]
                        [-b USERBUFFER_MODE] [-p PERFPROFILE] [-l PROFILINGLEVEL]
                        [-json] [-cache]

Run the snpe_bench

required arguments:
    -c CONFIG_FILE, --config_file CONFIG_FILE
                        Path to a valid config file
                        Refer to sample config file config_help.json for more
                        detail on how to fill params in config file

optional arguments:
    -o OUTPUT_BASE_DIR_OVERRIDE, --output_base_dir_override OUTPUT_BASE_DIR_OVERRIDE
                        Sets the output base directory.
    -v DEVICE_ID_OVERRIDE, --device_id_override DEVICE_ID_OVERRIDE
                        Use this device ID instead of the one supplied in config
                        file. Cannot be used with -a
    -r HOST_NAME, --host_name HOST_NAME
                        Hostname/IP of remote machine to which devices are
                        connected.
    -a, --run_on_all_connected_devices_override
                        Runs on all connected devices, currently only support 1.
                        Cannot be used with -v
    -t DEVICE_OS_TYPE_OVERRIDE, --device_os_type_override DEVICE_OS_TYPE_OVERRIDE
                        Specify the target OS type, valid options are
                        ['android-aarch64', 'le', 'le64_gcc4.9',
                        'le_oe_gcc6.4', 'le64_oe_gcc6.4']
    -d, --debug           Set to turn on debug log
    -s SLEEP, --sleep SLEEP
                        Set number of seconds to sleep between runs e.g. 20
                        seconds
    -b USERBUFFER_MODE, --userbuffer_mode USERBUFFER_MODE
                        [EXPERIMENTAL] Enable user buffer mode, default to
                        float, can be tf8exact0
    -p PERFPROFILE, --perfprofile PERFPROFILE
                        Set the benchmark operating mode (balanced, default,
                        sustained_high_performance, high_performance,
                        power_saver, low_power_saver, high_power_saver,
                        extreme_power_saver, low_balanced, system_settings)
    -l PROFILINGLEVEL, --profilinglevel PROFILINGLEVEL
                        Set the profiling level mode (off, basic, moderate, detailed).
                        Default is basic.
    -json, --generate_json
                        Set to produce json output.
    -cache, --enable_init_cache
                        Enable init caching mode to accelerate the network
                        building process. Defaults to disable.

qairt-dlc-diff
qairt-dlc-diff compares two DLCs and by default outputs some of the following differences in them in a tabular format:

unique layers between the two DLCs

parameter differences in common layers

differences in dimensions of buffers associated with common layers

weight differences in common layers

output tensor names differences in common layers

unique records between the two DLCs (currently checks for AIP records only)

usage: qairt-dlc-diff [-h] -i1 -i2 [-c] [-l] [-p] [-d] [-w] [-o] [-i] [-s]

required arguments:
  -i1, --input_dlc_one
                        Path to the first dl container archive.
  -i2, --input_dlc_two
                        Path to the second dl container archive.

optional arguments:
  -c, --compare_copyrights
                        Compare copyrights between models.
  -l, --compare_layers  Compare unique layers between models.
  -p, --compare_parameters
                        Compare parameter differences between identically named layers.
  -d, --compare_dimensions
                        Compare dimension differences between identically named layers.
  -w, --compare_weights
                        Compare weight differences between identically named layers.
  -o, --compare_outputs
                        Compare output_tensor name differences names between identically named layers.
  -i, --enable_diff_by_id
                        Overrides the default comparison strategy for diffing 2 models components.
                        By default comparison is made between identically named layers.
                        With this option the models are ordered by id and diff is done
                        in order as long as no more than 1 consecutive layers have different layer types.
  -s, --save            Save the output to a csv file. Specify a target file path.

qairt-dlc-info
qairt-dlc-info outputs layer information from a DLC file, which provides information about the network model.

usage: qairt-dlc-info [-h] -i [-s] [-m] [-d] [-t]

required arguments:
  -i, --input_dlc       Path to a DLC file.

optional arguments:
  -s, --save            Save the output to a csv file. Specify a target file path.
  -m, --display_memory  Show detailed information about memory usage.
  -d, --display_all_encodings
                        Show detailed axis-quantization encoding information.
  -t, --dump_framework_trace
                        Save framework trace info into the csv file that was passed to --save option.

Previous

Inference Accuracy

Next

Debug Tools

On this page
Model Conversion
snpe-onnx-to-dlc
snpe-pytorch-to-dlc
snpe-tensorflow-to-dlc
snpe-tflite-to-dlc
qairt-converter
Model Preparation
snpe-dlc-graph-prepare
snpe-dlc-quant
snpe-dlc-quantize
snpe-udo-package-generator
qairt-quantizer
Execution
snpe-net-run
snpe-parallel-run
snpe-throughput-net-run
Analysis
snpe-diagview
snpe-dlc-diff
snpe-dlc-info
snpe-dlc-viewer
snpe-platform-validator
snpe-platform-validator-py
snpe_bench.py
qairt-dlc-diff
qairt-dlc-info
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
