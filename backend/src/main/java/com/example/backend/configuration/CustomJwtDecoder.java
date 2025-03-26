package com.example.backend.configuration;

import com.example.backend.dto.request.IntrospectRequest;
import com.example.backend.dto.response.IntrospectResponse;
import com.example.backend.service.UserService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import java.security.interfaces.RSAPublicKey;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    private final UserService userService;
    private final NimbusJwtDecoder nimbusJwtDecoder;

    // Bỏ @Autowired vì dùng @RequiredArgsConstructor
    public CustomJwtDecoder(UserService userService, RSAConfig rsaConfig) {
        this.userService = userService;
        // Ép kiểu PublicKey sang RSAPublicKey
        this.nimbusJwtDecoder = NimbusJwtDecoder.withPublicKey((RSAPublicKey) rsaConfig.getPublicKey()).build();
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        // Kiểm tra token hợp lệ
        IntrospectResponse response = userService.introspect(IntrospectRequest.builder().token(token).build());
        if (!response.isValid()) {
            throw new JwtException("Token không hợp lệ hoặc đã hết hạn");
        }
        return nimbusJwtDecoder.decode(token); // Chỉ cần decode, không cần try-catch JOSEException
    }
}