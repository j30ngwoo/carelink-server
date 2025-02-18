package com.blaybus.server.domain.journal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "health_journal")
public class HealthJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "care_journal_id")
    private CareJournal careJournal;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal")
    private Meal meal;

    @Column(name = "breakfast", columnDefinition = "TEXT")
    private String breakfast;

    @Column(name = "lunch", columnDefinition = "TEXT")
    private String lunch;

    @Column(name = "dinner", columnDefinition = "TEXT")
    private String dinner;

    @Column(name = "snack", columnDefinition = "TEXT")
    private String snack;

    @Enumerated(EnumType.STRING)
    @Column(name = "restroom_small")
    private RestroomSmall restroomSmall;

    @Column(name = "restroom_small_method", columnDefinition = "TEXT")
    private String restroomSmallMethod;

    @Column(name = "restroom_small_condition", columnDefinition = "TEXT")
    private String restroomSmallCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "restroom_big")
    private RestroomBig restroomBig;

    @Column(name = "restroom_big_method", columnDefinition = "TEXT")
    private String restroomBigMethod;

    @Column(name = "restroom_big_condition", columnDefinition = "TEXT")
    private String restroomBigCondition;
}
