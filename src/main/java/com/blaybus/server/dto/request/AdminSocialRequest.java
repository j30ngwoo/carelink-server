package com.blaybus.server.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminSocialRequest {
    @NotNull(message = "이메일은 필수입니다.")
    private String email;
    @NotNull(message = "센터 id는 필수입니다.")
    private Long centerId;
    @NotNull(message = "이름는 필수입니다.")
    private String name;
    @NotNull(message = "연락처는 필수입니다.")
    @Pattern(regexp = "^010[0-9]{7,8}$", message = "연락처는 '010'으로 시작하고 뒤에 7 또는 8 자리 숫자로 작성해주세요.")
    private String contactNumber;
    private String introduction;
}
