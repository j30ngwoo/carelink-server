package com.blaybus.server.domain;

import com.blaybus.server.dto.request.CareGiverRequest;
import com.blaybus.server.dto.request.SignUpRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CareGiver extends Member {

    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Column(name = "contact_number")
    private String contactNumber; // 연락처

    @Column(name = "certificate_number")
    private String certificateNumber; // 자격증 번호

    @Column(name = "certificate_type")
    @Enumerated(EnumType.STRING)
    private CareGiverType careGiverType; // 요양보호사, 사회복지사 등

    @Column(name = "has_vehicle")
    private boolean hasVehicle; // 차량 소유 여부

    @Column(name = "completed_dementia_training")
    private boolean completedDementiaTraining; // 치매교육 이수 여부

    @Column(name = "address")
    private String address; // 주소

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "caregiver_kinds", joinColumns = @JoinColumn(name = "caregiver_id"))
    @Column(name = "kind")
    private List<String> kind; // 선생님을 나타낼 수 있는 단어 목록

    @Column(name = "certificated_at")
    private LocalDate certificatedAt; // 시작 경력 (yyyy-MM-dd)

    @Column(name = "end_certificated_at")
    private LocalDate endCertificatedAt; // 마지막 경력 (yyyy-MM-dd)

    @Column(name = "major_experience")
    private String majorExperience; // 주요 경력

    @Column(name = "introduction")
    private String introduction; // 한줄 소개

    @Column(name = "profile_picture_url")
    private String profilePictureUrl; // 프로필 사진

    @Column(name = "hour_pay")
    private int hourPay;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank")
    private BankType bank;
    private String account;
    private String accountName;

    public CareGiver(String email, String password,
                     LoginType loginType, GenderType genderType, String name) {
        super(email, password, Set.of(MemberRole.CAREGIVER), loginType, name);
        this.genderType = genderType;
    }

    public void updateCareGiverInfo(CareGiverRequest request) {
        this.certificateNumber = request.getContactNumber();
        this.careGiverType = request.getCareGiverType();
        this.address = request.getAddress();
        this.hasVehicle = request.isHasVehicle();
        this.completedDementiaTraining = request.isCompletedDementiaTraining();
        this.contactNumber = request.getContactNumber();

        this.kind = request.getKind();
        this.certificatedAt = request.getCertificatedAt();
        this.endCertificatedAt = request.getEndCertificatedAt();
        this.majorExperience = request.getMajorExperience();
        this.introduction = request.getIntroduction();
        this.hourPay = request.getHourPay();
        this.bank = request.getBank();
        this.account = request.getAccount();
        this.accountName = request.getAccountName();
    }

    /**
     *
     * @return X년 Y개월 로 변환
     */
    public String getCareerDuration() {
        Period period = Period.between(certificatedAt, endCertificatedAt);

        int years = period.getYears();
        int months = period.getMonths();

        return years + "년 " + months + "개월";
    }
}