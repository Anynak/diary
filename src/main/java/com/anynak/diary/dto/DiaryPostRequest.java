package com.anynak.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryPostRequest {
    @NotNull(message = "{postForm.text.required}")
    @NotBlank(message = "{postForm.text.blank}")
    @Length(max = 10_000, message = "{postForm.text.length}")
    private String text;

    Long diaryPostId;
}
