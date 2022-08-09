package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;

import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.ResourceNotFoundException;
import com.anynak.diary.repositories.DiaryPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;


@Service
public class DiaryPostServiceImpl implements DiaryPostService {
    private final DiaryPostRepository diaryPostRepository;


    public DiaryPostServiceImpl(DiaryPostRepository diaryPostRepository) {
        this.diaryPostRepository = diaryPostRepository;

    }


    @Override
    public DiaryPost save(DiaryPost diaryPost) {
        return diaryPostRepository.save(diaryPost);

    }

    @Override
    public DiaryPost findBuIdAndUser(Long postId, User user) {
        Optional<DiaryPost> optionalDiaryPost = diaryPostRepository.getDiaryPostByDiaryPostIdAndUser(postId, user);
        if (optionalDiaryPost.isPresent()) {
            return optionalDiaryPost.get();
        } else {
            throw new ResourceNotFoundException("post with id: " + postId + " and user: " + user.getEmail() + " not found");
        }
    }


    @Override
    @Transactional
    public int removePostByIdAndUser(Long diaryPostId, User user) {
        return diaryPostRepository.removeDiaryPostByDiaryPostIdAndUser(diaryPostId, user);
    }

}
