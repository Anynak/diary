package com.anynak.diary.dto;

import com.anynak.diary.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    private String login;
    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    private String email;
    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    private String password;
    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    private String repeatPassword;

    @Override
    public String toString() {
        return "UserRequest{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
