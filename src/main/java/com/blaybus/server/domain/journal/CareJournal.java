package com.blaybus.server.domain.journal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "care_journal")
public class CareJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @OneToOne(mappedBy = "careJournal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HealthJournal healthJournal;

    @OneToOne(mappedBy = "careJournal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ActivityJournal activityJournal;

    @OneToOne(mappedBy = "careJournal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HygieneJournal hygieneJournal;

    @OneToOne(mappedBy = "careJournal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MedicationJournal medicationJournal;

    @OneToOne(mappedBy = "careJournal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NotesJournal notesJournal;
}
