package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {

    private String username;
    private LocalDateTime birthDate;
    private String email;
    private String password;
    private UserRoleEnum role;
    private LocalDateTime createdAt;
}
