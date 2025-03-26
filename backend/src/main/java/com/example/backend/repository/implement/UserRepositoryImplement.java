package com.example.backend.repository.implement;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Lớp triển khai UserRepository, sử dụng UserRepository interface để thao tác
 * với database.
 */
@Repository
@Transactional
public class UserRepositoryImplement {

    @Autowired
    private UserRepository userRepository;

    /**
     * Lấy tất cả các bản ghi từ bảng USER.
     * 
     * @return Danh sách tất cả các User.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Lưu hoặc cập nhật một User vào bảng USER.
     * 
     * @param entity Đối tượng User cần lưu.
     * @return User đã được lưu hoặc cập nhật.
     */
    public User save(User entity) {
        return userRepository.save(entity);
    }

    /**
     * Tìm User theo email (EMAIL).
     * 
     * @param email Email cần tìm.
     * @return Optional<User> chứa kết quả nếu tìm thấy, hoặc empty nếu không.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Tìm User theo mã người dùng (USER_CODE).
     * 
     * @param userCode Mã người dùng cần tìm.
     * @return Optional<User> chứa kết quả nếu tìm thấy, hoặc empty nếu không.
     */
    public Optional<User> findByUserCode(String userCode) {
        return userRepository.findByUserCode(userCode);
    }
}