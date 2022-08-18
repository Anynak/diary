package com.anynak.diary.repositories;

import com.anynak.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT userId, name, password, email FROM User")
    Optional<List<User>> getAllUsers();

    boolean existsByEmail(String email);


}
