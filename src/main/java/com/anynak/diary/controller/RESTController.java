package com.anynak.diary.controller;
import com.anynak.diary.entity.User;
import com.anynak.diary.service.RoleService;
import com.anynak.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        //Role role1 = Role.builder().roleName("BANNED").build();
        //roleService.createNewRole(role1);
        return ResponseEntity.ok().body(userService.getAllUsers());
        //return ResponseEntity.ok().body("HELLO");
        //return userService.getAllUsers();
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }


    @GetMapping("")
    public ResponseEntity<String> home(){
       return ResponseEntity.ok().body("HELLO");

    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        System.out.println(user);
        userService.saveUser(user);
        return user;

    }
}
