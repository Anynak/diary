package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;

import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.ResourceNotFoundException;
import com.anynak.diary.repositories.DiaryPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DiaryPostServiceImpl implements DiaryPostService {
    private final DiaryPostRepository diaryPostRepository;

    @Override
    public DiaryPost save(DiaryPost diaryPost) {
        return diaryPostRepository.save(diaryPost);

    }

    @Override
    public DiaryPost findBuIdAndUser(Long postId, User user) {
        Optional<DiaryPost> optionalDiaryPost = diaryPostRepository.getDiaryPostByDiaryPostIdAndUser(postId, user);
        return optionalDiaryPost.orElseThrow(()->new ResourceNotFoundException("post with id: " + postId + " and user: " + user.getEmail() + " not found"));
    }


    @Override
    @Transactional
    public int removePostByIdAndUser(Long diaryPostId, User user) {
        return diaryPostRepository.removeDiaryPostByDiaryPostIdAndUser(diaryPostId, user);
    }

}
