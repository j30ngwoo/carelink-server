package com.blaybus.server.domain.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "매칭 필수 조건 타입")
public enum MatchingConditionType {
    @Schema(description = "근무지역") WORK_LOCATION,
    @Schema(description = "근무기간") WORK_DURATION,
    @Schema(description = "근무요일") WORK_DAYS,
    @Schema(description = "근무시간") WORK_HOURS,
    @Schema(description = "선호 성별") PREFERRED_GENDER,
    @Schema(description = "희망 시급") DESIRED_HOURLY_WAGE
}