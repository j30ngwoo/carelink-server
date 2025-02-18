package com.blaybus.server.repository;

import com.blaybus.server.domain.journal.CareJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CareJournalRepository extends JpaRepository<CareJournal, Long> {
    List<CareJournal> findByMemberId(Long memberId);
    Optional<CareJournal> findByMemberIdAndId(Long memberId, Long careJournalId);
}