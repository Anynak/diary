package com.anynak.diary.controller;

import com.anynak.diary.dto.DiaryPostRequest;
import com.anynak.diary.dto.DiaryPostResponse;
import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.service.DiaryPostService;
import com.anynak.diary.service.RoleService;
import com.anynak.diary.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
//TODO localisation, добить валидацию, секурица, add Mapstruct


@RestController
@RequestMapping("/api")
@Validated
public class RESTController {


    private final UserService userService;
    private final RoleService roleService;
    private final DiaryPostService diaryPostService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RESTController(UserService userService, RoleService roleService, DiaryPostService diaryPostService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.diaryPostService = diaryPostService;
        this.passwordEncoder = passwordEncoder;
    }

    /** public information about a profile */
    //@GetMapping("/user/{id}")
    //public User getUser(@PathVariable Long id){
    //    return userService.getUser(id);
    //}

    /**
     * private information about the users profile
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profile(Principal principal) {
        System.out.println(principal.getName());
        User user = userService.getByLogin(principal.getName());
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);

    }

    /**
     * create user
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> addUser(@RequestBody @Valid UserRequest userRequest, Principal principal, BindingResult bindingResult) {
        if (principal != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            UserResponse response = new UserResponse(userService.registerUser(userRequest));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }


    }

    /**
     * get post
     */
    @GetMapping("/post/{id}")
    public ResponseEntity<DiaryPostResponse> getUser(@PathVariable Long id) {
        DiaryPost diaryPost = diaryPostService.findBuId(id);
        return new ResponseEntity<>(new DiaryPostResponse(diaryPost), HttpStatus.FOUND);
    }

    /**
     * get all users posts
     */
    @GetMapping("/diary")
    public ResponseEntity<List<DiaryPost>> diary(Principal principal) {
        User user = userService.getByLogin(principal.getName());
        return ResponseEntity.ok(user.getDiaryPosts());
    }

    /**
     * add post to the diary
     */
    @PostMapping("/addPost")
    public ResponseEntity<DiaryPost> addPost(@RequestBody @Valid DiaryPostRequest diaryPostRequest, Principal principal) {
        System.out.println("diaryPost");


        User user = userService.getByLogin(principal.getName());

        DiaryPost diaryPost = new DiaryPost();
        diaryPost.setText(diaryPostRequest.getText());
        diaryPost.setCreation_UNIX_SEC(Instant.now().getEpochSecond());
        user.addDiaryPosts(diaryPost);
        //System.out.println(user);
        userService.saveUser(user);

        return new ResponseEntity<>(diaryPost, HttpStatus.CREATED);
    }

    /**
     * edit post
     */
    @PutMapping("/post")
    public ResponseEntity<DiaryPost> editPost(@RequestBody DiaryPost diaryPost, Principal principal) {
        DiaryPost updatedPost = diaryPostService.addBuUserName(diaryPost, principal.getName());
        return ResponseEntity.ok(updatedPost);
    }

    /** get strange post*/

    /**
     * remove post
     */
    @DeleteMapping("/post/{id}")
    public void removePost(@PathVariable Long id) {
        HashSet<Long> ids = new HashSet<>();
        ids.add(id);
        diaryPostService.removePost(ids);
    }
}
