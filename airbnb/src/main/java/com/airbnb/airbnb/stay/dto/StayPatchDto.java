package com.airbnb.airbnb.stay.dto;

import com.airbnb.airbnb.stay.entity.Stay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StayPatchDto {

    private String houseName;
    private String info;
    private String country;
    private String address;
    private String detailAddress;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long guest;
    private Double star;
//    private boolean guestFavourite;
    private Stay.PlaceType placeType;
    private Stay.PropertyType propertyType;
    private Long price;
    private Long beds;
    private Long bedrooms;
    private Long bathrooms;
    private List<Long> categories;

}
