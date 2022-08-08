package com.anynak.diary.repositories;

import com.anynak.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT userId, name, password, email FROM User")
    List<User> getAllUsers();
}
