package com.anynak.diary.dto;

import com.anynak.diary.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String login;
    private String email;

}
