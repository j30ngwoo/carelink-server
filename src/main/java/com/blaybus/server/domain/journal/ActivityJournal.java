package com.blaybus.server.domain.journal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "activity_journal")
public class ActivityJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "care_journal_id")
    private CareJournal careJournal;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_time")
    private SleepTime sleepTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_quality", nullable = false)
    private SleepQuality sleepQuality;

    @Column(name = "activity", columnDefinition = "TEXT")
    private String activity;

    @Column(name = "emotion", columnDefinition = "TEXT")
    private String emotion;
}
