package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEM_FUNCTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FUNCTION_NAME", nullable = false, length = 255)
    private String functionName;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}