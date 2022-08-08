package com.anynak.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryPostRequest {
    @NotNull(message = "лошара")
    @NotBlank(message = "лошара")
    private String text;

    Long diaryPostId;
}
