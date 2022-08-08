package com.anynak.diary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String name;
    private String email;

}
