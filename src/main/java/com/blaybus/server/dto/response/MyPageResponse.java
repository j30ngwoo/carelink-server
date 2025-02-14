package com.blaybus.server.dto.response;

import com.blaybus.server.domain.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
        private CareGiverType careGiverType;
        private boolean hasVehicle;
        private boolean completedDementiaTraining;
        private String address;
        private List<String> kind;
        private String introduction;
        private String profilePictureUrl;
        private int hourPay;
        private BankType bank;
        private String account;
        private String accountName;
        private String experience;

        public static CareGiverResponse createResponse(CareGiver careGiver) {

            return CareGiverResponse.builder()
                    .name(careGiver.getName())
                    .email(careGiver.getEmail())
                    .genderType(careGiver.getGenderType())
                    .createdAt(careGiver.getCreatedAt())
                    .contactNumber(careGiver.getContactNumber())
                    .certificateNumber(careGiver.getCertificateNumber())
                    .careGiverType(careGiver.getCareGiverType())
                    .hasVehicle(careGiver.isHasVehicle())
                    .completedDementiaTraining(careGiver.isCompletedDementiaTraining())
                    .address(careGiver.getAddress())
                    .kind(careGiver.getKind())
                    .introduction(careGiver.getIntroduction())
                    .profilePictureUrl(careGiver.getProfilePictureUrl())
                    .hourPay(careGiver.getHourPay())
                    .bank(careGiver.getBank())
                    .account(careGiver.getAccount())
                    .accountName(careGiver.getAccountName())
                    .experience(careGiver.getExperience())
                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static final class AdminResponse {
        private Center center;
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
                    .center(admin.getCenter()) // 소속 센터
                    .email(admin.getEmail()) // 이메일
                    .loginType(admin.getLoginType()) // 로그인 방식 (SNS 등)
                    .name(admin.getName()) // 이름
                    .createdAt(admin.getCreatedAt()) // 계정 생성일
                    .contactNumber(admin.getContactNumber()) // 연락처
                    .adminType(admin.getAdminType()) // 관리자 유형
                    .introduction(admin.getIntroduction()) // 한줄 소개
                    .profilePictureUrl(admin.getProfilePictureUrl() != null ?
                            admin.getProfilePictureUrl() : "기본_프로필_URL") // 기본 아이콘 설정
                    .build();
        }
    }

}
