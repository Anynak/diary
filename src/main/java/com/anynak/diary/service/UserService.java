package com.anynak.diary.service;

import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User saveUser(User user);
    public User getUser(Long id);
    public User getByLogin(String login);

    public void addRole(Role role);
}
