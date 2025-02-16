package com.blaybus.server.service;

import com.blaybus.server.domain.CareJournal;
import com.blaybus.server.dto.request.CareJournalRequest;
import com.blaybus.server.repository.CareJournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareJournalService {

    private final CareJournalRepository careJournalRepository;

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

    private CareJournal buildCareJournalFromRequest(CareJournalRequest request) {
        return CareJournal.builder()
                .meal(request.getMeal())
                .breakfast(request.getBreakfast())
                .lunch(request.getLunch())
                .dinner(request.getDinner())
                .snack(request.getSnack())
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
                .build();
    }
}