package com.anynak.diary.repositories;

import com.anynak.diary.RoleName;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User WHERE login = ?1")
    public User findByLogin(String login);
}
