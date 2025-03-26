package com.example.backend.service.implement;

import com.example.backend.model.RoleFunction;
import com.example.backend.model.RoleFunctionId;
import com.example.backend.repository.RoleFunctionRepository;
import com.example.backend.service.RoleFunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Lớp triển khai RoleFunctionService, cung cấp logic nghiệp vụ cho bảng
 * ROLE_FUNCTION.
 */
@Service
@RequiredArgsConstructor
public class RoleFunctionServiceImplement implements RoleFunctionService {

    private final RoleFunctionRepository roleFunctionRepository;

    /**
     * Tạo một bản ghi RoleFunction mới trong bảng ROLE_FUNCTION.
     * 
     * @param roleFunction Đối tượng RoleFunction cần tạo.
     * @return RoleFunction đã được lưu vào database.
     */
    @Override
    public RoleFunction createRoleFunction(RoleFunction roleFunction) {
        return roleFunctionRepository.save(roleFunction);
    }

    /**
     * Lấy tất cả các bản ghi từ bảng ROLE_FUNCTION.
     * 
     * @return Danh sách tất cả RoleFunction.
     */
    @Override
    public List<RoleFunction> getAllRoleFunctions() {
        return roleFunctionRepository.findAll();
    }

    /**
     * Cập nhật một RoleFunction dựa trên roleId và functionId.
     * 
     * @param roleId       ID của vai trò (ROLE_ID).
     * @param functionId   ID của chức năng (FUNCTION_ID).
     * @param roleFunction Đối tượng RoleFunction chứa thông tin cần cập nhật.
     * @return RoleFunction đã được cập nhật.
     */
    @Override
    public RoleFunction updateRoleFunction(int roleId, int functionId, RoleFunction roleFunction) {
        RoleFunctionId id = new RoleFunctionId(roleId, functionId);
        RoleFunction existingRoleFunction = roleFunctionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy role-function với ROLE_ID: " + roleId + " và FUNCTION_ID: " + functionId));
        existingRoleFunction.setRole(roleFunction.getRole());
        existingRoleFunction.setSystemFunction(roleFunction.getSystemFunction());
        return roleFunctionRepository.save(existingRoleFunction);
    }
}