package com.airbnb.airbnb.mapper;

import com.airbnb.airbnb.dto.StayPatchDto;
import com.airbnb.airbnb.dto.StayPostDto;
import com.airbnb.airbnb.dto.StayResponseDto;
import com.airbnb.airbnb.entity.Stay;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface StayMapper {
    Stay toStay (StayPostDto stayPostDto);
    Stay toStay (StayPatchDto stayPatchDto);
    StayResponseDto toStayResponseDto (Stay stay);
}
