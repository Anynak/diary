package com.anynak.diary.dto;
import com.anynak.diary.valodator.PasswordMatch;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


@Data
@NoArgsConstructor
@PasswordMatch(message = "{Different.userForm.password}")
public class UserRequest {

    @NotNull()
    @NotBlank()
    @Pattern(regexp = "^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "login is not correct")
    private String name;
    @NotNull()
    @NotBlank()
    @Email()
    private String email;
    @NotNull()
    @NotBlank()
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", message = "Minimum 6 characters, at least one uppercase letter, one lowercase letter and one number")
    private String password;
    @NotNull()
    @NotBlank()
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
