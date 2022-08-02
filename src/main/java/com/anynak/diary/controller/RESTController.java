package com.anynak.diary.controller;

import com.anynak.diary.dto.DiaryPostRequest;
import com.anynak.diary.dto.DiaryPostResponse;
import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.UserAlreadyExistsException;
import com.anynak.diary.mapers.DiaryPostMapper;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.service.DiaryPostService;
import com.anynak.diary.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.Instant;
import java.util.List;
//TODO localisation,  секурица, PostService, add Mapstruct


@RestController
@RequestMapping("/api")
@Validated
public class RESTController {

    private final UserService userService;
    private final DiaryPostService diaryPostService;

    @Autowired
    public RESTController(UserService userService, DiaryPostService diaryPostService) {
        this.userService = userService;
        this.diaryPostService = diaryPostService;
    }

    /**
     * private information about the users profile
     */
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profile(Principal principal) {
        System.out.println(principal.getName());
        User user = userService.getByEmail(principal.getName());
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserResponse(user), HttpStatus.OK);
    }

    /**
     * create user
     */
    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@RequestBody @Valid UserRequest userRequest, Principal principal, BindingResult bindingResult) {

        String t;
        if (principal != null) {
            return new ResponseEntity<>("you are already registered",HttpStatus.FORBIDDEN);
        }
        else {

            //try {
                User user = userService.registerUser(userRequest);
                UserResponse response = UserMapper.INSTANCE.toUserResponse(user);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            //}catch (Exception e){
            //    throw new UserAlreadyExistsException("User with email :"+userRequest.getEmail()+" already registered");            }

        }
    }

    /**
     * get post
     */
    @GetMapping("/post/{id}")
    public ResponseEntity<DiaryPostResponse> getUser(@PathVariable("id") @Min(1) Long id) {
        DiaryPost diaryPost = diaryPostService.findBuId(id);
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(diaryPost), HttpStatus.FOUND);
    }

    /**
     * get all users posts
     */
    @GetMapping("/diary")
    public ResponseEntity<List<DiaryPostResponse>> diary(Principal principal) {
        User user = userService.getByEmail(principal.getName());
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(user.getDiaryPosts()), HttpStatus.CREATED);
    }

    /**
     * add post to the diary
     */
    @PostMapping("/addPost")
    public ResponseEntity<DiaryPostResponse> addPost(@RequestBody @Valid DiaryPostRequest diaryPostRequest, Principal principal) {
        User user = userService.getByEmail(principal.getName());
        DiaryPost diaryPost = DiaryPostMapper.INSTANCE.toDiaryPost(diaryPostRequest);
        diaryPost.setUser(user);
        diaryPost.setCreation_UNIX_SEC(Instant.now().getEpochSecond());
        diaryPost = diaryPostService.save(diaryPost);
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(diaryPost), HttpStatus.CREATED);
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
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> removePost(@PathVariable Long postId) {
        if (diaryPostService.removePostById(postId) == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
