package com.blaybus.server.dto.request;

import com.blaybus.server.domain.auth.AdminType;
import com.blaybus.server.domain.auth.BankType;
import com.blaybus.server.domain.auth.CareGiverType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public record MyPageRequest() {
    @Data
    @Builder
    @AllArgsConstructor
    public static final class MemberUpdateRequest {
        private String contactNumber;
        private String certificateNumber;
        private CareGiverType careGiverType;
        private boolean hasVehicle;
        private boolean completedDementiaTraining;
        private String address;
        private String introduction;
        private List<String> kind;
        private String profilePictureUrl;
        private BankType bank;
        private String account;
        private String accountName;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static final class AdminUpdateRequest {
        Long centerId;
        private String contactNumber; // 연락처
        private String introduction; // 한줄 소개
        private String profilePictureUrl; // 프로필 사진 (없으면 기본 아이콘)
        private AdminType adminType;
    }
}
