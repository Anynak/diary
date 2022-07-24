package com.anynak.diary.controller;
import com.anynak.diary.dto.DiaryPostRequest;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import com.anynak.diary.service.DiaryPostService;
import com.anynak.diary.service.RoleService;
import com.anynak.diary.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//TODO наделать дто-шек, добить валидацию, секурица
import static com.anynak.diary.RoleName.ROLE_USER;

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

    /** private information about the users profile*/
    @GetMapping("/profile")
    public ResponseEntity<User> profile(Principal principal){
        System.out.println(principal.getName());
        User user = userService.getByLogin(principal.getName());
       return ResponseEntity.ok().body(user);

    }
    /** create user */
    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleService.getRoleByName(ROLE_USER);
        user.addRole(role);
        userService.saveUser(user);
        return user;

    }
    /**get post*/
    @GetMapping("/post/{id}")
    public DiaryPost getUser(@PathVariable Long id){
        return diaryPostService.findBuId(id);
    }
    /** get all users posts */
    @GetMapping("/diary")
    public ResponseEntity<List<DiaryPost>> diary(Principal principal){
        User user = userService.getByLogin(principal.getName());
        return ResponseEntity.ok(user.getDiaryPosts());
    }
    /** add post to the diary*/
    @PostMapping("/addPost")
    public ResponseEntity<DiaryPost> addPost(@RequestBody @Valid DiaryPostRequest diaryPostRequest, Principal principal){
        System.out.println("diaryPost");


        User user = userService.getByLogin(principal.getName());

        DiaryPost diaryPost = new DiaryPost();
        diaryPost.setText(diaryPostRequest.getText());
        diaryPost.setCreationTime(new Date());
        user.addDiaryPosts(diaryPost);
        //System.out.println(user);
        userService.saveUser(user);

        return new ResponseEntity<>(diaryPost, HttpStatus.CREATED);
    }
    /** edit post*/
    @PutMapping("/post")
    public ResponseEntity<DiaryPost> editPost(@RequestBody DiaryPost diaryPost, Principal principal){
        DiaryPost updatedPost = diaryPostService.addBuUserName(diaryPost, principal.getName());
        return ResponseEntity.ok(updatedPost);
    }

    /** get strange post*/

    /** remove post*/
    @DeleteMapping("/post/{id}")
    public void removePost(@PathVariable Long id){
        HashSet<Long> ids = new HashSet<>();
        ids.add(id);
        diaryPostService.removePost(ids);
    }
}
