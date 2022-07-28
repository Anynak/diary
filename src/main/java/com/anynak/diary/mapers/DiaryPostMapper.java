package com.anynak.diary.mapers;

import com.anynak.diary.dto.DiaryPostResponse;
import com.anynak.diary.entity.DiaryPost;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiaryPostMapper {
    DiaryPostMapper INSTANCE = Mappers.getMapper(DiaryPostMapper.class);
    DiaryPostResponse toDiaryPostResponse(DiaryPost diaryPost);
    DiaryPost toDiaryPost(DiaryPostResponse diaryPostResponse);
}
