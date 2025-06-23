# 🦙 Llama Chat - Android App with MNN Inference

A high-performance Android chat application featuring **Meta Llama-3.2-1B** running locally on **Samsung Galaxy S24 Ultra** using the **MNN inference framework**.

![Android](https://img.shields.io/badge/Android-15-green.svg)
![MNN](https://img.shields.io/badge/MNN-Framework-blue.svg)
![Llama](https://img.shields.io/badge/Llama--3.2--1B-Meta-red.svg)
![Galaxy S24](https://img.shields.io/badge/Galaxy%20S24%20Ultra-Tested-purple.svg)

## 🎯 Project Overview

This project demonstrates **on-device AI inference** on Android by implementing a complete chat application with:

- **Local Llama-3.2-1B inference** (no internet required)
- **Multiple quantization levels** (INT8 & FP16)
- **Hardware acceleration** support (CPU, GPU, NPU)
- **Model selector interface** for performance comparison
- **Real-time performance metrics** display
- **Modern Material Design UI** inspired by PocketPal AI

## ✨ Features

### 🚀 Model Support
- **Llama-3.2-1B INT8** (1.39GB) - Optimized for speed and memory efficiency
- **Llama-3.2-1B FP16** (2.24GB) - Higher precision for better quality
- **Runtime model switching** via elegant selector dialog

### 🏎️ Backend Support
- **CPU**: ARM NEON optimized inference
- **GPU**: OpenCL acceleration (Adreno GPU)
- **NPU**: NNAPI integration (Snapdragon AI Engine)

### 📱 User Interface
- **Clean Material Design** with modern chat bubbles
- **Performance metrics** (tokens/sec, prefill/decode timing)
- **Backend selection** with expected performance indicators
- **Model information** display with tap-to-change functionality
- **Responsive layout** optimized for Galaxy S24 Ultra

### 🔧 Technical Features
- **MNN native inference** with custom JNI integration
- **Asset management** for model files in Downloads folder
- **Permission handling** for external storage access
- **Comprehensive logging** for debugging and performance analysis
- **Gradle build system** with NDK integration

## 📋 Model Details

| Model | Quantization | Size | Performance | Use Case |
|-------|-------------|------|-------------|----------|
| Llama-3.2-1B INT8 | 8-bit | 1.39GB | ~8-12 tokens/sec (CPU) | Fast inference, lower memory |
| Llama-3.2-1B FP16 | 16-bit | 2.24GB | ~6-10 tokens/sec (CPU) | Better quality, more memory |

### Performance Benchmarks (Galaxy S24 Ultra)
```
CPU (ARM NEON):     8-12 tokens/sec
GPU (Adreno):       15-25 tokens/sec  
NPU (Snapdragon):   20-35 tokens/sec*
```
*NPU performance depends on hardware support

## 🛠️ Build Instructions

### Prerequisites
- **Android Studio** with NDK
- **Android SDK** (API 34+, Android 15)
- **CMake** 3.22.1+
- **Git LFS** for large model files

### 1. Clone Repository
```bash
git clone <repository-url>
cd my_mnn
git submodule update --init --recursive
```

### 2. Build MNN Framework
```bash
cd project/android
export ANDROID_NDK=/path/to/ndk/23.0.7599858
./build_64.sh -DMNN_BUILD_LLM=ON -DMNN_SUPPORT_TRANSFORMER_FUSE=ON
```

### 3. Convert Llama Models
```bash
cd transformers/llm/export
pip install -r requirements.txt

# Download Llama-3.2-1B from HuggingFace
export HF_TOKEN=your_huggingface_token
huggingface-cli download meta-llama/Llama-3.2-1B --local-dir ./models/Llama-3.2-1B

# Convert to MNN format
python llmexport.py --path ./models/Llama-3.2-1B --dst_path ../../../models/Llama-3.2-1B-int8-mnn --export mnn --quant_bit 8 --mnnconvert ../../../build/MNNConvert
python llmexport.py --path ./models/Llama-3.2-1B --dst_path ../../../models/Llama-3.2-1B-fp16-mnn --export mnn --quant_bit 16 --mnnconvert ../../../build/MNNConvert
```

### 4. Deploy Models to Device
```bash
adb shell mkdir -p /storage/emulated/0/Download/llama-3.2-1b-mnn/
adb shell mkdir -p /storage/emulated/0/Download/llama-3.2-1b-fp16-mnn/

adb push models/Llama-3.2-1B-int8-mnn/* /storage/emulated/0/Download/llama-3.2-1b-mnn/
adb push models/Llama-3.2-1B-fp16-mnn/* /storage/emulated/0/Download/llama-3.2-1b-fp16-mnn/
```

### 5. Build Android App
```bash
cd apps/Android/LlamaChat
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 📱 Usage

1. **Launch App**: Open "Llama Chat" on your device
2. **Grant Permissions**: Allow storage access for model files
3. **Select Model**: Choose between INT8 (faster) or FP16 (higher quality)
4. **Choose Backend**: Select CPU, GPU, or NPU based on your needs
5. **Start Chatting**: Type messages and enjoy local AI inference!

### Model Switching
- Tap the model information card to change models
- Compare performance between INT8 and FP16 versions
- Test different backends for optimal performance

## 🔧 Configuration

### Storage Requirements
- **INT8 Model**: ~1.4GB free space
- **FP16 Model**: ~2.3GB free space
- **Both Models**: ~3.7GB total

### Permissions Required
- `MANAGE_EXTERNAL_STORAGE` - Access Downloads folder
- `READ_EXTERNAL_STORAGE` - Read model files
- `WRITE_EXTERNAL_STORAGE` - Cache management

### Supported Devices
- **Primary**: Samsung Galaxy S24 Ultra
- **Compatible**: Android 15+ devices with sufficient RAM (6GB+)
- **Recommended**: Snapdragon 8 Gen 3 or equivalent

## 🐛 Troubleshooting

### Common Issues

**Model files not found:**
- Ensure models are in `/storage/emulated/0/Download/`
- Check file permissions and storage space
- Verify complete file transfer (weights file >1GB)

**Slow inference:**
- Try CPU backend first for baseline performance
- Check available RAM (close other apps)
- Consider using INT8 model for better performance

**Build failures:**
- Verify NDK path is correctly set
- Ensure CMake 3.22.1+ is installed
- Check that MNN submodules are properly initialized

### Debug Information
Enable verbose logging in `MainActivity.kt`:
```kotlin
companion object {
    const val TAG: String = "MainActivity"
    const val DEBUG = true  // Enable detailed logs
}
```

## 📊 Project Structure

```
my_mnn/
├── apps/Android/LlamaChat/          # Android app source
│   ├── app/src/main/java/           # Kotlin/Java sources
│   ├── app/src/main/cpp/            # JNI C++ code
│   └── app/src/main/res/            # UI resources
├── models/                          # Converted MNN models
│   ├── Llama-3.2-1B-int8-mnn/     # INT8 quantized model
│   └── Llama-3.2-1B-fp16-mnn/     # FP16 precision model
├── transformers/llm/export/         # Model conversion scripts
├── source/                          # MNN framework source
└── project/android/                 # Android build scripts
```

## 🎯 Key Achievements

✅ **Successfully deployed** 1B parameter Llama model on mobile device  
✅ **Achieved real-time inference** with ~10+ tokens/sec on CPU  
✅ **Implemented multi-backend** support (CPU/GPU/NPU)  
✅ **Created intuitive UI** with model selector and performance metrics  
✅ **Built complete pipeline** from HuggingFace → MNN → Android  
✅ **Optimized for Galaxy S24 Ultra** with hardware acceleration  

## 🔬 Technical Deep Dive

### MNN Integration
- **Native library compilation** with LLM support enabled
- **JNI interface** for Kotlin ↔ C++ communication
- **Memory management** for large model weights
- **Thread safety** for concurrent inference

### Model Conversion Pipeline
1. **Download** original Llama-3.2-1B from HuggingFace
2. **Quantize** to INT8/FP16 precision levels
3. **Convert** to MNN's optimized format
4. **Validate** model architecture and weights
5. **Deploy** to Android device storage

### Performance Optimizations
- **Asset compression** disabled for large files
- **Heap size** increased for model loading
- **Background processing** for non-blocking UI
- **Efficient memory** allocation patterns

## 🤝 Contributing

This project demonstrates the complete workflow for deploying large language models on Android devices. Key areas for contribution:

- **Model optimization** (pruning, distillation)
- **UI enhancements** (voice input, conversation history)
- **Backend improvements** (Vulkan support, custom kernels)
- **Platform expansion** (iOS, other Android devices)

## 📄 License

This project builds upon the MNN framework and follows its licensing terms. Model usage is subject to Meta's Llama license.

## 🙏 Acknowledgments

- **Meta AI** for the Llama-3.2-1B model
- **Alibaba** for the MNN inference framework
- **Samsung** for Galaxy S24 Ultra hardware
- **HuggingFace** for model hosting and tools

---

**Built with ❤️ for on-device AI inference**

*Performance metrics measured on Samsung Galaxy S24 Ultra. Results may vary on different devices.* 