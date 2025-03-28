package com.example.backend.controller;
import com.example.backend.model.User;
import com.example.backend.service.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Date;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // API HIỂN THỊ DANH SÁCH
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

     // API TÌM KIẾM
     @GetMapping("/search")
     public List<User> searchUsers(
             @RequestParam(required = false) String name,
             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
             @RequestParam(required = false) Integer roleId,
             @RequestParam(required = false) String statusCode) {
         return userService.searchUsers(name, dateOfBirth, roleId, statusCode);
     }

     // API CẬP NHẬT

}
