package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.Image;
import com.airbnb.airbnb.entity.Stay;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class StayResponseDto {

    private Long id;
    private String info;
    private String country;
    private String address;
    private Long guest;
    private Double star;
    private boolean guestFavourite;
    private Stay.PlaceType placeType;
    private Stay.PropertyType propertyType;
    private Long price;
    private Long beds;
    private Long bedrooms;
    private Long bathrooms;
    private List<String> imageUrls = new ArrayList<>();

    public void addImageUrl(Image image) {
        imageUrls.add(image.getImageUrl());
    }
}
