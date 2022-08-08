package com.anynak.diary.repositories;

import com.anynak.diary.entity.DiaryPost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryPostRepository extends JpaRepository<DiaryPost, Long> {
    int removeDiaryPostByDiaryPostId(Long id);
}
