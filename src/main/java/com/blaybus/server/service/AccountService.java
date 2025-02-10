package com.blaybus.server.service;

import com.blaybus.server.common.Validator;
import com.blaybus.server.common.exception.CareLinkException;
import com.blaybus.server.common.exception.ErrorCode;
import com.blaybus.server.config.security.jwt.JwtUtils;
import com.blaybus.server.domain.*;
import com.blaybus.server.dto.request.AccountDto.*;
import com.blaybus.server.dto.request.AccountDto.LoginRequest;
import com.blaybus.server.dto.request.AccountDto.SignUpRequest;
import com.blaybus.server.dto.response.JwtDto.JwtResponse;
import com.blaybus.server.repository.CenterRepository;
import com.blaybus.server.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final Validator validator;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final CenterRepository centerRepository;

    public String joinMember(SignUpRequest request) {
        log.info("join Member");

        validateSignupRequest(request);

        Member member = (request.getType() == MemberRole.CAREGIVER) ?
                createCareGiver(request.getCareGiverRequest(), request.getMemberRequest()) :
                createAdmin(request.getAdminRequest(), request.getMemberRequest());

        memberRepository.save(member);

        return member.getEmail();
    }

    public JwtResponse loginMember(LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (!memberRepository.existsByEmail(username)) {
            throw new CareLinkException(ErrorCode.USER_NOT_FOUND);
        }

        log.info("validateUser: {}, {}", username, password);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );


        final String jwtToken = jwtUtils.generateJwtToken(authentication);
        final String refreshToken = jwtUtils.generateRefreshToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return JwtResponse.createJwtResponse(jwtToken, refreshToken, userDetails.getUsername(), roles);
    }

    private void validateSignupRequest(SignUpRequest request) {
        if (!validator.checkPassword(request.getMemberRequest().getPassword(), request.getMemberRequest().getConfirmPassword())) {
            throw new CareLinkException(ErrorCode.INVALID_CREDENTIALS);
        }

        if (!validator.checkRole(request.getType())) {
            throw new CareLinkException(ErrorCode.INVALID_TYPE);
        }

        if (memberRepository.existsByEmail(request.getMemberRequest().getEmail())) {
            throw new CareLinkException(ErrorCode.USER_ALREADY_EXISTS);
        }
    }

    private CareGiver createCareGiver(CareGiverRequest caregiverInfo, MemberRequest memberRequest) {
        validateCareGiverRequest(caregiverInfo);

        return new CareGiver(
                memberRequest.getEmail(),
                passwordEncoder.encode(memberRequest.getPassword()),
                LoginType.LOCAL,
                caregiverInfo.getName(),
                caregiverInfo.getContactNumber(),
                caregiverInfo.getCertificateNumber(),
                caregiverInfo.getCareGiverType(),
                caregiverInfo.isHasVehicle(),
                caregiverInfo.isCompletedDementiaTraining(),
                caregiverInfo.getAddress(),
                caregiverInfo.getCertificatedAt(),
                caregiverInfo.getMajorExperience(),
                caregiverInfo.getIntroduction(),
                caregiverInfo.getProfilePictureUrl()
        );
    }

    private void validateCareGiverRequest(CareGiverRequest caregiverInfo) {
        String certificateNumber = caregiverInfo.getCertificateNumber();
        CareGiverType careGiverType = caregiverInfo.getCareGiverType();

        if (careGiverType == CareGiverType.CAREWORKER) { // 🚀 1️⃣ 요양보호사: 자격증 번호 형식 검증
            if (!certificateNumber.matches("^[0-9]{9}[A-Z]$")) {
                throw new CareLinkException(ErrorCode.INVALID_CERTIFICATE_NUMBER_CAREWORKER);
            }
        } else if (careGiverType == CareGiverType.SOCIALWORKER) { // 🚀 2️⃣ 사회복지사: 자격증 번호 형식 검증
            if (!certificateNumber.matches("^[12]-[0-9]{5,6}$")) {
                throw new CareLinkException(ErrorCode.INVALID_CERTIFICATE_NUMBER_SOCIALWORKER);
            }
        } else if (careGiverType == CareGiverType.NURSINGASSISTANT) { // 🚀 3️⃣ 간호조무사: 자격증 번호 형식 검증
            if (!certificateNumber.matches("^[12]-[0-9]{5,6}$")) {
                throw new CareLinkException(ErrorCode.INVALID_CERTIFICATE_NUMBER_NURSINGASSISTANT);
            }
        } else { // 🚀 4️⃣ 알 수 없는 자격증 종류
            throw new CareLinkException(ErrorCode.INVALID_CERTIFICATE_TYPE);
        }
    }


    private Admin createAdmin(AdminRequest adminInfo, MemberRequest memberRequest) {
        // 1️⃣ 센터 존재 여부 확인
        Center center = centerRepository.findById(adminInfo.getCenterId())
                .orElseThrow(() -> new CareLinkException(ErrorCode.CENTER_NOT_FOUND));

        // 2️⃣ Admin 객체 생성
        return new Admin(
                memberRequest.getEmail(),
                passwordEncoder.encode(memberRequest.getPassword()),
                LoginType.LOCAL,
                center,
                adminInfo.getContactNumber(),
                adminInfo.getIntroduction(),
                adminInfo.getProfilePictureUrl(),
                adminInfo.getAdminType()
        );
    }


}
