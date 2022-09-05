package com.anynak.diary.service;

import com.anynak.diary.dto.RoleRequest;
import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User registerUser(UserRequest userRequest);

    User saveUser(User user);

    User getUser(Long id);

    User getByEmail(String email);

    User banUser(Long id);

    User setRoles(RoleRequest roleRequest);

    User makeDiaryPublic(String email);

    User makeDiaryPrivate(String email);
}
