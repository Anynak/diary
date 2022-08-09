package com.anynak.diary.repositories;

import com.anynak.diary.entity.DiaryPost;

import com.anynak.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryPostRepository extends JpaRepository<DiaryPost, Long> {
    int removeDiaryPostByDiaryPostIdAndUser(Long diaryPostId, User user);
    Optional<DiaryPost>  getDiaryPostByDiaryPostIdAndUser(Long diaryPostId, User user);
}
