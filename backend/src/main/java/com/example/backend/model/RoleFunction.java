package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity ánh xạ với bảng ROLE_FUNCTION trong database.
 */
@Entity
@Table(name = "ROLE_FUNCTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleFunction {
    @EmbeddedId
    private RoleFunctionId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne
    @MapsId("functionId")
    @JoinColumn(name = "FUNCTION_ID")
    private SystemFunction systemFunction;
}