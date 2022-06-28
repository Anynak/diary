package com.anynak.diary.service;

import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User saveUser(User user);
    public User getUser(Long id);

    public void addRole(Role role);
}