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

    private Long id;
    private Long hostId;
    private String houseName;
    private String info;
    private String country;
    private String address;
    private Long guest;
    private Double star;
//    private boolean guestFavourite;
    private Stay.PlaceType placeType;
    private Stay.PropertyType propertyType;
    private Long price;
    private Long beds;
    private Long bedrooms;
    private Long bathrooms;
    private List<String> imageUrls = new ArrayList<>();
    private LocalDateTime createdAt;

    public void addImageUrl(Image image) {
        imageUrls.add(image.getImageUrl());
    }
}
