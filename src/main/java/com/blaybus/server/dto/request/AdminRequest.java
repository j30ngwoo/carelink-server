package com.blaybus.server.dto.request;

import com.blaybus.server.domain.AdminType;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
@JsonTypeName("ADMIN")
public class AdminRequest extends SignUpRequest {
    @NotNull(message = "센터 id는 필수입니다.")
    private Long centerId;
    @NotNull(message = "연락처는 필수입니다.")
    @Pattern(regexp = "^010[0-9]{7,8}$", message = "연락처는 '010'으로 시작하고 뒤에 7 또는 8 자리 숫자로 작성해주세요.")
    private String contactNumber;
    @NotNull(message = "관리자 타입은 필수입니다.")
    private AdminType adminType;
    private String introduction;
    private String profilePictureUrl;
}
