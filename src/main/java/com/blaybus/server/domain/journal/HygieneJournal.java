package com.blaybus.server.domain.journal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hygiene_journal")
public class HygieneJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "care_journal_id")
    private CareJournal careJournal;

    @Column(name = "bath")
    private Boolean bath;

    @Column(name = "skin_condition", columnDefinition = "TEXT")
    private String skinCondition;
}

