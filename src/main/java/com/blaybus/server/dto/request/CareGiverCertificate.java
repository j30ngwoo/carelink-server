package com.blaybus.server.dto.request;

import com.blaybus.server.domain.auth.CareGiverType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "요양 보호사 자격증 정보")
public class CareGiverCertificate {

    @Schema(description = "요양 보호사 유형", example = "SOCIALWORKER", allowableValues = {"SOCIALWORKER", "NURSINGASSISTANT"})
    private CareGiverType careGiverType;

    @Schema(description = "자격증 번호", example = "2024-123456")
    private String certificatedNumber;
}
