package com.blaybus.server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CareGiver extends Member {

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "contact_number", nullable = false)
    private String contactNumber; // 연락처

    @Column(name = "certificate_number", nullable = false)
    private String certificateNumber; // 자격증 번호

    @Column(name = "certificate_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CareGiverType careGiverType; // 요양보호사, 사회복지사 등

    @Column(name = "has_vehicle")
    private boolean hasVehicle; // 차량 소유 여부

    @Column(name = "completed_dementia_training")
    private boolean completedDementiaTraining; // 치매교육 이수 여부

    @Column(name = "address", nullable = false)
    private String address; // 주소

    @Column(name = "certificated_at")
    private String certificatedAt; // 날짜로 경력을 확인(선택 입력: 경력 기간)

    @Column(name = "major_experience")
    private String majorExperience; // 주요 경력

    @Column(name = "introduction")
    private String introduction; // 한줄 소개

    @Column(name = "profile_picture_url")
    private String profilePictureUrl; // 프로필 사진

    public CareGiver(String email, String password, LoginType loginType, String name,
                     String contactNumber, String certificateNumber, CareGiverType careGiverType,
                     boolean hasVehicle, boolean completedDementiaTraining, String address,
                     String certificatedAt, String majorExperience, String introduction,
                     String profilePictureUrl) {
        super(email, password, Set.of(MemberRole.CAREGIVER), loginType);
        this.name = name;
        this.contactNumber = contactNumber;
        this.certificateNumber = certificateNumber;
        this.careGiverType = careGiverType;
        this.hasVehicle = hasVehicle;
        this.completedDementiaTraining = completedDementiaTraining;
        this.address = address;
        this.certificatedAt = certificatedAt;
        this.majorExperience = majorExperience;
        this.introduction = introduction;
        this.profilePictureUrl = profilePictureUrl;
    }
}