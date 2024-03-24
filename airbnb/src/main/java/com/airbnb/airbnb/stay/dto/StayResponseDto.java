package com.airbnb.airbnb.stay.dto;

import com.airbnb.airbnb.image.entity.Image;
import com.airbnb.airbnb.stay.entity.Stay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class StayResponseDto {

    private Long houseId;
    private Long hostId;
    private String houseName;
    private String hostName;
    private String info;
    private String country;
    private String address;
    private String detailAddress;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long guest;
    private Double star;
    private String category;
//    private boolean guestFavourite;
    private Stay.PlaceType placeType;
    private Stay.PropertyType propertyType;
    private Long price;
    private Long beds;
    private Long bedrooms;
    private Long bathrooms;
    private List<String> houseImageUrls = new ArrayList<>();
    private LocalDateTime createdAt;

    public void addImageUrl(Image image) {
        houseImageUrls.add(image.getImageUrl());
    }
}
