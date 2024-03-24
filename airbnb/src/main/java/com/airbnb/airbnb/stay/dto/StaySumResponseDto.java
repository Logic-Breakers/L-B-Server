package com.airbnb.airbnb.stay.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class StaySumResponseDto {
    private Long houseId;
    private Long hostId;
    private String houseName;
    private LocalDateTime createdAt;
    private Double star;
    private Long price;
    private String category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String houseImageUrl;
}
