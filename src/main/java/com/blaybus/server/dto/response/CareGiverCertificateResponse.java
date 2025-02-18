package com.blaybus.server.dto.response;

import com.blaybus.server.domain.auth.CareGiverCertificate;
import com.blaybus.server.domain.auth.CareGiverType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareGiverCertificateResponse {
    private CareGiverType careGiverType;
    private String certificatedNumber;

    public static CareGiverCertificateResponse fromEntity(CareGiverCertificate certificate) {
        return CareGiverCertificateResponse.builder()
                .careGiverType(certificate.getCareGiverType())
                .certificatedNumber(certificate.getCertificatedNumber())
                .build();
    }
}
