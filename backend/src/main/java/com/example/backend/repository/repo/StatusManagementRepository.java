package com.example.backend.repository;

import com.example.backend.model.StatusManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface cho StatusManagementRepository, sử dụng JpaRepository để thao tác
 * với bảng STATUS_MANAGEMENT.
 */
public interface StatusManagementRepository extends JpaRepository<StatusManagement, Integer> {
    /**
     * Tìm StatusManagement theo mã trạng thái (STATUS_CODE) và loại (TYPE).
     * 
     * @param statusCode Mã trạng thái cần tìm.
     * @param type       Loại trạng thái cần tìm.
     * @return Optional<StatusManagement> chứa kết quả nếu tìm thấy, hoặc empty nếu
     *         không.
     */
    Optional<StatusManagement> findByStatusCodeAndType(String statusCode, String type);
}