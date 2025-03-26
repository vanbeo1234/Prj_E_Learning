package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity ánh xạ với bảng STATUS_MANAGEMENT trong database.
 */
@Entity
@Table(name = "STATUS_MANAGEMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STATUS_CODE", nullable = false, length = 20)
    private String statusCode;

    @Column(name = "TYPE", nullable = false, length = 20)
    private String type;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}