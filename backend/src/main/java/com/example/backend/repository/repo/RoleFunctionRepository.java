package com.example.backend.repository;

import com.example.backend.model.RoleFunction;
import com.example.backend.model.RoleFunctionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleFunctionRepository extends JpaRepository<RoleFunction, RoleFunctionId> {
    // Hiện tại không cần thêm phương thức tùy chỉnh, JpaRepository đã cung cấp các
    // phương thức CRUD cơ bản.
}