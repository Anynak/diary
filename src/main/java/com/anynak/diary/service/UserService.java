package com.anynak.diary.service;

import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User registerUser(UserRequest userRequest);
    User saveUser(User user);
    User getUser(Long id);
    User getByEmail(String login);

    void addRole(Role role);
}
