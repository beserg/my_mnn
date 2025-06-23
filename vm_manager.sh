#!/bin/bash

# VM Manager for NPU LLM Project
# Manages Yandex Cloud VMs for model conversion tasks
# Author: Auto-generated for npullm project

set -e

# Configuration
A100_VM_ID="fhmtpo6uiaj60o99o1p3"
CHEAP_VM_ID="fhmr3lkgrr73m2427ovh"
SSH_KEY="/home/serg/.ssh/id_ed25519"
SSH_USER="bolser"
LOG_DIR="remote_vm/logs"
AUTO_STOP_HOURS=2

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Ensure log directory exists
mkdir -p "$LOG_DIR"

# Get current timestamp
get_timestamp() {
    date '+%Y-%m-%d_%H-%M-%S'
}

# Logging function
log_operation() {
    local operation="$1"
    local vm_id="$2"
    local timestamp=$(get_timestamp)
    local log_file="$LOG_DIR/${operation}_${timestamp}.log"
    
    echo "Logging to: $log_file"
    return 0
}

# Get VM ID based on options
get_vm_id() {
    if [[ "$USE_CHEAP_VM" == "true" ]]; then
        echo "$CHEAP_VM_ID"
    else
        echo "$A100_VM_ID"
    fi
}

# Get VM name for display
get_vm_name() {
    local vm_id="$1"
    if [[ "$vm_id" == "$A100_VM_ID" ]]; then
        echo "A100 GPU VM"
    else
        echo "Cheap VM (no GPU)"
    fi
}

# Check VM status
check_vm_status() {
    local vm_id="$1"
    local status=$(yc compute instance get --id "$vm_id" --format json | jq -r '.status' 2>/dev/null)
    echo "$status"
}

# Get VM external IP
get_vm_ip() {
    local vm_id="$1"
    local ip=$(yc compute instance get --id "$vm_id" --format json | jq -r '.network_interfaces[0].primary_v4_address.one_to_one_nat.address' 2>/dev/null)
    if [[ "$ip" == "null" ]]; then
        echo ""
    else
        echo "$ip"
    fi
}

# Start VM
start_vm() {
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    local timestamp=$(get_timestamp)
    local log_file="$LOG_DIR/start_${timestamp}.log"
    
    echo -e "${BLUE}Starting $vm_name...${NC}"
    echo "VM ID: $vm_id"
    echo "Log file: $log_file"
    
    # Check current status
    local current_status=$(check_vm_status "$vm_id")
    if [[ "$current_status" == "RUNNING" ]]; then
        echo -e "${YELLOW}VM is already running!${NC}"
        return 0
    fi
    
    # Confirmation prompt
    if [[ "$SKIP_CONFIRMATION" != "true" ]]; then
        echo -e "${YELLOW}⚠️  This will start the VM and incur costs!${NC}"
        read -p "Continue? (y/N): " -n 1 -r
        echo
        if [[ ! $REPLY =~ ^[Yy]$ ]]; then
            echo "Cancelled."
            return 1
        fi
    fi
    
    # Start VM and log output
    echo "Starting VM..." | tee "$log_file"
    if yc compute instance start "$vm_id" 2>&1 | tee -a "$log_file"; then
        echo -e "${GREEN}✅ VM started successfully!${NC}"
        
        # Wait for VM to be fully running
        echo "Waiting for VM to be fully operational..."
        local max_wait=60
        local wait_count=0
        while [[ $wait_count -lt $max_wait ]]; do
            local status=$(check_vm_status "$vm_id")
            if [[ "$status" == "RUNNING" ]]; then
                break
            fi
            sleep 2
            ((wait_count+=2))
            echo -n "."
        done
        echo
        
        # Get and display IP
        local ip=$(get_vm_ip "$vm_id")
        if [[ -n "$ip" ]]; then
            echo -e "${GREEN}VM IP: $ip${NC}"
        fi
        
        # Set up auto-stop if requested
        if [[ "$AUTO_STOP" == "true" ]]; then
            setup_auto_stop "$vm_id"
        fi
        
        echo -e "${GREEN}VM is ready for use!${NC}"
    else
        echo -e "${RED}❌ Failed to start VM!${NC}"
        return 1
    fi
}

# Stop VM
stop_vm() {
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    local timestamp=$(get_timestamp)
    local log_file="$LOG_DIR/stop_${timestamp}.log"
    
    echo -e "${BLUE}Stopping $vm_name...${NC}"
    echo "VM ID: $vm_id"
    echo "Log file: $log_file"
    
    # Check current status
    local current_status=$(check_vm_status "$vm_id")
    if [[ "$current_status" == "STOPPED" ]]; then
        echo -e "${YELLOW}VM is already stopped!${NC}"
        return 0
    fi
    
    # Confirmation prompt
    if [[ "$SKIP_CONFIRMATION" != "true" ]]; then
        echo -e "${YELLOW}⚠️  This will stop the VM. Any unsaved work will be lost!${NC}"
        read -p "Continue? (y/N): " -n 1 -r
        echo
        if [[ ! $REPLY =~ ^[Yy]$ ]]; then
            echo "Cancelled."
            return 1
        fi
    fi
    
    # Stop VM and log output
    echo "Stopping VM..." | tee "$log_file"
    if yc compute instance stop "$vm_id" 2>&1 | tee -a "$log_file"; then
        echo -e "${GREEN}✅ VM stopped successfully!${NC}"
        
        # Clean up auto-stop if it was set
        cleanup_auto_stop "$vm_id"
    else
        echo -e "${RED}❌ Failed to stop VM!${NC}"
        return 1
    fi
}

# Show VM status
show_status() {
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    
    echo -e "${BLUE}VM Status for $vm_name${NC}"
    echo "VM ID: $vm_id"
    echo "----------------------------------------"
    
    # Get detailed status
    local status=$(check_vm_status "$vm_id")
    local ip=$(get_vm_ip "$vm_id")
    
    echo -e "Status: ${GREEN}$status${NC}"
    if [[ -n "$ip" ]]; then
        echo -e "External IP: ${GREEN}$ip${NC}"
    else
        echo "External IP: None (VM stopped or no external IP)"
    fi
    
    # Show auto-stop status if VM is running
    if [[ "$status" == "RUNNING" ]]; then
        check_auto_stop_status "$vm_id"
    fi
    
    echo "----------------------------------------"
    echo "All VMs overview:"
    yc compute instance list
}

# Connect to VM via SSH
connect_vm() {
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    
    echo -e "${BLUE}Connecting to $vm_name...${NC}"
    
    # Check if VM is running
    local status=$(check_vm_status "$vm_id")
    if [[ "$status" != "RUNNING" ]]; then
        echo -e "${RED}❌ VM is not running! Current status: $status${NC}"
        echo "Start the VM first with: $0 start"
        return 1
    fi
    
    # Get IP and connect
    local ip=$(get_vm_ip "$vm_id")
    if [[ -z "$ip" ]]; then
        echo -e "${RED}❌ Could not get VM IP address!${NC}"
        return 1
    fi
    
    echo "Connecting to $ip..."
    yc compute ssh --id "$vm_id" --identity-file "$SSH_KEY" --login "$SSH_USER"
}

# Test connection without hanging (for automated testing)
test_connection() {
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    
    echo -e "${BLUE}Testing connection to $vm_name...${NC}"
    
    # Check if VM is running
    local status=$(check_vm_status "$vm_id")
    if [[ "$status" != "RUNNING" ]]; then
        echo -e "${RED}❌ VM is not running! Current status: $status${NC}"
        return 1
    fi
    
    # Get IP and test connection
    local ip=$(get_vm_ip "$vm_id")
    if [[ -z "$ip" ]]; then
        echo -e "${RED}❌ Could not get VM IP address!${NC}"
        return 1
    fi
    
    echo "Testing connection to $ip..."
    
    # Test SSH connection with a simple command that exits immediately
    if timeout 10 ssh -i "$SSH_KEY" -o StrictHostKeyChecking=no -o ConnectTimeout=5 "$SSH_USER@$ip" "echo 'Connection test successful'" 2>/dev/null; then
        echo -e "${GREEN}✅ Connection test successful!${NC}"
        return 0
    else
        echo -e "${YELLOW}⚠️ Connection test failed (may need key exchange or VM not fully ready)${NC}"
        return 1
    fi
}

# Setup auto-stop functionality
setup_auto_stop() {
    local vm_id="$1"
    local stop_time=$(date -d "+${AUTO_STOP_HOURS} hours" +%s)
    local stop_file="/tmp/vm_autostop_${vm_id}"
    
    echo "$stop_time" > "$stop_file"
    
    # Create background process to stop VM
    (
        while true; do
            if [[ -f "$stop_file" ]]; then
                local target_time=$(cat "$stop_file")
                local current_time=$(date +%s)
                
                if [[ $current_time -ge $target_time ]]; then
                    echo "Auto-stopping VM $vm_id after $AUTO_STOP_HOURS hours..."
                    SKIP_CONFIRMATION=true vm_id="$vm_id" stop_vm
                    rm -f "$stop_file"
                    break
                fi
            else
                break
            fi
            sleep 60
        done
    ) &
    
    echo -e "${YELLOW}⏰ Auto-stop scheduled in $AUTO_STOP_HOURS hours${NC}"
}

# Check auto-stop status
check_auto_stop_status() {
    local vm_id="$1"
    local stop_file="/tmp/vm_autostop_${vm_id}"
    
    if [[ -f "$stop_file" ]]; then
        local target_time=$(cat "$stop_file")
        local current_time=$(date +%s)
        local remaining=$((target_time - current_time))
        
        if [[ $remaining -gt 0 ]]; then
            local hours=$((remaining / 3600))
            local minutes=$(((remaining % 3600) / 60))
            echo -e "Auto-stop: ${YELLOW}${hours}h ${minutes}m remaining${NC}"
        fi
    fi
}

# Cleanup auto-stop
cleanup_auto_stop() {
    local vm_id="$1"
    local stop_file="/tmp/vm_autostop_${vm_id}"
    rm -f "$stop_file"
}

# Show help
show_help() {
    echo "VM Manager for NPU LLM Project"
    echo "Usage: $0 [OPTIONS] COMMAND"
    echo
    echo "Commands:"
    echo "  start           Start the VM"
    echo "  stop            Stop the VM"
    echo "  status          Show VM status"
    echo "  connect         Connect to VM via SSH (interactive)"
    echo "  exec 'cmd'      Execute command on VM (non-interactive)"
    echo "  upload file remote_path   Upload file to VM"
    echo "  download remote_path file Download file from VM"
    echo "  test-connection Test SSH connectivity (non-interactive)"
    echo "  logs            Show recent logs"
    echo "  help            Show this help message"
    echo
    echo "Options:"
    echo "  --no-gpu          Use cheaper VM without GPU (default: A100 GPU VM)"
    echo "  --no-auto-stop    Disable auto-stop after 2 hours"
    echo "  --yes             Skip confirmation prompts"
    echo
    echo "Examples:"
    echo "  $0 start                    # Start A100 GPU VM with auto-stop"
    echo "  $0 start --no-gpu           # Start cheap VM"
    echo "  $0 start --no-auto-stop     # Start without auto-stop"
    echo "  $0 connect                  # SSH into running VM"
    echo "  $0 stop --yes               # Stop VM without confirmation"
    echo
    echo "Cost Optimization:"
    echo "  - VMs auto-stop after 2 hours by default"
    echo "  - Always stop VMs when finished to save costs"
    echo "  - Use --no-gpu for lighter tasks"
}

# Show logs
show_logs() {
    echo -e "${BLUE}Recent VM operation logs:${NC}"
    echo "----------------------------------------"
    
    if [[ -d "$LOG_DIR" ]]; then
        local log_files=$(ls -t "$LOG_DIR"/*.log 2>/dev/null | head -5)
        if [[ -n "$log_files" ]]; then
            for log_file in $log_files; do
                echo -e "${GREEN}$(basename "$log_file")${NC}"
                echo "  Location: $log_file"
                echo "  Size: $(du -h "$log_file" | cut -f1)"
                echo
            done
        else
            echo "No log files found."
        fi
    else
        echo "Log directory not found."
    fi
}

# Execute remote command on VM
execute_remote() {
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    local command="$1"
    
    echo -e "${BLUE}Executing on $vm_name: $command${NC}"
    
    # Check if VM is running
    local status=$(check_vm_status "$vm_id")
    if [[ "$status" != "RUNNING" ]]; then
        echo -e "${RED}❌ VM is not running! Current status: $status${NC}"
        return 1
    fi
    
    # Get IP and execute command
    local ip=$(get_vm_ip "$vm_id")
    if [[ -z "$ip" ]]; then
        echo -e "${RED}❌ Could not get VM IP address!${NC}"
        return 1
    fi
    
    echo "Executing on $ip..."
    ssh -i "$SSH_KEY" -o StrictHostKeyChecking=no -o ConnectTimeout=10 "$SSH_USER@$ip" "$command"
}

# Copy file to VM
copy_to_vm() {
    local local_file="$1"
    local remote_path="$2"
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    
    echo -e "${BLUE}Copying $local_file to $vm_name:$remote_path${NC}"
    
    # Get IP and copy
    local ip=$(get_vm_ip "$vm_id")
    if [[ -z "$ip" ]]; then
        echo -e "${RED}❌ Could not get VM IP address!${NC}"
        return 1
    fi
    
    scp -i "$SSH_KEY" -o StrictHostKeyChecking=no "$local_file" "$SSH_USER@$ip:$remote_path"
}

# Copy file from VM
copy_from_vm() {
    local remote_path="$1"
    local local_file="$2"
    local vm_id=$(get_vm_id)
    local vm_name=$(get_vm_name "$vm_id")
    
    echo -e "${BLUE}Copying $vm_name:$remote_path to $local_file${NC}"
    
    # Get IP and copy
    local ip=$(get_vm_ip "$vm_id")
    if [[ -z "$ip" ]]; then
        echo -e "${RED}❌ Could not get VM IP address!${NC}"
        return 1
    fi
    
    # Get file size for progress tracking
    local remote_size=$(ssh -i "$SSH_KEY" -o StrictHostKeyChecking=no "$SSH_USER@$ip" "stat -c%s '$remote_path' 2>/dev/null || echo 0")
    if [[ "$remote_size" -gt 0 ]]; then
        local size_mb=$((remote_size / 1024 / 1024))
        echo -e "${GREEN}File size: ${size_mb}MB${NC}"
    fi
    
    # Use rsync for better progress display, fallback to scp if rsync not available
    echo -e "${YELLOW}Starting download with progress...${NC}"
    if command -v rsync >/dev/null 2>&1; then
        rsync -avz --progress -e "ssh -i $SSH_KEY -o StrictHostKeyChecking=no" "$SSH_USER@$ip:$remote_path" "$local_file"
    else
        # Fallback to scp with verbose output
        echo -e "${YELLOW}Using SCP (rsync not available)...${NC}"
        scp -v -i "$SSH_KEY" -o StrictHostKeyChecking=no "$SSH_USER@$ip:$remote_path" "$local_file"
    fi
    
    if [[ $? -eq 0 ]]; then
        echo -e "${GREEN}✅ Download completed successfully!${NC}"
        if [[ -f "$local_file" ]]; then
            local local_size=$(stat -c%s "$local_file" 2>/dev/null || echo 0)
            local local_size_mb=$((local_size / 1024 / 1024))
            echo -e "${GREEN}Local file size: ${local_size_mb}MB${NC}"
        fi
    else
        echo -e "${RED}❌ Download failed!${NC}"
        return 1
    fi
}

# Parse command line arguments
USE_CHEAP_VM=false
AUTO_STOP=true
SKIP_CONFIRMATION=true

while [[ $# -gt 0 ]]; do
    case $1 in
        --no-gpu)
            USE_CHEAP_VM=true
            shift
            ;;
        --no-auto-stop)
            AUTO_STOP=false
            shift
            ;;
        --yes)
            SKIP_CONFIRMATION=true
            shift
            ;;
        start|stop|status|connect|test-connection|logs|help)
            COMMAND="$1"
            shift
            ;;
        exec)
            COMMAND="$1"
            shift
            EXEC_COMMAND="$1"
            shift
            ;;
        upload)
            COMMAND="$1"
            shift
            UPLOAD_FILE="$1"
            shift
            UPLOAD_PATH="$1"
            shift
            ;;
        download)
            COMMAND="$1"
            shift
            DOWNLOAD_PATH="$1"
            shift
            DOWNLOAD_FILE="$1"
            shift
            ;;
        *)
            echo "Unknown option: $1"
            show_help
            exit 1
            ;;
    esac
done

# Validate command
if [[ -z "$COMMAND" ]]; then
    echo "Error: No command specified."
    show_help
    exit 1
fi

# Execute command
case "$COMMAND" in
    start)
        start_vm
        ;;
    stop)
        stop_vm
        ;;
    status)
        show_status
        ;;
        connect)
        connect_vm
        ;;
    exec)
        if [[ -z "$EXEC_COMMAND" ]]; then
            echo "Error: No command specified for exec."
            exit 1
        fi
        execute_remote "$EXEC_COMMAND"
        ;;
    upload)
        if [[ -z "$UPLOAD_FILE" || -z "$UPLOAD_PATH" ]]; then
            echo "Error: Upload requires both local file and remote path."
            exit 1
        fi
        copy_to_vm "$UPLOAD_FILE" "$UPLOAD_PATH"
        ;;
    download)
        if [[ -z "$DOWNLOAD_PATH" || -z "$DOWNLOAD_FILE" ]]; then
            echo "Error: Download requires both remote path and local file."
            exit 1
        fi
        copy_from_vm "$DOWNLOAD_PATH" "$DOWNLOAD_FILE"
        ;;
        test-connection)
        test_connection
        ;;
    logs)
        show_logs
        ;;
    help)
        show_help
        ;;
    *)
        echo "Unknown command: $COMMAND"
        show_help
        exit 1
        ;;
esac 