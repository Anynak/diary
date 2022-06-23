package com.anynak.diary.controller;
import com.anynak.diary.entity.User;
import com.anynak.diary.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class RESTController {

    private final UserService userService;

    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        userService.saveUser(user);
        return user;

    }
}
