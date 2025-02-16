package com.blaybus.server.repository;

import com.blaybus.server.domain.CareJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CareJournalRepository extends JpaRepository<CareJournal, Long> {
    List<CareJournal> findByMemberId(Long memberId);
}