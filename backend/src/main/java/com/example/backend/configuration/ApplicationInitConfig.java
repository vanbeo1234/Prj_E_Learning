package com.example.backend.configuration;

import com.example.backend.common.Constant;
import com.example.backend.model.Role;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    UserRepository userRepository;
    RoleRepository roleRepository;

    static final String ADMIN_EMAIL = "admin@example.com";

    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource", name = "driver-class-name", havingValue = "com.mysql.cj.jdbc.Driver")
    public ApplicationRunner applicationRunner() {
        return args -> {
            log.info("Khởi tạo dữ liệu hệ thống...");

            // Tạo các vai trò nếu chưa có
            createRoleIfNotExists(Constant.ADMIN_ROLE, "Admin");
            createRoleIfNotExists(Constant.INSTRUCTOR_ROLE, "Instructor");
            createRoleIfNotExists(Constant.STUDENT_ROLE, "Student");

            // Kiểm tra tài khoản admin
            if (userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
                log.warn("Tài khoản admin chưa có trong cơ sở dữ liệu! Vui lòng chèn dữ liệu admin vào cơ sở dữ liệu.");
            } else {
                log.info("Tài khoản admin đã tồn tại.");
            }

            log.info("Khởi tạo dữ liệu hoàn tất.");
        };
    }

    private void createRoleIfNotExists(int roleId, String roleName) {
        if (roleRepository.findById(roleId).isEmpty()) {
            Role role = Role.builder()
                    .id(roleId)
                    .roleName(roleName)
                    .build();
            roleRepository.save(role);
            log.info("Đã tạo vai trò: {} (ID: {})", roleName, roleId);
        }
    }
}