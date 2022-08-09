package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;

public interface DiaryPostService {

    DiaryPost save(DiaryPost diaryPost);

    DiaryPost findBuIdAndUser(Long postId, User user);

    int removePostByIdAndUser(Long diaryPostId, User user);

}
