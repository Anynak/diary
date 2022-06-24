package com.anynak.diary.service;

import com.anynak.diary.entity.Role;

public interface RoleService {
    public void createNewRole(Role role);
    public void removeRole(Role role);

    public Role getRoleByName(String roleName);
}
