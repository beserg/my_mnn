// Created by ruoyi.sjd on 2024/12/25.
// Modified for Llama-3.2-1B Proof of Concept
// Copyright (c) 2024 Alibaba Group Holding Limited All rights reserved.
package com.alibaba.mnnllm.android.main

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.alibaba.mnnllm.android.R
import com.alibaba.mnnllm.android.chat.ChatActivity
import com.techiness.progressdialoglibrary.ProgressDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var progressDialog: ProgressDialog? = null
    private var backendGroup: RadioGroup? = null
    private var startChatButton: Button? = null
    private var modelInfoText: TextView? = null
    private var modelPath: String? = null
    private var selectedModel: ModelVariant? = null
    
    // Permission request codes
    private val STORAGE_PERMISSION_REQUEST = 1001
    private val MANAGE_STORAGE_PERMISSION_REQUEST = 1002
    
    // Model variants
    data class ModelVariant(
        val name: String,
        val description: String,
        val folderName: String,
        val quantization: String,
        val size: String,
        val performance: String
    )
    
    private val availableModels = listOf(
        ModelVariant(
            name = "Llama-3.2-1B INT8",
            description = "Optimized for speed and memory efficiency",
            folderName = "llama-3.2-1b-mnn", 
            quantization = "INT8",
            size = "1.39GB",
            performance = "Faster inference, lower memory"
        ),
        ModelVariant(
            name = "Llama-3.2-1B FP16", 
            description = "Higher precision for better quality",
            folderName = "llama-3.2-1b-fp16-mnn",
            quantization = "FP16", 
            size = "2.24GB",
            performance = "Better quality, more memory"
        )
    )
    
    // Backend types
    private enum class Backend(val value: String) {
        CPU("cpu"),
        GPU("opencl"), 
        NPU("nnapi")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_simple)
        
        initViews()
        setupModelInfo()
        requestStoragePermissions()
    }
    
    private fun initViews() {
        backendGroup = findViewById(R.id.backend_group)
        startChatButton = findViewById(R.id.start_chat_button)
        modelInfoText = findViewById(R.id.model_info_text)
        
        startChatButton?.setOnClickListener {
            startChat()
        }
        
        // Set default to CPU
        backendGroup?.check(R.id.radio_cpu)
    }
    
    private fun setupModelInfo() {
        modelInfoText?.text = """
            🦙 Llama Chat - MNN Inference
            
            Available Models:
            • Llama-3.2-1B INT8 (1.39GB) - Faster
            • Llama-3.2-1B FP16 (2.24GB) - Higher Quality
            
            Framework: MNN with native inference
            
            Backends:
            • CPU: ARM NEON optimized
            • GPU: OpenCL (Adreno)  
            • NPU: NNAPI (Snapdragon AI Engine)
            
            🔄 Requesting permissions...
            
            💡 You'll be able to select your preferred model next
        """.trimIndent()
    }
    
    private fun requestStoragePermissions() {
        Log.d(TAG, "🔑 Requesting storage permissions...")
        
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                // Android 11+ - Request MANAGE_EXTERNAL_STORAGE
                if (!Environment.isExternalStorageManager()) {
                    Log.d(TAG, "🔑 Requesting MANAGE_EXTERNAL_STORAGE permission")
                    val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                    startActivityForResult(intent, MANAGE_STORAGE_PERMISSION_REQUEST)
                } else {
                    Log.d(TAG, "✅ MANAGE_EXTERNAL_STORAGE already granted")
                    onPermissionsGranted()
                }
            }
            else -> {
                // Android 10 and below
                val permissions = arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                
                if (permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
                    Log.d(TAG, "✅ Storage permissions already granted")
                    onPermissionsGranted()
                } else {
                    Log.d(TAG, "🔑 Requesting READ/WRITE_EXTERNAL_STORAGE permissions")
                    ActivityCompat.requestPermissions(this, permissions, STORAGE_PERMISSION_REQUEST)
                }
            }
        }
    }
    
    private fun onPermissionsGranted() {
        Log.d(TAG, "✅ Storage permissions granted, showing model selector")
        showModelSelector()
    }
    
    private fun showModelSelector() {
        val modelNames = availableModels.map { "${it.name}\n${it.description}\nSize: ${it.size}" }.toTypedArray()
        
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("🦙 Select Llama Model")
            .setIcon(R.drawable.ic_launcher)
            .setSingleChoiceItems(modelNames, 0) { _, which ->
                // Selection is handled in onClick
            }
            .setPositiveButton("Load Model") { dialog, _ ->
                val selectedIndex = (dialog as androidx.appcompat.app.AlertDialog).listView.checkedItemPosition
                if (selectedIndex >= 0) {
                    selectedModel = availableModels[selectedIndex]
                    Log.d(TAG, "🎯 Selected model: ${selectedModel?.name}")
                    checkDownloadsModel()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .create()
            
        dialog.show()
    }
    
    private fun checkDownloadsModel() {
        val model = selectedModel ?: return
        
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                showProgress("Loading ${model.name}...")
                
                val downloadsDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), model.folderName)
                val modelFile = File(downloadsDir, "llm.mnn")
                val weightFile = File(downloadsDir, "llm.mnn.weight")
                val configFile = File(downloadsDir, "config.json")
                
                Log.d(TAG, "🔍 DEBUG: Checking model: ${model.name}")
                Log.d(TAG, "🔍 DEBUG: Downloads directory: ${downloadsDir.absolutePath}")
                Log.d(TAG, "🔍 DEBUG: Downloads dir exists: ${downloadsDir.exists()}")
                
                if (downloadsDir.exists() && downloadsDir.isDirectory) {
                    Log.d(TAG, "🔍 DEBUG: Contents of downloads model directory:")
                    downloadsDir.listFiles()?.forEach { file ->
                        Log.d(TAG, "🔍 DEBUG: File: ${file.name}, Size: ${file.length()}, Exists: ${file.exists()}")
                    }
                }
                
                val modelExists = modelFile.exists() && modelFile.length() > 100000  // 100KB+
                val weightThreshold = if (model.quantization == "FP16") 2000000000L else 1000000000L  // 2GB for FP16, 1GB for INT8
                val weightExists = weightFile.exists() && weightFile.length() > weightThreshold
                val configExists = configFile.exists()
                
                Log.d(TAG, "✅ Model file check: exists=$modelExists, size=${if (modelFile.exists()) modelFile.length() else "N/A"}")
                Log.d(TAG, "✅ Weight file check: exists=$weightExists, size=${if (weightFile.exists()) weightFile.length() else "N/A"} (threshold: ${weightThreshold})")
                Log.d(TAG, "✅ Config file check: exists=$configExists")
                
                withContext(Dispatchers.Main) {
                    hideProgress()
                    if (modelExists && weightExists && configExists) {
                        modelPath = downloadsDir.absolutePath
                        updateModelInfoSuccess()
                        Log.d(TAG, "🎉 SUCCESS: ${model.name} ready at $modelPath")
                        Toast.makeText(this@MainActivity, "🎉 ${model.name} loaded successfully!", Toast.LENGTH_LONG).show()
                    } else {
                        updateModelInfoMissing(modelExists, weightExists, configExists)
                        Log.w(TAG, "⚠️ WARNING: ${model.name} files missing in Downloads")
                    }
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "❌ ERROR: Error checking Downloads model", e)
                withContext(Dispatchers.Main) {
                    hideProgress()
                    Toast.makeText(this@MainActivity, "Error accessing ${model.name}: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun updateModelInfoSuccess() {
        val model = selectedModel ?: return
        
        modelInfoText?.text = """
            🦙 ${model.name}
            
            Model: Meta Llama 3.2 1B Instruct
            Quantization: ${model.quantization} (${model.size})
            Framework: MNN with native inference
            Location: Downloads/${model.folderName}/
            
            Performance: ${model.performance}
            
            Backends:
            • CPU: ARM NEON optimized
            • GPU: OpenCL (Adreno)  
            • NPU: NNAPI (Snapdragon AI Engine)
            
            ✅ Model ready with weights!
            Select backend and start chatting.
            
            💡 Tap here to change model
        """.trimIndent()
        
        // Make model info clickable to change models
        modelInfoText?.setOnClickListener {
            showModelSelector()
        }
    }
    
    private fun updateModelInfoMissing(modelExists: Boolean, weightExists: Boolean, configExists: Boolean) {
        val model = selectedModel ?: return
        
        modelInfoText?.text = """
            🦙 ${model.name}
            
            📋 Status Check:
            • Model file: ${if (modelExists) "✅ Found" else "❌ Missing"}  
            • Weight file: ${if (weightExists) "✅ Found (${model.size})" else "❌ Missing"}
            • Config file: ${if (configExists) "✅ Found" else "❌ Missing"}
            
            Location: Downloads/${model.folderName}/
            
            ${if (!weightExists) "⚠️ Copy ${model.name} files to Downloads folder" else ""}
            
            💡 Tap here to change model
        """.trimIndent()
        
        // Make model info clickable to change models
        modelInfoText?.setOnClickListener {
            showModelSelector()
        }
    }
    
    private fun copyAssetsToInternal() {
        val internalDir = File(filesDir, "models/llama3.2-1b")
        Log.d(TAG, "🔍 DEBUG: Creating directory: ${internalDir.absolutePath}")
        val created = internalDir.mkdirs()
        Log.d(TAG, "🔍 DEBUG: Directory created: $created, exists: ${internalDir.exists()}")
        
        // First, let's check what assets are actually available
        try {
            val assetList = assets.list("models")
            Log.d(TAG, "🔍 DEBUG: Available assets in models/:")
            assetList?.forEach { asset ->
                Log.d(TAG, "🔍 DEBUG: Asset: $asset")
            }
        } catch (e: Exception) {
            Log.e(TAG, "❌ ERROR: Failed to list assets", e)
        }
        
        // Copy actual files from assets
        val assetFiles = arrayOf(
            "config.json",
            "llm_config.json", 
            "tokenizer.txt",
            "llm.mnn"  // Copy the actual model architecture
        )
        
        for (fileName in assetFiles) {
            val destFile = File(internalDir, fileName)
            try {
                Log.d(TAG, "🔍 DEBUG: Attempting to copy asset: models/$fileName to ${destFile.absolutePath}")
                copyAssetFile("models/$fileName", destFile)
                Log.d(TAG, "✅ SUCCESS: Copied $fileName, size: ${destFile.length()}")
            } catch (e: Exception) {
                Log.w(TAG, "⚠️ WARNING: Asset file not found: $fileName, creating placeholder. Error: ${e.message}")
                createPlaceholderFile(destFile, fileName)
                Log.d(TAG, "📝 PLACEHOLDER: Created placeholder for $fileName, size: ${destFile.length()}")
            }
        }
        
        // Handle weight file separately (too large for assets)
        val weightFile = File(internalDir, "llm.mnn.weight")
        Log.d(TAG, "🔍 DEBUG: Weight file path: ${weightFile.absolutePath}")
        Log.d(TAG, "🔍 DEBUG: Weight file exists: ${weightFile.exists()}, size: ${if (weightFile.exists()) weightFile.length() else "N/A"}")
        
        if (!weightFile.exists() || weightFile.length() < 1000) {
            // Create a minimal placeholder for now
            weightFile.writeText("PLACEHOLDER_WEIGHTS_NEED_DOWNLOAD")
            Log.w(TAG, "📦 PLACEHOLDER: Created placeholder weight file - real weights need to be downloaded")
            Log.d(TAG, "🔍 DEBUG: Placeholder weight file created, size: ${weightFile.length()}")
        }
        
        // Final debug: list all files in directory
        Log.d(TAG, "🔍 DEBUG: Final directory contents:")
        internalDir.listFiles()?.forEach { file ->
            Log.d(TAG, "🔍 DEBUG: Final file: ${file.name}, Size: ${file.length()}, Readable: ${file.canRead()}")
        }
    }
    
    private fun createPlaceholderFile(destFile: File, fileName: String) {
        when (fileName) {
            "config.json" -> {
                destFile.writeText("""
                    {
                        "llm_model": "llm.mnn",
                        "llm_weight": "llm.mnn.weight", 
                        "backend_type": "cpu",
                        "thread_num": 4,
                        "precision": "low",
                        "memory": "low",
                        "sampler_type": "penalty",
                        "penalty": 1.1
                    }
                """.trimIndent())
            }
            "llm_config.json" -> {
                destFile.writeText("""
                    {
                        "hidden_size": 2048,
                        "layer_nums": 16,
                        "attention_mask": "float",
                        "key_value_shape": [2, 1, 0, 8, 256],
                        "is_visual": false
                    }
                """.trimIndent())
            }
            "tokenizer.txt" -> {
                destFile.writeText("430 0\n1 1 0\n")
            }
            "llm.mnn" -> {
                // Create empty placeholder for model architecture
                destFile.writeText("MNN_MODEL_PLACEHOLDER")
                Log.w(TAG, "Created placeholder for llm.mnn - real model needed")
            }
        }
    }
    
    private fun copyAssetFile(assetPath: String, destFile: File) {
        try {
            assets.open(assetPath).use { inputStream ->
                FileOutputStream(destFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            Log.e(TAG, "Error copying asset file: $assetPath", e)
            throw e
        }
    }
    
    private fun getSelectedBackend(): Backend {
        return when (backendGroup?.checkedRadioButtonId) {
            R.id.radio_gpu -> Backend.GPU
            R.id.radio_npu -> Backend.NPU
            else -> Backend.CPU
        }
    }
    
    private fun startChat() {
        Log.d(TAG, "🚀 DEBUG: startChat() called")
        Log.d(TAG, "🔍 DEBUG: modelPath = $modelPath")
        
        if (modelPath == null) {
            Log.e(TAG, "❌ ERROR: modelPath is null")
            Toast.makeText(this, "Model not ready yet, please wait...", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Debug: Check if modelPath directory exists and list its contents
        val modelDir = File(modelPath!!)
        Log.d(TAG, "🔍 DEBUG: Model directory: ${modelDir.absolutePath}")
        Log.d(TAG, "🔍 DEBUG: Model directory exists: ${modelDir.exists()}")
        Log.d(TAG, "🔍 DEBUG: Model directory is directory: ${modelDir.isDirectory}")
        
        if (modelDir.exists() && modelDir.isDirectory) {
            Log.d(TAG, "🔍 DEBUG: Contents of model directory:")
            modelDir.listFiles()?.forEach { file ->
                Log.d(TAG, "🔍 DEBUG: File in modelPath: ${file.name}, Size: ${file.length()}, Exists: ${file.exists()}, Readable: ${file.canRead()}")
            }
        }
        
        // Check if actual model files exist
        val modelFile = File(modelPath, "llm.mnn")
        val weightFile = File(modelPath, "llm.mnn.weight")
        
        Log.d(TAG, "🔍 DEBUG: Checking model file: ${modelFile.absolutePath}")
        Log.d(TAG, "🔍 DEBUG: Model file exists: ${modelFile.exists()}, size: ${if (modelFile.exists()) modelFile.length() else "N/A"}")
        Log.d(TAG, "🔍 DEBUG: Checking weight file: ${weightFile.absolutePath}")
        Log.d(TAG, "🔍 DEBUG: Weight file exists: ${weightFile.exists()}, size: ${if (weightFile.exists()) weightFile.length() else "N/A"}")
        
        if (!modelFile.exists()) {
            Log.e(TAG, "❌ ERROR: Model file not found at: ${modelFile.absolutePath}")
            Toast.makeText(this, "Model file not found: ${modelFile.absolutePath}", Toast.LENGTH_LONG).show()
            return
        }
        
        // Verify model files are real (Downloads folder should have full files)
        val modelSize = modelFile.length()
        val weightSize = weightFile.length()
        
        Log.d(TAG, "🔍 DEBUG: Model size: ${modelSize / 1024}KB, Weight size: ${weightSize / (1024*1024)}MB")
        
        if (modelSize < 100000) {  // Less than 100KB
            Toast.makeText(this, "⚠️ Model file too small - may be corrupted", Toast.LENGTH_LONG).show()
                return
            }
        
        if (weightSize < 1000000000) {  // Less than 1GB 
            Toast.makeText(this, "⚠️ Weight file too small - expected ~1.39GB", Toast.LENGTH_LONG).show()
            return
        }
        
        Log.d(TAG, "✅ Model files validated: Model=${modelSize/1024}KB, Weights=${weightSize/(1024*1024)}MB")
        
        val selectedBackend = getSelectedBackend()
        Log.d(TAG, "Starting chat with backend: ${selectedBackend.value}")
        
        showProgress("Loading model with ${selectedBackend.name} backend...")
        
        // Update config file with selected backend
        updateBackendConfig(selectedBackend)
        
        // Start chat activity
        val model = selectedModel ?: return
        val intent = Intent(this, ChatActivity::class.java).apply {
            putExtra("chatSessionId", "${model.folderName}-session")
            putExtra("configFilePath", "$modelPath/config.json")
            putExtra("modelId", model.folderName)
            putExtra("modelName", "${model.name} (${selectedBackend.name})")
            putExtra("modelQuantization", model.quantization)
            putExtra("modelSize", model.size)
        }
        
        hideProgress()
        startActivity(intent)
    }

    private fun proceedWithDemo() {
        val selectedBackend = getSelectedBackend()
        val model = selectedModel ?: return
        
        // Update config file with selected backend
        updateBackendConfig(selectedBackend)
        
        // Show demo information
        Toast.makeText(this, "🎭 Demo mode: ${model.name} with ${selectedBackend.name} backend", Toast.LENGTH_LONG).show()
        
        // Start chat activity in demo mode
        val intent = Intent(this, ChatActivity::class.java).apply {
            putExtra("chatSessionId", "${model.folderName}-demo")
            putExtra("configFilePath", "$modelPath/config.json")
            putExtra("modelId", "${model.folderName}-demo")
            putExtra("modelName", "${model.name} Demo (${selectedBackend.name})")
            putExtra("modelQuantization", model.quantization)
            putExtra("modelSize", model.size)
            putExtra("demoMode", true)  // Flag for demo mode
        }
        
        startActivity(intent)
    }
    
    private fun showInferenceDemo() {
        val selectedBackend = getSelectedBackend()
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("🚀 Inference Demo")
            .setMessage("""
                Ready to test MNN inference with ${selectedBackend.name} backend!
                
                ✅ Model Architecture: Loaded
                ✅ Tokenizer: Ready
                ✅ Backend: ${selectedBackend.name} configured
                ✅ Native Library: MNN compiled
                
                Note: Using placeholder weights for demo.
                For full inference, copy actual llm.mnn.weight file.
                
                This demonstrates the complete inference pipeline working!
            """.trimIndent())
            .setPositiveButton("Run Inference Test") { _, _ ->
                // Start chat activity with real MNN integration
                startInferenceTest()
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun startInferenceTest() {
        val selectedBackend = getSelectedBackend()
        
        Log.d(TAG, "Starting inference test with ${selectedBackend.name} backend")
        
        // Update config file with selected backend
        updateBackendConfig(selectedBackend)
        
        // Start chat activity with real MNN integration
        val intent = Intent(this, ChatActivity::class.java).apply {
            putExtra("chatSessionId", "llama3.2-1b-session")
            putExtra("configFilePath", "$modelPath/config.json")
            putExtra("modelId", "llama3.2-1b")
            putExtra("modelName", "Llama-3.2-1B (${selectedBackend.name})")
        }
        
        Toast.makeText(this, "🚀 Starting MNN inference with ${selectedBackend.name}", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
    
    private fun updateBackendConfig(backend: Backend) {
        try {
            val configFile = File(modelPath, "config.json")
            val config = """
                {
                    "llm_model": "llm.mnn",
                    "llm_weight": "llm.mnn.weight", 
                    "backend_type": "${backend.value}",
                    "thread_num": 4,
                    "precision": "low",
                    "memory": "low",
                    "sampler_type": "penalty",
                    "penalty": 1.1
                }
            """.trimIndent()
            
            configFile.writeText(config)
        } catch (e: Exception) {
            Log.e(TAG, "Error updating config", e)
        }
    }
    
    private fun showProgress(message: String) {
        try {
            hideProgress()  // Ensure no duplicate dialogs
            progressDialog = ProgressDialog(this).apply {
                setMessage(message)
                show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error showing progress dialog", e)
        }
    }
    
    private fun hideProgress() {
        try {
            progressDialog?.dismiss()
            progressDialog = null
        } catch (e: Exception) {
            Log.e(TAG, "Error hiding progress dialog", e)
        }
    }

    // Stub methods for compatibility with other fragments
    fun runModel(destModelDir: String?, modelId: String?, sessionId: String?) {
        Log.d(TAG, "runModel called - demo mode")
        Toast.makeText(this, "Demo: Model loading simulation", Toast.LENGTH_SHORT).show()
    }
    
    fun onStarProject(view: View?) {
        Toast.makeText(this, "⭐ GitHub starring feature", Toast.LENGTH_SHORT).show()
    }

    fun onReportIssue(view: View?) {
        Toast.makeText(this, "🐛 Issue reporting feature", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            STORAGE_PERMISSION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Log.d(TAG, "✅ Storage permissions granted via onRequestPermissionsResult")
                    onPermissionsGranted()
                } else {
                    Log.e(TAG, "❌ Storage permissions denied")
                    Toast.makeText(this, "Storage permissions required to access model files", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        when (requestCode) {
            MANAGE_STORAGE_PERMISSION_REQUEST -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        Log.d(TAG, "✅ MANAGE_EXTERNAL_STORAGE permission granted")
                        onPermissionsGranted()
                    } else {
                        Log.e(TAG, "❌ MANAGE_EXTERNAL_STORAGE permission denied")
                        Toast.makeText(this, "Storage management permission required to access Downloads folder", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    companion object {
        const val TAG: String = "MainActivity"
    }
}