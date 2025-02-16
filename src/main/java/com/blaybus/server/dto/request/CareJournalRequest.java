package com.blaybus.server.dto.request;

import com.blaybus.server.domain.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareJournalRequest {
    private Meal meal;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snack;
    private RestroomSmall restroomSmall;
    private String restroomSmallMethod;
    private String restroomSmallCondition;
    private RestroomBig restroomBig;
    private String restroomBigMethod;
    private String restroomBigCondition;
    private SleepTime sleepTime;
    private SleepQuality sleepQuality;
    private String activity;
    private String emotion;
    private Boolean bath;
    private String skinCondition;
    private MedicationTime medicationTime;
    private String medicationName;
    private String notes;
    private String adminNote;
}