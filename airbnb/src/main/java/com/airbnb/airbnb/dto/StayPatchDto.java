package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.Stay;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StayPatchDto {

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
    private List<Long> categories;

}
