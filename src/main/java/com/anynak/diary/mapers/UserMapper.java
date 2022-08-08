package com.anynak.diary.mapers;

import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.dto.UserResponse;
import com.anynak.diary.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toUserResponse(User user);

    User toUser(UserRequest userRequest);

}
