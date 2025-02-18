package com.blaybus.server.dto.request;

import com.blaybus.server.domain.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CareJournalRequest {
    @NotNull
    private List<MealMenu> meal;
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