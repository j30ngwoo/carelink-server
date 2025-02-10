package com.blaybus.server.dto.request;

import com.blaybus.server.domain.AdminType;
import com.blaybus.server.domain.CareGiverType;
import com.blaybus.server.domain.MemberRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public record AccountDto() {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class SignUpRequest {
        @NotNull(message = "멤버 타입은 필수입니다.")
        private MemberRole type;
        private MemberRequest memberRequest;
        private CareGiverRequest careGiverRequest;
        private AdminRequest adminRequest;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class LoginRequest {
        @NotNull(message = "이메일은 필수입니다.")
        private String email;
        @NotNull
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{4,10}$", message = "비밀번호는 영문, 숫자 조합 4 ~ 10자 이내로 입력해주세요")
        private String password;
    }

    @Getter
    public static class MemberRequest {
        @NotNull(message = "이메일은 필수입니다.")
        private String email;
        @NotNull
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{4,10}$", message = "비밀번호는 영문, 숫자 조합 4 ~ 10자 이내로 입력해주세요")
        private String password;
        @NotNull
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{4,10}$", message = "비밀번호는 영문, 숫자 조합 4 ~ 10자 이내로 입력해주세요")
        private String confirmPassword;
    }

    @Getter
    public static class CareGiverRequest {
        @NotNull(message = "이름은 필수입니다.")
        private String name;
        @NotNull(message = "연락처는 필수입니다.")
        @Pattern(regexp = "^010[0-9]{7,8}$", message = "연락처는 '010'으로 시작하고 뒤에 7 또는 8 자리 숫자로 작성해주세요.")
        private String contactNumber;
        @NotNull(message = "자격증번호는 필수입니다")
        private String certificateNumber;
        @NotNull(message = "자격증 종류은 필수입니다.")
        private CareGiverType careGiverType;
        @NotNull(message = "차량 소유 여부는 필수입니다.")
        private boolean hasVehicle;
        @NotNull(message = "치매교육 이수 여부는 필수입니다")
        private boolean completedDementiaTraining;
        @NotNull(message = "주소는 필수입니다.")
        private String address;
        private String profilePictureUrl;
        private String certificatedAt;
        private String majorExperience;
        private String introduction;
    }

    @Getter
    public static class AdminRequest {
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
}

