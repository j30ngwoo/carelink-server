package com.blaybus.server.domain.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "요양 보호사의 유형")
public enum CareGiverType {
    @Schema(description = "사회복지사") SOCIALWORKER,
    @Schema(description = "간호조무사") NURSINGASSISTANT
}
