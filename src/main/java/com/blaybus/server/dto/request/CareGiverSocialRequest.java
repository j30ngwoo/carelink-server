package com.blaybus.server.dto.request;

import com.blaybus.server.domain.auth.GenderType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CareGiverSocialRequest {
    @NotNull(message = "이메일은 필수입니다.")
    private String email;

    @NotNull
    private GenderType genderType;

    @NotNull
    private String name;
    @Pattern(regexp = "^010[0-9]{7,8}$", message = "연락처는 '010'으로 시작하고 뒤에 7 또는 8 자리 숫자로 작성해주세요.")
    private String contactNumber;
}
