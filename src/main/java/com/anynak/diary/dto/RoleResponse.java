package com.anynak.diary.dto;

import com.anynak.diary.entity.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleResponse {
    private RoleName roleName;
}
