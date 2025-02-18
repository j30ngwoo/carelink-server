package com.blaybus.server.controller;

import com.blaybus.server.domain.CareJournal;
import com.blaybus.server.dto.ResponseFormat;
import com.blaybus.server.dto.request.CareJournalRequest;
import com.blaybus.server.dto.request.CareJournalSearchRequest;
import com.blaybus.server.service.CareJournalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/care-journal")
@RequiredArgsConstructor
public class CareJournalController {

    private final CareJournalService careJournalService;

    @PostMapping
    public ResponseEntity<ResponseFormat<CareJournal>> createCareJournal(@Valid @RequestBody CareJournalRequest request) {
        CareJournal careJournal = careJournalService.createCareJournal(request);

        ResponseFormat<CareJournal> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "케어일지가 등록되었습니다.",
                careJournal
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseFormat<List<CareJournal>>> getAllCareJournals() {
        List<CareJournal> careJournals = careJournalService.getAllCareJournals();

        ResponseFormat<List<CareJournal>> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "케어일지가 조회되었습니다.",
                careJournals
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFormat<CareJournal>> getCareJournalById(@PathVariable Long id) {
        Optional<CareJournal> careJournal = careJournalService.getCareJournalById(id);

        ResponseFormat<CareJournal> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "케어일지를 상세 조회되었습니다.",
                careJournal.orElse(null)
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFormat<CareJournal>> updateCareJournal(@PathVariable Long id, @RequestBody CareJournalRequest request) {
        CareJournal updatedCareJournal = careJournalService.updateCareJournal(id, request);

        ResponseFormat<CareJournal> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "케어일지가 수정되었습니다.",
                updatedCareJournal
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseFormat<Void>> deleteCareJournal(@PathVariable Long id) {
        careJournalService.deleteCareJournal(id);

        ResponseFormat<Void> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "케어일지가 삭제되었습니다.",
                null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ResponseFormat<List<CareJournal>>> getCareJournalsByMemberId(@PathVariable Long memberId) {
        List<CareJournal> careJournals = careJournalService.getCareJournalsByMemberId(memberId);

        ResponseFormat<List<CareJournal>> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "멤버의 케어일지가 조회되었습니다.",
                careJournals
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 검색
     */
    @GetMapping
    public ResponseEntity<ResponseFormat<List<CareJournal>>> getCareJournalBy(@ModelAttribute CareJournalSearchRequest careJournalSearchRequest) {
        List<CareJournal> careJournals = careJournalService.searchCareJournal(careJournalSearchRequest);

        ResponseFormat<List<CareJournal>> response = new ResponseFormat<>(
                HttpStatus.OK.value(),
                "케어일지가 검색되었습니다.",
                careJournals
        );

        return ResponseEntity.ok(response);
    }
}