package com.blaybus.server.service;

import com.blaybus.server.domain.*;
import com.blaybus.server.domain.journal.*;
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
        if (!careJournalRepository.existsById(id)) {
            throw new RuntimeException("CareJournal not found");
        }
        CareJournal updatedJournal = buildCareJournalFromRequest(request);
        updatedJournal.setId(id);
        return careJournalRepository.save(updatedJournal);
    }

    public void deleteCareJournal(Long id) {
        careJournalRepository.deleteById(id);
    }

    public List<CareJournal> getCareJournalsByMemberId(Long memberId) {
        return careJournalRepository.findByMemberId(memberId);
    }

    public Optional<CareJournal> getCareJournalByMemberIdAndCareJournalId(Long memberId, Long careJournalId) {
        return careJournalRepository.findByMemberIdAndId(memberId, careJournalId);
    }

    private CareJournal buildCareJournalFromRequest(CareJournalRequest request) {
        return CareJournal.builder()
                .memberId(request.getMemberId())
                .healthJournal(buildHealthJournal(request))
                .activityJournal(buildActivityJournal(request))
                .hygieneJournal(buildHygieneJournal(request))
                .medicationJournal(buildMedicationJournal(request))
                .notesJournal(buildNotesJournal(request))
                .build();
    }

    private HealthJournal buildHealthJournal(CareJournalRequest request) {
        return HealthJournal.builder()
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
                .build();
    }

    private ActivityJournal buildActivityJournal(CareJournalRequest request) {
        return ActivityJournal.builder()
                .sleepTime(request.getSleepTime())
                .sleepQuality(request.getSleepQuality())
                .activity(request.getActivity())
                .emotion(request.getEmotion())
                .build();
    }

    private HygieneJournal buildHygieneJournal(CareJournalRequest request) {
        return HygieneJournal.builder()
                .bath(request.getBath())
                .skinCondition(request.getSkinCondition())
                .build();
    }

    private MedicationJournal buildMedicationJournal(CareJournalRequest request) {
        return MedicationJournal.builder()
                .medicationTime(request.getMedicationTime())
                .medicationName(request.getMedicationName())
                .build();
    }

    private NotesJournal buildNotesJournal(CareJournalRequest request) {
        return NotesJournal.builder()
                .specialNotes(request.getSpecialNotes())
                .adminNote(request.getAdminNote())
                .build();
    }
}
