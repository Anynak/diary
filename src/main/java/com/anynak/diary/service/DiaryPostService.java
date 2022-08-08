package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;

public interface DiaryPostService {
    DiaryPost addBuUserName(DiaryPost diaryPost, String userName);

    DiaryPost save(DiaryPost diaryPost);

    DiaryPost findBuId(Long id);

    int removePostById(Long id);

}
