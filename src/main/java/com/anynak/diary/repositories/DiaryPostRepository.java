package com.anynak.diary.repositories;

import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//эта пагинация типа производительнее
//SELECT * FROM bicyun7l9t2mwlhhaaxv.diary_post WHERE diary_post_id >= (SELECT diary_post_id FROM
//        bicyun7l9t2mwlhhaaxv.diary_post ORDER BY diary_post_id LIMIT 999 , 1) LIMIT 30
public interface DiaryPostRepository extends JpaRepository<DiaryPost, Long> {
    int removeDiaryPostByDiaryPostIdAndUser(Long diaryPostId, User user);

    Optional<DiaryPost> getDiaryPostByDiaryPostIdAndUser(Long diaryPostId, User user);

    @Query(value = "SELECT * FROM diary_post WHERE user_id!=?1 LIMIT ?2 OFFSET ?3 ", nativeQuery = true)
    Optional<List<DiaryPost>> getPageExceptUserId(long id, int limit, int offset);

    @Query(value = "SELECT * FROM diary_post WHERE user_id=?1 LIMIT ?2 OFFSET ?3 ", nativeQuery = true)
    Optional<List<DiaryPost>> getPageByUserId(long id, int limit, int offset);


    int countDiaryPostByUserNot(User user);

    int countDiaryPostByUser(User user);
}
