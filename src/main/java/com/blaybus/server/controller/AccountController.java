package com.blaybus.server.controller;

import com.blaybus.server.dto.ResponseFormat;
import com.blaybus.server.dto.request.*;
import com.blaybus.server.dto.response.JwtDto.JwtResponse;
import com.blaybus.server.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tags(value = @Tag(name = "AccountController", description = "Certification and Accreditation"))
public class AccountController {

    private final AccountService accountService;

    /**
     * 요양 보호사 회원가입
     * @param signUpRequest
     * @return
     */
    @PostMapping(value = "/sign-up/member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "요양보호사 자사 회원가입")
    public ResponseEntity<ResponseFormat<Long>> signUpMember(
            @Valid @RequestPart("signUpRequest") SignUpRequest signUpRequest,
            @RequestPart MultipartFile file) {
        log.info("member created");
        Long createdMemberId = accountService.joinMember(signUpRequest, file);

        ResponseFormat<Long> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "요양보호사가 성공적으로 등록되었습니다. (id: " + createdMemberId + ")",
                createdMemberId
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 요양보호사 소셜 로그인을 위한 정보 수정
     */
    @PutMapping(value = "/sign-up/member", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "요양보호사 소셜 로그인시 정보 수정 필드")
    public ResponseEntity<ResponseFormat<Long>> updateMemberInfo(
            @Valid @RequestPart("careGiverSocialRequest") CareGiverSocialRequest careGiverSocialRequest,
            @RequestPart MultipartFile file) {
        Long saveMemberInfoId = accountService.saveMemberInfo(careGiverSocialRequest, file);

        ResponseFormat<Long> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "요양보호사 정보가 성공적으로 등록되었습니다. (id: " + saveMemberInfoId + ")",
                saveMemberInfoId
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 프로필 등록
     * JSON과 프로필 사진 파일을 함께 받음.
     */
    @PutMapping(value = "/profile/member", consumes = {"multipart/form-data"})
    @Operation(summary = "프로필 등록")
    public ResponseEntity<ResponseFormat<Long>> signUpMember(
            @Valid @RequestPart("careGiverRequest") CareGiverRequest careGiverRequest,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture) {
        Long createdId = accountService.updateCareGiver(careGiverRequest, profilePicture);

        ResponseFormat<Long> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "멤버의 프로필이 성공적으로 등록되었습니다. (id: " + createdId + ")",
                createdId
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 관리자 자사 회원가입
     */
    @PostMapping(value = "/sign-up/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "관리자 자사 회원가입")
    public ResponseEntity<ResponseFormat<Long>> signUpAdmin(
            @Valid @RequestPart("adminRequest") AdminRequest adminRequest,
            @RequestPart(required = false) MultipartFile file) {
        log.info("Admin created");
        Long createdId = accountService.joinAdmin(adminRequest, file);

        ResponseFormat<Long> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "관리자가 성공적으로 등록되었습니다. (email: " + createdId + ")",
                createdId
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 관리자 소셜로그인 회원가입을 위한 정보 수정
     */
    @PutMapping(value = "/sign-up/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "요양보호사 소셜 로그인시 정보 수정 필드")
    public ResponseEntity<ResponseFormat<Long>> updateAdminInfo(
            @Valid @RequestPart("adminSocialRequest") AdminSocialRequest adminSocialRequest,
            @RequestPart MultipartFile file) {
        Long createdId = accountService.saveAdminInfo(adminSocialRequest, file);

        ResponseFormat<Long> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "요양보호사 정보가 성공적으로 등록되었습니다. (email: " + createdId + ")",
                createdId
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<ResponseFormat<JwtResponse>> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("member sign in");
        JwtResponse loginResult = accountService.loginMember(loginRequest);

        ResponseFormat<JwtResponse> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "성공적으로 로그인 되었습니다.",
                loginResult
        );

        return ResponseEntity.ok(response);
    }
}
