package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity ánh xạ với bảng USER trong database.
 */
@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_CODE", nullable = false, unique = true, length = 50)
    private String userCode;

    @Column(name = "ENCRYPTION_KEY", length = 255)
    private String encryptionKey;

    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Column(name = "GENDER", nullable = false, columnDefinition = "INT(1) DEFAULT 0")
    private Integer gender;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDateTime dateOfBirth;

    @Column(name = "ROLE_ID", nullable = false)
    private Integer roleId;

    @Column(name = "STATUS_CODE", nullable = false, length = 20)
    private String statusCode;

    @Column(name = "EXPERIENCE")
    private Integer experience;

    @Column(name = "CERTIFICATION", length = 255)
    private String certification;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "CREATED_AT", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
    private Role role;
}