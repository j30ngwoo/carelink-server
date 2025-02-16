package com.blaybus.server.controller;

import com.blaybus.server.domain.CareJournal;
import com.blaybus.server.dto.request.CareJournalRequest;
import com.blaybus.server.service.CareJournalService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<CareJournal> createCareJournal(@RequestBody CareJournalRequest request) {
        CareJournal careJournal = careJournalService.createCareJournal(request);
        return ResponseEntity.ok(careJournal);
    }

    @GetMapping
    public ResponseEntity<List<CareJournal>> getAllCareJournals() {
        return ResponseEntity.ok(careJournalService.getAllCareJournals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareJournal> getCareJournalById(@PathVariable Long id) {
        Optional<CareJournal> careJournal = careJournalService.getCareJournalById(id);
        return careJournal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareJournal> updateCareJournal(@PathVariable Long id, @RequestBody CareJournalRequest request) {
        CareJournal updatedCareJournal = careJournalService.updateCareJournal(id, request);
        return ResponseEntity.ok(updatedCareJournal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareJournal(@PathVariable Long id) {
        careJournalService.deleteCareJournal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<CareJournal>> getCareJournalsByMemberId(@PathVariable Long memberId) {
        List<CareJournal> careJournals = careJournalService.getCareJournalsByMemberId(memberId);
        return ResponseEntity.ok(careJournals);
    }
}