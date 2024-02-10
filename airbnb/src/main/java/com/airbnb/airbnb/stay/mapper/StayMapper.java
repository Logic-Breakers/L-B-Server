package com.airbnb.airbnb.stay.mapper;

import com.airbnb.airbnb.stay.dto.StayPatchDto;
import com.airbnb.airbnb.stay.dto.StaySumResponseDto;
import com.airbnb.airbnb.stay.dto.StayPostDto;
import com.airbnb.airbnb.stay.dto.StayResponseDto;
import com.airbnb.airbnb.image.entity.Image;
import com.airbnb.airbnb.stay.entity.Stay;
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
        stayResponseDto.setHouseName(stay.getHouseName());
        stayResponseDto.setInfo(stay.getInfo());
        stayResponseDto.setCountry(stay.getCountry());
        stayResponseDto.setAddress(stay.getAddress());
        stayResponseDto.setGuest(stay.getGuest());
        stayResponseDto.setStar(stay.getStar());
//        stayResponseDto.setGuestFavourite(stay.isGuestFavourite());
        stayResponseDto.setPlaceType(stay.getPlaceType());
        stayResponseDto.setPropertyType(stay.getPropertyType());
        stayResponseDto.setPrice(stay.getPrice());
        stayResponseDto.setBeds(stay.getBeds());
        stayResponseDto.setBedrooms(stay.getBedrooms());
        stayResponseDto.setBathrooms(stay.getBathrooms());
        stayResponseDto.setCreatedAt(stay.getCreatedAt());
        stayResponseDto.setHostId(stay.getMember().getId());
        if (stay.getImages() != null) {
            List<Image> images = stay.getImages();
            for (Image image : images) {
                stayResponseDto.addImageUrl(image);
            }
        }
        return stayResponseDto;
    }
    List<StayResponseDto> toStayResponseDtos (List<Stay> stays);

    default StaySumResponseDto toStaySumResponseDto (Stay stay) {
        if (stay == null)  {
            return null;
        }
        StaySumResponseDto staySumResponseDto = new StaySumResponseDto();
        staySumResponseDto.setId(stay.getId());
        staySumResponseDto.setHouseName(stay.getHouseName());
        staySumResponseDto.setCreatedAt(stay.getCreatedAt());
        staySumResponseDto.setHostId(stay.getMember().getId());
        return staySumResponseDto;
    }

    List<StaySumResponseDto> toStaySumResponseDtos (List<Stay> stays);
}
