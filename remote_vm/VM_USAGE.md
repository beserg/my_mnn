# 🖥️ Remote VM Manager - Usage Guide

## 📋 Overview

The VM Manager script (`vm_manager.sh`) provides easy management of Yandex Cloud VMs for model conversion tasks. It handles **A100 GPU VM** by default with automatic cost optimization features.

**Key Features:**
- ✅ **Auto-stop after 2 hours** (cost protection)
- ✅ **Confirmation prompts** (prevent accidental costs)
- ✅ **Comprehensive logging** (all operations tracked)
- ✅ **Cheap VM option** (--no-gpu flag)
- ✅ **SSH integration** (direct connect)

---

## 🚀 Quick Start

### **Basic Usage**
```bash
# Start A100 VM (with auto-stop)
./vm_manager.sh start

# Connect to running VM
./vm_manager.sh connect

# Check VM status
./vm_manager.sh status

# Stop VM when done
./vm_manager.sh stop
```

### **VM Selection**
```bash
# Use A100 GPU VM (default)
./vm_manager.sh start

# Use cheaper VM without GPU
./vm_manager.sh start --no-gpu
```

---

## 📋 All Commands

### **Core Commands**
| Command | Description | Example |
|---------|-------------|---------|
| `start` | Start the VM | `./vm_manager.sh start` |
| `stop` | Stop the VM | `./vm_manager.sh stop` |
| `status` | Show VM status | `./vm_manager.sh status` |
| `connect` | SSH into VM | `./vm_manager.sh connect` |
| `logs` | Show recent logs | `./vm_manager.sh logs` |
| `help` | Show help message | `./vm_manager.sh help` |

### **Options**
| Option | Description | Example |
|--------|-------------|---------|
| `--no-gpu` | Use cheaper VM without GPU | `./vm_manager.sh start --no-gpu` |
| `--no-auto-stop` | Disable 2-hour auto-stop | `./vm_manager.sh start --no-auto-stop` |
| `--yes` | Skip confirmation prompts | `./vm_manager.sh stop --yes` |

---

## 🔄 Common Workflows

### **1. Model Conversion Session**
```bash
# Start VM for conversion work
./vm_manager.sh start
# ⚠️  This will start the VM and incur costs!
# Continue? (y/N): y
# ✅ VM started successfully!
# ⏰ Auto-stop scheduled in 2 hours

# Connect and work
./vm_manager.sh connect
# ... do your model conversion work ...

# Check remaining time
./vm_manager.sh status
# Auto-stop: 1h 23m remaining

# Stop when done (saves money)
./vm_manager.sh stop
```

### **2. Quick Status Check**
```bash
./vm_manager.sh status
# VM Status for A100 GPU VM
# VM ID: fhmtpo6uiaj60o99o1p3
# ----------------------------------------
# Status: STOPPED
# External IP: None (VM stopped or no external IP)
```

### **3. Extended Work Session**
```bash
# Start without auto-stop for longer work
./vm_manager.sh start --no-auto-stop

# Work as long as needed
./vm_manager.sh connect

# Manual stop when finished
./vm_manager.sh stop
```

### **4. Lightweight Tasks**
```bash
# Use cheaper VM for non-GPU tasks
./vm_manager.sh start --no-gpu

# Connect and work
./vm_manager.sh connect

# Stop when done
./vm_manager.sh stop
```

---

## 💰 Cost Optimization

### **Automatic Protections**
- **⏰ Auto-stop**: VMs automatically stop after 2 hours
- **⚠️ Confirmation**: Prompts before starting (costs money)
- **💡 Smart defaults**: Uses appropriate VM for task

### **Best Practices**
1. **Always stop VMs** when finished working
2. **Use --no-gpu** for lightweight tasks (copying files, etc.)
3. **Monitor auto-stop timer** with `./vm_manager.sh status`
4. **Check logs** to track usage patterns

### **Emergency Stop**
```bash
# Force stop without confirmation
./vm_manager.sh stop --yes
```

---

## 📊 Logging and Monitoring

### **Log Files**
All operations are logged to `remote_vm/logs/`:
- `start_YYYY-MM-DD_HH-MM-SS.log` - VM startup logs
- `stop_YYYY-MM-DD_HH-MM-SS.log` - VM shutdown logs

### **View Recent Logs**
```bash
./vm_manager.sh logs
# Recent VM operation logs:
# ----------------------------------------
# start_2024-01-15_14-30-22.log
#   Location: remote_vm/logs/start_2024-01-15_14-30-22.log
#   Size: 2.1K
```

### **Manual Log Analysis**
```bash
# View specific log file
cat remote_vm/logs/start_2024-01-15_14-30-22.log

# Monitor all logs
ls -la remote_vm/logs/
```

---

## 🖥️ VM Specifications

### **A100 GPU VM** (Default)
- **ID**: `fhmtpo6uiaj60o99o1p3`
- **Hardware**: A100 GPU, High-performance CPU
- **Use Case**: Model conversion, training, GPU-intensive tasks
- **Cost**: Higher ($$$ per hour)

### **Cheap VM** (--no-gpu)
- **ID**: `fhmr3lkgrr73m2427ovh`  
- **Hardware**: CPU only, no GPU
- **Use Case**: File operations, lightweight scripts
- **Cost**: Lower ($ per hour)

---

## 🔧 Troubleshooting

### **Common Issues**

#### **VM Won't Start**
```bash
# Check current status
./vm_manager.sh status

# Try starting with verbose output
./vm_manager.sh start
# Check log file mentioned in output
```

#### **Cannot Connect via SSH**  
```bash
# Ensure VM is running
./vm_manager.sh status

# Check if VM has external IP
./vm_manager.sh status | grep "External IP"

# Force restart if needed
./vm_manager.sh stop --yes
./vm_manager.sh start
```

#### **Auto-stop Not Working**
```bash
# Check auto-stop status
./vm_manager.sh status

# Manual stop if needed
./vm_manager.sh stop

# Check background processes
ps aux | grep vm_autostop
```

### **Error Messages**

| Error | Solution |
|-------|----------|
| `VM is already running!` | Use `status` to check, or `connect` directly |
| `VM is not running!` | Start VM first with `./vm_manager.sh start` |
| `Could not get VM IP address!` | Wait a moment, then try `status` again |
| `Unknown option: X` | Check `./vm_manager.sh help` for valid options |

### **Reset Everything**
```bash
# Stop all VMs forcefully  
./vm_manager.sh stop --yes
./vm_manager.sh stop --no-gpu --yes

# Clean up auto-stop files
rm -f /tmp/vm_autostop_*

# Check status
./vm_manager.sh status
```

---

## 🔐 Security and Access

### **SSH Configuration**
- **Key**: `/home/serg/.ssh/id_ed25519`
- **User**: `bolser`
- **Method**: Yandex Cloud SSH integration

### **Prerequisites**
- ✅ Yandex Cloud CLI (`yc`) configured
- ✅ SSH keys set up
- ✅ `jq` installed (for JSON parsing)

### **Test Prerequisites**
```bash
# Test yc CLI
yc compute instance list

# Test jq
echo '{"test": "value"}' | jq -r '.test'

# Test SSH key
ls -la /home/serg/.ssh/id_ed25519
```

---

## 📝 Advanced Usage

### **Scripting Integration**
```bash
#!/bin/bash
# Start VM, run task, stop VM

./vm_manager.sh start --yes
if [ $? -eq 0 ]; then
    echo "VM started, running task..."
    ./vm_manager.sh connect << 'EOF'
    # Your remote commands here
    python my_conversion_script.py
    exit
EOF
    ./vm_manager.sh stop --yes
fi
```

### **Multiple VM Management**
```bash
# Start A100 for heavy work
./vm_manager.sh start

# In another terminal, start cheap VM for file prep
./vm_manager.sh start --no-gpu

# Monitor both
./vm_manager.sh status
./vm_manager.sh status --no-gpu
```

### **Custom Auto-stop Times**
Edit `vm_manager.sh` and modify:
```bash
AUTO_STOP_HOURS=2  # Change to desired hours
```

---

## 🚨 Important Reminders

### **💸 COST WARNINGS**
- VMs charge **per minute** when running
- **ALWAYS STOP** VMs when not in use
- A100 VM costs significantly more than cheap VM
- Auto-stop is your safety net, but **manual stop is better**

### **⚡ EFFICIENCY TIPS**
- Prepare your work locally first
- Copy files to VM efficiently
- Run batch operations when possible
- Use cheap VM for non-GPU tasks

### **🔄 WORKFLOW REMINDERS**
1. **Start** → **Connect** → **Work** → **Stop**
2. Check status regularly during long tasks
3. Use logs to debug issues
4. Test with cheap VM first if unsure

---

## 📞 Support

### **If Something Goes Wrong**
1. Check logs: `./vm_manager.sh logs`
2. Check status: `./vm_manager.sh status` 
3. Force stop: `./vm_manager.sh stop --yes`
4. Check Yandex Cloud console manually

### **Script Location**
- Main script: `./vm_manager.sh`
- Logs: `remote_vm/logs/`
- Config: Edit variables in `vm_manager.sh`

---

**Remember: This tool is designed to save you money by preventing forgotten running VMs! 💰** 