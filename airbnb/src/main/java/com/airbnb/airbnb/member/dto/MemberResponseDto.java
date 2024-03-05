package com.airbnb.airbnb.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDto {
    private Long id;
    private String username;
    private LocalDateTime birthDate;
    private String country;
    private String phone;
    private String email;
//    private UserRoleEnum role;
    private LocalDateTime createdAt;
}
