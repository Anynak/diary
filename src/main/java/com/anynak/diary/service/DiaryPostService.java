package com.anynak.diary.service;

import com.anynak.diary.dto.DiaryPostRequest;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;

import java.util.List;

public interface DiaryPostService {

    DiaryPost save(DiaryPost diaryPost);

    DiaryPost findBuIdAndUser(Long postId, User user);

    int removePostByIdAndUser(Long diaryPostId, User user);

    DiaryPost editDiaryPost(DiaryPost newDiaryPost, User user);

    List<DiaryPost> getPostPageById(long id, int limit, int offset);

    DiaryPost getRandomPostByUserNot(User user);

}
