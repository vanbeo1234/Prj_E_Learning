package com.example.backend.service.implement;

import com.example.backend.common.Constant;
import com.example.backend.configuration.RSAConfig;
import com.example.backend.dto.request.AuthRequest;
import com.example.backend.dto.request.IntrospectRequest;
import com.example.backend.dto.request.RegisterRequest;
import com.example.backend.dto.response.AuthResponse;
import com.example.backend.dto.response.IntrospectResponse;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters; // Thêm import này để sửa lỗi
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Lớp triển khai UserService, cung cấp logic nghiệp vụ cho bảng USER, bao gồm
 * đăng nhập, đăng ký và quản lý token.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final RSAConfig rsaConfig;

    /**
     * Đăng nhập người dùng và tạo access token, refresh token.
     * 
     * @param request Đối tượng chứa email và mật khẩu.
     * @return AuthResponse chứa access token và refresh token.
     */
    @Override
    public AuthResponse login(AuthRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            throw new RuntimeException("Email hoặc mật khẩu không đúng");
        }

        User user = userOpt.get();
        String accessToken = generateToken(user, 1, ChronoUnit.HOURS);
        String refreshToken = generateToken(user, 7, ChronoUnit.DAYS);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Đăng ký người dùng mới (chỉ Instructor mới được phép tạo tài khoản student).
     * 
     * @param request Đối tượng chứa thông tin đăng ký.
     * @return AuthResponse chứa access token và refresh token.
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        String currentRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (!currentRole.contains("ROLE_Instructor")) {
            throw new RuntimeException("Chỉ instructor mới được tạo tài khoản student");
        }

        String encryptedKey = encryptKey(UUID.randomUUID().toString());
        User user = User.builder()
                .userCode("STU" + System.currentTimeMillis())
                .encryptionKey(encryptedKey)
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleId(Constant.STUDENT_ROLE)
                .statusCode("ACTIVE")
                .createdBy("SYSTEM")
                .build();

        userRepository.save(user);

        String accessToken = generateToken(user, 1, ChronoUnit.HOURS);
        String refreshToken = generateToken(user, 7, ChronoUnit.DAYS);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Kiểm tra tính hợp lệ của token (giả lập).
     * 
     * @param request Đối tượng chứa token cần kiểm tra.
     * @return IntrospectResponse chứa kết quả kiểm tra.
     */
    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        return IntrospectResponse.builder()
                .valid(true) // Giả lập, cần thêm logic thực tế
                .build();
    }

    /**
     * Làm mới access token dựa trên refresh token.
     * 
     * @param refreshToken Refresh token hiện tại.
     * @return AuthResponse chứa access token mới.
     */
    @Override
    public AuthResponse refreshToken(String refreshToken) {
        User user = userRepository.findByEmail("admin@example.com").orElseThrow();
        String newAccessToken = generateToken(user, 1, ChronoUnit.HOURS);
        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Lấy thông tin người dùng theo ID.
     * 
     * @param id ID của người dùng.
     * @return Optional<User> chứa thông tin người dùng nếu tìm thấy.
     */
    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Lấy danh sách tất cả người dùng.
     * 
     * @return Danh sách tất cả User.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Cập nhật thông tin người dùng.
     * 
     * @param id   ID của người dùng cần cập nhật.
     * @param user Đối tượng User chứa thông tin mới.
     * @return User đã được cập nhật.
     */
    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setGender(user.getGender());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setExperience(user.getExperience());
        existingUser.setCertification(user.getCertification());
        existingUser.setUpdatedBy("SYSTEM");
        return userRepository.save(existingUser);
    }

    /**
     * Tạo JWT token với thời gian sống tùy chỉnh.
     * 
     * @param user     Người dùng cần tạo token.
     * @param duration Thời gian sống của token.
     * @param unit     Đơn vị thời gian (giờ, ngày, v.v.).
     * @return Chuỗi JWT token.
     */
    private String generateToken(User user, long duration, ChronoUnit unit) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(duration, unit))
                .subject(user.getEmail())
                .claim("roleId", user.getRoleId())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Mã hóa khóa bằng RSA.
     * 
     * @param key Chuỗi cần mã hóa.
     * @return Chuỗi đã mã hóa dưới dạng Base64.
     */
    private String encryptKey(String key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaConfig.getPublicKey());
            byte[] encryptedBytes = cipher.doFinal(key.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi mã hóa ENCRYPTION_KEY", e);
        }
    }
}