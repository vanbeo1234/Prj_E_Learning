package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Class đại diện cho khóa chính composite của bảng ROLE_FUNCTION.
 * Bao gồm ROLE_ID và FUNCTION_ID.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunctionId implements Serializable {
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "FUNCTION_ID")
    private Integer functionId;
}