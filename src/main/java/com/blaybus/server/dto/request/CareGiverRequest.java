package com.blaybus.server.dto.request;

import com.blaybus.server.domain.auth.BankType;
import com.blaybus.server.domain.auth.Kindness;
import com.blaybus.server.domain.auth.MatchingConditionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CareGiverRequest {

    @NotNull(message = "이메일은 필수입니다.")
    private String email;

    @NotNull(message = "자격증번호는 필수입니다")
    private String certificateNumber; // 요양 보호사 자격증 번호
    @Schema(description = "보유 자격증 목록",
            type = "array",
            example = "[{\"careGiverType\": \"SOCIALWORKER\", \"certificatedNumber\": \"1231311\"}]")
    private List<CareGiverCertificate> certificates; // 보유 자격증

    @NotNull(message = "치매교육 이수 여부는 필수입니다")
    private boolean completedDementiaTraining;

    @NotNull(message = "도로명 주소는 필수입니다.")
    private String streetAddress; // 도로명 주소 (예: 서울특별시 성동구 금호동4가 123)
    @NotNull(message = "상세 주소는 필수입니다.")
    private String detailAddress; // 상세 주소 (예: 101호)

    @NotNull(message = "차량 소유 여부는 필수입니다.")
    private boolean hasVehicle;

    @NotNull(message = "선생님을 나타낼 수 있는 단어는 필수입니다.")
    @Schema(description = "선생님을 나타낼 수 있는 특성 목록",
            example = "[\"DILIGENT\", \"FRIENDLY\", \"PATIENT\"]",
            type = "array", allowableValues = {
            "DILIGENT", "FUNNY", "METICULOUS", "FRIENDLY", "WARM",
            "SENSITIVE", "RESPONSIBLE", "EXPERIENCED", "DELICATE",
            "THOUGHTFUL", "CALM", "PATIENT"
    })
    private List<Kindness> kinds; // 선생님을 나타낼 수 있는 단어

    @Schema(description = "신입/경력 여부", example = "true")
    private boolean isMajorExperience;

    @Schema(description = "경력 목록",
            type = "array",
            example = "[{\"centerId\": 123, \"certificatedAt\": \"2020-01-01\", \"endCertificatedAt\": \"2023-12-31\", \"assignedTask\": \"요양보호사\"}]")
    private List<Experience> experiences;

    private String introduction; // 한줄 소개

    @NotNull(message = "시급은 필수 입력 값입니다.")
    @Schema(description = "시급 (단위: 원)", example = "10000")
    private int hourPay;

    @NotNull(message = "은행 정보는 필수 입력 값입니다.")
    @Schema(description = "은행", example = "KB_KOOKMIN", allowableValues = {
            "KB_KOOKMIN", "SHINHAN", "WOORI", "HANA", "NH_NONGHYUP", "IBK", "KAKAO", "TOSS",
            "SC", "CITI", "DAEGU", "BUSAN", "GWANGJU", "JEONBUK", "KYONGNAM", "SUHYUP",
            "SAEMAUL", "SHINHYEOP", "POST", "KEB_HANA"
    })
    private BankType bank;
    @NotNull(message = "계좌번호는 필수 입력 값입니다.")
    @Schema(description = "계좌번호 (숫자만 입력)", example = "110123456789")
    private String account;
    @NotNull(message = "예금주는 필수 입력 값입니다.")
    @Schema(description = "예금주 이름", example = "홍길동")
    private String accountName;

    @NotNull(message = "필수 조건은 최소 2개 이상 선택해야 합니다.")
    @Size(min = 2, message = "필수조건은 2개 이상 선택해야 합니다.")
    @Schema(description = "사용자가 선택한 필수 조건 리스트",
            type = "array",
            example = "[\"WORK_LOCATION\", \"WORK_HOURS\"]",
            allowableValues = {"WORK_LOCATION", "WORK_DURATION", "WORK_DAYS", "WORK_HOURS", "PREFERRED_GENDER", "DESIRED_HOURLY_WAGE"})
    private List<MatchingConditionType> selectedConditions;
}
