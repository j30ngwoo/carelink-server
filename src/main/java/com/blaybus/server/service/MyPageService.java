package com.blaybus.server.service;

import com.blaybus.server.common.exception.CareLinkException;
import com.blaybus.server.common.exception.ErrorCode;
import com.blaybus.server.domain.auth.Admin;
import com.blaybus.server.domain.auth.CareGiver;
import com.blaybus.server.dto.response.MyPageResponse.CareGiverResponse;
import com.blaybus.server.dto.response.MyPageResponse.AdminResponse;
import com.blaybus.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final MemberRepository memberRepository;

    @Transactional
    public CareGiverResponse getCareGiverInfo(Long memberId) {
        CareGiver careGiver = (CareGiver) memberRepository.findById(memberId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.USER_NOT_FOUND));

        log.info("요양 보호사 조회 성공: {}", memberId);

        return CareGiverResponse.createResponse(careGiver);
    }

    public AdminResponse getAdminInfo(Long memberId) {
        Admin admin = (Admin) memberRepository.findById(memberId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.USER_NOT_FOUND));
        log.info("관리자 조회 성공: {}", memberId);

        return AdminResponse.createResponse(admin);
    }
}
