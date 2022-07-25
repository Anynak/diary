package com.anynak.diary.repositories;

import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import java.util.List;

public interface DiaryPostRepository extends JpaRepository<DiaryPost, Long> {
    //@Query("FROM DiaryPost WHERE User = ?1")
    //List<DiaryPost> findByUser(User user);
}
