package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.repositories.DiaryPostRepository;

import java.util.List;
import java.util.Set;

public interface DiaryPostService {
    DiaryPost addBuUserName(DiaryPost diaryPost, String userName);
    DiaryPost save(DiaryPost diaryPost);
    DiaryPost findBuId(Long id);
    List<DiaryPost> findByUser(User user);
    void removePost(Set<Long> ids);

}
