package com.blaybus.server.service;

import com.blaybus.server.common.exception.CareLinkException;
import com.blaybus.server.common.exception.ErrorCode;
import com.blaybus.server.domain.auth.Admin;
import com.blaybus.server.domain.auth.CareGiver;
import com.blaybus.server.domain.Center;
import com.blaybus.server.dto.request.MyPageRequest.MemberUpdateRequest;
import com.blaybus.server.dto.request.MyPageRequest.AdminUpdateRequest;
import com.blaybus.server.repository.CenterRepository;
import com.blaybus.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageWriteService {
    private final MemberRepository memberRepository;
    private final CenterRepository centerRepository;

    // TODO: 업데이트 기획 후 수정
    public Long updateMemberInfo(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        CareGiver careGiver = (CareGiver) memberRepository.findById(memberId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.USER_NOT_FOUND));

        log.info("요양 보호사 조회 성공: {}", memberId);
//        careGiver.updateCareGiverInfo(memberUpdateRequest);
        memberRepository.save(careGiver);
        log.info("요양 보호사 수정 성공: {}", memberId);

        return careGiver.getId();
    }

    // TODO: 업데이트 기획 후 수정
    public Long updateAdminInfo(Long memberId, AdminUpdateRequest adminUpdateRequest) {
        Admin admin = (Admin) memberRepository.findById(memberId)
                .orElseThrow(() -> new CareLinkException(ErrorCode.USER_NOT_FOUND));
        log.info("관리자 조회 성공: {}", memberId);

        Center newCenter = centerRepository.findById(adminUpdateRequest.getCenterId())
                .orElseThrow(() -> new CareLinkException(ErrorCode.CENTER_NOT_FOUND));
        log.info("센터 조회 성공: {}", newCenter.getId());

        admin.updateAdmin(newCenter, adminUpdateRequest);
        memberRepository.save(admin);
        log.info("관리자 수정 성공: {}", memberId);

        return admin.getId();
    }
}
