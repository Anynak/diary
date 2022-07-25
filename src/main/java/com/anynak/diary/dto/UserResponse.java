package com.anynak.diary.dto;

import com.anynak.diary.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    private Long userId;
    private String login;
    private String email;

    public UserResponse(User user) {
         this.userId = user.getUserId();
         this.login = user.getLogin();
         this.email = user.getEmail();
    }
}
