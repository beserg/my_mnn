

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

Introduction
Introduction
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Purpose

This document provides the Qualcomm® Neural Processing software development kit (SDK) user guide and API reference guide.

Note

Qualcomm® Neural Processing SDK is also referred to as Snapdragon Neural Processing Engine (SNPE) in the source code and documentation.

Conventions

Function declarations, function names, type declarations, filenames, directory names and library names are shown in a different font. For example: #include

Commands and code samples appear in a specially formatted code section. For example:

output = (input - offset) * scale.
Mathematical expressions appear in a specially formatted math section. For example:

Environment variables appear preceded by $, for example $SNPE_ROOT.

Release Notes

Release notes are available as ReleaseNotes.txt in the SDK.

Technical Support

For assistance or clarification on information in this guide, submit a case to Qualcomm Technologies, Inc. at https://support.cdmatech.com. If you do not have access to the CDMATech Support website, register for access or send email to support.cdmatech@qti.qualcomm.com.

Next

Overview

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

Overview
Supported Snapdragon Devices
Windows
Overview
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Capabilities
The Qualcomm® Neural Processing SDK is a Qualcomm Snapdragon software accelerated runtime for the execution of deep neural networks. With Qualcomm® Neural Processing SDK, users can:

Execute an arbitrarily deep neural network

Execute the network on the Snapdragon CPU, the Adreno GPU or the Hexagon DSP.

Debug the network execution on x86 Ubuntu Linux

Convert PyTorch, TFLite, ONNX, and TensorFlow models to a Qualcomm® Neural Processing SDK Deep Learning Container (DLC) file

Quantize DLC files to 8 or 16 bit fixed point for running on the Hexagon DSP

Debug and analyze the performance of the network with Qualcomm® Neural Processing SDK tools

Integrate a network into applications and other code via C++ or Java

Model Workflow
../images/snpe.png
Model training is performed on a popular deep learning framework (PyTorch, TFLite, ONNX, and TensorFlow models are supported by Qualcomm® Neural Processing SDK.) After training is complete the trained model is converted into a DLC file that can be loaded into the Qualcomm® Neural Processing SDK runtime. This DLC file can then be used to perform forward inference passes using one of the Snapdragon accelerated compute cores.

The basic Qualcomm® Neural Processing SDK workflow consists of only a few steps:

Convert the network model to a DLC file that can be loaded by Qualcomm® Neural Processing SDK.

Optionally quantize the DLC file for running on the Hexagon DSP.

Prepare input data for the model.

Load and execute the model using Qualcomm® Neural Processing SDK runtime.

Supported Snapdragon Devices
Linux/Android
Snapdragon Device/Chip

Supported Tool Chains

CPU

GPU

DSP Hexagon Arch

AIP

SD 8 Gen 4 (SM8750)

aarch64-android

Yes

Yes

V79

No

SD 8 Gen 3 (SM8650)

aarch64-android

Yes

Yes

V75

No

SD 8 Gen 2 (SM8550)

aarch64-android

Yes

Yes

V73

No

SD 8s Gen 3 (SM8635)

aarch64-android

Yes

Yes

V73

No

SD 8+ Gen 1 (SM8475)

aarch64-android

Yes

Yes

V69

No

SD 8 Gen 1 (SM8450)

aarch64-android

Yes

Yes

V69

No

888+ (SM8350P)

aarch64-android

Yes

Yes

V68

No

888 (SM8350)

aarch64-android

Yes

Yes

V68

No

7+ Gen 3 (SM7675)

aarch64-android

Yes

Yes

V73

No

7 Gen 1 (SM7450)

aarch64-android

Yes

Yes

V69

No

778G (SM7325)

aarch64-android

Yes

Yes

V68

No

QCM6490

aarch64-android, aarch64-ubuntu-gcc9.4, aarch64-oe-linux-gcc11.2

Yes

Yes

V68

No

865 (SM8250)

aarch64-android

Yes

Yes

V66

Yes

765 (SM7250)

aarch64-android

Yes

Yes

V66

Yes

750G (SM7225)

aarch64-android

Yes

Yes

V66

Yes

690 (SM6350)

aarch64-android

Yes

Yes

V66

Yes

QRB5165U

aarch64-ubuntu-gcc9.4

Yes

Yes

V66

Yes

QRB5165LE

aarch64-oe-linux-gcc9.3, aarch64-oe-linux-gcc11.2

Yes

Yes

V66

Yes

QCS7230LA

aarch64-android

Yes

Yes

V66

Yes

QCS7230LE

aarch64-oe-linux-gcc9.3, aarch64-oe-linux-gcc11.2

Yes

Yes

V66

Yes

695 (SM6375)

aarch64-android

Yes

Yes

V66

No

680 (SM6225)

aarch64-android

Yes

Yes

V66

No

480 (SM4350/6325)

aarch64-android

Yes

Yes

V66

No

460 (SM4250)

aarch64-android

Yes

Yes

V66

No

662 (SM6115)

aarch64-android

Yes

Yes

V66

No

QCS610LA

aarch64-android

Yes

Yes

V66

No

QCS610LE

aarch64-oe-linux-gcc9.3

Yes

Yes

V66

No

QCS410LA

aarch64-android

Yes

Yes

V66

No

QCS410LE

aarch64-oe-linux-gcc9.3

Yes

Yes

V66

No

QCM4290

aarch64-android

Yes

Yes

V66

No

QCM6125

aarch64-android

Yes

Yes

V66

No

QRB4210LE

aarch64-oe-linux-gcc9.3

Yes

Yes

V66

No

QCM4490

aarch64-android

Yes

Yes

N/A

No

XR2 Gen 2 (SXR2230P)

aarch64-android

Yes

Yes

V69

No

XR2 Gen 2 (SXR2250P)

aarch64-android

Yes

Yes

V69

No

QCS/QCM8550LA

aarch64-android

Yes

Yes

V73

No

QCS/QCM8550LE

aarch64-oe-linux-gcc11.2

Yes

Yes

V73

No

QCS9100

aarch64-oe-linux-gcc11.2

Yes

Yes

V73

No

QCS/QCM6690

aarch64-android

Yes

Yes

V73

No

QCS/QCM2290

aarch64-android

Yes

Yes

N/A

No

QCS8625

aarch64-oe-linux-gcc11.2

Yes

No

V75

No

Windows
Snapdragon Device/Chip

Supported Tool Chains

CPU

GPU

DSP Hexagon Arch

AIP

Snapdragon 8cx Gen 4 (SC8380XP)

aarch64-windows-msvc, arm64x-windows-msvc

Yes

No

V73

No

Snapdragon 8cx Gen 3 (SC8280X)

aarch64-windows-msvc

Yes

No

V68

No

Snapdragon 8cx Gen 2 (SC8180X)

aarch64-windows-msvc

Yes

No

V66

No

Snapdragon 7c gen 2 (SC7280X)

aarch64-windows-msvc

Yes

No

V68

No

Note

See Windows ARM64X Support for more information about arm64x-windows-msvc toolchain.

What’s included in the SDK
Linux/Android
SDK Asset

Type

Compiler

C++ STL

Description

bin/x86_64-linux-clang

binary

clang

libc++ 14

x86 Linux binaries

bin/aarch64-android

binary

clang

libc++

Aarch64 Android binaries

bin/aarch64-ubuntu-gcc9.4

binary

gcc9.4

gnustl

Aarch64 Ubuntu binaries

bin/aarch64-oe-linux-gcc8.2

binary

gcc8.2

gnustl

Aarch64 Linux binaries

bin/aarch64-oe-linux-gcc9.3

binary

gcc9.3

gnustl

Aarch64 Linux binaries

bin/aarch64-oe-linux-gcc11.2

binary

gcc11.2

gnustl

Aarch64 Linux binaries

lib/android

lib

-

-

Android aar file used to include SNPE into your application

lib/x86_64-linux-clang

lib

clang

libc++ 14

x86 Linux libraries

lib/aarch64-android

lib

clang

libc++

Aarch64 Android libraries

lib/hexagon-<dsp_version>

lib

-

-

Hexagon DSP runtime libraries

lib/aarch64-ubuntu-gcc9.4

lib

gcc9.4

gnustl

Aarch64 Ubuntu libraries

lib/aarch64-oe-linux-gcc8.2

lib

gcc8.2

gnustl

Aarch64 Linux libraries

lib/aarch64-oe-linux-gcc9.3

lib

gcc9.3

gnustl

Aarch64 Linux libraries

lib/aarch64-oe-linux-gcc11.2

lib

gcc11.2

gnustl

Aarch64 Linux libraries

lib/python

lib

-

-

Qualcomm (R) Neural Processing SDK Python model tools modules

include/SNPE

include dir

-

-

Qualcomm (R) Neural Processing SDK API header files

examples

examples dir

-

-

Source code sample applications in Native C, C++ and Android Java

docs

documents

-

-

Reference Guide

benchmarks

scripts

-

-

Benchmark framework to gather runtime performance data on devices

Windows
SDK Asset

Type

Compiler

C++ STL

Description

bin/x86_64-windows-msvc

binary

MSVC

MSVC

x86_64 Windows binaries

bin/aarch64-windows-msvc

binary

MSVC

MSVC

ARM64 Windows binaries

bin/arm64x-windows-msvc

binary

MSVC

MSVC

ARM64X Windows binaries

lib/x86_64-windows-msvc

lib

MSVC

MSVC

x86_64 Windows libraries

lib/aarch64-windows-msvc

lib

MSVC

MSVC

ARM64 Windows libraries

lib/arm64x-windows-msvc

lib

MSVC

MSVC

ARM64X Windows libraries

lib/hexagon-v66

lib

-

-

Hexagon DSP v66 runtime libraries

lib/hexagon-v68

lib

-

-

Hexagon DSP v68 runtime libraries

lib/hexagon-v73

lib

-

-

Hexagon DSP v73 runtime libraries

lib/python

lib

-

-

Qualcomm (R) Neural Processing SDK Python model tools modules

include/SNPE

include dir

-

-

Qualcomm (R) Neural Processing SDK API header files

examples

examples dir

-

-

Source code sample applications in Native C++

docs

documents

-

-

Reference Guide

share

resources

-

-

Qualcomm (R) Neural Processing SDK Udo resources

Important Files and Locations
$SNPE_ROOT refers to the base directory where the SDK is installed.

Linux/Android
File

Type

Details

Location

envsetup.sh

script

Sets up environment variables needed to run SDK tools and binaries

$SNPE_ROOT/bin

snpe-onnx-to-dlc

script

Converts an ONNX model to a DLC file

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-tensorflow-to-dlc

script

Converts a TensorFlow model to a DLC file

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-tflite-to-dlc

script

Converts a TfLite model to a DLC file

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-pytorch-to-dlc

script

Converts a Pytorch model to a DLC file

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-dlc-quant

executable

Quantize a DLC file using 8/16 bit quantization

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-dlc-graph-prepare

executable

Prepares (offline) a graph for HTP on the host

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-dlc-quantize

script

Invokes snpe-dlc-quant and snpe-dlc-graph-prepare (for backward compatibility)

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-diagview

executable

Displays Neural Processing SDK timing output

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-dlc-info

script

Prints DLC file information

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-dlc-viewer

script

Displays a DLC file as an HTML file

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-dlc-diff

script

Compares two different DLC files

$SNPE_ROOT/bin/x86_64-linux-clang

snpe-udo-package-generator

executable

Generates a UDO package

$SNPE_ROOT/bin/x86_64-linux-clang

snpe_bench.py

script

Executes DLC model on device and collect benchmark information

$SNPE_ROOT/benchmarks

snpe-net-run

executable

Executes neural networks using SDK APIs

$SNPE_ROOT/bin/x86_64-linux-clang

$SNPE_ROOT/bin/aarch64-android

$SNPE_ROOT/bin/aarch64-oe-linux-gcc8.2

$SNPE_ROOT/bin/aarch64-oe-linux-gcc9.3

libSNPE.so

library

Qualcomm® Neural Processing SDK runtime for host and device development

$SNPE_ROOT/lib/x86_64-linux-clang

$SNPE_ROOT/lib/aarch64-android

$SNPE_ROOT/lib/aarch64-oe-linux-gcc8.2

$SNPE_ROOT/lib/aarch64-oe-linux-gcc9.3

libHtpPrepare.so

library

Library for offline graph preparation for HTP

$SNPE_ROOT/lib/x86_64-linux-clang

libSnpeDspV66Skel.so

library

Hexagon DSP runtime library for v66 targets

$SNPE_ROOT/lib/hexagon-v66/unsigned

libSnpeHtpVxxSkel.so

library

Hexagon DSP runtime library for v68/69/73/75/79 targets

$SNPE_ROOT/lib/hexagon-v68/unsigned

$SNPE_ROOT/lib/hexagon-v69/unsigned

$SNPE_ROOT/lib/hexagon-v73/unsigned

$SNPE_ROOT/lib/hexagon-v75/unsigned

$SNPE_ROOT/lib/hexagon-v79/unsigned

Windows
File

Type

Details

Location

snpe-net-run.exe

executable

Executes neural networks using SDK APIs

$SNPE_ROOT/bin/x86_64-windows-msvc

$SNPE_ROOT/bin/aarch64-windows-msvc

$SNPE_ROOT/bin/arm64x-windows-msvc

snpe-onnx-to-dlc

script

Converts an ONNX model to a DLC file

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-tensorflow-to-dlc

script

Converts a TensorFlow model to a DLC file

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-dlc-quant

executable

Quantizes a DLC file using 8/16 bit quantization

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-dlc-graph-prepare

executable

Prepares (offline) a graph for HTP on the host

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-diagview

executable

Displays Neural Processing SDK timing output

$SNPE_ROOT/bin/x86_64-windows-msvc

$SNPE_ROOT/bin/aarch64-windows-msvc

snpe-dlc-info

script

Prints DLC file information

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-dlc-viewer

script

Displays a DLC file as an HTML file

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-dlc-diff

script

Compares two different DLC files

$SNPE_ROOT/bin/x86_64-windows-msvc

snpe-udo-package-generator

executable

Generates UDO package

$SNPE_ROOT/bin/x86_64-windows-msvc

envsetup.ps1

script

Sets up environment variables needed to run SDK tools and binaries

$SNPE_ROOT/bin

SNPE.dll

library

Qualcomm® Neural Processing SDK runtime for host and device development

$SNPE_ROOT/lib/aarch64-windows-msvc

$SNPE_ROOT/lib/arm64x-windows-msvc

$SNPE_ROOT/lib/x86_64-windows-msvc

SnpeHtpPrepare.dll

library

Library for offline graph preparation for HTP

$SNPE_ROOT/lib/aarch64-windows-msvc

$SNPE_ROOT/lib/arm64x-windows-msvc

libSnpeDspV66Skel.so

library

Hexagon DSP runtime library for v66 targets

$SNPE_ROOT/lib/hexagon-v66/unsigned

libSnpeHtpV68Skel.so

library

Hexagon DSP runtime library for v68 targets

$SNPE_ROOT/lib/hexagon-v68/unsigned

libSnpeHtpV73Skel.so

library

Hexagon DSP runtime library for v73 targets

$SNPE_ROOT/lib/hexagon-v73/unsigned

Previous

Introduction

Next

Setup

On this page
Capabilities
Model Workflow
Supported Snapdragon Devices
Linux/Android
Windows
What’s included in the SDK
Linux/Android
Windows
Important Files and Locations
Linux/Android
Windows
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

Setup
Machine Learning Frameworks
Setup
Updated: Jun 11, 2025 80-63442-2 Rev: BL
This page outlines the required setup to use the Qualcomm® Neural Processing SDK. The page is split into Linux Platform Dependencies , Windows Platform Dependencies and Environment Setup.

Linux Platform Dependencies
Host OS
The Qualcomm® Neural Processing SDK is verified with the Ubuntu 22.04 LTS (Focal) Linux host operating system. The Qualcomm® Neural Processing SDK is also verified to work in the Windows Subsystem for Linux (WSL2) environment, version 1.1.3.0, but currently limited to Linux host runnable artifacts such as converters, model generation and run tools. To set up your own WSL2 environment, follow the instructions provided by Microsoft at https://learn.microsoft.com/en-us/windows/wsl/install.

Python
The Qualcomm® Neural Processing SDK distribution is supported on python3. Download and installation instructions vary based on the host OS. The SDK has been tested with python3.10. If python3.10 is not already installed on your system, you may install it with the following commands:

$ sudo apt-get update
$ sudo apt-get install python3.10 python3-distutils libpython3.10
Virtual Environment (VENV)

Whether managing multiple python installations or just to keep your system python installation clean we recommend using python virtual environments.

$ sudo apt-get install python3.10-venv
$ python3.10 -m venv "<PYTHON3.10_VENV_ROOT>"
$ source <PYTHON3.10_VENV_ROOT>/bin/activate
Note

If your environment is in WSL, <PYTHON3.10_VENV_ROOT> must be under $HOME.

Additional Packages: To interact with Qualcomm® Neural Processing components and tools, additional python3 packages must be available in your environment. This Qualcomm® Neural Processing SDK release is verified to work with the following package versions:

Package

Version

absl-py

2.1.0

decorator

4.4.2

invoke

2.0.0

joblib

1.4.0

lxml

4.6.3

Mako

1.1.0

matplotlib

3.3.4

numpy

1.26.4

opencv-python

4.5.4.58

packaging

24.0

pandas

2.0.1

pathlib2

2.3.6

Pillow

10.2.0

protobuf

3.19.6

PyYAML

5.3

scipy

1.10.1

six

1.16.0

tabulate

0.8.5

typing-extensions

4.10.0

xlsxwriter

1.2.2

Run the following script to check and install missing dependencies:

$ python3 -m pip install --upgrade pip
$ ${SNPE_ROOT}/bin/check-python-dependency
Note

To set SNPE_ROOT see Environment Setup for Linux. Matplotlib with 3.5.0 and Pandas with 2.0.2 have been verified on Windows.

Linux
Clang++ is required to compile artifacts for the x86_64 target. This Qualcomm® Neural Processing SDK release is verified to work with Clang 14.

Run the following script to check and install missing Linux dependencies:

# Note: Run the following command as administrator/root to install system libraries.

 $ sudo bash ${SNPE_ROOT}/bin/check-linux-dependency.sh
Note

To set SNPE_ROOT see Environment Setup for Linux.

ML Frameworks
To convert ML models trained on different frameworks into intermediate representations consumable by the Qualcomm® Neural Processing SDK you may need to download and install the corresponding frameworks on your host machine.

This Qualcomm® Neural Processing SDK release is verified to work with the following versions of the ML training frameworks:

TensorFlow: tf-2.10.1

TFLite: tflite-2.3.0

PyTorch: - SNPE/QNN: torch-1.13.1 - QAIRT: torch-2.4.0 + onnx-1.16.1

ONNX: onnx-1.16.1

ONNX Runtime: onnxruntime-1.18.0

The Qualcomm® Neural Processing SDK allows users to compile user defined operation packages to use with the runtimes such as CPU, GPU, and DSP. You may need to install appropriate cross-compilation toolchains to compile such packages for a particular backend.

MAKE:

Operation packages are compiled with a front-end that is written with makefiles. If make is not available on your host machine, install it using the following command:

$ sudo apt-get install make
OE Linux

Following section provides steps to acquire the gcc toolchain for targets which are based on Linux distributions like Yocto or Ubuntu. In this case taking Yocto Kirkstone as an example, which requires GCC toolchain. To support Yocto Kirkstone based devices, the SDK libraries are required to compile with GCC-11.2.

If the required compiler is not available in your system PATH, please use the below steps to install the dependency and make them available in your PATH.

Please follow the below steps to download and install eSDK that contains cross compiler toolchain -

wget https://artifacts.codelinaro.org/artifactory/qli-ci/flashable-binaries/qimpsdk/qcm6490/x86/qcom-6.6.28-QLI.1.1-Ver.1.1_qim-product-sdk-1.1.3.zip
unzip qcom-6.6.28-QLI.1.1-Ver.1.1_qim-product-sdk-1.1.3.zip
umask a+rx
sh qcom-wayland-x86_64-qcom-multimedia-image-armv8-2a-qcm6490-toolchain-ext-1.0.sh
export ESDK_ROOT=<path of installation directory>
cd $ESDK_ROOT
source environment-setup-armv8-2a-qcom-linux
Android NDK:

This Qualcomm® Neural Processing SDK release is verified to work with Android NDK version r26c, which is available at https://dl.google.com/android/repository/android-ndk-r26c-linux.zip. After download, extract the zip file and add the extracted location to your PATH environment variable.

Use the following commands to set the environment to use Android NDK and check configuration:

$ export ANDROID_NDK_ROOT=<PATH-TO-NDK>
$ export PATH=${ANDROID_NDK_ROOT}:${PATH}
$ ${SNPE_ROOT}/bin/envcheck -n
Clang 14:

This Qualcomm® Neural Processing SDK release is verified to work with Clang 14. See Environment Setup for Linux for Clang installation.

To check if the environment is setup properly to use clang-14, the following command can be used:

$ ${SNPE_ROOT}/bin/envcheck -c
CPU: Compiler used for X86_64 is clang 14. ARM CPU targets are built using Android NDK (see Android NDK).

GPU: GPU backend kernels are written based on OpenCL. The GPU operations must be implemented based on OpenCL headers with OpenCL version 1.2 or higher.

DSP: Compiling for DSP requires the use of the Hexagon toolchain available from Qualcomm® Hexagon SDK.

Hexagon SDK installation on Linux

The Hexagon SDK versions are available at https://developer.qualcomm.com/software/hexagon-dsp-sdk/tools.

Hexagon SDK installation on WSL2 Ubuntu 22.04

Download Hexagon SDKs from https://qpm.qualcomm.com in a Linux PC.

Copy Hexagon SDKs from Linux PC to Windows PC.

This Qualcomm® Neural Processing release is verified to work with:

Backend

Hexagon Architecture

Hexagon SDK Version

Hexagon Tools Version

DSP

V75

5.4.0

8.7.03

DSP

V73

5.4.0

8.6.02

DSP

V69

4.3.0

8.5.03

DSP

v68

4.2.0

8.4.09

DSP

v66

4.1.0

8.4.06

Additionally, compiling for DSP requires clang++.

Further setup instructions are available at $HEXAGON_SDK_ROOT/docs/readme.html, where HEXAGON_SDK_ROOT is the location of the Hexagon SDK installation.

Note

Hexagon SDK tools version 8.7.03/8.6.02/8.5.03/8.4.09/8.4.06 is not currently pre-packaged into Hexagon SDK version 5.4.0/4.3.0/4.2.0/4.1.0 respectively. It needs to be downloaded separately and placed at the location $HEXAGON_SDK_ROOT/tools/HEXAGON_Tools/ .

Java
Java environment is required to integrate Qualcomm® Neural Processing SDK with Android applications (for example Image Classifiers). This Qualcomm® Neural Processing SDK release is verified to work with Java 8. If Java 8 is not available on your host machine, install it using the following command:

$ sudo apt-get install openjdk-8-jdk
Windows Platform Dependencies
Host OS
Qualcomm® Neural Processing SDK is verified with Windows 10 and Windows 11 OS on a x86 host platform and with Windows 11 OS on a Snapdragon platform.

Python
The Qualcomm® Neural Processing SDK distributions are only supported on Python3. The SDK has been tested with Python 3.10. Install Python 3.10 from: https://www.python.org/ftp/python/3.10.11/python-3.10.11-amd64.exe.

After installation, check dependencies by running the following script in PowerShell terminal window.

$ py -3.10 -m venv "<PYTHON3.10_VENV_ROOT>"
$ & "<PYTHON3.10_VENV_ROOT>\Scripts\Activate.ps1"
$ python -m pip install --upgrade pip
$ python "${SNPE_ROOT}\bin\check-python-dependency"
To work with TensorFlow 1.15.0, download Python 3.6 at https://www.python.org/ftp/python/3.6.8/python-3.6.8-amd64.exe

After installation, as above, check dependencies by running the following script in PowerShell terminal window.

$ py -3.6 -m venv "<PYTHON3.6_VENV_ROOT>"
$ & "<PYTHON3.6_VENV_ROOT>\Scripts\Activate.ps1"
$ python -m pip install --upgrade pip
$ python "${SNPE_ROOT}\bin\check-python-dependency"
Note

SNPE_ROOT environment variable can be set by following Environment Setup for Windows.

Windows
Compiling artifacts for the Windows targets requires Visual Studio setup.

Qualcomm® Neural Processing SDK was verified with the following build environment.

Visual Studio 2022 17.5.1

MSVC v143 - VS 2022 C++ x64/x86 build tools - 14.34

MSVC v143 - VS 2022 C++ ARM64/ARM64EC build tools - 14.34

Windows SDK 10.0.22621.0

MSBuild support for LLVM (clang-cl) toolset

C++ Clang Compiler for Windows (15.0.1)

C++ CMake tools for Windows

Run the following script in Powershell terminal as Administrator to check and install missing Windows dependencies:

$ & "${SNPE_ROOT}/bin/check-windows-dependency.ps1"
Note

You can set SNPE_ROOT environment variable by following Environment Setup for Windows.

To just check if the environment satisfies the requirements, run the following command in your Developer Powershell terminal:

$ & "${SNPE_ROOT}/bin/envcheck.ps1" -m
Environment Setup
Linux
After Linux Platform Dependencies have been satisfied, the user environment can be set with the provided envsetup.sh script.

Open a command shell on Linux host and run:

$ source ${SNPE_ROOT}/bin/envsetup.sh
This will set/update the following environment variables:

SNPE_ROOT

PYTHONPATH

PATH

LD_LIBRARY_PATH

${SNPE_ROOT} represents the full path to Qualcomm® Neural Processing SDK root.

The SNPE API headers are in ${SNPE_ROOT}/include/SNPE.

The tools are in ${SNPE_ROOT}/bin/x86_64-linux-clang.

Target specific libraries are in ${SNPE_ROOT}/lib/*/.

Windows
After Windows Platform Dependencies have been satisfied, the user environment can be set with the provided envsetup.ps1 script.

First, Open Developer PowerShell for VS2022 as Administrator.

$ Set-ExecutionPolicy RemoteSigned
Then, execute the following script:

$ & "${SNPE_ROOT}\bin\envsetup.ps1"
This will set/update the following environment variables:

SNPE_ROOT

${SNPE_ROOT} represents the full path to Qualcomm® Neural Processing SDK root.

The SNPE API headers are in ${SNPE_ROOT}/include/SNPE.

The tools are in ${SNPE_ROOT}/bin/x86_64-linux-clang.

Target specific libraries are in ${SNPE_ROOT}/lib/*/.

Note

envsetup.ps1 is only required to be executed on Windows host.

Machine Learning Frameworks
For information on which versions Qualcomm® Neural Processing SDK was verified with see ML Frameworks.

Install TensorFlow as a standalone Python module using https://pypi.org/project/tensorflow/2.10.1/ If installing tf-1.15.0, refer to the Python 3.6 installation section above. To ensure TensorFlow is in your PYTHONPATH by running the following command.

python -c "import tensorflow"
Note

Installing versions of TensorFlow besides 1.15.0 or 2.10.1 may update dependencies (e.g. numpy).

Install ONNX as a standalone Python module using https://pypi.org/project/onnx/1.16.1/ and ensure ONNX is in your PYTHONPATH by running the following command:

python -c "import onnx"
Install ONNX Runtime as a standalone Python module using https://pypi.org/project/onnxruntime/1.18.0/ and ensure ONNX Runtime is in your PYTHONPATH by running the following command.

python -c "import onnxruntime"
Install TFLite as a standalone Python module using https://pypi.org/project/tflite/2.3.0/ and ensure TFLite is in your PYTHONPATH by running the following command:

python -c "import tflite"
SNPE/QNN - Install PyTorch v1.13.1 as a standalone Python module using https://pytorch.org/get-started/previous-versions/#v1131 and ensure PyTorch is in your PYTHONPATH by running the following command:

python -c "import torch"
QAIRT - Install PyTorch v2.4.0 as a standalone Python module using https://pytorch.org/get-started/previous-versions/#v240 - Install ONNX as a standalone Python module using https://pypi.org/project/onnx/1.16.1/ - Ensure PyTorch is in your PYTHONPATH by running the following command:

python -c "import torch"
python -c "import onnx"
Previous

Windows

Next

Network Models

On this page
Linux Platform Dependencies
Host OS
Python
Linux
ML Frameworks
Java
Windows Platform Dependencies
Host OS
Python
Windows
Environment Setup
Linux
Windows
Machine Learning Frameworks
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
Supported Network Layers
Supported Network Layers
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Supported Network Layers

Qualcomm® Neural Processing SDK supports the network layer types listed in the table below.

See Limitations for details on the limitations and constraints for the supported runtimes and individual layer types.

All of supported layers in GPU runtime are valid for both of GPU modes: GPU_FLOAT32_16_HYBRID and GPU_FLOAT16. GPU_FLOAT32_16_HYBRID - data storage is done in half float and computation is done in full float. GPU_FLOAT16 - both data storage and computation is done in half float.

A list of supported ONNX operations can be found at ONNX Operator Support.

Converters Equivalent

COMMAND_LINE : indicates the Op is supported through command-line parameters provided during conversion and not as part of a source framework model. See the Source Framework’s converter help for more details.

INFERRED: indicates Source Framework does not have a concrete definition for Op. However, converter pattern-matches a sequence of Ops to map to listed QNN Op.

— : indicates there is no corresponding Source Framework Op, or the corresponding Op is not yet supported.

Runtime Support

YES: Runtime has an implementation for Op.

NO: Runtime does not have an implementation for Op.

No.

Operation

Converters Equivalent

Runtime Support

Onnx

TensorFlow

TensorFlow Lite

PyTorch

CPU

GPU

AIP (HTA + DSP)

HTP (DSP v68+)

DSP (v66)

1

ArgbToRgb

COMMAND_LINE

COMMAND_LINE

—

—

YES

YES

YES

YES

YES

2

Argmax

ArgMax

argmax

—

argmax

YES

YES

YES

YES

YES

3

Argmin

ArgMin

argmin

—

argmin

YES

YES

YES

YES

YES

4

AxisAlignedBboxTransform

BBoxTransform(org.pytorch._caffe2) with im_info’s img_scale = 1

—

—

—

YES

NO

NO

NO

NO

5

Batchnorm

BatchNormalization

batch_normalization, fused_batch_norm(FusedBatchNorm, FusedBatchNormV3)

INFERRED

BatchNorm2d

YES

YES

YES

YES

YES

6

BatchPermutation

—

—

—

—

YES

NO

NO

NO

NO

7

BatchToSpace

—

batch_to_space

—

—

YES

YES

YES

YES

YES

8

BboxTransform

—

—

—

—

YES

NO

NO

NO

NO

9

BoxWithNmsLimit

BoxWithNMSLimit(org.pytorch._caffe2)

—

—

—

YES

NO

YES

NO

YES

10

Cast

Cast

cast

—

to

YES

YES

YES

YES

YES

11

ChannelShuffle

INFERRED

INFERRED

—

ChannelShuffle

YES

YES

YES

YES

YES

12

CollectRpnProposals

—

—

—

—

YES

NO

NO

NO

NO

13

Concat

Concat

concat(Concat, ConcatV2)

concatenation

cat

YES

YES

YES

YES

YES

14

ConstantOfShape

—

—

—

—

YES

NO

NO

NO

NO

15

Conv2d

Conv

conv2d

conv_2d

Conv2d

YES

YES

YES

YES

YES

16

Conv3d

Conv

conv3d

—

—

YES

NO

YES

YES

YES

17

Convert

—

—

—

—

YES

NO

YES

YES

YES

18

Correlation1D

—

—

—

—

YES

NO

YES

NO

YES

19

CropAndResize

—

crop_and_resize

—

—

YES

NO

NO

NO

NO

20

CumulativeSum

CumSum

cumsum

—

cumsum

YES

NO

NO

YES

NO

21

DepthToSpace

DepthToSpace

depth_to_space

depth_to_space

PixelShuffle

YES

YES

YES

YES

YES

22

DepthWiseConv2d

Conv with ‘num_output’ == ‘input channels’ == ‘group’

depthwise_conv2d

—

—

YES

YES

YES

YES

YES

23

Dequantize

DequantizeLinear

—

—

—

YES

YES

YES

YES

YES

24

DetectionOutput

—

INFERRED

TfliteDetectionPostProcess

—

YES

YES

YES

YES

YES

25

DistributeFpnProposals

—

—

—

—

YES

NO

NO

NO

NO

26

ElementWiseAbs

Abs

abs

abs

abs

YES

YES

YES

YES

YES

27

ElementWiseAdd

Add, Sum

add(Add, AddV2, Sum), bias_add

add

add

YES

YES

YES

YES

YES

28

ElementWiseAnd

And

logical_and

—

logical_and

YES

YES

NO

YES

NO

29

ElementWiseAsin

Asin

—

—

asin

YES

NO

NO

NO

NO

30

ElementWiseAtan

Atan

—

—

atan

YES

NO

NO

YES

NO

31

ElementWiseCeil

Ceil

ceil

—

ceil

YES

NO

YES

YES

YES

32

ElementWiseCos

Cos

—

—

cos

YES

YES

NO

YES

NO

33

ElementWiseDivide

Div, Reciprocal

divide, realdiv

div

div

YES

YES

YES

YES

YES

34

ElementWiseEqual

Equal

equal

—

eq

YES

YES

YES

YES

YES

35

ElementWiseExp

Exp

exp

exp

exp

YES

YES

YES

YES

YES

36

ElementWiseFloor

Floor

floor

floor

floor

YES

YES

YES

YES

YES

37

ElementWiseFloorDiv

—

floordiv

—

floor_divide

YES

NO

YES

YES

YES

38

ElementWiseFmod

—

—

—

—

NO

NO

NO

NO

NO

39

ElementWiseGreater

Greater

greater

—

gt

YES

YES

YES

YES

YES

40

ElementWiseGreaterEqual

GreaterOrEqual

greater_equal

—

ge

YES

YES

YES

YES

YES

41

ElementWiseLess

Less

less

—

lt

YES

YES

YES

YES

YES

42

ElementWiseLessEqual

LessOrEqual

less_equal

—

le

YES

YES

YES

YES

YES

43

ElementWiseLog

Log

log

—

log

YES

YES

YES

YES

YES

44

ElementWiseMaximum

Max

maximum

maximum

maximum

YES

YES

YES

YES

YES

45

ElementWiseMinimum

Min

minimum

minimum

minimum

YES

YES

YES

YES

YES

46

ElementWiseMod

—

—

—

—

YES

NO

NO

NO

NO

47

ElementWiseMultiply

Mul

mul

mul

mul

YES

YES

YES

YES

YES

48

ElementWiseNeg

Neg

negative

—

neg

YES

YES

YES

YES

YES

49

ElementWiseNot

Not

logical_not

—

logical_not

YES

YES

NO

YES

NO

50

ElementWiseNotEqual

—

not_equal

—

ne

YES

YES

YES

YES

YES

51

ElementWiseOr

Or

logical_or

—

logical_or

YES

YES

NO

NO

NO

52

ElementWisePower

Pow

pow, square

—

pow

YES

YES

YES

YES

YES

53

ElementWiseRound

Round

round

—

round

YES

YES

YES

YES

YES

54

ElementWiseRsqrt

—

rsqrt

—

rsqrt

YES

YES

YES

YES

YES

55

ElementWiseSelect

Where

where

—

—

YES

YES

YES

YES

YES

56

ElementWiseSign

Sign

—

—

sign

YES

NO

NO

YES

NO

57

ElementWiseSin

Sin

sin

—

sin

YES

YES

NO

YES

NO

58

ElementWiseSoftplus

Softplus

Softplus

—

Softplus

YES

NO

NO

NO

NO

59

ElementWiseSquaredDifference

—

—

—

—

YES

YES

YES

YES

YES

60

ElementWiseSquareRoot

Sqrt

sqrt

sqrt

sqrt

YES

YES

YES

YES

YES

61

ElementWiseSubtract

Sub

subtract

sub

sub

YES

YES

YES

YES

YES

62

ElementWiseUnary

—

—

—

—

YES

NO

NO

NO

NO

63

ElementWiseXor

Xor

logical_xor

—

logical_xor

YES

YES

NO

NO

NO

64

Elu

Elu

elu

—

—

YES

YES

YES

YES

YES

65

ExpandDims

—

—

—

—

YES

YES

NO

YES

NO

66

ExtractGlimpse

—

extract_glimpse

—

—

YES

NO

YES

YES

YES

67

ExtractPatches

—

extract_patches

—

—

YES

NO

NO

YES

NO

68

FullyConnected

MatMul(limited usecase), Gemm(limited usecase)

dense and tensordot(MatMul)

fully_connected

Linear

YES

YES

YES

YES

YES

69

Gather

Gather

gather(Gather, GatherV2)

—

—

YES

YES

YES

YES

YES

70

GatherElements

GatherElements

—

—

—

YES

NO

NO

YES

NO

71

GatherNd

GatherND

gather_nd

—

—

YES

NO

NO

YES

NO

72

Gelu

INFERRED / Gelu(for onnx version>=1.15)

INFERRED

—

GELU

YES

NO

NO

YES

NO

73

GenerateProposals

GenerateProposals(org.pytorch._caffe2) with im_info’s img_scale = 1

—

—

—

YES

NO

YES

NO

YES

74

GridSample

GridSample

—

—

—

YES

NO

NO

YES

NO

75

GroupNorm

—

—

—

GroupNorm

YES

NO

NO

NO

NO

76

Gru

—

—

—

—

NO

NO

NO

NO

NO

77

HardSwish

MATCHED

INFERRED

—

Hardswish

YES

YES

YES

YES

YES

78

HeatMapMaxKeyPoint

—

—

—

—

YES

YES

NO

YES

NO

79

ImageProjectionTransform

—

image.transform(ImageProjectiveTransform)

—

—

YES

NO

YES

YES

YES

80

InstanceNorm

InstanceNormalization

INFERRED

—

InstanceNorm2d

YES

YES

YES

YES

YES

81

L2Norm

LpNormalization

INFERRED

—

—

YES

YES

YES

YES

YES

82

L2Pool2d

LpPool

—

—

—

YES

YES

NO

NO

NO

83

LayerNorm

MATCHED

Layer_Normalization

—

LayerNorm

YES

YES

NO

YES

NO

84

LogSoftmax

LogSoftmax

log_softmax

—

LogSoftmax

YES

YES

NO

YES

NO

85

Lrn

LRN

local_response_normalization

—

—

YES

YES

YES

YES

YES

86

Lstm

LSTM

INFERRED

—

—

YES

YES

YES

YES

YES

87

MatMul

MatMul

matmul(BatchMatMul, BatchMatMulV2)

—

matmul

YES

YES

YES

YES

YES

88

Moments

—

INFERRED

—

—

YES

NO

YES

NO

YES

89

MultiClassNms

nms + gather

nms + gather

—

—

YES

NO

NO

YES

NO

90

NonMaxSuppression

NonMaxSuppression

—

—

—

YES

NO

NO

YES

NO

91

NonZero

NonZero

—

—

—

YES

NO

NO

YES

NO

92

Nv12ToRgb

COMMAND_LINE

COMMAND_LINE

—

—

YES

YES

YES

YES

YES

93

Nv21ToRgb

COMMAND_LINE

COMMAND_LINE

—

—

YES

YES

YES

YES

YES

94

OneHot

OneHot

one_hot

—

—

YES

NO

NO

YES

NO

95

Pack

—

stack(Stack, Pack)

—

stack

YES

YES

YES

YES

YES

96

Pad

Pad

pad(Pad, PadV2)

—

ConstantPad

YES

YES

YES

YES

YES

97

PoolAvg2d

AveragePool, GlobalAveragePool

average_pooling2d

average_pool_2d

AvgPool2d

YES

YES

YES

YES

YES

98

PoolAvg3d

AveragePool, GlobalAveragePool

—

—

—

YES

NO

YES

YES

YES

99

PoolMax2d

MaxPool, GlobalMaxPool

max_pooling2d

max_pool_2d

MaxPool2d

YES

YES

YES

YES

YES

100

PoolMax3d

MaxPool, GlobalMaxPool

—

—

—

YES

NO

YES

NO

YES

101

Prelu

PRelu, LeakyRelu

PReLU

—

PReLU

YES

YES

YES

YES

YES

102

Quantize

QuantizeLinear

—

—

—

YES

NO

YES

YES

YES

103

ReduceMax

ReduceMax

reduce_max

—

max

YES

YES

YES

YES

YES

104

ReduceMean

ReduceMean

reduce_mean

—

mean

YES

YES

YES

YES

YES

105

ReduceMin

ReduceMin

reduce_min

—

min

YES

YES

YES

YES

YES

106

ReduceProd

ReduceProd

reduce_prod

—

prod

YES

YES

NO

NO

NO

107

ReduceSum

ReduceSum

reduce_sum

—

sum

YES

YES

YES

YES

YES

108

Relu

Relu

relu

relu

ReLU

YES

YES

YES

YES

YES

109

Relu1

—

—

—

—

NO

YES

NO

YES

NO

110

Relu6

—

relu6

—

ReLU6

YES

YES

YES

YES

YES

111

ReluMinMax

Clip

INFERRED

relu6

Hardtanh

YES

YES

YES

YES

YES

112

Reshape

Reshape, Flatten, Squeeze, UnSqueeze

reshape, squeeze, expand_dims

reshape

reshape

YES

YES

YES

YES

YES

113

Resize

Resize

—

—

Resize

YES

NO

NO

YES

NO

114

ResizeBilinear

Resize

resize_bilinear

resize_bilinear

UpsamplingBilinear2d

YES

YES

YES

YES

YES

115

ResizeNearestNeighbor

Resize, ResizeNearest(org.pytorch._caffe2)

resize_nearest_neighbor

—

—

YES

YES

YES

YES

YES

116

RoiAlign

RoiAlign, RoIAlign(org.pytorch._caffe2)

—

—

—

YES

YES

YES

YES

YES

117

RoiPooling

MaxRoiPool

—

—

—

YES

NO

YES

NO

YES

118

ScatterElements

ScatterElements, Scatter (deprecated)

—

—

—

YES

NO

NO

YES

NO

119

ScatterNd

ScatterND

—

—

—

YES

NO

NO

YES

NO

120

Shape

—

—

—

—

YES

NO

NO

NO

NO

121

Sigmoid

Sigmoid

sigmoid

—

sigmoid

YES

YES

YES

YES

YES

122

Softmax

Softmax

softmax

softmax

Softmax

YES

YES

YES

YES

YES

123

SpaceToBatch

—

space_to_batch(SpaceToBatchND)

—

—

YES

YES

YES

YES

YES

124

SpaceToDepth

SpaceToDepth

space_to_depth

—

—

YES

YES

YES

YES

YES

125

Split

Split

split(Split, SplitV)

—

split

YES

YES

YES

YES

YES

126

Squeeze

—

—

—

—

YES

YES

YES

YES

YES

127

StridedSlice

Slice

strided_slice

slice

—

YES

YES

YES

YES

YES

128

Tanh

Tanh

tanh

tanh

tanh

YES

YES

YES

YES

YES

129

Tile

Tile

tile

—

—

YES

YES

YES

YES

YES

130

TopK

TopK

top_k

—

topk

YES

YES

NO

YES

NO

131

Transpose

Transpose

transpose

—

transpose

YES

YES

YES

YES

YES

132

TransposeConv2d

ConvTranspose

conv2d_transpose

transpose_conv

ConvTranspose2d

YES

YES

YES

YES

YES

133

TransposeConv3d

ConvTranspose

—

—

—

YES

NO

NO

YES

NO

134

UnPack

—

unstack

—

unbind

YES

YES

YES

YES

YES

Note : AIP Runtime supports all layers supported by the DSP runtime, as layers not supported by HTA run on HVX.

Previous

Network Models

Next

Supported ONNX Ops

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
Supported ONNX Ops
Supported ONNX Ops
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Operator Support

snpe-onnx-to-dlc currently supports the following operators and parameters:

Operator

Opset Version Supported

Notes

Abs

1,6,13

Add

1,6,7,13,14

And

1,7

AveragePool

1,7,10,11,19

ArgMax

1,11,12,13

Only default value(0) is supported for select_last_index

ArgMin

1,11,12,13

Only default value(0) is supported for select_last_index

Asin

7

Atan

7

BatchNormalization

1,6,7,9,14,15

Momentum is not supported. Only default value (0) for training_mode is supported. All inputs after the first must be static. Only the first output is generated.

Ceil

1,6,13

Clip

1,6,11,12

Concat

1,4,11,13

Constant

1,9,11,12,13

sparse tensor is not currently supported.

ConstantOfShape

9

Conv

1,11

“kernel_shape” which differs from that of weights is not supported.

ConvTranspose

1,11

Cos

7

CumSum

11,14

value of axis attribute should be in the range of (-input_rank,

input_rank )

Cast

1,6,9,13

DepthToSpace

1,11,13

DequantizeLinear

10,13,19,21

Div

1,6,7,13,14

Dropout

1,6,7,10

Only test mode (i.e. noop) is supported. Mask output is not generated.

Einsum

12

Elu

1,6

Equal

1,7,11,13

Erf

9,13

Only certain cases which can be matched to GeLU will be supported

Exp

1,6,13

Expand

8,13

Static shape tensor only supported

Flatten

1,9,11,13

Floor

1,6

Gather

1,11,13

GatherElements

11,13

GatherND

11,12,13

Gelu

20

Value of approximate attribute should be none or tanh

Gemm

1,6,7,9,11,13

Gemm is only supported in the case where it is used to emulate FC; transA=0, transB=1, broadcast=1.

GlobalAveragePool

1

Only Supports 3D,4D & 5D inputs

GlobalMaxPool

1

Only Supports 3D,4D & 5D inputs

Greater

1,7,9,13

GreaterOrEqual

12

GridSample

16,20

Only Supports 4D & 5D inputs

GroupNormalization

18,21

GRU

1,7,14

Custom activations, user-defined activations and clip are not supported.

HardSigmoid

1,6

HardSwish

14

Identity

1,13,14,16

InstanceNormalization

1,6

If

1,11,13,16,19,21

Doesn’t support THEN and/or ELSE producing more than one output. Doesn’t support THEN and ELSE producing different output shapes.

IsNaN

9, 13, 20

IsInf

10, 20

LayerNormalization

17

LRN

1,13

LeakyRelu

1,6

Less

1,7,9,13

LessOrEqual

12

Log

1,6,13

LogSoftmax

1,11

LpNormalization

1

Only support attribute p=2

LpPool

1,2,11,18

LSTM

1,7,14

Custom activations, user-defined activations, clip and input-forget parameters are not supported. Only default value(0) is supported for layout

Matmul

1,9,13

Max

1,6,8,12,13

MaxPool

1,8,10,11,12

Only support Dilations = 1. Only support single output, doesn’t support Indices output.

MaxRoiPool

1

Min

1,6,8,12,13

Mod

10,13

Mul

1,6,7,13,14

Neg

1,6,13

NonMaxSuppression

10,11

NonZero

9,13

Not

1

OneHot

9,11

Or

1,7

Pad

1,2,11,13,18,19,21

Constant, Reflect & Edge padding mode is supported.

Pow

1,7,12,13,15

PRelu

1,6,7,9

Slope must be static.

QLinearConv

10

Translate to Conv.

QLinearMatMul

10,21

Translate to MatMul.

QuantizeLinear

10,13,19,21

Range

11

Reciprocal

1,6

Translate to Div.

Reducel2

1,11,13,18

ReduceLogSumExp

1,11,13,18

Translate to Log + ReduceSum + Exp

ReduceMax

1,11,12,13,18,20

ReduceMean

1,11,13,18

ReduceMin

1,11,12,13,18,20

ReduceProd

1,11,13,18

ReduceSum

1,11,13

ReduceSumSquare

1,11,13,18

Relu

1,6

Reshape

1,5,13,14

Only default value(0) is supported for allow_zero

Resize

10,11,13,18,19

Only 3D,4D & 5D inputs are supported.

RoiAlign

10,16

Only output_haf_pixel is supported for coordinate transformation mode

Round

11

RNN

1, 7

Custom and user-defined activations (activation_alpha and beta) are not supported.

Scatter

9,11

ScatterElements

11,13,16

ScatterND

11,13,16

Shape

1,13,15

Shape is only supported at conversion time. The input to Shape and all operators which manipulate the resulting output shape (eg slice, concat, etc) are also computed at conversion time.

Sign

13

Sigmoid

1,6,13

Sin

7

Slice

1,10,11,13

Softmax

1,11,13

Softplus

1

Split

1,2,11,13,18

Squeeze

1,11,13

Sqrt

1,6,13

Sub

1,6,7,13,14

Sum

1,6,8,13

Tanh

1,6,13

Tile

1.6,13

TopK

1,10,11

Transpose

1,13

Unsqueeze

1,11,13

Upsample

1,7,9

Only bilinear and nearest neighbor modes are supported.

FC

1

Both “axis” and “axis_w” must be 1.

ScaledTanh

1,6

Scaledtanh is removed in ONNX release v1.5.0

ThresholdedRelu

10

SpaceToDepth

1,13

Where

9,16

Xor

1,7

Previous

Supported Network Layers

Next

Quantized vs Non-Quantized Models

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
Supported ONNX Ops
Supported ONNX Ops
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Operator Support

snpe-onnx-to-dlc currently supports the following operators and parameters:

Operator

Opset Version Supported

Notes

Abs

1,6,13

Add

1,6,7,13,14

And

1,7

AveragePool

1,7,10,11,19

ArgMax

1,11,12,13

Only default value(0) is supported for select_last_index

ArgMin

1,11,12,13

Only default value(0) is supported for select_last_index

Asin

7

Atan

7

BatchNormalization

1,6,7,9,14,15

Momentum is not supported. Only default value (0) for training_mode is supported. All inputs after the first must be static. Only the first output is generated.

Ceil

1,6,13

Clip

1,6,11,12

Concat

1,4,11,13

Constant

1,9,11,12,13

sparse tensor is not currently supported.

ConstantOfShape

9

Conv

1,11

“kernel_shape” which differs from that of weights is not supported.

ConvTranspose

1,11

Cos

7

CumSum

11,14

value of axis attribute should be in the range of (-input_rank,

input_rank )

Cast

1,6,9,13

DepthToSpace

1,11,13

DequantizeLinear

10,13,19,21

Div

1,6,7,13,14

Dropout

1,6,7,10

Only test mode (i.e. noop) is supported. Mask output is not generated.

Einsum

12

Elu

1,6

Equal

1,7,11,13

Erf

9,13

Only certain cases which can be matched to GeLU will be supported

Exp

1,6,13

Expand

8,13

Static shape tensor only supported

Flatten

1,9,11,13

Floor

1,6

Gather

1,11,13

GatherElements

11,13

GatherND

11,12,13

Gelu

20

Value of approximate attribute should be none or tanh

Gemm

1,6,7,9,11,13

Gemm is only supported in the case where it is used to emulate FC; transA=0, transB=1, broadcast=1.

GlobalAveragePool

1

Only Supports 3D,4D & 5D inputs

GlobalMaxPool

1

Only Supports 3D,4D & 5D inputs

Greater

1,7,9,13

GreaterOrEqual

12

GridSample

16,20

Only Supports 4D & 5D inputs

GroupNormalization

18,21

GRU

1,7,14

Custom activations, user-defined activations and clip are not supported.

HardSigmoid

1,6

HardSwish

14

Identity

1,13,14,16

InstanceNormalization

1,6

If

1,11,13,16,19,21

Doesn’t support THEN and/or ELSE producing more than one output. Doesn’t support THEN and ELSE producing different output shapes.

IsNaN

9, 13, 20

IsInf

10, 20

LayerNormalization

17

LRN

1,13

LeakyRelu

1,6

Less

1,7,9,13

LessOrEqual

12

Log

1,6,13

LogSoftmax

1,11

LpNormalization

1

Only support attribute p=2

LpPool

1,2,11,18

LSTM

1,7,14

Custom activations, user-defined activations, clip and input-forget parameters are not supported. Only default value(0) is supported for layout

Matmul

1,9,13

Max

1,6,8,12,13

MaxPool

1,8,10,11,12

Only support Dilations = 1. Only support single output, doesn’t support Indices output.

MaxRoiPool

1

Min

1,6,8,12,13

Mod

10,13

Mul

1,6,7,13,14

Neg

1,6,13

NonMaxSuppression

10,11

NonZero

9,13

Not

1

OneHot

9,11

Or

1,7

Pad

1,2,11,13,18,19,21

Constant, Reflect & Edge padding mode is supported.

Pow

1,7,12,13,15

PRelu

1,6,7,9

Slope must be static.

QLinearConv

10

Translate to Conv.

QLinearMatMul

10,21

Translate to MatMul.

QuantizeLinear

10,13,19,21

Range

11

Reciprocal

1,6

Translate to Div.

Reducel2

1,11,13,18

ReduceLogSumExp

1,11,13,18

Translate to Log + ReduceSum + Exp

ReduceMax

1,11,12,13,18,20

ReduceMean

1,11,13,18

ReduceMin

1,11,12,13,18,20

ReduceProd

1,11,13,18

ReduceSum

1,11,13

ReduceSumSquare

1,11,13,18

Relu

1,6

Reshape

1,5,13,14

Only default value(0) is supported for allow_zero

Resize

10,11,13,18,19

Only 3D,4D & 5D inputs are supported.

RoiAlign

10,16

Only output_haf_pixel is supported for coordinate transformation mode

Round

11

RNN

1, 7

Custom and user-defined activations (activation_alpha and beta) are not supported.

Scatter

9,11

ScatterElements

11,13,16

ScatterND

11,13,16

Shape

1,13,15

Shape is only supported at conversion time. The input to Shape and all operators which manipulate the resulting output shape (eg slice, concat, etc) are also computed at conversion time.

Sign

13

Sigmoid

1,6,13

Sin

7

Slice

1,10,11,13

Softmax

1,11,13

Softplus

1

Split

1,2,11,13,18

Squeeze

1,11,13

Sqrt

1,6,13

Sub

1,6,7,13,14

Sum

1,6,8,13

Tanh

1,6,13

Tile

1.6,13

TopK

1,10,11

Transpose

1,13

Unsqueeze

1,11,13

Upsample

1,7,9

Only bilinear and nearest neighbor modes are supported.

FC

1

Both “axis” and “axis_w” must be 1.

ScaledTanh

1,6

Scaledtanh is removed in ONNX release v1.5.0

ThresholdedRelu

10

SpaceToDepth

1,13

Where

9,16

Xor

1,7

Previous

Supported Network Layers

Next

Quantized vs Non-Quantized Models

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
Quantized vs Non-Quantized Models
Quantized vs Non-Quantized Models
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Overview

Non-quantized DLC files use 32 bit floating point representations of network parameters.

Quantized DLC files use fixed point representations of network parameters, generally 8 bit weights and 8 or 32 bit biases. The fixed point representation is the same used in Tensorflow quantized models.

ONNX

The default output of snpe-onnx-to-dlc is a non-quantized model. This means that all the network parameters are left in the 32 bit floating point representation as present in the original ONNX model. To quantize the model to 8 bit fixed point, see snpe-dlc-quantize. Note that models that are intended to be quantized using snpe-dlc-quantize must have their batch dimension set to 1. A different batch dimension can be used during inference, by resizing the network during initialization.

TensorFlow

The default output of snpe-tensorflow-to-dlc is a non-quantized model. This means that all the network parameters are left in the 32 bit floating point representation as present in the original TensorFlow model. To quantize the model to 8 bit fixed point, see snpe-dlc-quantize. Note that models that are intended to be quantized using snpe-dlc-quantize must have their batch dimension set to 1. A different batch dimension can be used during inference, by resizing the network during initialization.

Choosing Between a Quantized or Non-Quantized Model

Summary

Runtime

Quantized DLC

Non-Quantized DLC

CPU

Compatible. If CPU fixed-point mode is enabled, model can be passed directly to the runtime. Else the model is dequantized by the runtime, increasing network initialization time. Accuracy may be impacted.

Compatible. The model is native format for this runtime. Model can be passed directly to the runtime. May be more accurate than a quantized model.

GPU

Compatible. The model is dequantized by the runtime, increasing network initialization time. Accuracy may be impacted.

Compatible. The model is native format for this runtime. Model can be passed directly to the runtime. May be more accurate than a quantized model.

DSP

Compatible. The model is native format for DSP runtime. Model can be passed directly to the runtime. Accuracy may be different than a non-quantized model

Compatible. The model is quantized by the runtime, increasing network initialization time. Accuracy may be different than a quantized model.

AIP

Compatible. The model is in supported format for AIP runtime. Model can be passed directly to the runtime.

Incompatible. Non-quantized models are not supported by the AIP runtime.

Details

CPU

The CPU by default uses floating point (non-quantized) network parameters.

Using quantized DLC files with CPU runtime is supported. To use quantized network parameters directly, CPU fixed-point mode should be enabled. If it is not enabled, network initialization time will dramatically increase as Qualcomm® Neural Processing SDK will automatically de-quantize the network parameters in order to run on CPU.

Quantization of the DLC file does introduce noise, as quantization is lossy.

The network performance during execute is not impacted by the choice of quantized vs non-quantized DLC files.

GPU

The GPU always uses floating point (non-quantized) network parameters.

Using quantized DLC files with GPU runtime is supported. Network initialization time will dramatically increase as Qualcomm® Neural Processing SDK will automatically de-quantize the network parameters in order to run on GPU.

If network initialization time is a concern, it is recommended to use non-quantized DLC files (default) for GPU.

Quantization of the DLC file does introduce noise, as quantization is lossy.

The network performance during execute is not impacted by the choice of quantized vs non-quantized DLC files.

DSP

The DSP always uses quantized network parameters.

Using a non-quantized DLC file on the DSP is supported. Network initialization time will dramatically increase as Qualcomm® Neural Processing SDK will automatically quantize the network parameters in order to run on the DSP.

It is generally recommended to use quantized DLC files for running on the DSP. In addition to faster network initialization time, using quantized models also reduces peak memory usage during initialization, and decreases DLC file size.

AIP

The AIP runtime always uses quantized network parameters.

Passing through snpe-dlc-quantize is mandatory for generating the binaries for HTA subnets.

Using a non-quantized DLC file with the AIP runtime is not supported.

HTA subnets use the quantized parameters in the DLC.

HNN (Hexagon NN) subnets use the quantization parameters in the same way DSP runtime does.

Balancing DLC file size, network initialization time and accuracy

If the network will mainly run on the GPU and CPU it is recommended to try both quantized and non-quantized models during development. If a quantized model provides enough accuracy, then it may be used directly for CPU using CPU fixed-point mode. For GPU, it may be used at the expense of increased network initialization time. The benefit is a much smaller DLC file. The tradeoff between accuracy, network initialization time, and DLC file size is application specific.

If the network will mainly run on the DSP, there is no benefit to using a non-quantized model. As previously stated it will dramatically increase network initialization time and DLC file size, but provide no accuracy benefit.

Quantization Algorithm

This section describes the concepts behind the quantization algorithm used in Qualcomm® Neural Processing SDK. These concepts are used by snpe-dlc-quantize and is also used by Qualcomm® Neural Processing SDK for input quantization when using the DSP runtime.

Overview

Note: Qualcomm® Neural Processing SDK supports multiple quantization modes. The basics of the quantization, regardless of mode, are described here. See Quantization Modes for more information.

Quantization converts floating point data to Tensorflow-style 8-bit fixed point format.

The following requirements are satisfied:

Full range of input values is covered.

Minimum range of 0.0001 is enforced.

Floating point zero is exactly representable.

Quantization algorithm inputs:

Set of floating point values to be quantized.

Quantization algorithm outputs:

Set of 8-bit fixed point values.

Encoding parameters:

encoding-min : minimum floating point value representable (by fixed point value 0).

encoding-max : maximum floating point value representable (by fixed point value 255).

Algorithm

Compute the true range (min, max) of input data.

Compute the encoding-min and encoding-max.

Quantize the input floating point values.

Output:

fixed point values

encoding-min and encoding-max parameters

Details

This section outlines more information regarding the quantization process.

Compute the true range of the input floating point data.

finds the smallest and largest values in the input data

represents the true range of the input data

Compute the encoding-min and encoding-max.

These parameters are used in the quantization step.

These parameters define the range and floating point values that will be representable by the fixed point format.

encoding-min: specifies the smallest floating point value that will be represented by the fixed point value of 0

encoding-max: specifies the largest floating point value that will be represented by the fixed point value of 255

floating point values at every step size, where step size = (encoding-max - encoding-min) / 255, will be representable

encoding-min and encoding-max are first set to the true min and true max computed in the previous step

First requirement: encoding range must be at least a minimum of 0.0001

encoding-max is adjusted to max(true max, true min + 0.01)

Second requirement: floating point value of 0 must be exactly representable

encoding-min or encoding-max may be further adjusted

Handling 0.

Case 1: Inputs are strictly positive

the encoding-min is set to 0.0

zero floating point value is exactly representable by smallest fixed point value 0

e.g. input range = [5.0, 10.0]

encoding-min = 0.0, encoding-max = 10.0

Case 2: Inputs are strictly negative

encoding-max is set to 0.0

zero floating point value is exactly representable by the largest fixed point value 255

e.g. input range = [-20.0, -6.0]

encoding*min = *20.0, encoding*max = 0.0

Case 3: Inputs are both negative and positive

encoding-min and encoding-max are slightly shifted to make the floating point zero exactly representable

e.g. input range = [-5.1, 5.1]

encoding-min and encoding-max are first set to -5.1 and 5.1, respectively

encoding range is 10.2 and the step size is 10.2/255 = 0.04

zero value is currently not representable. The closest values representable are -0.02 and +0.02 by fixed point values 127 and 128, respectively

encoding-min and encoding-max are shifted by -0.02. The new encoding-min is -5.12 and the new encoding-max is 5.08

floating point zero is now exactly representable by the fixed point value of 128

Quantize the input floating point values.

encoding-min and encoding-max parameter determined in the previous step are used to quantize all the input floating values to their fixed point representation

Quantization formula is:

quantized value = round(255 * (floating point value - encoding.min) / (encoding.max - encoding.min))

quantized value is also clamped to be within 0 and 255

Outputs

the fixed point values

encoding-min and encoding-max parameters

Quantization Example

Inputs:

input values = [-1.8, -1.0, 0, 0.5]

encoding-min is set to -1.8 and encoding-max to 0.5

encoding range is 2.3, which is larger than the required 0.0001

encoding-min is adjusted to −1.803922 and encoding-max to 0.496078 to make zero exactly representable

step size (delta or scale) is 0.009020

Outputs:

quantized values are [0, 89, 200, 255]

Dequantization Example

Inputs:

quantized values = [0, 89, 200, 255]

encoding-min = −1.803922, encoding-max = 0.496078

step size is 0.009020

Outputs:

dequantized values = [−1.8039, −1.0011, 0.0000, 0.4961]

Bias BitWidth

Qualcomm® Neural Processing SDK currently supports a default quantization bit width of 8 for both weights and biases. The bias bitwidth, however, can be overriden to use 32 bit quantization by specifying the command line option “–bias_bitwidth 32” from snpe-dlc-quantize. For some models, using 32 bit biases may give a small improvement in accuracy. Unfortunately it is difficult to predict which models may benefit from this since model architectures, weight distributions, etc all have an impact on quantization performance.

Activation BitWidth

Qualcomm® Neural Processing SDK also supports, quantization bitwidth of 16 for activation.(See Notes)

To enable 16-bit fixed point inference, specify quantization bitwidth of activations to 16 while keeping that of weights to 8. Passing the command line options: “–act_bitwidth 16 –weights_bitwidth 8” to snpe-dlc-quantize, will generate quantized model files with 16-bit activations and 8-bit weights.

It is recommended to use UserBuffer TF16 as input/output data format for better efficiency. In this case, users of Qualcomm® Neural Processing SDK need quantize/dequantize input/output data on their own if floating point data are used. When testing with snpe-net-run, command line option “–userbuffer_tfN 16” can be used to select UserBuffer TF16 mode. ITensor and UserBuffer floating point format can still be used with 16-bit integer inference with less efficient quantization applied internally.

Packed 4-bit quantization

In packed 4-bit quantization, two 4-bit quantized tensors can be stored in a single 8-bit buffer. The lower nibble stores the first value while the higher nibble stores the second value. This can be enabled by providing the “–pack_4_bit_weights” from snpe-dlc-quantize. For the quantized values (10, 4) the unpacked and packed representation is given below.

Unpacked = (0000 1010, 0000 0100)

Packed = (0100 1010)

In case of per-channel/per-row quantization, the quantized values are packed along each channel/row. For a tensor of size (3,3,3,32) containing 32 output channels and 27 values per channel, the unpacked and packed representation will take the following amount of memory for the 27 quantized values per channel.

Unpacked = (3*3*3) = 27 bytes

Packed = ceil((3*3*3)/2) = 14 bytes

Note Packed 4-bit tensors are stored with QNN_DATATYPE_SFIXED_POINT_4/QNN_DATATYPE_UFIXED_POINT_4 datatypes while unpacked 4-bit tensors are stored with QNN_DATATYPE_SFIXED_POINT_8/QNN_DATATYPE_UFIXED_POINT_8 datatypes. Please refer to the backend supplements to find ops which support 4-bit packed tensors.

Quantization Modes

Qualcomm® Neural Processing SDK supports multiple quantization modes, the difference is in how quantization parameters are chosen.

Default Quantization Mode

The default mode has been described above, and uses the true min/max of the data being quantized, followed by an adjustment of the range to ensure a minimum range and to ensure 0.0 is exactly quantizable.

Enhanced Quantization Mode

Enhanced quantization mode (invoked by using the “use_enhanced_quantizer” parameter to snpe-dlc-quantize) uses an algorithm to try to determine a better set of quantization parameters to improve accuracy. The algorithm may pick a different min/max value than the default quantizer, and in some cases it may set the range such that some of the original weights and/or activations cannot fall into that range. However, this range does produce better accuracy than simply using the true min/max. The enhanced quantizer can be enabled independently for weights and activations by appending either “weights” or “activations” after the option.

This is useful for some models where the weights and/or activations may have “long tails”. (Imagine a range with most values between -100 and 1000, but a few values much greater than 1000 or much less than -100.) In some cases these long tails can be ignored and the range -100, 1000 can be used more effectively than the full range.

Enhanced quantizer still enforces a minimum range and ensures 0.0 is exactly quantizable.

Adjusted Weights Quantization Mode

This mode is used only for quantizing weights to 8 bit fixed point (invoked by using the “use_adjusted_weights_quantizer” parameter to snpe-dlc-quantize), which uses adjusted min or max of the data being quantized other than true min/max or the min/max that exclude the long tail. This has been verified to be able to provide accuracy benefit for denoise model specifically. Using this quantizer, the max will be expanded or the min will be decreased if necessary.

Adjusted weights quantizer still enforces a minimum range and ensures 0.0 is exactly quantizable.

Enhanced Quantization Techniques

Quantization can be a difficult problem to solve due to the myriad of training techniques, model architectures, and layer types. In an attempt to mitigate quantization problems two new model preprocessing techniques have been added to snpe-dlc-quantize that may improve quantization performance on models which exhibit sharp drops in accuracy upon quantization.

The new technique introduced is CLE (Cross Layer Equalization).

CLE works by scaling the convolution weight ranges in the network by making use of a scale-equivariance property of activation functions. In addition, the process absorbs high biases which may be result from weight scaling from one convolution layer to a subsequent convolution layer.

Enhanced Quantization Techniques: Limitations

In many cases, CLE may enable quantized models to return to close to their original floating-point accuracy. There are some caveats/limitations to the current algorithms:

CLE operates on specific patterns of operations that all exist in a single branch (outputs cannot be consumed by more than one op). The matched operation patterns (r=required, o=optional) are:

Conv(r)->Batchnorm(r)->activation(o)->Conv(r)->Batchnorm(r)->activation(o)

Conv(r)->Batchnorm(r)->activation(o)->DepthwiseConv(r)->Batchnorm(r)->activation(o)->Conv(r)->Batchnorm(r)->activation(o)

The CLE algorithm currently only supports Relu activations. Any Relu6 activations will be automatically changed to Relu and any activations other than these will cause the algorithm to ignore the preceding convolution. Typically the switch from Relu6->Relu is harmless and does not cause any degradation in accuracy, however some models may exhibit a slight degradation of accuracy. In this case, CLE can only recover accuracy to that degraded level, and not to the original float accuracy.

CLE requires batchnorms (specifically detectable batchnorm beta/gamma data) be present in the original model before conversion to DLC for the complete algorithm to be run and to regain maximum accuracy. For Tensorflow, the beta and gamma can sometimes still be found even with folded batchnorms, so long as the folding didn’t fold the parameters into the convolution’s static weights and bias. If it does not detect the required information you may see a message that looks like: “Invalid model for HBA quantization algorithm.” This indicates the algorithm will only partially run and accuracy issues may likely be present.

To run CLE pass the “–optimizations cle” to snpe-dlc-quantize.

The original converted float model should always be used as input to snpe-dlc-quantize. Passing quantized models back to the quantizer is not supported and will result in undefined behavior.

More information about the algorithms can be found here: https://arxiv.org/abs/1906.04721

Quantization Impacts

Quantizing a model and/or running it in a quantized runtime (like the DSP) can affect accuracy. Some models may not work well when quantized, and may yield incorrect results. The metrics for measuring impact of quantization on a model that does classification are typically “Mean Average Precision”, “Top-1 Error” and “Top-5 Error”. These metrics published in Qualcomm® Neural Processing SDK release notes for various models.

Mixed Precision and FP16 Support

Mixed Precision enables specifying different bit widths (e.g. 8 or 16) or datatypes (integer or floating point) for different operations within the same graph. Data type conversion operations are automatically inserted when activation precision or data type is different between successive operations. Graphs can have a mix of floating-point and fixed-point data types. Each operation can have different precision for weights and activations. However, for a particular operation, either all inputs, outputs and parameters (weights/biases) will be floating-point or all will be fixed-point format.

Quantization Overrides

If the option –quantization_overrides is provided during model conversion the user can provide a json file with parameters to use for quantization. These will be cached along with the model and can be used to override any quantization data carried from conversion (eg TF fake quantization) or calculated during the normal quantization process in snpe-dlc-quantize. To override the params during snpe-dlc-quantize the option –override_params must be passed, and the cached values will be used instead. The json format is defined as per AIMET specification and can be found below.

There are two sections in the json, a section for overriding operator output encodings called “activation_encodings” and a section for overriding parameter (weight and bias) encodings called “param_encodings”. Both must be present in the file, but can be empty if no overrides are desired.

An example with all of the currently supported options:

{
  "activation_encodings": {
      "Conv1:0": [
          {
              "bitwidth": 8,
              "max": 12.82344407824954,
              "min": 0.0,
              "offset": 0,
              "scale": 0.050288015993135454
          }
      ],
      "input:0": [
          {
              "bitwidth": 8,
              "max": 0.9960872825108046,
              "min": -1.0039304197656937,
              "offset": 127,
              "scale": 0.007843206675594112
          }
      ]
  },
  "param_encodings": {
      "Conv2d/weights": [
          {
              "bitwidth": 8,
              "max": 1.700559472933134,
              "min": -2.1006477158567995,
              "offset": 140,
              "scale": 0.01490669485799974
          }
      ]
  }
}
Under “activation_encodings” the names (eg “Conv1:0”) represent the output tensor names where quantization should be overriden. Under “param_encodings” the names represent the weights or biases for which the encodings will be specified. A brief breakdown of the common parameters:

bitwidth (int, required) - The bitwidth to use for quantization. Note that this much match the existing bitwidth support for the runtime on which the model will be run.

max (float, required) - The largest number in the distribution or desired range.

min (float, required) - The smalled number in the distribution or desired range.

offset (int) - The integer offset indicating the zero point (ie The point at which 0 is exactly represnted).

scale (float) - The value indicating the integer size divided by the desired distribution range.

Note that it is not required to provide scale (also referred to as delta) and offset (zero point) but bitwidth, min, and max should be provided. Scale and offset will be calculated from the provided bitwidth, min, and max parameters regardless if they are provided or not.

Note : Quantization bit width 16 for activation, supported from Snapdragon 865/765 onwards on certain runtimes and currently not enable for all ops.

Float16 (half-precision) additionally enables converting the entire models to FP16 or selecting between FP16 and FP32 data-types for the float ops in case of mixed precision graphs with a mix of floating point and integer ops. The different modes of using mixed precision are described below.

No override: If no –quantization_overrides flag is given with an encoding file, all activations are quantized as per –act_bitwidth (default 8) and parameters are quantized as per –weight_bitwidth/–bias_bitwidth (default 8/8) respectively.

Full override: If –quantization_overrides flag is given along with encoding file specifying encodings for all ops in the model. In this case, the bitwidth with be set as per JSON for all ops defined as integer/float as per encoding file (dtype=’int’ or dtype=’float’ in encoding json).

Partial override: If –quantization_overrides flag is given along with encoding file specifying partial encodings (i.e. encodings are missing for some ops), the following will happen.

Layers for which encoding are NOT available in json file are encoded in the same manner as the no override case i.e. defined as integer with bitwidth defined as per –act_bitwidth/–weight_bitwidth/–bias_bitwidth (or their default values 8/8/8). For some ops (Conv2d, Conv3d, TransposeConv2d, DepthwiseConv2d, FullyConnected, MatMul) even if any of the output/weights/bias are specified as float in the encoding file, all three of them will be overridden to float. The float bitwidth used will be same as the float bitwidth of the overriding tensor in the encodings file. We can also manually control the bitwidth of bias tensors in such case (if encodings for it are absent in encodings json and present for output/weights) with the use of the –float_bias_bitwidth (16/32) flag.

Layers for which encoding are available in json are encoded in same manner as full override case.

We show a sample json for network with 3 Conv2d ops. The first and third Conv2d ops are INT8 while the second Conv2d op is marked as FP32. The FP32 op (namely conv2_1) is sandwiched between two INT8 ops in “activation_encodings”, hence convert ops will be inserted before and after the FP32 op. The corresponding weights and biases for conv2_1 are also marked as floating-point in the JSON in “param_encodings”.

{
       "activation_encodings": {
           "data_0": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ],
           "conv1_1": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ],
           "conv2_1": [
               {
                   "bitwidth": 32,
                   "dtype": "float"
               }
           ],
           "conv3_1": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ]
       },
       "param_encodings": {
           "conv1_w_0": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ],
           "conv1_b_0": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ],
           "conv2_w_0": [
               {
                   "bitwidth": 32,
                   "dtype": "float"
               }
           ],
           "conv2_b_0": [
               {
                   "bitwidth": 32,
                   "dtype": "float"
               }
           ],
           "conv3_w_0": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ],
           "conv3_b_0": [
               {
                   "bitwidth": 8,
                   "dtype": "int"
               }
           ]
       }
    }
The ops that are not present in json will be assumed to be fixed-point and the bit widths will be selected according to –act_bitwidth/–weight_bitwidth/–bias_bitwidth respectively.

{
       "activation_encodings": {
           "conv2_1": [
               {
                   "bitwidth": 32,
                   "dtype": "float"
               }
           ]
       },
       "param_encodings": {
           "conv2_w_0": [
               {
                   "bitwidth": 32,
                   "dtype": "float"
               }
           ],
           "conv2_b_0": [
               {
                   "bitwidth": 32,
                   "dtype": "float"
               }
           ]
       }
    }
The following quantized mixed-precision graph will be generated based on the JSON shown above. Please note that the convert operations are added appropriately to convert between float and int types and vice-versa.

../images/snpe_quantization_mp_graph.png
Per-channel Quantization Overrides

Per-channel quantization should be used for tensors that are weight inputs to Conv consumers (Conv2d, Conv3d, TransposeConv2d, DepthwiseConv2d). This section provides examples to manually override per-channel encodings for these Conv-based op weight tensors. Per-channel quantization will be used when we provide multiple encodings (equal to the number of channels) for the given tensor. We see an example for convolution weight for the following cases.

Case 1: Asymmetric encodings without per-channel quantization

{
    "features.9.conv.3.weight": [
        {
            "bitwidth": 8,
            "is_symmetric": "False",
            "max": 3.0387749017453665,
            "min": -2.059169834735364,
            "offset": -103,
            "scale": 0.019991940143061618
        }
    ]
}
Case 2: Per-channel quantization encodings with 3 output channels

{
    "features.8.conv.3.weight": [
        {
            "bitwidth": 8,
            "is_symmetric": "True",
            "max": 0.7011175155639648,
            "min": -0.7066381259227362,
            "offset": -128.0,
            "scale": 0.005520610358771377
        },
        {
            "bitwidth": 8,
            "is_symmetric": "True",
            "max": 0.5228064656257629,
            "min": -0.5269230519692729,
            "offset": -128.0,
            "scale": 0.004116586343509945
        },
        {
            "bitwidth": 8,
            "is_symmetric": "True",
            "max": 0.7368279099464417,
            "min": -0.7426297045129491,
            "offset": -128.0,
            "scale": 0.005801794566507415
        }
    ]
}
Note: Per-channel quantization must use symmetric representation with offset == -2^(bitwidth-1). Per-channel always has is_symmetric = True.

INT32 Overrides

INT32 overrides can also be provided to override an op to run in INT32 precision. To support running an op in INT32 precision, INT32 overrides should be provided for all of its inputs and outputs. This will inject a Dequantize op followed by a Cast (to: INT32) op at the inputs of the op and a Cast (to: FP32) op followed by a Quantize op at the output of the op for a quantized model. We show a sample graph below where the op “Op2” has its input and output tensor overridden to INT32 through the use of external overrides. This in turn generates the second graph to support the INT32 overrides through the use of Dequantize, Cast (to: INT32), Cast (to: FP32), and Quantize op.

../images/quantization_int32_graph.png
Note: INT32 overrides are only supported for ops which do not have weights and bias.

Previous

Supported ONNX Ops

Next

User-defined Operations

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
