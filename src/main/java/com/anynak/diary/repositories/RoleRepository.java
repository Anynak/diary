package com.anynak.diary.repositories;

import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //@Query("FROM Role WHERE roleName = ?1")
    Role findRoleByRoleName(RoleName roleName);

    Set<Role> findAllByRoleNameIn(Set<RoleName> roles);
}
