package com.example.backend.service;

import com.example.backend.dto.request.AuthRequest;
import com.example.backend.dto.request.IntrospectRequest;
import com.example.backend.dto.request.RegisterRequest;
import com.example.backend.dto.response.AuthResponse;
import com.example.backend.dto.response.IntrospectResponse;
import com.example.backend.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    AuthResponse login(AuthRequest request);

    AuthResponse register(RegisterRequest request);

    IntrospectResponse introspect(IntrospectRequest request);

    AuthResponse refreshToken(String refreshToken);

    Optional<User> getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(Integer id, User user);
}