package com.example.backend.repository;

import com.example.backend.model.SystemFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface cho SystemFunctionRepository, sử dụng JpaRepository để thao tác với
 * bảng SYSTEM_FUNCTION.
 */
public interface SystemFunctionRepository extends JpaRepository<SystemFunction, Integer> {
    /**
     * Tìm SystemFunction theo tên chức năng (FUNCTION_NAME).
     * 
     * @param functionName Tên chức năng cần tìm.
     * @return Optional<SystemFunction> chứa kết quả nếu tìm thấy, hoặc empty nếu
     *         không.
     */
    Optional<SystemFunction> findByFunctionName(String functionName);
}