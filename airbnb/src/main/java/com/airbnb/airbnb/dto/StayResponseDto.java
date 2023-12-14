package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StayResponseDto {

    private Long id;
    private String info;
//    private Category category;
    private String country;
    private Long charge;
    private Long beds;
    private Long bedrooms;
    private Long bathrooms;
}
