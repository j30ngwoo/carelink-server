package com.blaybus.server.domain;

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

    // 건강 페이지
    // 식사 여부
    @Enumerated(EnumType.STRING)
    @Column(name = "meal")
    private Meal meal;

    // 아침 식사 메뉴
    @Column(name = "breakfast", columnDefinition = "TEXT")
    private String breakfast;

    // 점심 식사 메뉴
    @Column(name = "lunch", columnDefinition = "TEXT")
    private String lunch;

    // 저녁 식사 메뉴
    @Column(name = "dinner", columnDefinition = "TEXT")
    private String dinner;

    // 간식 메뉴
    @Column(name = "snack", columnDefinition = "TEXT")
    private String snack;

    // 소변 횟수
    @Enumerated(EnumType.STRING)
    @Column(name = "restroom_small")
    private RestroomSmall restroomSmall;

    // 소변 방법
    @Column(name = "restroom_small_method", columnDefinition = "TEXT")
    private String restroomSmallMethod;

    // 소변 상태
    @Column(name = "restroom_small_condition", columnDefinition = "TEXT")
    private String restroomSmallCondition;

    // 대변 횟수
    @Enumerated(EnumType.STRING)
    @Column(name = "restroom_big")
    private RestroomBig restroomBig;

    // 대변 방법
    @Column(name = "restroom_big_method", columnDefinition = "TEXT")
    private String restroomBigMethod;

    // 대변 상태
    @Column(name = "restroom_big_condition", columnDefinition = "TEXT")
    private String restroomBigCondition;

    // 활동 및 정서 페이지
    // 수면 시간 (Enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_time")
    private SleepTime sleepTime;

    // 수면 깊이 (Enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_quality", nullable = false)
    private SleepQuality sleepQuality;

    // 운동 및 외부활동 (String)
    @Column(name = "activity", columnDefinition = "TEXT")
    private String activity;

    // 정서 상태 (String)
    @Column(name = "emotion", columnDefinition = "TEXT")
    private String emotion;

    // 위생 페이지
    // 목욕 여부
    @Column(name = "bath")
    private Boolean bath;

    // 피부 및 위생 상태
    @Column(name = "skin_condition", columnDefinition = "TEXT")
    private String skinCondition;

    // 투약 페이지
    // 아침, 점심, 저녁
    @Enumerated(EnumType.STRING)
    @Column(name = "medication_time")
    private MedicationTime medicationTime;

    // 복용 약 이름
    @Column(name = "medicationName", columnDefinition = "TEXT")
    private String medicationName;

    // 특이사항
    @Column(name = "notes",columnDefinition = "TEXT")
    private String notes;

    // 관리자 전달사항
    @Column(name = "admin_note", columnDefinition = "TEXT")
    private String adminNote;
}
