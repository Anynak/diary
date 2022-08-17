package com.anynak.diary.dto;

import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.RoleName;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleRequest {
    private Long userId;
    private Set<RoleName> roles = new HashSet<>();

}
