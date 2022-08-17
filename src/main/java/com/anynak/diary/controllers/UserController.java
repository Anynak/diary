package com.anynak.diary.controllers;

import com.anynak.diary.dto.RoleRequest;
import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.User;

import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * private information about the users profile
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profile(Principal principal) {
        //System.out.println(principal);
        User user = userService.getByEmail(principal.getName());
        System.out.println(user);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);
    }

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
            return new ResponseEntity<>("you are already registered", HttpStatus.FORBIDDEN);
        } else {
            User user = userService.registerUser(userRequest);
            UserResponse response = UserMapper.INSTANCE.toUserResponse(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PostMapping("/setRoles")
    public ResponseEntity<Object> setRoles(@RequestBody @Valid RoleRequest roleRequest, Principal principal){
        System.out.println(roleRequest);
        User user= userService.setRoles(roleRequest);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user),HttpStatus.OK);
    }
}
