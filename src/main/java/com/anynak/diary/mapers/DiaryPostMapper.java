package com.anynak.diary.mapers;

import com.anynak.diary.dto.DiaryPostRequest;
import com.anynak.diary.dto.DiaryPostResponse;
import com.anynak.diary.entity.DiaryPost;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiaryPostMapper {
    DiaryPostMapper INSTANCE = Mappers.getMapper(DiaryPostMapper.class);

    DiaryPostResponse toDiaryPostResponse(DiaryPost diaryPost);

    List<DiaryPostResponse> toDiaryPostResponse(List<DiaryPost> diaryPosts);

    DiaryPost toDiaryPost(DiaryPostRequest diaryPostRequest);
}
