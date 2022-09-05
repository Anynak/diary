package com.anynak.diary.controllers;

import com.anynak.diary.config.security.data.UserDetailsImpl;
import com.anynak.diary.dto.RoleRequest;
import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.User;

import com.anynak.diary.exceptions.AlreadyLoggedException;
import com.anynak.diary.exceptions.UserAlreadyExistsException;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * only ROLE_ADMIN can patch roles
     * ex body:
     * {
     * "userId": 15,
     * "roles": [
     * "ROLE_ADMIN",
     * "ROLE_USER"
     * ]
     * }
     */
    @PutMapping("/roles")
    public ResponseEntity<Object> setRoles(@RequestBody @Valid RoleRequest roleRequest, Principal principal) {

        User user = userService.setRoles(roleRequest);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);
    }

    /**
     * only ROLE_ADMIN and ROLE_MODERATOR can ban
     */
    @PatchMapping("/banUser/{id}")
    public ResponseEntity<Object> banUser(@PathVariable("id") @Min(1) Long id, Principal principal) {
        User user = userService.banUser(id);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);
    }

    /**
     * make diary available for all users by GET /api/strangePost
     * not for ROLE_BANNED
     */
    @PatchMapping("/makeDiaryPublic")
    public ResponseEntity<Object> makeDiaryPublic(Principal principal) {
        User user = userService.makeDiaryPublic(principal.getName());
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);
    }
    /**
     * make diary not available for all users by GET /api/strangePost
     */
    @PatchMapping("/makeDiaryPrivate")
    public ResponseEntity<Object> makeDiaryPrivate(Principal principal) {
        User user = userService.makeDiaryPrivate(principal.getName());
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);
    }

}
