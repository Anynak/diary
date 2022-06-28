package com.anynak.diary.service;

import com.anynak.diary.RoleName;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
        User user =  new User();
        //user.setLogin("admin");
        //user.setPasswordHash(new BCryptPasswordEncoder().encode("hdddf"));
        //user.setEmail("dfddhghdf");
//
        //Role role = roleService.getRoleByName(RoleName.ADMIN);
        //user.addRole(role);
//
        //User user1 = userService.saveUser(user);
        //System.out.println(user1);
    }

    @Test
    void getUser() {

    }
}