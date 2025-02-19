package com.blaybus.server.dto.response;

import com.blaybus.server.domain.Center;
import com.blaybus.server.domain.Day;
import com.blaybus.server.domain.auth.GenderType;
import com.blaybus.server.domain.senior.*;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class CenterResponse {

    private List<CenterInfo> centers;
    private List<SeniorInfo> seniors;

    @Data
    @Builder
    @AllArgsConstructor
    public static class CenterInfo {
        private Long id;
        private String centerName; // 센터 이름
        private boolean hasBathVehicle; // 목욕 차량 소유 여부
        private LocalDateTime createdAt; // 설립년도(이걸로 운영기간 확인)
        private String address; // 주소
        private String centerRating; // 센터 등급
        private String tel;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class SeniorInfo {
        private Long id;
        private String name;
        private LocalDate birthDate;
        private GenderType genderType;
        private CareLevel careLevel;
        private String address;
        private String contactInfo;
        private String guardianContact;
        private VisitType visitType;
        private Set<Day> visitDays; // 타입 수정
        private Set<LocalDate> visitDates;
        private LocalTime careStartTime;
        private LocalTime careEndTime;
        private MobilityLevel mobilityLevel;
        private EatingLevel eatingLevel;
        private Set<MedicalCondition> medicalConditions;
        private Set<RequiredService> requiredServices;
        private String additionalNotes;
    }

    public static CenterInfo createCenterInfo(Center center) {
        return CenterInfo.builder()
                .id(center.getId())
                .centerName(center.getCenterName())
                .hasBathVehicle(center.isHasBathVehicle())
                .createdAt(center.getCreatedAt())
                .address(center.getAddress())
                .centerRating(center.getCenterRating())
                .tel(center.getTel())
                .build();
    }

    public static CenterResponse createListResponse(List<Center> centerList) {
        List<CenterInfo> centerResponses = centerList.stream()
                .map(CenterResponse::createCenterInfo)
                .collect(Collectors.toList());

        return CenterResponse.builder()
                .centers(centerResponses)
                .build();
    }

    // 효주 추가
    public static SeniorInfo createSeniorInfo(Senior senior) {
        return SeniorInfo.builder()
                .id(senior.getId())
                .name(senior.getName())
                .birthDate(senior.getBirthDate())
                .genderType(senior.getGenderType())
                .careLevel(senior.getCareLevel())
                .address(senior.getAddress())
                .contactInfo(senior.getContactInfo())
                .guardianContact(senior.getGuardianContact())
                .visitType(senior.getVisitType())
                .visitDays(senior.getVisitDays())
                .visitDates(senior.getVisitDates())
                .careStartTime(senior.getCareStartTime())
                .careEndTime(senior.getCareEndTime())
                .mobilityLevel(senior.getMobilityLevel())
                .eatingLevel(senior.getEatingLevel())
                .medicalConditions(senior.getMedicalConditions())
                .requiredServices(senior.getRequiredServices())
                .additionalNotes(senior.getAdditionalNotes())
                .build();
    }

    public static CenterResponse createSeniorListResponse(List<Senior> seniorList) {
        List<SeniorInfo> seniorResponses = seniorList.stream()
                .map(CenterResponse::createSeniorInfo)
                .collect(Collectors.toList());

        return CenterResponse.builder()
                .seniors(seniorResponses)
                .build();
    }
    // 효주 추가 끝
}