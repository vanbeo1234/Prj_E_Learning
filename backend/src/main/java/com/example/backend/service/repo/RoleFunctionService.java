package com.example.backend.service;

import com.example.backend.model.RoleFunction;
import java.util.List;

public interface RoleFunctionService {
    RoleFunction createRoleFunction(RoleFunction roleFunction);

    List<RoleFunction> getAllRoleFunctions();

    RoleFunction updateRoleFunction(int roleId, int functionId, RoleFunction roleFunction);
}