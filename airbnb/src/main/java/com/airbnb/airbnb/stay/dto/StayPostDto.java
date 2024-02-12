package com.airbnb.airbnb.stay.dto;

import com.airbnb.airbnb.stay.entity.Stay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StayPostDto {

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String houseName;
    @NotBlank(message = "설명은 공백이 아니어야 합니다.")
    private String info;
    @NotBlank(message = "국가를 입력해야 합니다.")
    private String country;
    @NotBlank(message = "주소를 입력해야 합니다.")
    private String address;
    @NotNull(message = "사용 가능 인원을 입력해야 합니다.")
    private Long guest;
    private Double star;
//    @NotNull(message = "게스트 선호를 입력해야 합니다.")
//    private boolean guestFavourite;
    @NotNull(message = "숙소 유형을 입력해야 합니다.")
    private Stay.PlaceType placeType;
    @NotNull(message = "건물 유형을 입력해야 합니다.")
    private Stay.PropertyType propertyType;
    @NotNull(message = "평균 금액을 입력해야 합니다.")
    private Long price;
    @NotNull(message = "침대 개수를 입력해야 합니다.")
    private Long beds;
    @NotNull(message = "침실 개수를 입력해야 합니다.")
    private Long bedrooms;
    @NotNull(message = "화장실 개수를 입력해야 합니다.")
    private Long bathrooms;

}
