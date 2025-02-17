package com.blaybus.server.dto.response;

import com.blaybus.server.domain.*;
import com.blaybus.server.domain.auth.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.blaybus.server.dto.response.CenterResponse.createCenterInfo;

public record MyPageResponse() {

    @Data
    @Builder
    @AllArgsConstructor
    public static final class CareGiverResponse {
        private String name;
        private String email;
        private GenderType genderType;
        private LocalDateTime createdAt;
        private String contactNumber;
        private String certificateNumber;
        private boolean hasVehicle;
        private boolean completedDementiaTraining;

        private String streetAddress;
        private String detailAddress;
        private String region;

        private List<Kindness> kinds;
        private String introduction;
        private String profilePictureUrl;
        private int hourPay;
        private BankType bank;
        private String account;
        private String accountName;

        private String careerPeriod; // "X년 Y개월" 형식
        private List<CareGiverCertificateResponse> certificates; // 보유 자격증 목록
        private List<ExperienceResponse> experiences; // 전체 경력 목록
        private List<MatchingConditionType> selectedConditions; // 매칭 필수 조건

        public static CareGiverResponse createResponse(CareGiver careGiver) {
            return CareGiverResponse.builder()
                    .name(careGiver.getName())
                    .email(careGiver.getEmail())
                    .genderType(careGiver.getGenderType())
                    .createdAt(careGiver.getCreatedAt())
                    .contactNumber(careGiver.getContactNumber())
                    .certificateNumber(careGiver.getCertificateNumber())
                    .hasVehicle(careGiver.isHasVehicle())
                    .completedDementiaTraining(careGiver.isCompletedDementiaTraining())

                    // 🚀 주소 관련 필드 분리
                    .streetAddress(careGiver.getStreetAddress())
                    .detailAddress(careGiver.getDetailAddress())
                    .region(careGiver.getRegion())

                    // 🚀 성격 유형 (List<Kindness>)
                    .kinds(careGiver.getKinds())

                    .introduction(careGiver.getIntroduction())
                    .profilePictureUrl(careGiver.getProfilePictureUrl())
                    .hourPay(careGiver.getHourPay())
                    .bank(careGiver.getBank())
                    .account(careGiver.getAccount())
                    .accountName(careGiver.getAccountName())

                    // 🚀 경력 계산 ("X년 Y개월")
                    .careerPeriod(careGiver.getCareerPeriod())

                    // 🚀 보유 자격증 (List<CareGiverCertificateResponse>)
                    .certificates(
                            careGiver.getCertificates().stream()
                                    .map(CareGiverCertificateResponse::fromEntity)
                                    .collect(Collectors.toList())
                    )

                    // 🚀 전체 경력 (List<ExperienceResponse>)
                    .experiences(
                            careGiver.getExperiences().stream()
                                    .map(ExperienceResponse::fromEntity)
                                    .collect(Collectors.toList())
                    )

                    // 🚀 매칭 필수 조건
                    .selectedConditions(careGiver.getSelectedConditions())

                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static final class AdminResponse {
        private CenterResponse.CenterInfo centerInfo;
        private String email;
        private LoginType loginType;
        private String name; // 이름
        private LocalDateTime createdAt;
        private String contactNumber; // 연락처
        private AdminType adminType;
        private String introduction; // 한줄 소개
        private String profilePictureUrl; // 프로필 사진 (없으면 기본 아이콘)

        public static AdminResponse createResponse(Admin admin) {
            return AdminResponse.builder()
                    .centerInfo(createCenterInfo(admin.getCenter())) // 소속 센터
                    .email(admin.getEmail()) // 이메일
                    .loginType(admin.getLoginType()) // 로그인 방식 (SNS 등)
                    .name(admin.getName()) // 이름
                    .createdAt(admin.getCreatedAt()) // 계정 생성일
                    .contactNumber(admin.getContactNumber()) // 연락처
                    .introduction(admin.getIntroduction()) // 한줄 소개
                    .profilePictureUrl(admin.getProfilePictureUrl() != null ?
                            admin.getProfilePictureUrl() : "기본_프로필_URL") // 기본 아이콘 설정
                    .build();
        }
    }

}
