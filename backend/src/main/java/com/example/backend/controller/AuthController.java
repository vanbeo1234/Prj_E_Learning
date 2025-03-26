package com.example.backend.controller;

import com.example.backend.dto.request.AuthRequest;
import com.example.backend.dto.request.IntrospectRequest;
import com.example.backend.dto.request.RegisterRequest;
import com.example.backend.dto.response.AuthResponse;
import com.example.backend.dto.response.IntrospectResponse;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/introspect")
    public IntrospectResponse introspect(@RequestBody IntrospectRequest request) {
        return userService.introspect(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestParam String refreshToken) {
        return userService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public void logout() {
        // Logic logout
    }
}