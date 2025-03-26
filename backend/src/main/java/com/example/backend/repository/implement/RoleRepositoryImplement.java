package com.example.backend.repository.implement;

import com.example.backend.model.Role;
import com.example.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Lớp triển khai RoleRepository, sử dụng RoleRepository interface để thao tác
 * với database.
 * Không cần extends SimpleJpaRepository vì JpaRepository đã cung cấp sẵn các
 * phương thức cần thiết.
 */
@Repository
@Transactional
public class RoleRepositoryImplement {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Lấy tất cả các bản ghi từ bảng ROLE.
     * 
     * @return Danh sách tất cả các Role.
     */
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * Lưu hoặc cập nhật một Role vào bảng ROLE.
     * 
     * @param entity Đối tượng Role cần lưu.
     * @return Role đã được lưu hoặc cập nhật.
     */
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    /**
     * Tìm Role theo tên vai trò (ROLE_NAME).
     * 
     * @param roleName Tên vai trò cần tìm.
     * @return Optional<Role> chứa kết quả nếu tìm thấy, hoặc empty nếu không.
     */
    public Optional<Role> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}