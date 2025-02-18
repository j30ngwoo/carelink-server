package com.blaybus.server.dto.response;

import com.blaybus.server.domain.Center;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterExperienceResponse {
    private Long centerId;
    private String centerName;
    private String centerAddress;
    private String centerPhone;

    public static CenterExperienceResponse fromEntity(Center center) {
        return CenterExperienceResponse.builder()
                .centerId(center.getId())
                .centerName(center.getCenterName())
                .centerAddress(center.getAddress())
                .centerPhone(center.getTel())
                .build();
    }
}
