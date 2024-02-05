package com.airbnb.airbnb.stay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StaySumResponseDto {
    private Long id;
    //private Long hostId;
    private String houseName;
    private LocalDateTime createdAt;
}
