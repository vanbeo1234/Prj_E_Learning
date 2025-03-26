package com.example.backend.service.repo;

import com.example.backend.model.User;
import com.example.backend.repository.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    // HIỂN THỊ
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

// TÌM KIẾM

    public List<User> searchUsers(String name, Date dateOfBirth, Integer roleId, String statusCode) {
        return userRepository.searchUsers(name, dateOfBirth, roleId, statusCode);
    }

// CẬP NHẬT

}