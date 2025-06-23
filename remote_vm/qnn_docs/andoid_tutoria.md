


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

Tutorials and Examples
Code Examples
Android Tutorial
Android Tutorial
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Prerequisites

The Qualcomm® Neural Processing SDK has been set up following the Qualcomm (R) Neural Processing SDK Setup chapter.

The Tutorials Setup has been completed.

Introduction

This tutorial walks through the process of integrating the Qualcomm® Neural Processing SDK and snpe-platform-validator Java APIs within an Android application.

The Qualcomm® Neural Processing SDK and Platform Validator Java APIs are made available as an Android Archive (AAR) file which application developers include as a dependency of their applications.

Gradle project dependency

allprojects {
    repositories {
        ...
        flatDir {
            // Marks the directory as a repository for
            // dependencies. Place the snpe-release.aar
            // in the directory below.
            dirs 'libs'
        }
    }
}
...
dependencies {
    ...
    // This adds the Qualcomm (R) Neural Processing SDK as a project dependency
    compile(name: 'snpe-release', ext:'aar')
    // This adds the Platform Validator tool (optional) as a project dependency
    compile(name: 'platformvalidator-release', ext:'aar')
}
In case both the archives are required in a project, “pickFirst” need to be used in gradle to avoid library conflicts.

android {
    ...
    packagingOptions {
        pickFirst 'lib/arm64-v8a/libc++_shared.so'
        pickFirst 'lib/arm64-v8a/libSNPE.so'
        pickFirst 'lib/arm64-v8a/libSnpeHtpV73Skel.so'
        pickFirst 'lib/arm64-v8a/libSnpeHtpV73tub.so '
        pickFirst 'lib/arm64-v8a/libSnpeHtpV69Skel.so'
        pickFirst 'lib/arm64-v8a/libSnpeHtpV69tub.so '
        pickFirst 'lib/arm64-v8a/libSnpeHtpV68Skel.so'
        pickFirst 'lib/arm64-v8a/libSnpeHtpV68tub.so '
        pickFirst 'lib/arm64-v8a/libSnpeDspV66Skel.so'
        pickFirst 'lib/arm64-v8a/libSnpeDspV66Stub.so '
        pickFirst 'lib/arm64-v8a/libSnpeDspV65Skel.so'
        pickFirst 'lib/arm64-v8a/libSnpeDspV65Stub.so '
        pickFirst 'lib/arm64-v8a/libSnpeHtpPrepare.so '
    }
Platform Validator Java API Overview

Once the optional dependency of platform validator is added, the Platform Validator classes under the com.qualcomm.qti.platformvalidator package will be available in the application classpath. All applications will first create an object of Platform Validator with required runtime and then use that object to call validation APIs as described below.

Using Platform Validator

//Platform validator class for object creation
import com.qualcomm.qti.platformvalidator.PlatformValidator;
//available runtimes are defined in this class
import com.qualcomm.qti.platformvalidator.PlatformValidatorUtil;
...
//This create platform validator object for GPU runtime class
PlatformValidator pv = new PlatformValidator(PlatformValidatorUtil.Runtime.GPU);
// To check in general runtime is working use isRuntimeAvailable
boolean check = pv.isRuntimeAvailable(getApplication());
// To check Qualcomm (R) Neural Processing SDK runtime is working use runtimeCheck
boolean check = pv.runtimeCheck(getApplication());
//To get core version use libVersion api
String str = pv.coreVersion(getApplication());
//To get core version use coreVersion api
String str = pv.coreVersion(getApplication());
//List of available runtimes
PlatformValidatorUtil.Runtime.CPU
PlatformValidatorUtil.Runtime.GPU
PlatformValidatorUtil.Runtime.DSP
PlatformValidatorUtil.Runtime.GPU_FLOAT16
PlatformValidatorUtil.Runtime.AIP
Qualcomm® Neural Processing SDK Java API Overview

Once the dependency is added, the Qualcomm® Neural Processing SDK classes under the com.qualcomm.qti.snpe package will be available in the application classpath.

Most applications will follow the following pattern while using a neural network:

Select the neural network model and runtime target

Create one or more input tensor(s)

Populate one or more input tensor(s) with the network input(s)

Forward propagate the input tensor(s) through the network

Process the network output tensor(s)

The sections below describe how to implement each step described above.

Configuring a Neural Network

The code excerpt below illustrates how to configure and build a neural network using the JAVA APIs.

final SNPE.NeuralNetworkBuilder builder = new SNPE.NeuralNetworkBuilder(application)
    // Allows selecting a runtime order for the network.
    // In the example below use DSP and fall back, in order, to GPU then CPU
    // depending on whether any of the runtimes is available.
    .setRuntimeOrder(DSP, GPU, CPU)
    // Loads a model from DLC file
    .setModel(new File("<model-path>"));
final NeuralNetwork network = builder.build();
...
// Calling release() once the application no longer needs the network instance
// is highly recommended as it releases native resources. Alternatively the
// resources will be released when the instance is garbage collected.
network.release();
Multiple ways to load a model

The SDK currently supports loading a model from a java.io.File within the Android device or from an java.io.FileInputStream.

Creating an Input Tensor

The code excerpt below illustrates how to create an input tensor and fill it with the input data.

final FloatTensor tensor = network.createFloatTensor(height, width, depth);
float[] input = // input data from application...
// Fills the tensor fully
tensor.write(input, 0, input.length);
// Fills the tensor at a specific position
tensor.write(input[0], y, x, z);
// Fills the input tensors map which will be passed to execute()
final Map<String, FloatTensor> inputsMap;
inputsMap.put(/*network input name*/, tensor);
Notes about tensors

Reuse of input tensors Developers are encouraged to re-use the same input tensor instance across multiple calls to NeuralNetwork.execute(..). Tensors are memory bound types and the effect of creating new instances for every execute call may have an impact in the application responsiveness.

Batch write to tensor Tensors are backed by native memory and writing multiple values at once, if possible, will reduce the overhead of crossing the Java and Native boundaries.

Propagate Input Tensors Through the Network

The excerpt of code below shows how to propagate input tensors through the neural network.

final Map<String, FloatTensor> outputsMap = network.execute(inputsMap);
for (Map.Entry<String, FloatTensor> output : outputsMap.entrySet()) {
    // An output tensor for each output layer will be returned.
}
Process the Neural Network Output

The excerpt of code below shows how to read the output tensor of an output layer.

final Map<String, FloatTensor> outputsMap = network.execute(inputsMap);
for (Map.Entry<String, FloatTensor> output : outputsMap.entrySet()) {
    final FloatTensor tensor = output.getValue();
    final float[] values = new float[tensor.getSize()];
    tensor.read(values, 0, values.length);
    // Process the output ...
}
Release Input and Output Tensors

Tensors are encouraged to be reused to reduce the application overhead. However, once the application no longer needs the input and/or output tensors, it is highly recommended to call release() on them to release native resources. This is particularly important for multi-threaded applications.

// Release input tensors
for (FloatTensor tensor: inputsMap) {
    tensor.release();
}
// Release output tensors
for (FloatTensor tensor: outputsMap) {
    tensor.release();
}
Android Sample Application

The Qualcomm® Neural Processing Android SDK includes a sample application that showcases the SDK features. The application source code is in:

$SNPE_ROOT/examples/SNPE/android/image-classifiers

Here is a screenshot of the sample:

../images/snpe_root_sample_images.png
Note that Qualcomm® Neural Processing SDK provides the following AAR file which include necessary binaries:

snpe-release.aar: Native binaries compiled with clang using libc++ STL. Available at $SNPE_ROOT/lib/android/snpe-release.aar

Please set environment variable SNPE_AAR to this AAR file. Also, set the environment variable JAVA_HOME to Java installation location. For example,

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
To build this sample, include the Qualcomm® Neural Processing SDK AAR as described above and build with the following commands.

export SNPE_AAR=snpe-release.aar
cd $SNPE_ROOT/examples/SNPE/android/image-classifiers
bash ./setup_inceptionv3.sh
cp $SNPE_ROOT/lib/android/${SNPE_AAR} app/libs/snpe-release.aar

# exporting gradle user_home is optional but suggested if USER_HOME storage is low
export GRADLE_USER_HOME=<local path for caching gradle files>
export ANDROID_SDK_ROOT=<path to android-sdk-9>
./gradlew assembleDebug
Note:

When building the app through Android Studio with JVM VERSION >=16, you may observe Unable to make field private final java.lang.String java.io.File.path accessible: module java.base does not “opens java.io” to unnamed module. To solve this issue, either update the JAVA_HOME to JDK < 16, or add -–add-opens java.base/java.io=ALL-UNNAMED in gradle.properties.

To build the sample, import the network model and sample images by invoking the setup_inceptionv3.sh script as described above.

If building produces the error gradle build failure due to “SDK location not found”, set the environment variable ANDROID_HOME to point to your SDK location.

Building the sample code with gradle requires Java 8.

The unsigned PD switch in the app can only be set/unset once when the model is loaded. On returning back to the menu screen and changing the option might result in unexpected behaviour.

Running networks with UDO on a specific runtime requires UDO packages for the corresponding runtime to be pushed on the device (Run setup_inceptionv3.sh). On running a UDO network without UDO package may result in unexpected behaviour.

After the build successfully completes, the output APK can be found in the application build folder:

$SNPE_ROOT/examples/SNPE/android/image-classifiers/app/build/outputs/apk/debug

Previous

C API Guidelines

Next

Windows Tutorial

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
