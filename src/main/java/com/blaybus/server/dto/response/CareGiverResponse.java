package com.blaybus.server.dto.response;

import com.blaybus.server.domain.auth.CareGiver;
import lombok.*;

@Getter
@Setter
@Builder
public class CareGiverResponse {
    private String careGiver;
    private int score;

    public static CareGiverResponse fromEntity(CareGiver careGiver, int score) {
        return CareGiverResponse.builder()
                .careGiver(careGiver.getName())
                .score(score)
                .build();
    }
}