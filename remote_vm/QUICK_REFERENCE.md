# 🚀 VM Manager Quick Reference

## ⚡ Essential Commands
```bash
./vm_manager.sh start          # Start A100 VM (2h auto-stop)
./vm_manager.sh connect        # SSH into running VM  
./vm_manager.sh status         # Check VM status
./vm_manager.sh stop           # Stop VM (save money!)
```

## 🔧 Common Options
```bash
./vm_manager.sh start --no-gpu      # Use cheap VM
./vm_manager.sh start --no-auto-stop # No time limit
./vm_manager.sh stop --yes          # Skip confirmation
```

## 💰 Cost Savers
- ✅ Auto-stop after 2 hours (default)
- ✅ Always `stop` when done
- ✅ Use `--no-gpu` for lightweight tasks

## 🚨 Emergency
```bash
./vm_manager.sh stop --yes     # Force stop immediately
```

## 📋 Workflow
1. `./vm_manager.sh start` 
2. `./vm_manager.sh connect`
3. Do your model conversion work
4. `./vm_manager.sh stop`

**Full docs**: `remote_vm/VM_USAGE.md` 