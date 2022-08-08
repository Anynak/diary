package com.anynak.diary.repositories;

import com.anynak.diary.RoleName;
import com.anynak.diary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("FROM Role WHERE roleName = ?1")
    Role findByName(RoleName roleName);
}
