package com.example.backend.service.implement;

import com.example.backend.model.Role;
import com.example.backend.repository.RoleRepository;
import com.example.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImplement implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
            throw new RuntimeException("Vai trò đã tồn tại");
        }
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Integer id, Role role) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò với ID: " + id));
        existingRole.setRoleName(role.getRoleName());
        return roleRepository.save(existingRole);
    }
}