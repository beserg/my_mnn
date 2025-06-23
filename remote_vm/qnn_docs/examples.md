

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
Running Nets
Running the Inception v3 Model
Running the Inception v3 Model
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Overview

The example C++ application in this tutorial is called snpe-net-run. It is a command line executable that executes a neural network using Qualcomm® Neural Processing SDK APIs.

The required arguments to snpe-net-run are:

A neural network model in the DLC file format

An input list file with paths to the input data.

Optional arguments to snpe-net-run are:

Choice of GPU, DSP or AIP runtimes (default is CPU)

Output directory (default is ./output)

Show help description

snpe-net-run creates and populates an output directory with the results of executing the neural network on the input data.

../images/neural_network.png
The Qualcomm® Neural Processing SDK provides Linux and Android binaries of snpe-net-run under

$SNPE_ROOT/bin/x86_64-linux-clang

$SNPE_ROOT/bin/aarch64-android

$SNPE_ROOT/bin/aarch64-oe-linux-gcc8.2

$SNPE_ROOT/bin/aarch64-oe-linux-gcc9.3

$SNPE_ROOT/bin/aarch64-ubuntu-gcc9.4

$SNPE_ROOT/bin/aarch64-oe-linux-gcc11.2

Prerequisites

The Qualcomm® Neural Processing SDK has been set up following the Qualcomm (R) Neural Processing SDK Setup chapter.

The Tutorials Setup has been completed.

TensorFlow is installed (see TensorFlow Setup)

Introduction

The Inception v3 Imagenet classification model is trained to classify images with 1000 labels.

The examples below shows the steps required to execute a pretrained optimized and optionally quantized Inception v3 model with snpe-net-run to classify a set of sample images. An optimized and quantized model is used in this example to showcase the DSP and AIP runtimes which execute quantized 8-bit neural network models.

The DLC for the model used in this tutorial was generated and optimized using the TensorFlow optimizer tool, during the Getting Inception v3 portion of the Tutorials Setup, by the script $SNPE_ROOT/examples/Models/InceptionV3/scripts/setup_inceptionv3_snpe.py. Additionally, if a fixed-point runtime such as DSP or AIP was selected when running the setup script, the model was quantized by snpe-dlc-quantize.

Learn more about a quantized model.

Run on Linux Host

Go to the base location for the model and run snpe-net-run

cd $SNPE_ROOT/examples/Models/InceptionV3
snpe-net-run --container dlc/inception_v3_quantized.dlc --input_list data/cropped/raw_list.txt
After snpe-net-run completes, the results are populated in the $SNPE_ROOT/examples/Models/InceptionV3/output directory. There should be one or more .log files and several Result_X directories, each containing a InceptionV3/Predictions/Reshape_1:0.raw file.

One of the inputs is data/cropped/notice_sign.raw and it was created from data/cropped/notice_sign.jpg which looks like the following:

../images/notice_sign.jpg
With this input, snpe-net-run created the output file $SNPE_ROOT/examples/Models/InceptionV3/output/Result_0/InceptionV3/Predictions/Reshape_1:0.raw. It holds the output tensor data of 1000 probabilities for the 1000 categories. The element with the highest value represents the top classification. A python script to interpret the classification results is provided and can be used as follows:

python3 $SNPE_ROOT/examples/Models/InceptionV3/scripts/show_inceptionv3_classifications_snpe.py -i data/cropped/raw_list.txt \
                                                                                  -o output/ \
                                                                                  -l data/imagenet_slim_labels.txt
The output should look like the following, showing classification results for all the images.

Classification results
<input_files_dir>/notice_sign.raw 0.170401 459 brass
<input_files_dir>/plastic_cup.raw 0.977711 648 measuring cup
<input_files_dir>/chairs.raw      0.299139 832 studio couch
<input_files_dir>/trash_bin.raw   0.747274 413 ashcan
Note: The <input_files_dir> above maps to a path such as $SNPE_ROOT/examples/Models/InceptionV3/data/cropped/

The output shows the image was classified as “brass” (index 459 of the labels) with a probability of 0.170401. The rest of the output can be examined to see the model’s classification on other images.

Binary data input

Note that the Inception v3 image classification model does not accept jpg files as input. The model expects its input tensor dimension to be 299x299x3 as a float array. The scripts/setup_inceptionv3_snpe.py script performs a jpg to binary data conversion by calling scripts/create_inceptionv3_raws.py. The scripts are an example of how jpg images can be preprocessed to generate input for the Inception v3 model.

Run on Target Platform ( Android/LE/UBUN )

Select target architecture

Qualcomm® Neural Processing SDK provides binaries for different target platforms. Android binaries are compiled with clang using libc++ STL implementation. Below are examples for aarch64-android (Android platform) and aarch64-oe-linux-gcc11.2 toolchain (LE platform). Similarly other toolchains for different platforms can be set as SNPE_TARGET_ARCH

# For Android targets: architecture: arm64-v8a - compiler: clang - STL: libc++
export SNPE_TARGET_ARCH=aarch64-android

# Example for LE targets
export SNPE_TARGET_ARCH=aarch64-oe-linux-gcc11.2
For simplicity, this tutorial sets the target binaries to aarch64-android. Following are commands for on host and on target.

Push libraries and binaries to target

Push Qualcomm® Neural Processing SDK libraries and the prebuilt snpe-net-run executable to /data/local/tmp/snpeexample on the Android target. Set SNPE_TARGET_DSPARCH to the DSP architecture of the target Android device.

export SNPE_TARGET_ARCH=aarch64-android
export SNPE_TARGET_DSPARCH=hexagon-v73

adb shell "mkdir -p /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin"
adb shell "mkdir -p /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib"
adb shell "mkdir -p /data/local/tmp/snpeexample/dsp/lib"

adb push $SNPE_ROOT/lib/$SNPE_TARGET_ARCH/*.so \
      /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
adb push $SNPE_ROOT/lib/$SNPE_TARGET_DSPARCH/unsigned/*.so \
      /data/local/tmp/snpeexample/dsp/lib
adb push $SNPE_ROOT/bin/$SNPE_TARGET_ARCH/snpe-net-run \
      /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
Set up enviroment variables

Set up the library path, the path variable, and the target architecture in adb shell to run the executable with the -h argument to see its description.

adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
snpe-net-run -h
exit
Push model data to Android target

To execute the Inception v3 classification model on Android target follow these steps:

cd $SNPE_ROOT/examples/Models/InceptionV3
mkdir data/rawfiles && cp data/cropped/*.raw data/rawfiles/
adb shell "mkdir -p /data/local/tmp/inception_v3"
adb push data/rawfiles /data/local/tmp/inception_v3/cropped
adb push data/target_raw_list.txt /data/local/tmp/inception_v3
adb push dlc/inception_v3_quantized.dlc /data/local/tmp/inception_v3
rm -rf data/rawfiles
Note: It may take some time to push the Inception v3 dlc file to the target.

Running on Android using CPU Runtime

The Android C++ executable is run with the following commands:

adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
cd /data/local/tmp/inception_v3
snpe-net-run --container inception_v3_quantized.dlc --input_list target_raw_list.txt
exit
The executable will create the results folder: /data/local/tmp/inception_v3/output. To pull the output:

adb pull /data/local/tmp/inception_v3/output output_android
Check the classification results by running the following python script:

python3 scripts/show_inceptionv3_classifications_snpe.py -i data/target_raw_list.txt \
                                                   -o output_android/ \
                                                   -l data/imagenet_slim_labels.txt
The output should look like the following, showing classification results for all the images.

Classification results
cropped/notice_sign.raw 0.170409 459 brass
cropped/plastic_cup.raw 0.977708 648 measuring cup
cropped/chairs.raw      0.299145 832 studio couch
cropped/trash_bin.raw   0.747256 413 ashcan
Running on Android using DSP Runtime

Try running on an Android target with the –use_dsp option as follows:
Note the extra environment variable ADSP_LIBRARY_PATH must be set to use DSP. (See DSP Runtime Environment for details.)
adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
export ADSP_LIBRARY_PATH="/data/local/tmp/snpeexample/dsp/lib;/system/lib/rfsa/adsp;/system/vendor/lib/rfsa/adsp;/dsp"
cd /data/local/tmp/inception_v3
snpe-net-run --container inception_v3_quantized.dlc --input_list target_raw_list.txt --use_dsp
exit
Pull the output into an output_android_dsp directory.

adb pull /data/local/tmp/inception_v3/output output_android_dsp
Check the classification results by running the following python script:

python3 scripts/show_inceptionv3_classifications_snpe.py -i data/target_raw_list.txt \
                                                   -o output_android_dsp/ \
                                                   -l data/imagenet_slim_labels.txt
The output should look like the following, showing classification results for all the images.

Classification results
cropped/notice_sign.raw 0.175781 459 brass
cropped/plastic_cup.raw 0.976562 648 measuring cup
cropped/chairs.raw      0.285156 832 studio couch
cropped/trash_bin.raw   0.773438 413 ashcan
Classification results are identical to the run with CPU runtime, but there are differences in the probabilities associated with the output labels due to floating point precision differences.

Running on Android using AIP Runtime

The AIP runtime allows you to run the Inception v3 model on the HTA. Running the model using the AIP runtime requires setting the –runtime argument as ‘aip’ in the script $SNPE_ROOT/examples/Models/InceptionV3/scripts/setup_inceptionv3_snpe.py to allow HTA-specific metadata to be packed into the DLC that is required by the AIP runtime. Refer to Getting Inception v3 for more details.

Other than that the additional settings for AIP runtime are quite similar to those for the DSP runtime. Note the extra environment variable ADSP_LIBRARY_PATH must be set to use DSP (See DSP Runtime Environment for details). Try running on an Android target with the –use_aip option as follows:

adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
export ADSP_LIBRARY_PATH="/data/local/tmp/snpeexample/dsp/lib;/system/lib/rfsa/adsp;/system/vendor/lib/rfsa/adsp;/dsp"
cd /data/local/tmp/inception_v3
snpe-net-run --container inception_v3_quantized.dlc --input_list target_raw_list.txt --use_aip
exit
Pull the output into an output_android_aip directory.

adb pull /data/local/tmp/inception_v3/output output_android_aip
Check the classification results by running the following python script:

python3 scripts/show_inceptionv3_classifications_snpe.py -i data/target_raw_list.txt \
                                                   -o output_android_aip/ \
                                                   -l data/imagenet_slim_labels.txt
The output should look like the following, showing classification results for all the images.

Classification results
cropped/notice_sign.raw 0.175781 459 brass
cropped/plastic_cup.raw 0.976562 648 measuring cup
cropped/chairs.raw      0.285156 832 studio couch
cropped/trash_bin.raw   0.773438 413 ashcan
Classification results are identical to the run with CPU runtime, but there are differences in the probabilities associated with the output labels due to floating point precision differences.

Previous

Running Nets

Next

Running the Inception v3 Model in Windows

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

Tutorials and Examples
Running Nets
Running the Word-RNN Model
Running the Word-RNN Model
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Overview

The example C++ application in this tutorial is called snpe-net-run. It is a command line executable that executes a neural network using Qualcomm® Neural Processing SDK APIs.

The required arguments to snpe-net-run are:

A neural network model in the DLC file format

An input list file with paths to the input data.

Optional arguments to snpe-net-run are:

Choice of GPU or DSP runtime (default is CPU)

Output directory (default is ./output)

Show help description

snpe-net-run creates and populates an output directory with the results of executing the neural network on the input data.

../images/neural_network.png
The Qualcomm® Neural Processing SDK provides Linux and Android binaries of snpe-net-run under

$SNPE_ROOT/bin/x86_64-linux-clang

$SNPE_ROOT/bin/aarch64-android

$SNPE_ROOT/bin/aarch64-oe-linux-gcc8.2

$SNPE_ROOT/bin/aarch64-oe-linux-gcc9.3

Introduction

Recurrent Neural Network (RNN) architectures are widely used in Machine Learning Applications for processing sequential input data. This chapter will show a simple Word-RNN example for predicting the next word in an embedding using Long Short-Term Memory (LSTM). The step-by-step example will create, train, convert, and execute a Word-RNN model with Qualcomm® Neural Processing SDK.

The external python3 packages needed by this example are:

numpy

pandas

sklearn

tensorflow (1.6 or 1.11)

There are six files in $SNPE_ROOT/examples/Models/word_rnn folder

inference.py

input_list.txt

belling_the_cat.txt

word_rnn.py

word_rnn_adb.sh

NOTICE.txt

The word_rnn.py python3 script creates and trains an RNN model with one LSTM layer. After RNN training is done, the corresponding frozen protobuf file will be generated.

The inference.py prompts the user to enter several words, at which point snpe-net-run will be called in a loop to generate subsequent words.

Prerequisites

The Qualcomm® Neural Processing SDK has been set up following the Qualcomm (R) Neural Processing SDK Setup chapter.

The Tutorials Setup has been completed.

TensorFlow is installed (see TensorFlow Setup)

Create, Train, and Convert Word-RNN Model

Run word_rnn.py to create and train the Word-RNN model.

cd $SNPE_ROOT/examples/Models/word_rnn
python3 word_rnn.py
The terminal will show the following messages.

Training will be logged in word_rnn_log.
Load training file belling_the_cat.txt.
Embedding created.
Iter= 1000
Iter= 2000
Iter= 3000
Iter= 4000
Iter= 5000
Optimization done.
Converted 2 variables to const ops.
Save frozen graph in word_rnn.pb.
Then, convert the frozen graph model with snpe-tensorflow-to-dlc.

snpe-tensorflow-to-dlc --input_network word_rnn.pb \
                       --input_dim Placeholder "1, 4, 1" \
                       --out_node "rnn/lstm_cell/mul_11" \
                       --output_path word_rnn.dlc
After dlc conversion, we can view the converted dlc architecture with snpe-dlc-info and snpe-dlc-viewer as follows:

snpe-dlc-info -i word_rnn.dlc
snpe-dlc-viewer -i word_rnn.dlc
Run on Linux Host

Go to the base location for the model and run the python3 script including snpe-net-run

python3 inference.py
After running inference.py, you will see a list of word embedding keys and user input prompt as follows:

Load training file belling_the_cat.txt.
Embedding created.
Use host cpu.
Display word embedding keys:
dict_keys(['long', 'is', 'up', 'it', 'i', 'chief', 'our', 'procured', 'her', 'in', 'mouse', 'council', 'treacherous', 'meet', 'manner', 'approaches', 'with', 'propose', 'which', 'consider', 'thought', 'know', 'bell', 'signal', 'always', 'by', 'small', 'old', 'could', 'about', 'neck', 'of', 'approach', 'well', 'easy', 'take', 'all', 'outwit', 'met', 'they', 'this', 'who', 'cat', 'what', '.', 'will', 'attached', 'their', 'when', 'receive', 'agree', 'applause', 'and', 'if', 'now', 'to', 'a', 'round', 'enemy', 'was', 'ribbon', 'us', 'had', 'general', 'ago', 'means', 'last', 'venture', 'got', 'sly', 'measures', 'young', 'she', 'very', 'impossible', 'therefore', 'we', 'should', 'one', 'mice', 'case', '?', 'make', 'nobody', 'he', 'that', 'consists', 'spoke', 'from', 'easily', 'at', 'neighbourhood', 'the', 'looked', 'then', 'until', 'an', 'common', 'but', 'be', 'would', 'danger', 'retire', 'proposal', 'another', 'you', ',', 'while', 'escape', 'some', 'remedies', 'said'])
Please input 4 words:
User can input embedded words and see the results. For example: long ago , the

...
...
-------------------------------------------------------------------------------
Model String: N/A
SNPE vX.Y.Z.dev
-------------------------------------------------------------------------------
Processing DNN input(s):
./input.raw
-------------------------------------------------------------------------------
Model String: N/A
SNPE vX.Y.Z.dev
-------------------------------------------------------------------------------
Processing DNN input(s):
./input.raw
Inference result: long ago , the said she a , , and the could could , , and the could could , , and the could could , , and the could could , , and the could
The inference.py will call snpe-net-run several times to generate subsequent words with trained LSTM model.

Binary data input

Note that the Word-RNN model does not accept pure text files as input. The model expects its input tensor dimension to be 1x4x1 as a float array. The create_embedding function in inference.py will parse, collect, encode, and build the word embedding. User inputs will then be transformed into a 1x4x1 vector and sent into the LSTM model. Afterwards the LSTM output will be also transformed into the corresponding embedded word.

Run on Target Platform ( Android/LE/UBUN )

Select target architecture

Qualcomm® Neural Processing SDK provides binaries for different target platforms. Android binaries are compiled with clang using libc++ STL implementation. Below are examples for aarch64-android (Android platform) and aarch64-oe-linux-gcc11.2 toolchain (LE platform). Similarly other toolchains for different platforms can be set as SNPE_TARGET_ARCH

# For Android targets: architecture: arm64-v8a - compiler: clang - STL: libc++
export SNPE_TARGET_ARCH=aarch64-android

# Example for LE targets
export SNPE_TARGET_ARCH=aarch64-oe-linux-gcc11.2
For simplicity, this tutorial sets the target binaries to aarch64-android.

Push libraries and binaries to target

Push Qualcomm® Neural Processing SDK libraries and the prebuilt snpe-net-run executable to /data/local/tmp/snpeexample on the Android target. Set SNPE_TARGET_DSPARCH to the DSP architecture of the target Android device.

export SNPE_TARGET_ARCH=aarch64-android
export SNPE_TARGET_DSPARCH=hexagon-v73

adb -s $DEVICE_ID shell "mkdir -p /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin"
adb -s $DEVICE_ID shell "mkdir -p /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib"
adb -s $DEVICE_ID shell "mkdir -p /data/local/tmp/snpeexample/dsp/lib"

adb -s $DEVICE_ID push $SNPE_ROOT/lib/$SNPE_TARGET_ARCH/*.so \
      /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
adb -s $DEVICE_ID push $SNPE_ROOT/lib/$SNPE_TARGET_DSPARCH/unsigned/*.so \
      /data/local/tmp/snpeexample/dsp/lib
adb -s $DEVICE_ID push $SNPE_ROOT/bin/$SNPE_TARGET_ARCH/snpe-net-run \
      /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
Set up enviroment variables

Set up the library path, the path variable, and the target architecture in adb shell to run the executable with the -h argument to see its description.

adb -s $DEVICE_ID shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
snpe-net-run -h
exit
Push model data to Android target

To execute the Word-RNN model on your Android target follow these steps:

adb -s $DEVICE_ID shell "mkdir -p /data/local/tmp/word_rnn"
adb -s $DEVICE_ID push input_list.txt /data/local/tmp/word_rnn
adb -s $DEVICE_ID push input.raw /data/local/tmp/word_rnn
adb -s $DEVICE_ID push word_rnn.dlc /data/local/tmp/word_rnn
adb -s $DEVICE_ID push word_rnn_adb.sh /data/local/tmp/word_rnn
Note: It may take some time to push the word_rnn dlc file to your target.

Running on Android using CPU Runtime

Run the Android C++ executable with the following commands:

cd /data/local/tmp/word_rnn
snpe-net-run --container word_rnn.dlc --input_list input_list.txt
We will get the same result as when we Run on Linux Host.

Previous

Running the Inception v3 Model in Windows

Next

Running the Spoken Digit Recognition Model

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

Tutorials and Examples
Running Nets
Running the Spoken Digit Recognition Model
Running the Spoken Digit Recognition Model
Updated: Jun 11, 2025 80-63442-2 Rev: BL
Overview

The example C++ application in this tutorial is called snpe-net-run. It is a command line executable that executes a neural network using Qualcomm® Neural Processing SDK APIs.

The required arguments to snpe-net-run are:

A neural network model in the DLC file format

An input list file with paths to the input data.

Optional arguments to snpe-net-run are:

Choice of GPU or DSP runtime (default is CPU)

Output directory (default is ./output)

Show help description

snpe-net-run creates and populates an output directory with the results of executing the neural network on the input data.

../images/neural_network.png
The Qualcomm® Neural Processing SDK provides Linux and Android binaries of snpe-net-run under

$SNPE_ROOT/bin/x86_64-linux-clang

$SNPE_ROOT/bin/aarch64-android

$SNPE_ROOT/bin/aarch64-oe-linux-gcc8.2

$SNPE_ROOT/bin/aarch64-oe-linux-gcc9.3

Introduction

This chapter will show an example of recognizing the 10 classes in the free spoken digit dataset, with data processing and a 4-layer neural network, through Qualcomm® Neural Processing SDK. The step-by-step example will create, train, convert, and execute a TensorFlow-Keras audio model with Qualcomm® Neural Processing SDK.

As a prerequisite, users should download the Free Spoken Digit Dataset (FSDD).

cd $SNPE_ROOT/examples/Models/spoken_digit
git clone https://github.com/Jakobovski/free-spoken-digit-dataset
The external python3 packages required for this example are:

librosa (0.10.2)

tensorflow (2.10.1)

There are five files and a single directory in the $SNPE_ROOT/examples/Models/spoken_digit folder

free-spoken-digit-dataset (download from git)

input_list.txt

interpretRawDNNOutput.py

processSpokenDigitInput.py

spoken_digit.py

NOTICE.txt

The interpretRawDNNOutput.py will translate Qualcomm® Neural Processing SDK output and display the prediction.

The processSpokenDigitInput.py processes user input wav audio file into raw format for snpe-net-run.

The spoken_digit.py python3 script creates and trains a 5-layer neural network model. After training is done, the corresponding frozen protobuf file will be generated.

The free-spoken-digit-dataset directory is the dataset downloaded by the user.

Prerequisites

The Qualcomm® Neural Processing SDK has been set up following the Qualcomm (R) Neural Processing SDK Setup chapter.

The Tutorials Setup has been completed.

TensorFlow is installed (see TensorFlow Setup)

Create, Train, and Convert Spoken Digit Model

Run spoken_digit.py to create and train the Word-RNN model.

cd $SNPE_ROOT/examples/Models/spoken_digit
python3 spoken_digit.py
The terminal will show the following messages.

Successfully split free-spoken-digit-dataset training/testing data.
Training data created.
Epoch 1/20
4/4 [==============================] - 1s 142ms/step - loss: 30.2495 - accuracy: 0.1328 - val_loss: 25.0048 - val_accuracy: 0.1406
Epoch 2/20
4/4 [==============================] - 0s 42ms/step - loss: 16.7755 - accuracy: 0.1680 - val_loss: 10.5915 - val_accuracy: 0.1680
Epoch 3/20
4/4 [==============================] - 0s 44ms/step - loss: 9.0951 - accuracy: 0.1504 - val_loss: 8.5512 - val_accuracy: 0.1641
...
...
...
Epoch 18/20
4/4 [==============================] - 0s 31ms/step - loss: 0.2890 - accuracy: 0.9297 - val_loss: 1.8068 - val_accuracy: 0.6504
Epoch 19/20
4/4 [==============================] - 0s 28ms/step - loss: 0.2399 - accuracy: 0.9336 - val_loss: 1.7628 - val_accuracy: 0.6602
Epoch 20/20
4/4 [==============================] - 0s 29ms/step - loss: 0.2024 - accuracy: 0.9512 - val_loss: 1.7234 - val_accuracy: 0.6777
Optimization done.
Save frozen graph in spoken_digit.pb.
Next, convert the frozen graph model with snpe-tensorflow-to-dlc.

snpe-tensorflow-to-dlc --input_network model/spoken_digit.pb \
                       --input_dim x "1, 10, 35" \
                       --out_node "Identity \
                       --output_path spoken_digit.dlc
After DLC conversion, we can view the converted dlc architecture with snpe-dlc-info and snpe-dlc-viewer as follows:

snpe-dlc-info -i spoken_digit.dlc
The output will be:

-------------------------------------------------------------------------------------------------------------------------------------------------------------
| Id | Name                                   | Type              | Inputs                                                                                                                | Outputs                                                                                                           | Out Dims | Runtimes | Parameters                               |
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
| 0  | sequential/dense/Tensordot/transpose   | Transpose         | x:0 (data type: Float_32; tensor dimension: [1,10,35]; tensor type: APP_WRITE) [NW Input]                             | sequential/dense/Tensordot/transpose:0 (data type: Float_32; tensor dimension: [1,10,35]; tensor type: NATIVE)    | 1x10x35  | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | perm: [0, 1, 2]                          |
| 1  | sequential/dense/Tensordot/Reshape:0   | Reshape           | sequential/dense/Tensordot/transpose:0 (data type: Float_32; tensor dimension: [1,10,35]; tensor type: NATIVE)        | sequential/dense/Tensordot/Reshape:0 (data type: Float_32; tensor dimension: [10,35]; tensor type: NATIVE)        | 10x35    | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | shape: [10, 35]                          |
| 2  | sequential/dense/Tensordot/MatMul      | FullyConnected    | sequential/dense/Tensordot/Reshape:0 (data type: Float_32; tensor dimension: [10,35]; tensor type: NATIVE)            | sequential/dense/Tensordot/MatMul:0 (data type: Float_32; tensor dimension: [10,256]; tensor type: NATIVE)        | 10x256   | A D G C  | bias_op_name: sequential/dense/BiasAdd   |
|    |                                        |                   | sequential/dense/Tensordot/ReadVariableOp:0 (data type: Float_32; tensor dimension: [256,35]; tensor type: STATIC)    |                                                                                                                   |          |          | packageName: qti.aisw                    |
|    |                                        |                   | sequential/dense/BiasAdd/ReadVariableOp:0 (data type: Float_32; tensor dimension: [256]; tensor type: STATIC)         |                                                                                                                   |          |          | param count: 9k (16.2%)                  |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | MACs per inference: 8k (15.9%)           |
| 3  | sequential/dense/BiasAdd:0             | Reshape           | sequential/dense/Tensordot/MatMul:0 (data type: Float_32; tensor dimension: [10,256]; tensor type: NATIVE)            | sequential/dense/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10,256]; tensor type: NATIVE)               | 1x10x256 | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | shape: [1, 10, 256]                      |
| 4  | sequential/dense/Relu                  | ElementWiseNeuron | sequential/dense/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10,256]; tensor type: NATIVE)                   | sequential/dense_1/Tensordot/transpose:0 (data type: Float_32; tensor dimension: [1,10,256]; tensor type: NATIVE) | 1x10x256 | A D G C  | operation: 4                             |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | packageName: qti.aisw                    |
| 5  | sequential/dense_1/Tensordot/Reshape:0 | Reshape           | sequential/dense_1/Tensordot/transpose:0 (data type: Float_32; tensor dimension: [1,10,256]; tensor type: NATIVE)     | sequential/dense_1/Tensordot/Reshape:0 (data type: Float_32; tensor dimension: [10,256]; tensor type: NATIVE)     | 10x256   | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | shape: [10, 256]                         |
| 6  | sequential/dense_1/Tensordot/MatMul    | FullyConnected    | sequential/dense_1/Tensordot/Reshape:0 (data type: Float_32; tensor dimension: [10,256]; tensor type: NATIVE)         | sequential/dense_1/Tensordot/MatMul:0 (data type: Float_32; tensor dimension: [10,128]; tensor type: NATIVE)      | 10x128   | A D G C  | bias_op_name: sequential/dense_1/BiasAdd |
|    |                                        |                   | sequential/dense_1/Tensordot/ReadVariableOp:0 (data type: Float_32; tensor dimension: [128,256]; tensor type: STATIC) |                                                                                                                   |          |          | packageName: qti.aisw                    |
|    |                                        |                   | sequential/dense_1/BiasAdd/ReadVariableOp:0 (data type: Float_32; tensor dimension: [128]; tensor type: STATIC)       |                                                                                                                   |          |          | param count: 32k (57.9%)                 |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | MACs per inference: 32k (58.2%)          |
| 7  | sequential/dense_1/BiasAdd:0           | Reshape           | sequential/dense_1/Tensordot/MatMul:0 (data type: Float_32; tensor dimension: [10,128]; tensor type: NATIVE)          | sequential/dense_1/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10,128]; tensor type: NATIVE)             | 1x10x128 | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | shape: [1, 10, 128]                      |
| 8  | sequential/dense_1/Relu                | ElementWiseNeuron | sequential/dense_1/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10,128]; tensor type: NATIVE)                 | sequential/dense_2/Tensordot/transpose:0 (data type: Float_32; tensor dimension: [1,10,128]; tensor type: NATIVE) | 1x10x128 | A D G C  | operation: 4                             |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | packageName: qti.aisw                    |
| 9  | sequential/dense_2/Tensordot/Reshape:0 | Reshape           | sequential/dense_2/Tensordot/transpose:0 (data type: Float_32; tensor dimension: [1,10,128]; tensor type: NATIVE)     | sequential/dense_2/Tensordot/Reshape:0 (data type: Float_32; tensor dimension: [10,128]; tensor type: NATIVE)     | 10x128   | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | shape: [10, 128]                         |
| 10 | sequential/dense_2/Tensordot/MatMul    | FullyConnected    | sequential/dense_2/Tensordot/Reshape:0 (data type: Float_32; tensor dimension: [10,128]; tensor type: NATIVE)         | sequential/dense_2/Tensordot/MatMul:0 (data type: Float_32; tensor dimension: [10,64]; tensor type: NATIVE)       | 10x64    | A D G C  | bias_op_name: sequential/dense_2/BiasAdd |
|    |                                        |                   | sequential/dense_2/Tensordot/ReadVariableOp:0 (data type: Float_32; tensor dimension: [64,128]; tensor type: STATIC)  |                                                                                                                   |          |          | packageName: qti.aisw                    |
|    |                                        |                   | sequential/dense_2/BiasAdd/ReadVariableOp:0 (data type: Float_32; tensor dimension: [64]; tensor type: STATIC)        |                                                                                                                   |          |          | param count: 8k (14.5%)                  |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | MACs per inference: 8k (14.5%)           |
| 11 | sequential/dense_2/BiasAdd:0           | Reshape           | sequential/dense_2/Tensordot/MatMul:0 (data type: Float_32; tensor dimension: [10,64]; tensor type: NATIVE)           | sequential/dense_2/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10,64]; tensor type: NATIVE)              | 1x10x64  | A D G C  | packageName: qti.aisw                    |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | shape: [1, 10, 64]                       |
| 12 | sequential/dense_2/Relu                | ElementWiseNeuron | sequential/dense_2/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10,64]; tensor type: NATIVE)                  | sequential/dense_2/Relu:0 (data type: Float_32; tensor dimension: [1,10,64]; tensor type: NATIVE)                 | 1x10x64  | A D G C  | operation: 4                             |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | packageName: qti.aisw                    |
| 13 | sequential/dense_3/MatMul              | FullyConnected    | sequential/dense_2/Relu:0 (data type: Float_32; tensor dimension: [1,10,64]; tensor type: NATIVE)                     | sequential/dense_3/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10]; tensor type: NATIVE)                 | 1x10     | A D G C  | bias_op_name: sequential/dense_3/BiasAdd |
|    |                                        |                   | sequential/dense_3/MatMul/ReadVariableOp:0 (data type: Float_32; tensor dimension: [10,640]; tensor type: STATIC)     |                                                                                                                   |          |          | packageName: qti.aisw                    |
|    |                                        |                   | sequential/dense_3/BiasAdd/ReadVariableOp:0 (data type: Float_32; tensor dimension: [10]; tensor type: STATIC)        |                                                                                                                   |          |          | param count: 6k (11.3%)                  |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | MACs per inference: 6k (11.4%)           |
| 14 | sequential/dense_3/Softmax             | Softmax           | sequential/dense_3/BiasAdd:0 (data type: Float_32; tensor dimension: [1,10]; tensor type: NATIVE)                     | Identity:0 (data type: Float_32; tensor dimension: [1,10]; tensor type: APP_READ)                                 | 1x10     | A D G C  | axis: 1                                  |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | beta: 1                                  |
|    |                                        |                   |                                                                                                                       |                                                                                                                   |          |          | packageName: qti.aisw                    |
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
snpe-dlc-viewer -i spoken_digit.dlc
The output network model HTML file will be saved at /tmp/spoken_digit.html.

Run on Linux Host

First, processSpokenDigitInput.py script needs to be run in order to process the audio input data test/5_jackson_0.wav to raw format. The output name will be input.raw

python3 processSpokenDigitInput.py test/5_jackson_0.wav
Next, run snpe-net-run to get the inference result.

snpe-net-run --container spoken_digit.dlc --input_list input_list.txt
After snpe-net-run completes, verify that the results are populated in the $SNPE_ROOT/examples/Models/spoken_digit/output directory. There should be one or more .log files and several Result_X directories.

The raw output prediction will be located in $SNPE_ROOT/examples/Models/spoken_digit/output/Result_0/output/Result_0/Identity:0.raw. It holds the output tensor data of 10 probabilities for the 10 categories. The element with the highest value represents the top classification. We can use a python3 script to interpret the classification results as follows:

python3 interpretRawDNNOutput.py output/Result_0/Identity:0.raw
The output should look like the following, showing classification results for all the images.

 0 : 0.000110
 1 : 0.012185
 2 : 0.000011
 3 : 0.000593
 4 : 0.002053
 5 : 0.814478
 6 : 0.002425
 7 : 0.043664
 8 : 0.003228
 9 : 0.121254
Classification Result: Class 5.
The final output shows the audio file was classified as “Class 5” (from a total of 10 labels) with a probability of 0.814478. Look at the rest of the output to see the model’s classification on other classes.

Binary data input

Note that the spoken digit classification model does not accept wav files as input. The model expects its input tensor dimension to be 1 x 10 x 35 as a float array. The processSpokenDigitInput.py script performs a wav to binary data conversion. The script is an example of how wav audio files can be preprocessed to generate input for the classification model.

Run on Target Platform ( Android/LE/UBUN )

Select target architecture

Qualcomm® Neural Processing SDK provides binaries for different target platforms. Android binaries are compiled with clang using libc++ STL implementation. Below are examples for aarch64-android (Android platform) and aarch64-oe-linux-gcc11.2 toolchain (LE platform). Similarly other toolchains for different platforms can be set as SNPE_TARGET_ARCH

# For Android targets: architecture: arm64-v8a - compiler: clang - STL: libc++
export SNPE_TARGET_ARCH=aarch64-android

# Example for LE targets
export SNPE_TARGET_ARCH=aarch64-oe-linux-gcc11.2
For simplicity, this tutorial sets the target binaries to aarch64-android.

Push libraries and binaries to target

Push Qualcomm® Neural Processing SDK libraries and the prebuilt snpe-net-run executable to /data/local/tmp/snpeexample on the Android target. Set SNPE_TARGET_DSPARCH to the DSP architecture of the target Android device.

export SNPE_TARGET_ARCH=aarch64-android
export SNPE_TARGET_DSPARCH=hexagon-v73

adb shell "mkdir -p /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin"
adb shell "mkdir -p /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib"
adb shell "mkdir -p /data/local/tmp/snpeexample/dsp/lib"

adb push $SNPE_ROOT/lib/$SNPE_TARGET_ARCH/*.so \
      /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
adb push $SNPE_ROOT/lib/$SNPE_TARGET_DSPARCH/unsigned/*.so \
      /data/local/tmp/snpeexample/dsp/lib
adb push $SNPE_ROOT/bin/$SNPE_TARGET_ARCH/snpe-net-run \
      /data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
Set up enviroment variables

Set up the library path, the path variable, and the target architecture in adb shell to run the executable with the -h argument to see its description.

adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
snpe-net-run -h
exit
Push model data to Android target

To execute the spoken digit classification model on your Android target follow these steps:

adb shell "mkdir -p /data/local/tmp/spoken_digit"
adb push input.raw /data/local/tmp/spoken_digit
adb push input_list.txt /data/local/tmp/spoken_digit
adb push spoken_digit.dlc /data/local/tmp/spoken_digit
Note: It may take some time to push the DLC file to your target.

Running on Android using CPU Runtime

Run the Android C++ executable with the following commands:

adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
cd /data/local/tmp/spoken_digit
snpe-net-run --container spoken_digit.dlc --input_list input_list.txt
exit
The executable will create the results folder: /data/local/tmp/spoken_digit/output. To pull the output:

adb pull /data/local/tmp/spoken_digit/output output_android
Check the classification results by running the interpret python3 script.

python3 interpretRawDNNOutput.py output_android/Result_0/Identity:0.raw
The output should look like the following, showing classification results for all the images.

 0 : 0.000110
 1 : 0.012185
 2 : 0.000011
 3 : 0.000593
 4 : 0.002053
 5 : 0.814478
 6 : 0.002425
 7 : 0.043664
 8 : 0.003228
 9 : 0.121254
Classification Result: Class 5.
Running on Android using GPU Runtime

Try running on an Android target with the –use_gpu option as follows. By default, the GPU runtime runs in GPU_FLOAT32_16_HYBRID (math: full float and data storage: half float) mode. We can change the mode to GPU_FLOAT16 (math: half float and data storage: half float) using –gpu_mode option.

adb shell
export SNPE_TARGET_ARCH=aarch64-android
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/lib
export PATH=$PATH:/data/local/tmp/snpeexample/$SNPE_TARGET_ARCH/bin
cd /data/local/tmp/spoken_digit
snpe-net-run --container spoken_digit.dlc --input_list input_list.txt --use_gpu
exit
Pull the output into an output_android_gpu directory.

adb pull /data/local/tmp/spoken_digit/output output_android_gpu
Again, we can run the interpret script to see the classification results.

python3 interpretRawDNNOutput.py output_android_gpu/Result_0/Identity:0.raw
The output should look like the following, showing classification results for all the images.

 0 : 0.000113
 1 : 0.012330
 2 : 0.000011
 3 : 0.000604
 4 : 0.002087
 5 : 0.813591
 6 : 0.002461
 7 : 0.043883
 8 : 0.003279
 9 : 0.121640
Classification Result: Class 5.
Review the output for the classification results. Classification results are identical to the run with CPU runtime, but there are differences in the probabilities associated with the output labels due to floating point precision differences.

Previous

Running the Word-RNN Model

Next

Running a VGG Model

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
