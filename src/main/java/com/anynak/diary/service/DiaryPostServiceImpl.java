package com.anynak.diary.service;

import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.repositories.DiaryPostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DiaryPostServiceImpl implements DiaryPostService{
    private final DiaryPostRepository diaryPostRepository;
    private final UserService userService;
    public DiaryPostServiceImpl(DiaryPostRepository diaryPostRepository, UserService userService) {
        this.diaryPostRepository = diaryPostRepository;
        this.userService = userService;
    }

    @Override
    public DiaryPost addBuUserName(DiaryPost diaryPost, String userName) {
        //User user = userService.getByLogin(userName);
        //diaryPost.setUser(user);
        diaryPost.setCreationTime(new Date());
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
    public List<DiaryPost> findByUser(User user) {
        return null;
    }

    @Override
    public void removePost(Set<Long> ids) {
         diaryPostRepository.deleteAllById(ids);
    }
}
