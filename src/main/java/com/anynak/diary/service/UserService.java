package com.anynak.diary.service;

import com.anynak.diary.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);
}
