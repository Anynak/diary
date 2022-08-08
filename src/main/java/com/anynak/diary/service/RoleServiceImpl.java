package com.anynak.diary.service;

import com.anynak.diary.RoleName;
import com.anynak.diary.entity.Role;
import com.anynak.diary.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createNewRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void removeRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
