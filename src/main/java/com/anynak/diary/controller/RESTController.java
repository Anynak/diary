package com.anynak.diary.controller;
import com.anynak.diary.entity.User;
import com.anynak.diary.service.RoleService;
import com.anynak.diary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;

    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        //Role role1 = Role.builder().roleName("BANNED").build();
        //roleService.createNewRole(role1);
        return ResponseEntity.ok().body(userService.getAllUsers());
        //return userService.getAllUsers();
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
