package com.anynak.diary.dto;

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

}
