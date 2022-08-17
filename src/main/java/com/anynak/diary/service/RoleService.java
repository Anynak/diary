package com.anynak.diary.service;

import com.anynak.diary.entity.RoleName;
import com.anynak.diary.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public void createNewRole(Role role);

    public void removeRole(Role role);

    public Set<Role> findRolesByName(Set <RoleName> roleNames);
}
