package com.anynak.diary.service;

import com.anynak.diary.entity.RoleName;
import com.anynak.diary.entity.Role;
import com.anynak.diary.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void createNewRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void removeRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Set<Role> findRolesByName(Set<RoleName> roleNames) {
        return roleRepository.findAllByRoleNameIn(roleNames);
    }


}
