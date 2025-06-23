


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
User-defined Operations
Overview of UDO
Overview of UDO
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Introduction

Qualcomm® Neural Processing SDK provides the ability for users to plug in custom neural network operations that may not be inherently supported by the runtime engine in the form of User-Defined Operations (hereafter referred to as UDO). These could be operations defined in popular training frameworks such as Tensorflow or custom operations that are built based as framework extensions but not available in the Qualcomm® Neural Processing SDK. They can be natively executed on any of the supported hardware accelerators for which they are implemented. Qualcomm® Neural Processing SDK provides the infrastructure to execute these operations in a seamless fashion with little to no overhead compared to executing internally supported operations.

Anatomy of a UDO package

Qualcomm® Neural Processing SDK allows users to provide UDO implementations in the form of dynamic libraries that can be queried, loaded and exercised to execute inference using kernels defined within them. Qualcomm® Neural Processing SDK promotes the notion of a ‘UDO package’ with which a user can easily express the association between the different components of a UDO. This notion is central to all the tools that enable users to create UDO packages to be used in network inference. However, it is to be noted that Qualcomm® Neural Processing SDK still directly interfaces with the various UDO libraries at runtime and not with the UDO package construct. Thus users are free to just build standalone libraries without being strictly bound to this notion of a package.
The figure below illustrates the concept of a UDO package:
../images/UdoPackage.png
As seen from the picture, a UDO package consists of a registration component and an implementation component. They are usually expressed separately with one registration library and a set of implementation libraries, one for each hardware accelerator for which an implementation kernel is available. Users can optionally build both components into a single library if they so wish.
The registration library consists of methods that specify all user-defined operations and the hardware cores they are designed for. It also consists of methods that allow operations to be validated for sanity at the time of network creation. The registration library is loaded and executed on the ARM CPU.

The hardware-specific implementation libraries expose several other methods that implement operation instance creation, execution, profiling and destruction. These are implemented with programming constructs supported from corresponding software platforms, such as OpenCL for GPU and Hexagon-NN SDK for DSP. While core-specific implementation files may differ entirely in source, they are all required to interface with Qualcomm® Neural Processing SDK using a set of C APIs defined in $SNPE_ROOT/include/SNPE/SnpeUdo. The complete details on these APIs can be obtained from Qualcomm® Neural Processing SDK API.

UDO workflow

Qualcomm® Neural Processing SDK recommends the following workflow in developing and integrating a UDO into the runtime:

../images/UdoWorkflow.png
The first step in the workflow is to identify the operations in the model that need to be expressed as user-defined operations and describing their attributes through a configuration file. The format and contents of this file are described in Defining a UDO.

The next set of steps produce the components of a UDO package by creating source files for the UDO kernels and compiling them against appropriate tool-chains to generate dynamic libraries specific to hardware cores such as the GPU and DSP. Qualcomm® Neural Processing SDK provides a tool called snpe-udo-package-generator that assists users in creating common skeleton code for interfacing with Qualcomm® Neural Processing SDK UDO APIs and leaves placeholders for users to fill in the kernel implementation. It also generates makefiles for common targets such as x86, Android, and for runtimes per target specified in the config file. For more details on the package generation refer to Creating a UDO Package. For details on compiling the UDO package for a specific runtime refer to Compiling a UDO package.

The config file created in the first step is also required to be used by the Qualcomm® Neural Processing SDK model conversion tools along with the actual trained model to allow interpretation of the user-defined operations using definitions from the file. The resulting DLC files can then be inspected using tools like snpe-dlc-info to probe the attributes of the UDOs in the model. For details on creating (and optionally quantizing) DLCs with UDOs refer to Preparing a model with UDO. Optionally, models with UDOs can also be quantized using Qualcomm® Neural Processing SDK quantization tools to use with fixed-point runtimes such as DSP. The quantizer tool estimates the quantization ranges for activations from all layers in the network including UDOs. Since the tool runs offline on an x86 host machine, it is required to have a CPU implementation for the UDO in order to perform inference through the entire network. This is also illustrated in dotted lines in the workflow diagram. Refer to Quantizing a DLC with UDO for details on the quantization process.

The final step in this workflow is to be able to actually execute network models with UDOs. Qualcomm® Neural Processing SDK applications use the UDO package to register UDO implementations within the process that runs inference on select network models. It should be noted that these UDOs can be exercised by multiple instances of Qualcomm® Neural Processing SDK simultaneously without race conditions, which increases the overall throughput for network inference. For more details on the UDO package registration process refer to Running a model with UDO.

If the DSP implementation library of the UDO is not signed for execution on a signed process domain (the default for a Qualcomm® Neural Processing SDK application), it is required to request the use of an unsigned process domain. Unsigned process domains apply only to the DSP target, and allow Qualcomm® Neural Processing SDK to use unsigned UDO implementation libraries. To see how to utilize an unsigned process domain with the Qualcomm® Neural Processing SDK application, refer to Running a model with UDO.

UDO Backward Compatibility

This section specifies limitations of UDO packages :

The UDOs compiled for DSP V68 or later on a particular Qualcomm® Neural Processing SDK release version, needs to be used with same release version and can’t be used with different release version.

Users need to recompile UDO packages generated for DSP V68 by using correct Qualcomm® AI Direct SDK which is compatible with a particular Qualcomm® Neural Processing SDK release.

Previous

User-defined Operations

Next

Defining a UDO

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
User-defined Operations
Defining a UDO
Defining a UDO
Updated: Jun 11, 2025 80-63442-2 Rev: BL
A User-Defined Operation (UDO) allows users to integrate their custom operations with Qualcomm® Neural Processing SDK to enable execution on any supported hardware accelerator. The UDO mechanism accepts a specification of a custom operation (defined below), and processes that information to process a model containing that custom operation. This section explains how such a UDO can be specified. See Overview of UDO for more details about UDO and Preparing a model with UDO for details on how to convert a model that contains a UDO into a Qualcomm® Neural Processing SDK DLC for supported frameworks.

The UDO Configuration Specification

As described in the Overview of UDO section, a user can express the attributes of their custom operation with a configuration specification file. This UDO configuration (henceforth known as UDO config) is a description of the operation that can be created using the Extensible Markup Language (XML) (henceforth known as XML OpDef Config) or the Javascript Object Notation (JSON) syntax and formatting (henceforth known as JSON UDO Config). The configuration file syntax defines fields that describe the UDO Operation information. The fields are pre-determined and will ultimately be parsed into the required information that constitutes a UDO. The information provided should be generic and independent of a particular model, meaning model-specific parameters or names need not be part of the configuration. The information will be used to identify the op within a framework model, and then ultimately serialized into the DLC model. This implies that any changes in the config would require re-generation of the DLC model to ensure the correct information is serialized. The following sections describe the configuration file specification.

The XML OpDef Config Description

The XML OpDef Config describes the operations the package contains as well as the package information such as the package name, version, and domain. Package information and operations are described with respect to a predefined XML schema (described below), that requires information about operation inputs, outputs, and parameters.

The XML OpDef Schema Breakdown

This section provides an overview of the schema used to define Operation Definitions (Op Defs) in the XML OpDef Config. Op Defs specify the inputs, outputs, parameters, and descriptive metadata that constitute an operation. The schema is formalized using Extensible Markup Language (XML) and XML Schema Definition (XSD).

Operation Definition Schema

The following diagram describes the relationship between these Op Def entities.

../images/OpDefUml.png
In the above OpDef Schema diagram members prefixed by @ are XML attributes and those with no prefix are XML elements.

OpDef

An OpDef is an XML element that describes an operation at thehighest level. It contains the following elements. Elements with content are required and elements that are empty are optional.

<OpDef>
     <Name>OpName</Name>

     <Description>
         <Content></Content>
         <Code></Code>
     </Description>

     <Reference Source="" Url=""></Reference>

     <!--Requires at least one input-->
     <Input>
         <Name>in[0]</Name>
         <Mandatory>true</Mandatory>
         <Constraint id="" Type=""></Constraint>
         <Datatype>FLOAT_32</Datatype>
         <Shape>
             <Rank>1D</Rank>
             <Layout></Layout>
             <Text></Text>
         </Shape>
         <Default></Default>
         <Repeated></Repeated>
         <IsStaticTensor></IsStaticTensor>
     </Input>

     <!--Requires at least one output-->
     <Output>
         <Name>out[0]</Name>
         <Mandatory>true</Mandatory>
         <Constraint id="" Type=""></Constraint>
         <Datatype>FLOAT_32</Datatype>
         <Shape>
             <Rank>1D</Rank>
             <Layout></Layout>
             <Text></Text>
         </Shape>
         <Repeated></Repeated>
     </Output>

     <!--Parameters are optional-->
     <Parameter>
         <Name>param</Name>
         <Mandatory>false</Mandatory>
         <Constraint id="" Type=""></Constraint>
         <Datatype>INT_32</Datatype>
         <Shape>
             <Rank>1D</Rank>
             <Layout></Layout>
             <Text></Text>
         </Shape>
         <Default></Default>
         <Enumeration>
             <Enum></Enum>
         </Enumeration>
     </Parameter>

     <UseDefaultTranslation></UseDefaultTranslation>
     <SupportedBackend>DSP_V68</SupportedBackend>
 </OpDef>
Name: The name of the operation.

Description: Optional; describes the operation through sequences of content and code.

Content: String describing the operation.

Code: String that represents code describing the operation, e.g., output_height = input_height - crop_top - crop_bottom

Reference: Optional; defines one or more references for the operation.

Source: Attribute for the source of operation. E.g. Tensorflow, ONNX, etc.

Url: Attribute for the URL of the source.

Input: Defines one or more inputs to the operation. Inputs are extensions of Tensors that have the following additional field(s):.

IsStaticTensor: Optional boolean flag that if set to True, indicates that an input tensor is a parameter which contains or references static data. If unset, the tensor is treated as a dynamic input.

Repeated: Optional boolean that specifies whether this input is repeated. Used for operations which have variadic inputs, such as Concat. 7

Output: Defines one or more outputs to the operation. Outputs are extensions of Tensors that have the following additional field(s):

Repeated: Optional boolean that specifies whether this output is repeated. Used for operations which have variadic outputs, such as MultiClassNms. 7

Parameter: Optional; defines one or more parameters for the operation. Parameters are extensions of Tensors that additionally define.

Enumeration: Optional field for enumerated params. Enumerations are composed of subfields called Enum whose content gives the name of the enum representing a given value. Values are assigned in the order in which the Enum is specified.

SupportedBackend: Field(s) that specify one or more backends on which this operation is supported. Used when backends share a common definition of an operation. If fields vary across backend for the same operation use SupplementalOpDef and mark the field with BACKEND_SPECIFIC.

UseDefaultTranslation: Boolean field that if set to true, indicates that a custom operation overrides a QNN native operation. The custom operation type must match the type of the QNN native operation for accurate conversion. When set to false, a custom operation is converted as a generic user defined operation. In the false mode, the custom op type must match the source framework type.

A key component of creating an OpDef are Inputs, Outputs, and Parameters, all of which are extensions of Tensors. A Tensor element is defined as follows:

Name: The name of the tensor.

Description: Optional: describes the operation through sequences of content and code:

Content: String describing the operation.

Code: String that represents code describing the tensor. e.g. output_height = input_height - crop_top - crop_bottom

Constraint: Optional: defines one or more constraints on the given tensor. The constraint is given as a string in the body of the element. 8

id: Specifies the ID of the constraint. Used to override constraints for supplemental operation definitions.

Type: The type of the constraint. Valid types are:

Number: a constraint characterized by cardinality, e.g., Number of inputs >= 1

Shape: a constraint characterized by a restriction on dimension of the tensor, e.g., Rank >= 1

Value: a constraint characterized by the value of the tensor, e.g., Tensor is only positive.

Datatype: a constraint characterized by a restriction on datatype. Typically used for tensors which can be of several datatypes but must be of the same datatype as another tensor.

Description: a constraint which does not conform to any other category of constraint.

Mandatory: Boolean; indicates if the tensor must be provided/defined. 9

Datatype: Defines the allowable datatypes for the tensor. Must be one of:

FLOAT_16

FLOAT_32

FIXED_4

FIXED_8

FIXED_16

UINT_8

UINT_16

UINT_32

STRING

BACKEND_SPECIFIC used to indicate that the datatype is dependent on the Backend. Must be used in conjunction with a SupplementalOpDef to specify a concrete datatype.

Shape: Specifies the shape of the tensor.

Rank: The rank of the tensor as an enumeration with the following values:

SCALAR: Scalar

1D: Vector

2D: Matrix

3D: 3D Tensor or Image

4D: 4D Tensor or Batched Image

ND: Generic N-D Tensor N >= 0

Layout: Optional; specifies the layout of the tensor. Must be one of:

NHWC

NHCW

UNDEFINED used to identify that the layout is neither NHCW or NHWC

BACKEND_SPECIFIC used to indicate that the layout is dependent on the Backend. Must be used in conjunction with a SupplementalOpDef to specify a concrete layout.

Text: Optional; string description for the shape of the tensor.

Default: Optional; string representing the default value for the tensor. Can be one of

Tensor: use braces or brackets to create list e.g. [[1, 2], [3, 4]]

Scalar: provide scalar value e.g. 1, 1.1, -1

Boolean: provide either 0 (false) or 1 (true)

String: any other string. If text cannot be resolved into one of above categories it will be stored as string.

SupplementalOpDef

SupplementalOpDef’s are XML elements which define content that is variable across backend(s). SupplementalOpDef’s extend the content defined in OpDefs, but limits the fields that can be overridden. The SupplementalOpDef is structured as follows. Elements with content are required, and elements that are empty are optional.

<SupplementalOpDef>

      <Name>OpName</Name>

     <!--Only supplemented Inputs are required-->
      <Input>
          <Name>in[0]</Name>
          <Constraint id="" Type=""></Constraint>
          <Datatype></Datatype>
          <Shape>
              <Layout></Layout>
              <Text></Text>
          </Shape>
          <OnlyDefaultSupported></OnlyDefaultSupported>
      </Input>

       <!--Only supplemented Outputs are required-->
      <Output>
          <Name>out[0]</Name>
          <Constraint id="" Type=""></Constraint>
          <Datatype></Datatype>
          <Shape>
              <Layout></Layout>
              <Text></Text>
          </Shape>
          <OnlyDefaultSupported></OnlyDefaultSupported>
      </Output>

       <!--Only supplemented Params are required-->
      <Parameter>
          <Name>param</Name>
          <Constraint id="" Type=""></Constraint>
          <Datatype></Datatype>
          <Shape>
              <Layout></Layout>
              <Text></Text>
          </Shape>
          <OnlyDefaultSupported></OnlyDefaultSupported>
      </Parameter>

</SupplementalOpDef>
Name: The name of the operation.

Input: Optional; extends one or more inputs to the operation. Supplemental inputs are Supplemental Tensors.

Output: Optional; extends one or more outputs to the operation. Supplemental outputs are Supplemental Tensors.

Parameter: Optional; extends one or more parameters for the operation. Supplemental parameters are Supplemental Tensors.

Inputs, outputs, and parameters are all Supplemental Tensors, which can only specify certain fields. All fields, except name, in a supplemental tensor are optional.

Name: The name of the tensor. Must correspond to the name of the tensor in the original OpDef that is being extended.

Constraint: Optional; defines one or more constraints on the given tensor. The constraint is given as a string in the body of the element. 8

id: Specifies the ID of the constraint. Used to override constraints for supplemental operation definitions.

Type: The type of the constraint. Valid types are:

Number: a constraint characterized by cardinality. e.g. Number of inputs >= 1

Shape: a constraint characterized by a restriction on dimension of the tensor. e.g. Rank >= 1

Value: a constraint characterized by the value of the tensor. e.g. Tensor is only positive.

Datatype: a constraint characterized by a restriction on datatype. Typically used for tensors which can be of several datatypes but must be of the same datatype as another tensor.

Description: a constraint which does not conform to any other category of constraint.

Datatype: Defines the allowable datatypes for the tensor. Must be one of:

FLOAT_16

FLOAT_32

FIXED_4

FIXED_8

FIXED_16

UINT_8

UINT_16

UINT_32

STRING

Shape: Specifies the shape of the tensor.

Layout: Optional; specifies the layout of the tensor. Must be one of:

NHWC

NHCW

UNDEFINED used to identify that the layout is neither NHCW or NHWC

BACKEND_SPECIFIC used to indicate that the layout is dependent on the Backend. Must be used in conjunction with a SupplementalOpDef to specify a concrete layout.

Text: Optional; string description for the shape of the tensor.

OnlyDefaultSupported: Optional; boolean that indicates if the backend only supports the default value defined in the corresponding OpDef for this tensor.

OpDefList

An OpDefList is an XML element composed of a sequence of OpDef elements. OpDefLists are backend agnostic and only serve as a wrapper around multiple OpDefs.

<OpDefList>

    <!--One or more OpDef-->
    <OpDef>
      <!--OpDef defined above -->
    </OpDef>

</OpDefList>
SupplementalOpDefList

A SupplementalOpDefList is an XML element composed of a sequence of SupplementalOpDef elements. In addition, SupplementalOpDefLists contain the following fields.

<SupplementalOpDefList Backend="HTP">

   <SupportedOps>
      <OpName></OpName>
   </SupportedOps>

   <SupplementalOpDef>
      <!--SupplementalOpDef defined above-->
   </SupplementalOpDef>

</SupplementalOpDefList>
Backend: Specifies which backend the SupplementalOpDef’s are supplementing.

SupportedOps: Sequence of OpName elements. Each OpName corresponds to an operation defined in the corresponding OpDefList and indicates the backend supports the operation. This information may be redundant with the SupportedBackend field of the OpDef element.

OpDefCollection

The OpDefCollection is the root XML element of the configuration file meant to be used with the snpe-udo-package-generator. It contains all the information needed to specify all of a users packages. The OpDefCollection contains the following

<OpDefCollection
     xmlns:xs="http://www.w3.org/2001/XMLSchema-instance"
     xs:noNamespaceSchemaLocation="OpDef.xsd"
     PackageName="ExamplePackage"
     Domain="example"
     Version="1.0"
>

   <!--One OpDefList-->
   <OpDefList>
      <!--OpDefList defined above-->
   </OpDefList>

   <!--SupplementalOpDefLists are not required-->
   <SupplementalOpDefList Backend="HTP">
      <!--SupplementalOpDefList defined above-->
   </SupplementalOpDefList>

</OpDefCollection>
PackageName: Specifies the name for the user’s OpPackage. Because packages are per backend, the actual package name will be the value specified here appended with <Backend> e.g. MyPackageNameHtp.

Domain: Specifies the domain of the package.

Version: Specifies the version of the package.

OpDefList: One OpDefList specifying all operations for the package(s).

SupplementalOpDefList: Optional; specifies one or more SupplementalOpDefList to specify per-backend information.

One OpDefCollection element can be used to produce multiple per-backend packages.

The JSON UDO Config Description

The details of the aforementioned UDO config file can be described below.

{
    "UdoPackage_0":
    {
        "Operators": [
            {
                "type": "",
                "inputs":[
                    {"name":"", "per_core_data_types":{"CPU":"FLOAT_32", "GPU":"FLOAT_32", "DSP":"UINT_8"},
                    "static": true, "tensor_layout": "NHWC"},
                    {"name":"", "data_type": "FLOAT_32",
                    "static": true, "tensor_layout": "NHWC"},
                ],
                "outputs":[
                    {"name":"", "per_core_data_types":{"CPU":"FLOAT_32", "GPU":"FLOAT_32", "DSP":"UINT_8"}},
                    {"name":"", "data_type": "FLOAT_32"}
                ],
                "scalar_params": [
                    {"name":"scalar_param_1", "data_type": "INT_32"}
                ],
                "tensor_params": [
                    {"name":"tensor_param_1", "data_type": "FLOAT_32", "tensor_layout": "NHWC"},
                ],
                "core_types": ["CPU", "GPU", "DSP"],
                "dsp_arch_types": ["v66", "v68", "v69", "v73"]
            }
        ],
        "UDO_PACKAGE_NAME": "MyCustomUdoPackage"
    }
}
The above description is simply a generic configuration file to aid the definition of the fields that the user can fill. Required fields are provided with a specific value, while optional fields are denoted with empty strings. Note that an optional field only implies that there is either a default value if it is not provided, or that an empty string will be used. The full details of each available field is described hierarchically below:

UdoPackage: Every UDO package can be described as “UdoPackage_i” where i indicates the order in which the packages will be generated. The user is also free to use empty strings but the dictionary structure is necessary.1

Operators: This is a child node of a particular UdoPackage indicating the number of operators present. 5

type: defines the type of the operation.

inputs: a list of input tensors to the operation. Each input is a dictionary object. 2

name: An optional field that describes the name of the input tensor. Since the name of the input tensor is variable, the user is not required to provide this.

per_core_data_type: A dictionary object specifying the data-type of this input tensor in each core. Alternatively, if the user wishes to have the same data-type across all specified cores, then the user can specify the option “data_type” followed by the data-type. The supported data-types are:

FLOAT_16

FLOAT_32

FIXED_4

FIXED_8

FIXED_16

UINT_8

UINT_16

UINT_32

STRING

static: A boolean field that is required if the input data is static, i.e data is provided in the model. This field needs to be set if the input tensor will contain data, otherwise the input will be treated dynamically, and the data will not be serialized.

tensor_layout: A string field that describes the canonical dimension format of the input tensor. The supported values are: 4

NCHW

NHWC

outputs: A list of output tensors to the operation.2

scalar_params: A list of scalar-valued attributes.3

name: A required field that describes the name of the scalar parameter.

data_type: A required field that describes the data-type supported by this scalar parameter.

tensor_params: A list of tensor-valued attributes.2 3

core_types: The intended IP cores for this particular operation. The supported core_types:

CPU

GPU

DSP

dsp_arch_types: The intended DSP architecture types for DSP core type. The supported dsp_arch_types:

v65

v66

v68

v69

v73

UDO_PACKAGE_NAME: The name of the UDO Package, which can be any valid string.1

Creating a UDO config

The user should aim to fill out the fields described in the config above to adequately describe a UDO. In some cases, the information required in this config could be easily obtained from framework documentation about the operation. However, there may be subtle caveats, therefore the user is encouraged to ensure that inputs, outputs and params are properly categorized and described. A potential caveat is that inputs can be mis-categorized as parameters and vice versa, if the config is written only according to documentation. In this scenario, a useful tip is to visualize the model using an open source tool such as Netron (found here: https://github.com/lutzroeder/netron) to assist with crafting the UDO config correctly.

Once an adequately descriptive config has been created, it can be used as an argument to the framework converters as described in Preparing a model with UDO.

Notes:

More than one UDO package can be defined in a single config file. Users should note that the package name specified here must match the package name used in creating the corresponding package.

Each input, output and tensor parameter is categorized as the same kind of tensor object, meaning that all the fields are shared. The names of inputs and outputs are not required since the config is a generic description of an operation. An operator must have at least one input and output.

In the case of the parameters, the name field is always required.

The tensor layout is a convention to indicate the arrangement of data within the tensor. Therefore a tensor layout of NHWC means that the data is organized in (batch x height x width x channel), where channel is the fastest changing dimension. Note this is the default arrangement for Qualcomm® Neural Processing SDK, and that may have implications on a model containing a UDO if other tensor layouts are selected. Notably, if a tensor layout of NCHW is selected, then the data and/or tensor parameters may need to be reshaped to the Qualcomm® Neural Processing SDK default to maintain dimensional understanding. Should the user encounter this scenario, they may notice the introduction of intermediary permute layers prior to the UDO layer which will ultimately feed the tensors in question. These caveats should be visible as either converter warnings, debug messages or through outputs of the visualization tools described in Tools. For more details on tensor layouts, the user can consult the section: Input Image Formatting of the documentation.

For CPU, GPU and DSP coretypes, there can be an arbitrary number of operators defined per UDO package. However the provided skeleton code is tailored to define only one operation in one package. One subtle distinction is that the generated DSP V65 or DSP V66 implementation source code expects one operation per implementation library. While in the CPU, GPU, and DSP V68 or later cases, there may be an arbitrary number of operations in a library.

The data-type of a tensor determines both how the data contained in the tensor will be stored in the DLC, and the type of memory handed over by Qualcomm® Neural Processing SDK during runtime execution. While tensors get stored within the DLC in the exact data-type specified by the UDO definition, there may be runtime restrictions on the type of memory users can expect to receive depending on the chosen core-type. Users should visit the following section: Compiling a UDO package for more details.

Operations with an unknown number of inputs/outputs are currently not supported on the DSP and HTP backend.

Constraints are purely a descriptive field and do not require mathematical expression. Constraints are currently not enforced.

Outputs are not mandatory, but cannot have default values and are assumed to be NULL if not provided.

Previous

Overview of UDO

Next

Defining a UDO Package

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
User-defined Operations
Defining a UDO Package
Defining a UDO Package
Updated: Jun 11, 2025 80-63442-2 Rev: BL
A UDO package consists primarily of a registration library and one or more implementation libraries. The main idea is that the registration library contains information about the nature of the operations, while the implementation(s) contains the kernels required to execute the operations (see Overview of UDO for more details). A UDO package can be defined via a UDO configuration, which contains a text specification of the operations and paths that will ultimately help define a directory structure that will represent a UDO package. The definition of a UDO package will be discussed in this section, while the creation of a package once its been defined will be deferred to the section on Creating a UDO Package.

UDO Package Config Description

All the fields found in the UDO Config Field Description are replicated for the UDO package config specification. The breakdown of the aforementioned UDO config file is thus a combination of the description found in that section with the addition of two package specific fields. A generic config is included below with the package-specific fields explained. Users should see UDO Config Field Description for more detailed descriptions of the replicated fields, as well as review the associated notes.

{
    "UdoPackage_0":
    {
        "Operators": [
            {
                "type": "Softmax",
                "inputs":[
                    {"name":"Placeholder", "per_core_data_types": {"CPU":"FLOAT_32", "GPU":"FLOAT_32", "DSP":"UINT_8"},
                        "quantization_mode": "TF"}
                ],
                "outputs":[
                    {"name":"Output","per_core_data_types": {"CPU":"FLOAT_32", "GPU":"FLOAT_32", "DSP":"UINT_8"},
                        "quantization_mode": "TF"}
                ],
                "core_types": ["CPU", "GPU", "DSP"],
                "dsp_arch_types": ["v68"]
            }
        ],
        "UDO_PACKAGE_NAME": "SoftmaxUdoPackage"
    }
}
Additional fields are explained below:

UDO_PACKAGE_NAME: The name of the package containing the UDO.

UDO_PACKAGE_PATH: A path to where the UDO package will be saved. If this is not provided the current directory will be used.

SNPE_UDO_ROOT: This is an optional variable that lets the tool know where the SnpeUdo API directory is located within the user’s environment. This can be set here or as an environment variable.

Notes

The information specified in the config is used to instantiate info data structures which are critical to how Qualcomm® Neural Processing SDK executes a model containing a UDO. This implies a synergy between a UDO package and a model containing a UDO, as such, the recommended strategy is to use the same config for defining a UDO and its corresponding package.

The definition of a package name will determine the names of source files and implementation libraries found in a generated package.

Operation types will determine the names of methods, classes and source files contained in a generated package.

The package core-types are a set of all core-types mentioned for each operator in the package .

Previous

Defining a UDO

Next

Creating a UDO Package

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
User-defined Operations
Creating a UDO Package
Creating a UDO Package
Updated: Jun 11, 2025 80-63442-2 Rev: BL
This section describes the process of creating a UDO package from a simple text specification of a user-defined operation using the snpe-udo-package-generator. From the Qualcomm® Neural Processing SDK API standpoint, a UDO package consists of a registration library and one or more implementation libraries. As such, while a user can create a UDO package independent of this prescription, this section describes the process of creating a partially defined UDO package which can be easily implemented and compiled to produce the relevant libraries.

Generating UDO Skeleton Code

To generate a package using Qualcomm® Neural Processing SDK tools, it is necessary to create a UDO configuration describing the operation and the package details. See Defining a UDO Package for more information. Once a configuration has been specified to adequately represent the desired UDO, it can be supplied as an argument to the Qualcomm® Neural Processing SDK UDO package generator tool described in snpe-udo-package-generator. The intention of the tool is to generate partial skeleton code to aid rapid prototyping. This section describes the usage of the package generator tool and the artifacts it generates.

In order to run the snpe-udo-package-generator, the user is expected to have followed the setup instructions at Qualcomm (R) Neural Processing SDK Setup. The tool also has a dependency on the Mako Template Library, which can be found here: https://www.makotemplates.org/download.html. Additionally, we need an extracted Qualcomm® AI Direct SDK (no need of Qualcomm® AI Direct SDK setup) for generating the skeleton code. For Qualcomm® AI Direct SDK details, refer to the Qualcomm® AI Direct SDK documentation at $QNN_SDK_ROOT/docs/index.html page, where QNN_SDK_ROOT is the location of the Qualcomm® AI Direct SDK installation. Set the $QNN_SDK_ROOT to the unzipped Qualcomm® AI Direct SDK location. Once setup is complete, the following command can be used to generate a package:

snpe-udo-package-generator -p $SNPE_ROOT/examples/SNPE/NativeCpp/UdoExample/Softmax/config/Softmax_Htp.json -o <my-dir>
The above command will create a UDO package which will be a directory composed of skeleton code and build files that can be used to compile the package contents into stand-alone shared libraries. The config file referenced in UDO Tutorial has been used to generate the udo package contents below:

|-- Makefile
|-- common.mk
|-- config
|   `-- Softmax_Htp.json
|-- include
|   `-- utils
|       |-- IUdoOpDefinition.hpp
|       |-- UdoMacros.hpp
|       `-- UdoUtil.hpp
`-- jni
    |-- Android.mk
    |-- Application.mk
    `-- src
        |-- CPU
        |   |-- Makefile
        |   |-- makefiles
        |   |   |-- Android.mk
        |   |   |-- Application.mk
        |   |   `-- Makefile.linux-x86_64
        |   `-- src
        |       |-- CpuCustomOpPackage.cpp
        |       |-- SoftmaxUdoPackageInterface.cpp
        |       |-- ops
        |       |   `-- Softmax.cpp
        |       `-- utils
        |           |-- BackendUtils.hpp
        |           |-- CPU
        |           |   |-- CpuBackendUtils.cpp
        |           |   `-- CpuBackendUtils.hpp
        |           `-- CustomOpUtils.hpp
        |-- DSP_V68
        |   |-- Makefile
        |    `-- src
        |       |-- SoftmaxUdoPackageInterface.cpp
        |       `-- ops
        |           `-- Softmax.cpp
        |-- GPU
        |   |-- Makefile
        |   |-- include
        |   |   |-- GpuCustomOpPackage.hpp
        |   |   `-- Operation.hpp
        |   |-- makefiles
        |   |   |-- Android.mk
        |   |   `-- Application.mk
        |   `-- src
        |       |-- GpuCustomOpPackage.cpp
        |       |-- SoftmaxUdoPackageInterface.cpp
        |       `-- ops
        |           `-- Softmax.cpp
        |-- reg
        |   |-- Makefile
        |   `-- SoftmaxUdoPackageRegLib.cpp
        `-- utils
            `-- UdoUtil.cpp
Contents of a UDO package

The package can be compiled using the make build system for a Linux host machine or the Android-NDK build system for an Android device. Briefly, the make system is configured using the top level Makefile, common.mk and the individual makefiles in each runtime directory. The android-build system is configured using jni/Android.mk and jni/Application.mk. See Compiling a UDO package for more compilation details.

The config directory contains the JSON configuration used to create the package.

The include directory contains three kinds of files: headers from the Qualcomm® Neural Processing SDK UDO API, header files specific to the UDO package and its operations, and a directory of C++ helper utils which wrap the Qualcomm® Neural Processing SDK UDO API calls. Users should note that the utils API is included simply for convenience in creating implementation source code. The use of the utils is not a prerequisite for constructing or executing a UDO package.

The relevant source files for the package are organized under the jni/src directory. There will be a sub-directory for each core-type specified in the config. The registration (reg) directory contains files necessary to create the registration library, which is generally the point of entry for the Qualcomm® Neural Processing SDK API. There is also source code from the previously mentioned C++ helper utils. In general, users are only expected to edit code contained in runtime-specific or registration directories.

Generated Source Code

This section and the following sub-sections cover the source code generated in a package using the package contents displayed in Generating UDO Skeleton Code. When finalized, a UDO package is expected to contain a registration library and one or more implementation libraries. To produce the registration library, the source code in jni/src/reg is compiled. The implementation library is compiled using source code from each core-type specific directory. Recall that the package created by the tool will still need to be implemented. The following subsections will address the files that need to be implemented. All generated source code will have the tag Auto-generated in the header. The source code is considered partially complete in the generation stage, and it is the user’s responsibility to implement certain files as needed to ensure proper compatibility and functionality with the Qualcomm® Neural Processing SDK API. All code to be implemented will have the tag add code here in the body to indicate that it needs to be implemented. Note that all libraries link against the C++ utils source code.

Completing the Registration Skeleton Code

As mentioned previously, the registration library is created from source code in jni/src/reg. The directory contains a Makefile to compile the package and the package specific file: SoftmaxUdoPackageRegLib.cpp which contains the function symbols that get resolved by the Qualcomm® Neural Processing SDK UDO API when the library is opened. The registration library file contains API calls that provide the Qualcomm® Neural Processing SDK UDO API with information about the nature of the operations in the model, as well as the implementation libraries they belong to.

Completing the Implementation Skeleton Code

The implementation library is created per core-type, from source code that lives under the core-type specific directory within jni/src. Using the CPU runtime as an example, the jni/src/CPU directory contains a Makefile to build the CPU implementation library, a package-specific source file: SoftmaxUdoPackageInterface.cpp for all operations to be contained in the library, and a per operation source file: Softmax.cpp that should contain the runtime implementation. As in the registration case, the package-specific source file should not be edited in the general case. Similarly this file contains methods that return information about the operations contained in the implementation library, and methods that act as a layer of indirection above the code that is ultimately executed in the per operation file. In the CPU case, the three methods in Softmax.cpp namely: finalize, execute, and free are the user’s responsibility to edit. Note these methods create the operation, execute its implementation, and free the operation respectively. As such, these are completely determined by the user. A sample generated version of the implementation library is included below:

Qnn_ErrorHandle_t execute(CustomOp* operation) {

  /**
   * Add code here
   **/

  return QNN_SUCCESS;
}

Qnn_ErrorHandle_t finalize(const CustomOp* operation) {
  QNN_CUSTOM_BE_ENSURE_EQ(operation->numInput(), 1, QNN_OP_PACKAGE_ERROR_VALIDATION_FAILURE)
  QNN_CUSTOM_BE_ENSURE_EQ(operation->numOutput(), 1, QNN_OP_PACKAGE_ERROR_VALIDATION_FAILURE)

  /**
   * Add code here
   **/

  return QNN_SUCCESS;
}

Qnn_ErrorHandle_t free(CustomOp& operation) {

    /**
    * Add code here
    **/

    return QNN_SUCCESS;
}
To have good performance and stability, it is required to avoid heap memory allocation in the completed op execution functions, that is, <op_name>Impl, <op_name>_executeOp, <op_name>Operation and execute functions for DSP V68 and later, DSP V66 / V65, GPU, and CPU respectively which are executed during graph execution. The heap memory allocation includes but not limited to calling malloc, operator new, constructing STL container objects like std::vector with default allocator, and adding items like calling std::vector::push_back to STL container objects with default allocator.

The reason to avoid heap memory allocation is because the time to finish heap memory allocation is unbounded and may have huge variance. Especially for DSP and HTP, the heap memory allocation can trigger CPU request in some cases and significantly impact the inference speed. Also, the heap memory allocation can fail and return null pointers or throw exceptions. In such case, there is usually no good way to continue the execution. In applications with strict functional safety requirements, heap memory allocation after initialization is not even permitted.

If a working buffer is required to carry out the op computation, here are some potential alternatives:

construct std::array instead of std::vector for local variables: Unlike std::vector, std::array uses stack memory. This works if the maximum memory size can be known in advance and the size is not large.

use output tensor space as scratch memory: Each execution function has at least one output tensor. You can use the space of the output tensor as the scratch buffer before you fill in the real output data. Please note that the output tensor space can only be safely written in the execution function which owns the output tensor.

Notes

In the general case, the package should only require functional edits that enable proper execution. The initial un-implemented package is guaranteed to compile.

One subtle distinction is that the generated DSP V65 or DSP V66 implementation source code expects one operation per implementation library. While in the CPU, GPU, and DSP V68 or later cases, there may be an arbitrary number of operations in a library.

There are differences between the implementation source files for each runtime. In the GPU case, the execute workflow is already implemented and the user is only expected to implement the <OpName>Operation and setKernelInfo methods. In contrast to CPU and GPU, DSP uses API which does not depend on C++ helper utils discussed in the Generated Source Code section. This means that certain helper methods and constructors may not be available in the DSP case. For DSP case, the user is expected to implement softmaxImpl method.

Previous

Defining a UDO Package

Next

Compiling a UDO package

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
User-defined Operations
Compiling a UDO package
Compiling a UDO package
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Introduction

This section provides information about compiling UDO packages for all supported runtimes in Qualcomm® Neural Processing SDK.

As explained in Overview of UDO, a set of registration and implementation libraries is collectively referred to as a UDO package. The user has complete control over building these libraries for their desired runtimes using compatible tool-chains. Alternatively, Qualcomm® Neural Processing SDK offers tools and utilities to create and compile a UDO easily. For more information about the tool used to create a UDO package refer to Creating a UDO package. This section explains UDO package compilation based on the directory structure provided by the package generator.

Implementing a User-defined operation

Fundamentally, a UDO is required to be developed using the set of APIs defined in header files located at $SNPE_ROOT/include/SNPE/SnpeUdo/. Each runtime may impose additional requirements and provide options for customizing the implementation to suit the runtime. Details of the UDO APIs can be found in the API documentation at Qualcomm® Neural Processing SDK API. This section assumes that a UDO package was generated using the UDO package generator tool described in Creating a UDO package which produces a partial implementation skeleton based on the UDO specification configured by the user.

Make Targets for Package Compilation

The UDO package generator tool creates a makefile to compile the package for a specific runtime and target platform combination. The makefile is intended to provide a simple interface to compile for platforms that use make natively or require ndk-build. Using the provided makefile also allows for per library compilation for various targets.

The general form of each make target is <runtime>_<platform>. Targets that are only of the form <runtime> include all possible targets. For instance, running

make cpu
will compile the CPU for both x86 and Android platforms (arm64-v8a). A comprehensive table of available make targets is presented below .

Note: Use of the makefile is optional and not required to generate libraries.

Note: For all following examples, the displayed artifacts are for arm64-v8a target.

Implementing a UDO for CPU

A CPU UDO implementation library based on core UDO APIs is required to run a UDO package on CPU runtime. The UDO package generator tool will create a skeleton containing blank constructs in the required format, but the core logic of creating and execution of the operation needs to be filled in by the user. This can be done by completing the implementation of finalize(), execute(), and free() functions in the <OpName>.cpp file generated by the UDO package generator tool.

To have good performance and stability, it is required to avoid heap memory allocation in the completed execute() functions. The heap memory allocation includes but not limited to calling malloc, operator new, constructing STL container objects like std::vector with default allocator, and adding items like calling std::vector::push_back to STL container objects with default allocator. Please check here for more information.

Note: One important notion to take into account is that the Qualcomm® Neural Processing SDK provides tensor data corresponding to all the inputs and outputs of a UDO not directly but as an opaque pointer. The UDO implementation is expected to get a handle to the raw tensor pointers using the methods in the CustomOp operation object issued by Qualcomm® Neural Processing SDK at the time of execution. The CPU runtime operates only with floating point activation tensors. Therefore, CPU UDO implementations should be implemented to receive and produce only floating point tensors and set the field data_type in the config file to FLOAT_32. All other data types will be ignored. Refer to Defining a UDO for more details.

Compiling and running the UDO package on host is required for Qualcomm® Neural Processing SDK model quantization tool, snpe-dlc-quantize. It is necessary to quantize a model using snpe-dlc-quantize, to run a UDO layer that has at least one non-float input on the DSP.

Compiling a UDO for CPU on host

Steps to compile the CPU UDO implementation library on host x86 platform are as below:

Set the environment variable $SNPE_UDO_ROOT.

export SNPE_UDO_ROOT=<absolute_path_to_SnpeUdo_headers_directory>
Run the make instruction below in UDO package directory to compile the UDO package:

make cpu_x86
The expected artifacts after compiling for Host CPU are

The UDO CPU implementation library: <UDO-Package>/libs/x86-64_linux_clang/libUdo<UDO-Package>ImplCpu.so

The UDO package registration library: <UDO-Package>/libs/x86-64_linux_clang/libUdo<UDO-Package>Reg.so

Note: The command must be run from the package root.

Compiling a UDO for CPU on device

Steps to compile the CPU UDO implementation library on Android platform are as below:

Set the environment variable $SNPE_UDO_ROOT.
export SNPE_UDO_ROOT=<absolute_path_to_SnpeUdo_headers_directory>
$ANDROID_NDK_ROOT must be set for the Android NDK build toolchain.

export ANDROID_NDK_ROOT=<absolute_path_to_android_ndk_directory>
Run the make instruction below in UDO package directory to compile the UDO package:

make cpu_android
The shared C++ standard library is required for the NDK build to run. Make sure libc++_shared.so is present on the device at LD_LIBRARY_PATH.

The expected artifacts after compiling for Android CPU are

The UDO CPU implementation library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>ImplCpu.so

The UDO package registration library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>Reg.so

A copy of shared standard C++ library: <UDO-Package>/libs/arm64-v8a/libc++_shared.so

Implementing a UDO for GPU

Similar to the CPU runtime, a GPU UDO implementation library based on core UDO APIs is required to run a UDO package on GPU runtime. The UDO package generator tool will create a skeleton containing blank constructs in the required format, but the core logic of creating and execution of the operation needs to be filled in by the user. This can be done by completing the implementation of setKernelInfo() and <OpName>Operation() function, and adding the GPU kernel implementations in the <OpName>.cpp file generated by the UDO package generator tool.

To have good performance and stability, it is required to avoid heap memory allocation in the completed <OpName>Operation() functions. The heap memory allocation includes but not limited to calling malloc, operator new, constructing STL container objects like std::vector with default allocator, and adding items like calling std::vector::push_back to STL container objects with default allocator. Please check here for more information.

Qualcomm® Neural Processing SDK GPU UDO supports 16-bit floating point activations in the network. Users should expect input/output OpenCL buffer memory from Qualcomm® Neural Processing SDK GPU UDO to be in 16-bit floating point (or OpenCL half) data format as the storage type. For increased accuracy, users may choose to implement internal math operations of the kernel using 32-bit floating point data, and converting to half precision when reading input buffers or writing output buffers from the UDO kernel.

Note: Qualcomm® Neural Processing SDK provides tensor data corresponding to all the inputs and outputs of a UDO not directly but as an opaque pointer. The UDO implementation is expected to convert it to <code>Qnn_Tensor_t</code> which holds OpenCL memory pointer for tensor.

Compiling a UDO for GPU on device

Steps to compile the GPU UDO implementation library on Android platform are as below:

Set the environment variable $SNPE_UDO_ROOT.
export SNPE_UDO_ROOT=<absolute_path_to_SnpeUdo_headers_directory>
$ANDROID_NDK_ROOT must be set for the Andorid NDK build toolchain.

export ANDROID_NDK_ROOT=<absolute_path_to_android_ndk_directory>
$CL_LIBRARY_PATH must be set for the libOpenCL.so library location.

export CL_LIBRARY_PATH=<absolute_path_to_OpenCL_library>
The OpenCL shared library is not distributed as part of Qualcomm® Neural Processing SDK.

Run the make instruction below in UDO package directory to compile the UDO package:

make gpu_android
Note: The shared OpenCL library is target specific. It should be discoverable in CL_LIBRARY_PATH.

The expected artifacts after compiling for Android GPU are

The UDO GPU implementation library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>ImplGpu.so

The UDO package registration library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>Reg.so

A copy of shared standard C++ library: <UDO-Package>/libs/arm64-v8a/libc++_shared.so

Implementing a UDO for DSP V65 and V66

Qualcomm® Neural Processing SDK utilizes Qualcomm® AI Direct SDK to run UDO layers on DSP. Therefore, a DSP implementation library based on Qualcomm® AI Direct SDK APIs is required to run a UDO package on DSP runtime. The UDO package generator tool will create the template file <OpName>.cpp and the user will need to implement the execution logic in the <OpName>_executeOp() function in the template file.

To have good performance and stability, it is required to avoid heap memory allocation in the completed <OpName>_executeOp() functions. The heap memory allocation includes but not limited to calling malloc, operator new, constructing STL container objects like std::vector with default allocator, and adding items like calling std::vector::push_back to STL container objects with default allocator. Please check here for more information.

Qualcomm® Neural Processing SDK UDO provides the support for multi-threading of the operation using worker threads, Hexagon Vector Extensions (HVX) code and VTCM support.

The DSP runtime only propagates unsigned 8-bit activation tensors between the network layers. But it has the ability to de-quantize data to floating point if required. Therefore users developing DSP kernels can expect either UINT_8 or FLOAT_32 activation tensors in and out of the operation, and thus can set the field data_type in the config file to one of these two settings. Refer to Defining a UDO for more details.

Compiling a UDO for DSP V65 and V66 on device

This Qualcomm® Neural Processing SDK release supports building UDO DSP implementation libraries using Hexagon-SDK 3.5.x.

Set the environment variables $SNPE_UDO_ROOT

export SNPE_UDO_ROOT=<absolute_path_to_SnpeUdo_headers_directory>
Hexagon-SDK needs to be installed and set up. For details, follow the setup instructions on $HEXAGON_SDK_ROOT/docs/readme.html page, where $HEXAGON_SDK_ROOT is the location of the Hexagon-SDK installation. Make sure $HEXAGON_SDK_ROOT is set to use the Hexagon-SDK build toolchain. Also set $HEXAGON_TOOLS_ROOT and $SDK_SETUP_ENV

export HEXAGON_SDK_ROOT=<path to hexagon sdk installation>
export HEXAGON_TOOLS_ROOT=$HEXAGON_SDK_ROOT/tools/HEXAGON_Tools/8.3.07
export ANDROID_NDK_ROOT=<path to Android NDK installation>
export SDK_SETUP_ENV=Done
$ANDROID_NDK_ROOT must be set for the Andorid NDK build toolchain.

export ANDROID_NDK_ROOT=<absolute_path_to_android_ndk_directory>
Run the make instruction below in UDO package directory to compile the UDO DSP implementation library:

make dsp
The expected artifacts after compiling for DSP are

The UDO DSP implementation library: <UDO-Package>/libs/dsp_<dsp_arch_type>/libUdo<UDO-Package>ImplDsp.so

The UDO package registration library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>Reg.so

Note: The command must be run from the package root. dsp_v60 folder is created for all aarchs which are less than v68.

Implementing a UDO for DSP V68 or later

Qualcomm® Neural Processing SDK utilizes Qualcomm® AI Direct SDK to run UDO layers on DSP v68 or later. Therefore, a DSP implementation library based on Qualcomm® AI Direct SDK APIs is required to run a UDO package on DSP runtime. The UDO package generator tool will create the template file <OpName>ImplLibDsp.cpp and the user will need to implement the execution logic in the <OpName>Impl() function in the template file.

To have good performance and stability, it is required to avoid heap memory allocation in the completed <OpName>Impl() functions. The heap memory allocation includes but not limited to calling malloc, operator new, constructing STL container objects like std::vector with default allocator, and adding items like calling std::vector::push_back to STL container objects with default allocator. Please check here for more information.

Qualcomm® Neural Processing SDK UDO provides the support for Hexagon Vector Extensions (HVX) code and cost based scheduling.

The DSP runtime propagates unsigned 8-bit or unsigned 16-bit activation tensors between the network layers. But it has the ability to de-quantize data to floating point if required. Therefore users developing DSP kernels can expect either UINT_8, UINT_16 or FLOAT_32 activation tensors in and out of the operation, and thus can set the field data_type in the config file to one of these three settings. Refer to Qualcomm® AI Direct SDK for more details.

Compiling a UDO for DSP_V68 or later on device

This Qualcomm® Neural Processing SDK release supports building UDO DSP implementation libraries using Hexagon-SDK 4.x and Qualcomm® AI Direct SDK.

Set the environment variables $SNPE_UDO_ROOT

export SNPE_UDO_ROOT=<absolute_path_to_SnpeUdo_headers_directory>
Hexagon-SDK 4.0+ needs to be installed and set up. For Hexagon-SDK details, follow the setup instructions on $HEXAGON_SDK4_ROOT/docs/readme.html page, where $HEXAGON_SDK4_ROOT is the location of the Hexagon-SDK installation. Make sure $HEXAGON_SDK4_ROOT is set to use the Hexagon-SDK build toolchain. Also, set $HEXAGON_TOOLS_ROOT and $SDK_SETUP_ENV. Additionally, we need an extracted Qualcomm® AI Direct SDK (no need of Qualcomm® AI Direct SDK setup) for building the libraries. For Qualcomm® AI Direct SDK details, refer to the Qualcomm® AI Direct SDK documentation at $QNN_SDK_ROOT/docs/QNN/index.html page, where $QNN_SDK_ROOT is the location of the Qualcomm® AI Direct SDK installation. Set the $QNN_SDK_ROOT to the unzipped Qualcomm® AI Direct SDK location.

export HEXAGON_SDK_ROOT=<path to hexagon sdk installation>
export HEXAGON_SDK4_ROOT=<path to hexagon sdk 4.x installation>
export HEXAGON_TOOLS_ROOT=$HEXAGON_SDK_ROOT/tools/HEXAGON_Tools/8.4.09
export QNN_SDK_ROOT=<path to QNN sdk installation>
export ANDROID_NDK_ROOT=<path to Android NDK installation>
export SDK_SETUP_ENV=Done
$ANDROID_NDK_ROOT must be set for the Andorid NDK build toolchain.

export ANDROID_NDK_ROOT=<absolute_path_to_android_ndk_directory>
Run the make instruction below in UDO package directory to compile the UDO DSP implementation library:

make dsp
Run the make instruction below in UDO package directory to generate a library for offline cache generation:

make dsp_x86 X86_CXX=<path_to_x86_64_clang>
Run the make instruction below in UDO package directory to generate a library for Android ARM architecture:

make dsp_aarch64
Note: This should only be run on linux based devices. This should not be run for Windows based devices.

The expected artifacts after compiling for DSP are

The UDO DSP implementation library: <UDO-Package>/libs/dsp_v68/libUdo<UDO-Package>ImplDsp.so

The UDO package registration library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>Reg.so

The expected artifact after compiling for offline cache generation is

The UDO DSP implementation library: <UDO-Package>/libs/x86-64_linux_clang/libUdo<UDO-Package>ImplDsp.so

The expected artifact after compiling for Android ARM architecture is

The UDO DSP implementation library: <UDO-Package>/libs/arm64-v8a/libUdo<UDO-Package>ImplDsp_AltPrep.so

Note: The command must be run from the package root.

Table of Make Targets

Make Target

Runtime

Platform

Misc.

all

CPU, GPU, DSP

x86, arm64-v8a

all_x86

CPU

x86

all_android

CPU, GPU, DSP

arm64-v8a

reg

x86, arm64-v8a

reg_x86

x86

reg_android

arm64-v8a

cpu

CPU

x86, arm64-v8a

cpu_x86

CPU

x86

Same as all_x86

cpu_android

CPU

arm64-v8a

gpu

GPU

arm64-v8a

gpu_android

GPU

arm64-v8a

Same as gpu

dsp

DSP

dsp_android

DSP

Same as dsp

dsp_x86

DSP

dsp_aarch64

DSP

Note: By default, compiling for a runtime additionally compiles the corresponding registration library

Previous

Creating a UDO Package

Next

Compiling a UDO package for Windows

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
User-defined Operations
Preparing a model with UDO
Preparing a model with UDO
Updated: Jun 11, 2025 80-63442-2 Rev: BL
This section talks about the steps required to convert a framework model with user defined operations (UDO).

Converting a network model with UDO into DLC

snpe-<framework>-to-dlc tools support UDO functionality by accepting configuration file(s) with the option –udo_config_paths. For input UDO config file specifications, see Defining a UDO. Currently UDO functionality is supported on TensorFlow and ONNX models.

Note: Any modifications in the UDO configuration file should be followed up with re-generation of DLCs to reflect the changes.

Converting Tensorflow model with UDO to DLC

The following syntax showcases the way TensorFlow models can be converted using UDO:

snpe-tensorflow-to-dlc -i <input-tensorflow-model>
                       -d <input-name> <input-dim>
                       --out_node <output-node-name>
                       --udo_config_paths <input-model.json>
                       -o <output-model.dlc>
where the option –udo_config_paths allows users to specify the UDO configuration file to be used in the conversion.

See snpe-tensorflow-to-dlc and TensorFlow Model Conversion for further details.

Converting ONNX model with UDO to DLC

The following syntax showcases the way ONNX models can be converted using UDO:

snpe-onnx-to-dlc -i <input-onnx-model>
                 --udo_config_paths <input-model.json>
                 -o <output-model.dlc>
where the option –udo_config_paths allows users to specify the UDO configuration file to be used in the conversion.

See snpe-onnx-to-dlc and ONNX Model Conversion for further details.

Quantizing a DLC with UDO

Additionally, users may want to quantize converted models having UDOs to run on fixed-point runtimes. Qualcomm® Neural Processing SDK provides the tool snpe-dlc-quantize for this purpose. This is an offline tool that can be run on the host x86 platform. Since it runs inferences with a representative data-set in order to determine quantization ranges for all layers in the network including UDOs, users will need to provide a UDO package containing CPU reference implementation to the tool. Refer to Creating a UDO Package and Compiling a UDO package for further instructions on creating such a package for the x86 platform.

The following syntax showcases the way DLCs with UDOs can be quantized with snpe-dlc-quantize:

snpe-dlc-quantize --input_dlc <model.dlc>
                  --input_list <input-list.txt>
                  --udo_package_path <udo-registration-lib>
                  --output_dlc <quantized-model.dlc>
where the option –udo_package_path allows users to specify the absolute path to the UDO registration library. LD_LIBRARY_PATH must also be updated to include the runtime-specific artifacts generated from package compilation during x86 Host Compilation .

Note: If your UDO contains static inputs or parameters, you need to handle both floating-point and fixed-point data types in CPU reference implementation. Refer to provided example $SNPE_ROOT/examples/SNPE/NativeCpp/UdoExample/Conv2D/src/CPU/Conv.cpp for details.

Previous

Compiling a UDO package for Windows

Next

Running a model with UDO

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
User-defined Operations
Running a model with UDO
Running a model with UDO
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Executing neural networks with UDO

This section describes how to provide the set of libraries representing a UDO to Qualcomm® Neural Processing SDK, to execute network models containing the UDO. Execution requires the model containing the UDO be converted into DLC format using Qualcomm® Neural Processing SDK converter tools as explained in Preparing a model with UDO. Execution additionally requires the creation of registration and implementation libraries compatible with UDO APIs as described in Overview of UDO.
UDOs can be registered with a process and be utilized by any instance of Qualcomm® Neural Processing SDK that employs them throughout the lifetime of the process. They are registered with Qualcomm® Neural Processing SDK using the Snpe_Util_AddOpPackage() API:
SNPE_API
int Snpe_Util_AddOpPackage(const char* regLibraryPath );
Note that the absolute path to the registration library is explicitly provided to Qualcomm® Neural Processing SDK using the above API, whereas the implementation libraries can exist anywhere on the system that should be discovered by the library loader (using LD_LIBRARY_PATH on Unix systems).

In addition to the native C API, the Qualcomm® Neural Processing SDK provides a Java API for use in Android applications. The API is a part of the SNPE class:
public static boolean
addOpPackage(final Application application, String regLibraryPath)
Running snpe-net-run command-line tool with UDO-based DLC

This section outlines the use of snpe-net-run command line tool with a UDO-based DLC. The use of snpe-net-run is largely unchanged from its typical usage. The snpe-net-run tool registers a UDO registration library through the command line option –udo_package_path which accepts the absolute path to the registration library.

Example usage is as follows:

snpe-net-run --container <path_to_dlc> --input_list <path_to_input_list> --udo_package_path <path_to_registration_lib>
Running a UDO-based DLC on a signed process domain

To run on an signed process domain, the same principles as the above section on utilizing the snpe-net-run command line tool apply with a few extensions. Firstly, signed process domains only apply to the DSP target. Second, for a signed process domain to be created, an argument must be passed to the command line to request it. This comes in the form of the platform_options key:value argument pairing. To enable model execution on a signed process domain, simply add the following to the command line:

--use_dsp --platform_options unsignedPD:OFF
Example usage is as follows:

snpe-net-run --container <path_to_dlc> --input_list <path_to_input_list> --udo_package_path <path_to_registration_lib> --use_dsp --platform_options unsignedPD:OFF
Note: snpe-net-run requires implementation libraries to be discoverable by the system library loader.

Previous

Preparing a model with UDO

Next

Model Conversion

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
