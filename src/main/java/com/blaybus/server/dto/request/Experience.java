package com.blaybus.server.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "경력 정보")
public class Experience {

    @Schema(description = "근무 기관 ID", example = "123")
    private Long centerId;

    @Schema(description = "시작 경력 (자격증 취득일 또는 근무 시작일)", example = "2020-01-01")
    private LocalDate certificatedAt;

    @Schema(description = "마지막 경력 (근무 종료일)", example = "2023-12-31")
    private LocalDate endCertificatedAt;

    @Schema(description = "담당 업무", example = "요양보호사")
    private String assignedTask;

}
