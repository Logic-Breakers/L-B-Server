package com.airbnb.airbnb.dto;

import com.airbnb.airbnb.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberPostDto {
    @NotBlank(message = "성명을 입력해야 합니다.")
    private String username;

    @NotNull(message = "생년월일을 입력해야 합니다.")
    private LocalDateTime birthDate;

    @NotBlank(message = "이메일을 입력해야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해야 합니다.")
    private String password;

//    @NotNull(message = "사용자 유형을 입력해야 합니다.")
//    private UserRoleEnum role;

    private LocalDateTime createdAt;

//    public static LocalDateTime stringBirthDateToLocalDateTime(String birthDate) {
//        DateTimeFormatter birthDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//       return LocalDateTime.parse(birthDate,birthDateFormatter);
//    }

}
