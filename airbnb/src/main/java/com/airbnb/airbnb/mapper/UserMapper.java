package com.airbnb.airbnb.mapper;

import com.airbnb.airbnb.dto.UserPatchDto;
import com.airbnb.airbnb.dto.UserPostDto;
import com.airbnb.airbnb.dto.UserResponseDto;
import com.airbnb.airbnb.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{
    User toUser (UserPostDto userPostDto);
    User toUser (UserPatchDto userPatchDto);
    UserResponseDto toUserResponseDto (User user);
}
