package com.example.backend.service;

import com.example.backend.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role createRole(Role role);

    Optional<Role> getRoleById(Integer id);

    List<Role> getAllRoles();

    Role updateRole(Integer id, Role role);
}