package com.anynak.diary.controllers;

import com.anynak.diary.dto.RoleRequest;
import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.User;

import com.anynak.diary.exceptions.UserAlreadyExistsException;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
        User user = userService.getByEmail(principal.getName());
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
    /**
     * only ROLE_ADMIN can patch roles
     * ex body:
     * {
     *  "userId": 15,
     *  "roles": [
     *      "ROLE_ADMIN",
     *      "ROLE_USER"
     *      ]
     * }
     */
    @PutMapping("/roles")
    public ResponseEntity<Object> setRoles(@RequestBody @Valid RoleRequest roleRequest, Principal principal){
        System.out.println(principal);
        User user= userService.setRoles(roleRequest);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user),HttpStatus.OK);
    }
    /**
     * only ROLE_ADMIN and ROLE_MODERATOR can ban
     * */
    @PatchMapping("/banUser/{id}")
    public ResponseEntity<Object> banUser(@PathVariable("id") @Min(1) Long id, Principal principal){
        User user = userService.banUser(id);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user),HttpStatus.OK);
    }

    @PatchMapping("/makeDiaryPublic")
    public ResponseEntity<Object> makeDiaryPublic(Principal principal){
        User user = userService.makeDiaryPublic(principal.getName());
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user),HttpStatus.OK);
    }

    @PatchMapping("/makeDiaryPrivate")
    public ResponseEntity<Object> makeDiaryPrivate(Principal principal){
        User user = userService.makeDiaryPrivate(principal.getName());
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user),HttpStatus.OK);
    }

}
