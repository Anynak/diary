package com.anynak.diary.mapers;

import com.anynak.diary.dto.RoleResponse;
import com.anynak.diary.entity.Role;
import org.mapstruct.factory.Mappers;

import java.util.Set;

public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponse toRoleResponse(Role role);

    Set<RoleResponse> toRoleResponse(Set<Role> roles);

}
