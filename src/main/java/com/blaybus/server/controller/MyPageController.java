package com.blaybus.server.controller;

import com.blaybus.server.dto.ResponseFormat;
import com.blaybus.server.dto.response.MyPageResponse.CareGiverResponse;
import com.blaybus.server.dto.response.MyPageResponse.AdminResponse;
import com.blaybus.server.service.MyPageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
@Tags(value = @Tag(name = "MyPageController", description = "Get My Info"))
public class MyPageController {

    private final MyPageService myPageService;

    /**
     * 내 정보 조회(요양보호사)
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ResponseFormat<CareGiverResponse>> lookUpMember(
            @PathVariable Long memberId) {
        log.info("memberId: {} 내정보 조회", memberId);
        CareGiverResponse response = myPageService.getCareGiverInfo(memberId);

        ResponseFormat<CareGiverResponse> responseFormat = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "요양보호사 정보가 조회되었습니다.",
                response
        );

        return ResponseEntity.ok(responseFormat);
    }


    /**
     * 내 정보 조회(관리자)
     */
    @GetMapping("/admin/{memberId}")
    public ResponseEntity<ResponseFormat<AdminResponse>> lookUpAdmin(
            @PathVariable Long memberId) {
        AdminResponse response = myPageService.getAdminInfo(memberId);

        ResponseFormat<AdminResponse> responseFormat = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "관리자 정보가 조회되었습니다.",
                response
        );

        return ResponseEntity.ok(responseFormat);
    }

    /**
     * 내 정보 수정 -> 이거는 AccountService에 있는데 수정 값은 아니니까.
     */

    /**
     * 내 일지 조회(요양보호사 용)
     */

    /**
     * 일지 조회(관리자 용)
     */


}
