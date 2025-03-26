package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface cho UserRepository, sử dụng JpaRepository để thao tác với bảng
 * USER.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Tìm User theo email (EMAIL).
     * 
     * @param email Email cần tìm.
     * @return Optional<User> chứa kết quả nếu tìm thấy, hoặc empty nếu không.
     */
    Optional<User> findByEmail(String email);

    /**
     * Tìm User theo mã người dùng (USER_CODE).
     * 
     * @param userCode Mã người dùng cần tìm.
     * @return Optional<User> chứa kết quả nếu tìm thấy, hoặc empty nếu không.
     */
    Optional<User> findByUserCode(String userCode);
}