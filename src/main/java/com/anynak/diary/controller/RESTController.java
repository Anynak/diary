package com.anynak.diary.controller;
import com.anynak.diary.entity.User;
import com.anynak.diary.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;

    public RESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllPosts(){
        User user = new User("toha2", "tyrty68756");
        userService.saveUser(user);
        return "wwwwwwwwwww";
    }
}
