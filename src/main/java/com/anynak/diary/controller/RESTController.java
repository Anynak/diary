package com.anynak.diary.controller;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.service.DiaryPostService;
import com.anynak.diary.service.RoleService;
import com.anynak.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {


    private final UserService userService;
    private final RoleService roleService;
    private final DiaryPostService diaryPostService;

    @Autowired
    public RESTController(UserService userService, RoleService roleService, DiaryPostService diaryPostService) {
        this.userService = userService;
        this.roleService = roleService;
        this.diaryPostService = diaryPostService;
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
        return ResponseEntity.ok(diaryPostService.findByUser(user));
    }
    /** add post to the diary*/
    @PostMapping("/addPost")
    public ResponseEntity<DiaryPost> addPost(@RequestBody DiaryPost diaryPost, Principal principal){
        System.out.println(diaryPost);
        diaryPost.setCreationTime(new Date());
        User user = userService.getByLogin(principal.getName());

        //user.addDiaryPosts(diaryPost);
        System.out.println(user);
        userService.saveUser(user);
        //diaryPostService.addBuUserName(diaryPost,principal.getName());
        return ResponseEntity.ok(diaryPost);
    }
    /** edit post*/
    @PutMapping("/post")
    public ResponseEntity<DiaryPost> editPost(@RequestBody DiaryPost diaryPost, Principal principal){
        DiaryPost updatedPost = diaryPostService.addBuUserName(diaryPost, principal.getName());
        return ResponseEntity.ok(updatedPost);
    }

    /** get strange post*/

    /** remove post*/
}
