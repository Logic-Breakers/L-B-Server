package com.airbnb.airbnb.mapper;

import com.airbnb.airbnb.dto.StayPatchDto;
import com.airbnb.airbnb.dto.StayPostDto;
import com.airbnb.airbnb.dto.StayResponseDto;
import com.airbnb.airbnb.entity.Image;
import com.airbnb.airbnb.entity.Stay;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface StayMapper {
    Stay toStay (StayPostDto stayPostDto);
    Stay toStay (StayPatchDto stayPatchDto);
    default StayResponseDto toStayResponseDto (Stay stay) {
        if (stay == null)  {
            return null;
        }
        StayResponseDto stayResponseDto = new StayResponseDto();
        stayResponseDto.setId(stay.getId());
        stayResponseDto.setInfo(stay.getInfo());
        stayResponseDto.setCountry(stay.getCountry());
        stayResponseDto.setAddress(stay.getAddress());
        stayResponseDto.setGuest(stay.getGuest());
        stayResponseDto.setStar(stay.getStar());
        stayResponseDto.setGuestFavourite(stay.isGuestFavourite());
        stayResponseDto.setPlaceType(stay.getPlaceType());
        stayResponseDto.setPropertyType(stay.getPropertyType());
        stayResponseDto.setPrice(stay.getPrice());
        stayResponseDto.setBeds(stay.getBeds());
        stayResponseDto.setBedrooms(stay.getBedrooms());
        stayResponseDto.setBathrooms(stay.getBathrooms());
        if (stay.getImages() != null) {
            List<Image> images = stay.getImages();
            for (Image image : images) {
                stayResponseDto.addImageUrl(image);
            }
        }
        return stayResponseDto;
    }
    List<StayResponseDto> toStayResponseDtos (List<Stay> stays);
}
