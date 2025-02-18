package com.blaybus.server.controller;

import com.blaybus.server.domain.journal.CareJournal;
import com.blaybus.server.dto.request.CareJournalRequest;
import com.blaybus.server.service.CareJournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/care-journal")
@RequiredArgsConstructor
public class CareJournalController {

    private final CareJournalService careJournalService;

    // 케어일지 생성
    @Operation(summary = "케어일지 생성", description = "새로운 케어일지를 생성합니다.")
    @ApiResponse(responseCode = "200", description = "케어일지 생성 성공")
    @PostMapping
    public ResponseEntity<CareJournal> createCareJournal(@RequestBody CareJournalRequest request) {
        CareJournal careJournal = careJournalService.createCareJournal(request);
        return ResponseEntity.ok(careJournal);
    }

    // 멤버 ID와 케어일지 ID로 케어일지 조회
    @Operation(summary = "특정 멤버의 케어일지 조회", description = "멤버 ID와 케어일지 ID를 통해 특정 케어일지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "케어일지를 찾을 수 없음")
    })
    @GetMapping("/member/{memberId}/care-journal/{careJournalId}")
    public ResponseEntity<CareJournal> getCareJournalByMemberIdAndCareJournalId(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "케어일지 ID") @PathVariable Long careJournalId) {
        return careJournalService.getCareJournalByMemberIdAndCareJournalId(memberId, careJournalId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 케어일지 단건 조회
    @Operation(summary = "케어일지 단건 조회", description = "케어일지 ID를 통해 특정 케어일지를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "케어일지를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CareJournal> getCareJournalById(
            @Parameter(description = "케어일지 ID") @PathVariable Long id) {
        return careJournalService.getCareJournalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 케어일지 업데이트
    @Operation(summary = "케어일지 업데이트", description = "기존의 케어일지를 업데이트합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "업데이트 성공"),
            @ApiResponse(responseCode = "404", description = "케어일지를 찾을 수 없음")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CareJournal> updateCareJournal(
            @Parameter(description = "케어일지 ID") @PathVariable Long id,
            @RequestBody CareJournalRequest request) {
        if (careJournalService.getCareJournalById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CareJournal updatedCareJournal = careJournalService.updateCareJournal(id, request);
        return ResponseEntity.ok(updatedCareJournal);
    }

    // 케어일지 삭제
    @Operation(summary = "케어일지 삭제", description = "케어일지를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "케어일지를 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareJournal(
            @Parameter(description = "케어일지 ID") @PathVariable Long id) {
        if (careJournalService.getCareJournalById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        careJournalService.deleteCareJournal(id);
        return ResponseEntity.noContent().build();
    }
}
