package com.anynak.diary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String name;
    private String email;
    private boolean publicDiary;
    private Set<RoleResponse> roles = new HashSet<>();

}
