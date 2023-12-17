package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.Category;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StayPatchDto {

    @NotBlank(message = "설명은 공백이 아니어야 합니다.")
    private String info;
    @NotBlank(message = "국가를 입력해야 합니다.")
    private String country;
    @NotNull(message = "평균 금액을 입력해야 합니다.")
    private Long charge;
    @NotNull(message = "침대 개수를 입력해야 합니다.")
    private Long beds;
    @NotNull(message = "침실 개수를 입력해야 합니다.")
    private Long bedrooms;
    @NotNull(message = "화장실 개수를 입력해야 합니다.")
    private Long bathrooms;

}
