package com.anynak.diary.dto;

import com.anynak.diary.entity.User;
import com.anynak.diary.valodator.PasswordMatch;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@PasswordMatch
public class UserRequest {

    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    @Pattern(regexp = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "login is not correct")
    private String login;
    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    @Email
    private String email;
    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", message = "Minimum 6 characters, at least one uppercase letter, one lowercase letter and one number")
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
