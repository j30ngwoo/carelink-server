package com.blaybus.server.domain.journal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "medication_journal")
public class MedicationJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "care_journal_id")
    private CareJournal careJournal;

    @Enumerated(EnumType.STRING)
    @Column(name = "medication_time")
    private MedicationTime medicationTime;

    @Column(name = "medication_name", columnDefinition = "TEXT")
    private String medicationName;
}

