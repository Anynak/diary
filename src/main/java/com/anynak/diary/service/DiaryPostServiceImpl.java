package com.anynak.diary.service;

import com.anynak.diary.Const;
import com.anynak.diary.Utils;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.DiaryPostEditTimeException;
import com.anynak.diary.exceptions.ResourceNotFoundException;
import com.anynak.diary.repositories.DiaryPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
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
        return optionalDiaryPost.orElseThrow(() -> new ResourceNotFoundException("post with id: " + postId + " and user: " + user.getEmail() + " not found"));
    }


    @Override
    @Transactional
    public int removePostByIdAndUser(Long diaryPostId, User user) {
        return diaryPostRepository.removeDiaryPostByDiaryPostIdAndUser(diaryPostId, user);
    }

    @Override
    public DiaryPost editDiaryPost(DiaryPost newDiaryPost, User user) {
        long currentDate = Instant.now().getEpochSecond();
        long createdSecAgo = currentDate - newDiaryPost.getCreation_UNIX_SEC();
        if (createdSecAgo > Const.diaryPostEditPeriodSec) {
            throw new DiaryPostEditTimeException("the permissible editing period is " + Const.diaryPostEditPeriodSec + " SEC. Post was created " + createdSecAgo + " SEC ago");
        }
        return save(newDiaryPost);
    }

    @Override
    public List<DiaryPost> getPostPageById(long id, int limit, int offset) {
        return null;
    }

    @Override
    public DiaryPost getRandomPostByUserNot(User user) {

        int n = diaryPostRepository.countDiaryPostByUserNot(user);
        int randNum = Utils.getRandomNumber(0, n);
        Optional<List<DiaryPost>> optionalDiaryPost = diaryPostRepository.getPageExceptUserId(user.getUserId(), 1, randNum);
        if (optionalDiaryPost.isEmpty()) {
            throw new ResourceNotFoundException("no public posts");
        }
        return optionalDiaryPost.get().get(0);
    }

}
