package com.example.backend.common.util;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, 401, "Chưa xác thực");

    private final HttpStatus statusCode;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus statusCode, int code, String message) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}