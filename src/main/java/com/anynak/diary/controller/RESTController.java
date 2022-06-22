package com.anynak.diary.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTController {

    @GetMapping("/")
    public String showAllPosts(){
        return "wwwwwwwwwww";
    }
}
