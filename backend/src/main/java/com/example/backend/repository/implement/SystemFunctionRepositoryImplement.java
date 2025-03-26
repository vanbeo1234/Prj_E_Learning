package com.example.backend.repository.implement;

import com.example.backend.model.SystemFunction;
import com.example.backend.repository.SystemFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Lớp triển khai SystemFunctionRepository, sử dụng SystemFunctionRepository
 * interface để thao tác với database.
 */
@Repository
@Transactional
public class SystemFunctionRepositoryImplement {

    @Autowired
    private SystemFunctionRepository systemFunctionRepository;

    /**
     * Lấy tất cả các bản ghi từ bảng SYSTEM_FUNCTION.
     * 
     * @return Danh sách tất cả các SystemFunction.
     */
    public List<SystemFunction> findAll() {
        return systemFunctionRepository.findAll();
    }

    /**
     * Lưu hoặc cập nhật một SystemFunction vào bảng SYSTEM_FUNCTION.
     * 
     * @param entity Đối tượng SystemFunction cần lưu.
     * @return SystemFunction đã được lưu hoặc cập nhật.
     */
    public SystemFunction save(SystemFunction entity) {
        return systemFunctionRepository.save(entity);
    }

    /**
     * Tìm SystemFunction theo tên chức năng (FUNCTION_NAME).
     * 
     * @param functionName Tên chức năng cần tìm.
     * @return Optional<SystemFunction> chứa kết quả nếu tìm thấy, hoặc empty nếu
     *         không.
     */
    public Optional<SystemFunction> findByFunctionName(String functionName) {
        return systemFunctionRepository.findByFunctionName(functionName);
    }
}