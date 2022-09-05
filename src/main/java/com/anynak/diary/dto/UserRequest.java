package com.anynak.diary.dto;

import com.anynak.diary.validators.PasswordMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@PasswordMatch(message = "{userForm.password.match}")
public class UserRequest {

    @NotNull(message = "{userForm.username.required}")
    @NotBlank(message = "{userForm.username.blank}")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,15}$", message = "{userForm.username.validation}")
    private String name;

    @NotNull(message = "{userForm.email.required}")
    @NotBlank(message = "{userForm.email.blank}")
    @Email(message = "{userForm.email.validation}")
    private String email;

    @NotNull(message = "{userForm.password.required}")
    @NotBlank(message = "{userForm.password.blank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", message = "{userForm.password.validation}")
    private String password;

    private String repeatPassword;
}
