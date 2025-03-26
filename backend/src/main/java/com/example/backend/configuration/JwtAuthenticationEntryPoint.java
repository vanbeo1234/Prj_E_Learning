package com.example.backend.configuration;

import com.example.backend.dto.response.ApiResponse;
import com.example.backend.common.util.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

// Lớp này thực hiện giao diện AuthenticationEntryPoint, được sử dụng khi người dùng không xác thực.
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Phương thức commence được gọi khi có lỗi xác thực
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException)
            throws IOException {

        // Sử dụng mã lỗi UNAUTHENTICATED từ ErrorCode
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        // Thiết lập mã trạng thái HTTP cho phản hồi (401 Unauthorized)
        response.setStatus(errorCode.getStatusCode().value());

        // Thiết lập loại nội dung phản hồi là JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Tạo đối tượng ApiResponse chứa thông tin lỗi (mã lỗi và thông điệp lỗi)
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(errorCode.getCode()) // Mã lỗi 401
                .message(errorCode.getMessage()) // Thông điệp lỗi "Chưa xác thực"
                .build();

        // Chuyển đối tượng ApiResponse thành chuỗi JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // Ghi chuỗi JSON vào phản hồi HTTP
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

        // Đảm bảo phản hồi được gửi ngay lập tức
        response.flushBuffer();
    }
}
