package com.anynak.diary.dto;
import com.anynak.diary.valodator.PasswordMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


@Data
@NoArgsConstructor
@PasswordMatch(message = "{userForm.password.match}")
public class UserRequest {

    @NotNull(message = "{userForm.username.required}")
    @NotBlank(message = "{userForm.username.required}")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,15}$", message = "{userForm.username.validation}")
    private String name;
    @NotNull(message = "{userForm.email.required}")
    @NotBlank(message = "{userForm.email.required}")
    @Email(message = "{userForm.email.validation}")
    private String email;
    @NotNull(message = "{userForm.password.required}")
    @NotBlank(message = "{userForm.password.required}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$",  message = "{userForm.password.validation}")
    private String password;

    private String repeatPassword;

    @Override
    public String toString() {
        return "UserRequest{" +
                "login='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
