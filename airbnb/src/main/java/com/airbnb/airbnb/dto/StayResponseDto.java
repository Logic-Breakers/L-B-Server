package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.Category;
import com.airbnb.airbnb.entity.Stay;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StayResponseDto {

    private Long id;
    private String info;
    private Long guest;
    private Double star;
    private Stay.PropertyType propertyType;
    private String country;
    private Long charge;
    private Long beds;
    private Long bedrooms;
    private Long bathrooms;
}
