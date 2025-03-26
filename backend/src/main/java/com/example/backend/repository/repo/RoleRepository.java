package com.example.backend.repository;

import com.example.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Tìm Role theo tên vai trò (ROLE_NAME).
     * 
     * @param roleName Tên vai trò cần tìm.
     * @return Optional<Role> chứa kết quả nếu tìm thấy, hoặc empty nếu không.
     */
    Optional<Role> findByRoleName(String roleName);
}