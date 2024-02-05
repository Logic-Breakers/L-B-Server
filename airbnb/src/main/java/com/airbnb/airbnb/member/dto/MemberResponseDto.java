package com.airbnb.airbnb.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {

    private String username;
    private LocalDateTime birthDate;
    private String email;
    private String password;
//    private UserRoleEnum role;
    private LocalDateTime createdAt;
}
