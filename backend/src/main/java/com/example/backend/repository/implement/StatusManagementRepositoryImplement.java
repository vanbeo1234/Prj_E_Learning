package com.example.backend.repository.implement;

import com.example.backend.model.StatusManagement;
import com.example.backend.repository.StatusManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Lớp triển khai StatusManagementRepository, sử dụng StatusManagementRepository
 * interface để thao tác với database.
 */
@Repository
@Transactional
public class StatusManagementRepositoryImplement {

    @Autowired
    private StatusManagementRepository statusManagementRepository;

    /**
     * Lấy tất cả các bản ghi từ bảng STATUS_MANAGEMENT.
     * 
     * @return Danh sách tất cả các StatusManagement.
     */
    public List<StatusManagement> findAll() {
        return statusManagementRepository.findAll();
    }

    /**
     * Lưu hoặc cập nhật một StatusManagement vào bảng STATUS_MANAGEMENT.
     * 
     * @param entity Đối tượng StatusManagement cần lưu.
     * @return StatusManagement đã được lưu hoặc cập nhật.
     */
    public StatusManagement save(StatusManagement entity) {
        return statusManagementRepository.save(entity);
    }

    /**
     * Tìm StatusManagement theo mã trạng thái (STATUS_CODE) và loại (TYPE).
     * 
     * @param statusCode Mã trạng thái cần tìm.
     * @param type       Loại trạng thái cần tìm.
     * @return Optional<StatusManagement> chứa kết quả nếu tìm thấy, hoặc empty nếu
     *         không.
     */
    public Optional<StatusManagement> findByStatusCodeAndType(String statusCode, String type) {
        return statusManagementRepository.findByStatusCodeAndType(statusCode, type);
    }
}