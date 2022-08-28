package com.anynak.diary.controllers;

import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.AlreadyLoggedException;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@Validated
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    /**
     * create user
     * ex body:
     * {
     * "name": "Max",
     * "email": "MaxPlanck@mail.com",
     * "password":"Qwerty123",
     * "repeatPassword":"Qwerty123"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@RequestBody @Valid UserRequest userRequest, Principal principal, BindingResult bindingResult) {
        if (principal != null) {
            throw  new AlreadyLoggedException("you are already logged");
        } else {
            User user = userService.registerUser(userRequest);
            UserResponse response = UserMapper.INSTANCE.toUserResponse(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
}
