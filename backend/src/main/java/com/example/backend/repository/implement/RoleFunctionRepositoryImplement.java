package com.example.backend.repository.implement;

import com.example.backend.model.RoleFunction;
import com.example.backend.repository.RoleFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Lớp triển khai RoleFunctionRepository, sử dụng RoleFunctionRepository
 * interface để thao tác với database.
 * Không cần định nghĩa lại các phương thức phức tạp vì JpaRepository đã cung
 * cấp sẵn.
 */
@Repository
@Transactional
public class RoleFunctionRepositoryImplement {

    @Autowired
    private RoleFunctionRepository roleFunctionRepository;

    /**
     * Lấy tất cả các bản ghi từ bảng ROLE_FUNCTION.
     * 
     * @return Danh sách tất cả các RoleFunction.
     */
    public List<RoleFunction> findAll() {
        return roleFunctionRepository.findAll();
    }

    /**
     * Lưu hoặc cập nhật một RoleFunction vào bảng ROLE_FUNCTION.
     * 
     * @param entity Đối tượng RoleFunction cần lưu.
     * @return RoleFunction đã được lưu hoặc cập nhật.
     */
    public RoleFunction save(RoleFunction entity) {
        return roleFunctionRepository.save(entity);
    }
}