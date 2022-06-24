package com.anynak.diary.service;

import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.RoleNames;
import com.anynak.diary.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Test
    void getAllUsers() {

    }

    @Test
    void saveUser() {
        User user = new User();
        user.setLogin("qqqq");
        user.setPasswordHash("werwrwr");
        //Role role = roleService.
        //user.addRole(new Role(RoleNames.USER.name()));
        //User user1 = userService.saveUser(user);
    }

    @Test
    void getUser() {

    }
}