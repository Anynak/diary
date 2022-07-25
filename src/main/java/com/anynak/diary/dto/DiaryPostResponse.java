package com.anynak.diary.dto;

import com.anynak.diary.entity.DiaryPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryPostResponse {

    private Long diaryPostId;
    private Long creation_UNIX_SEC;
    private String text;

    public DiaryPostResponse(DiaryPost diaryPost){
        this.diaryPostId=diaryPost.getDiaryPostId();
        this.creation_UNIX_SEC=diaryPost.getCreation_UNIX_SEC();
        this.text=diaryPost.getText();
    }
}
