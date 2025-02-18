package com.blaybus.server.service;

import com.blaybus.server.domain.CareJournal;
import com.blaybus.server.dto.request.CareJournalRequest;
import com.blaybus.server.dto.request.CareJournalSearchRequest;
import com.blaybus.server.repository.CareJournalQueryRepository;
import com.blaybus.server.repository.CareJournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareJournalService {

    private final CareJournalRepository careJournalRepository;
    private final CareJournalQueryRepository careJournalQueryRepository;

    public CareJournal createCareJournal(CareJournalRequest request) {
        CareJournal careJournal = buildCareJournalFromRequest(request);
        return careJournalRepository.save(careJournal);
    }

    public List<CareJournal> getAllCareJournals() {
        return careJournalRepository.findAll();
    }

    public Optional<CareJournal> getCareJournalById(Long id) {
        return careJournalRepository.findById(id);
    }

    public CareJournal updateCareJournal(Long id, CareJournalRequest request) {
        CareJournal careJournal = buildCareJournalFromRequest(request);
        careJournal.setId(id);
        return careJournalRepository.save(careJournal);
    }

    public void deleteCareJournal(Long id) {
        careJournalRepository.deleteById(id);
    }

    public List<CareJournal> getCareJournalsByMemberId(Long memberId) {
        return careJournalRepository.findByMemberId(memberId);
    }

    public List<CareJournal> searchCareJournal(CareJournalSearchRequest careJournalSearchRequest) {
        return careJournalQueryRepository.searchCareJournal(
                careJournalSearchRequest.getSeniorId(),
                careJournalSearchRequest.getStartDate(),
                careJournalSearchRequest.getEndDate()
        );
    }

    private CareJournal buildCareJournalFromRequest(CareJournalRequest request) {
        return CareJournal.builder()
                .meals(request.getMeal())
                .restroomSmall(request.getRestroomSmall())
                .restroomSmallMethod(request.getRestroomSmallMethod())
                .restroomSmallCondition(request.getRestroomSmallCondition())
                .restroomBig(request.getRestroomBig())
                .restroomBigMethod(request.getRestroomBigMethod())
                .restroomBigCondition(request.getRestroomBigCondition())
                .sleepTime(request.getSleepTime())
                .sleepQuality(request.getSleepQuality())
                .activity(request.getActivity())
                .emotion(request.getEmotion())
                .bath(request.getBath())
                .skinCondition(request.getSkinCondition())
                .medicationTime(request.getMedicationTime())
                .medicationName(request.getMedicationName())
                .notes(request.getNotes())
                .adminNote(request.getAdminNote())
                .createdAt(LocalDateTime.now())
                .build();
    }
}