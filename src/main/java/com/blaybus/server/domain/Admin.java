package com.blaybus.server.domain;

import com.blaybus.server.dto.request.MyPageRequest.AdminUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Member {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id")
    private Center center; // 센터 정보

    @Column(name = "contact_number", nullable = false)
    private String contactNumber; // 연락처

    @Column(name = "introduction")
    private String introduction; // 한줄 소개

    @Column(name = "profile_picture_url")
    private String profilePictureUrl; // 프로필 사진 (없으면 기본 아이콘)

    @Column(name = "admin_type")
    @Enumerated(EnumType.STRING)
    private AdminType adminType;

    public Admin(String email, String password, LoginType loginType, String name,
                 Center center, String contactNumber,
                 String introduction, String profilePictureUrl, AdminType adminType) {
        super(email, password, Set.of(MemberRole.CAREGIVER), loginType, name);
        this.center = center;
        this.contactNumber = contactNumber;
        this.introduction = introduction;
        this.profilePictureUrl = profilePictureUrl;
        this.adminType = adminType;
    }

    // TODO: 비밀번호 확인.
    public void updateAdmin(Center center, AdminUpdateRequest request) {

        this.center = center;
        this.contactNumber = request.getContactNumber();
        this.introduction = request.getIntroduction();
        this.profilePictureUrl = request.getProfilePictureUrl();
        this.adminType = request.getAdminType();
    }
}
