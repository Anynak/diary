package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;

import com.anynak.diary.repositories.DiaryPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
public class DiaryPostServiceImpl implements DiaryPostService {
    private final DiaryPostRepository diaryPostRepository;


    public DiaryPostServiceImpl(DiaryPostRepository diaryPostRepository) {
        this.diaryPostRepository = diaryPostRepository;

    }

    @Override
    public DiaryPost addBuUserName(DiaryPost diaryPost, String userName) {
        diaryPost.setCreation_UNIX_SEC(Instant.now().getEpochSecond());
        return diaryPostRepository.save(diaryPost);
    }

    @Override
    public DiaryPost save(DiaryPost diaryPost) {
        return diaryPostRepository.save(diaryPost);

    }

    @Override
    public DiaryPost findBuId(Long id) {
        return diaryPostRepository.getReferenceById(id);
    }


    @Override
    @Transactional
    public int removePostById(Long id) {
        return diaryPostRepository.removeDiaryPostByDiaryPostId(id);
    }

}
