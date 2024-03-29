package com.anynak.diary.controllers;

import com.anynak.diary.config.security.data.UserDetailsImpl;
import com.anynak.diary.dto.DiaryPostRequest;
import com.anynak.diary.dto.DiaryPostResponse;
import com.anynak.diary.dto.DiaryPostStrange;
import com.anynak.diary.entity.DiaryPost;
import com.anynak.diary.entity.User;
import com.anynak.diary.mapers.DiaryPostMapper;
import com.anynak.diary.service.DiaryPostService;
import com.anynak.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class DiaryPostController {
    private final DiaryPostService diaryPostService;
    private final UserService userService;

    /**
     * get post
     */
    @GetMapping("/post/{id}")
    public ResponseEntity<DiaryPostResponse> getUser(@PathVariable("id") @Min(1) Long id) {
        User user = getUserFromSecurity();
        DiaryPost diaryPost = diaryPostService.findBuIdAndUser(id, user);
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(diaryPost), HttpStatus.FOUND);
    }

    /**
     * get all users posts
     */
    @GetMapping("/diary")
    public ResponseEntity<List<DiaryPostResponse>> diary() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDetails.getUserId());
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(user.getDiaryPosts()), HttpStatus.FOUND);
    }

    /**
     * add post to the diary
     * ex body:
     * {
     * "text": "text"
     * }
     */
    @PostMapping("/newPost")
    public ResponseEntity<DiaryPostResponse> addPost(@RequestBody @Valid DiaryPostRequest diaryPostRequest) {
        User user = getUserFromSecurity();
        DiaryPost diaryPost = DiaryPostMapper.INSTANCE.toDiaryPost(diaryPostRequest);
        diaryPost.setUser(user);
        diaryPost.setCreation_UNIX_SEC(Instant.now().getEpochSecond());
        diaryPost = diaryPostService.save(diaryPost);
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(diaryPost), HttpStatus.CREATED);
    }

    /**
     * edit post text
     * ex body:
     * {
     * "diaryPostId": 22,
     * "text": "edited text"
     * }
     */
    @PatchMapping("/post")
    public ResponseEntity<DiaryPostResponse> editPost(@RequestBody @Valid DiaryPostRequest diaryPostRequest) {

        User user = getUserFromSecurity();
        DiaryPost diaryPost = diaryPostService.findBuIdAndUser(diaryPostRequest.getDiaryPostId(), user);
        //diaryPost.setText(diaryPostRequest.getText());
        DiaryPost updatedPost = diaryPostService.editDiaryPost(diaryPost, user);
        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostResponse(updatedPost), HttpStatus.CREATED);
    }

    /**
     * remove post
     */
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> removePost(@PathVariable Long postId) {
        User user = getUserFromSecurity();
        if (diaryPostService.removePostByIdAndUser(postId, user) == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * to get random stranger post
     */
    @GetMapping("/strangePost")
    public ResponseEntity<DiaryPostStrange> removePost() {
        User user = getUserFromSecurity();
        DiaryPost randomDiaryPost = diaryPostService.getRandomPostByUserNot(user);

        return new ResponseEntity<>(DiaryPostMapper.INSTANCE.toDiaryPostStrange(randomDiaryPost), HttpStatus.OK);
    }

    private User getUserFromSecurity() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setUserId(userDetails.getUserId());
        user.setEmail(userDetails.getUsername());
        return user;
    }
}
