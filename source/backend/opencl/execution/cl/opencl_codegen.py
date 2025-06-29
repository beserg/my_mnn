import os
import sys
import re
import hashlib
major_py_ver = sys.version_info.major

def generate_md5(input_str: str) -> str:
    encoded = input_str.encode('utf-8')
    md5_hash = hashlib.md5()
    md5_hash.update(encoded)
    return md5_hash.hexdigest()

def convert_string_to_hex_list(code_str):
    hex_list = []
    for i in range(len(code_str)):
        hex_ = hex(ord(code_str[i]))
        hex_list.append(hex_)
    return hex_list

def opencl_codegen():
    cl_kernel_dir = sys.argv[1]
    print("Generating OpenCL Kernels in "+cl_kernel_dir+" to this file")
    if not os.path.exists(cl_kernel_dir):
        print(cl_kernel_dir + " doesn't exist!")

    opencl_source_hpp = "#include <map> \n"
    opencl_source_hpp += "#include <string> \n"
    opencl_source_hpp += "#include <vector> \n"
    opencl_source_hpp += "namespace MNN { \n"

    opencl_source_map_hpp = "const std::map<std::string, const char*> OpenCLProgramMap = \n { \n"
    opencl_md5_map_hpp = "const std::map<std::string, std::string> OpenCLProgramMd5Map = \n { \n"

    spaceReg = re.compile(' +')
    for file_name_all in os.listdir(cl_kernel_dir):
        file_path = os.path.join(cl_kernel_dir, file_name_all)
        if file_path[-3:] == ".cl":
            with open(file_path, "r", encoding = 'utf-8') as f:
                file_name = file_name_all[:-3]
                opencl_source_map = "#include \"opencl_source_map.hpp\" \n"
                opencl_source_map += "namespace MNN { \n"
                if file_name[-4:] == "_buf":
                    opencl_source_map += "#ifndef MNN_OPENCL_BUFFER_CLOSED\n"
                    opencl_source_hpp += "#ifndef MNN_OPENCL_BUFFER_CLOSED\n"
                    opencl_source_map_hpp += "#ifndef MNN_OPENCL_BUFFER_CLOSED\n"
                if file_name[-13:] == "_subgroup_buf":
                    opencl_source_map += "#ifdef MNN_SUPPORT_INTEL_SUBGROUP\n"
                    opencl_source_hpp += "#ifdef MNN_SUPPORT_INTEL_SUBGROUP\n"
                    opencl_source_map_hpp += "#ifdef MNN_SUPPORT_INTEL_SUBGROUP\n"
                opencl_source_hpp += "extern const char* " + file_name + ";\n"
                opencl_source_map += "const char* " + file_name + " = \n"
                opencl_source_map_hpp += "  { \"" + file_name + "\", " + file_name + " },\n"
                md5_orig_str = str()
                lines = f.read().split("\n")
                for l in lines:
                    if (len(l) < 1):
                        continue
                    if l.find('printf') >= 0:
                        l = l.replace('\"', '\\\"')
                        l = l.replace('\\n', '\\\\n')
                        opencl_source_map += "\""+l+"\"\n"
                        md5_orig_str += l
                    elif l.find('\\') >= 0:
                        l = l.replace('\\', '')
                        l = spaceReg.sub(' ', l)
                        opencl_source_map += "\""+l+"\""
                        md5_orig_str += l
                    else:
                        l = l.replace('\t', '')
                        l = spaceReg.sub(' ', l)
                        l = l.replace(', ', ',')
                        l = l.replace(' = ', '=')
                        l = l.replace(' + ', '+')
                        l = l.replace(' - ', '-')
                        l = l.replace(' * ', '*')
                        l = l.replace(' / ', '/')
                        l = l.replace(' < ', '<')
                        l = l.replace(' > ', '>')
                        md5_orig_str += l + "\n"
                        l = l + "\\n"
                        opencl_source_map += "\""+l+"\"\n"
                opencl_source_map += ";\n"
                if file_name[-4:] == "_buf":
                    opencl_source_map += "#endif\n"
                    opencl_source_hpp += "#endif\n"
                    opencl_source_map_hpp += "#endif\n"
                if file_name[-13:] == "_subgroup_buf":
                    opencl_source_map += "#endif\n"
                    opencl_source_hpp += "#endif\n"
                    opencl_source_map_hpp += "#endif\n"
                opencl_source_map += "}\n"
                opencl_md5_map_hpp += "  { \"" + file_name + "\", \"" + generate_md5(md5_orig_str) + "\" },\n"
            #write into cpp
            output_path = file_path[:-3] + "_mnn_cl.cpp"
            with open(output_path, "w") as cpp_file:
                cpp_file.write(opencl_source_map)

    #write into hpp
    opencl_source_map_hpp += "};\n"
    opencl_source_map_hpp += "}\n"
    opencl_md5_map_hpp += "};\n"
    with open("opencl_source_map.hpp", "w") as w_file:
        w_file.write(opencl_source_hpp)
        w_file.write(opencl_source_map_hpp)
        w_file.write(opencl_md5_map_hpp)

    print("Generate OpenCL Source done !!! \n")

if __name__ == '__main__':
    opencl_codegen()
