package com.blaybus.server.dto.response;

import com.blaybus.server.domain.Center;
import com.blaybus.server.domain.auth.Experience;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceResponse {
    private CenterExperienceResponse centerResponse;
    private LocalDate certificatedAt;
    private LocalDate endCertificatedAt;
    private String assignedTask;

    public static ExperienceResponse fromEntity(Experience experience) {
        return ExperienceResponse.builder()
                .centerResponse(CenterExperienceResponse.fromEntity(experience.getCenter()))
                .certificatedAt(experience.getCertificatedAt())
                .endCertificatedAt(experience.getEndCertificatedAt())
                .assignedTask(experience.getAssignedTask())
                .build();
    }
}
