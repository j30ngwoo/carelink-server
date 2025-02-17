package com.blaybus.server.domain.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "한국 은행 목록")
public enum BankType {
    @Schema(description = "KB국민은행") KB_KOOKMIN,
    @Schema(description = "신한은행") SHINHAN,
    @Schema(description = "우리은행") WOORI,
    @Schema(description = "하나은행") HANA,
    @Schema(description = "NH농협은행") NH_NONGHYUP,
    @Schema(description = "IBK기업은행") IBK,
    @Schema(description = "카카오뱅크") KAKAO,
    @Schema(description = "토스뱅크") TOSS,
    @Schema(description = "SC제일은행") SC,
    @Schema(description = "씨티은행") CITI,
    @Schema(description = "대구은행") DAEGU,
    @Schema(description = "부산은행") BUSAN,
    @Schema(description = "광주은행") GWANGJU,
    @Schema(description = "전북은행") JEONBUK,
    @Schema(description = "경남은행") KYONGNAM,
    @Schema(description = "수협은행") SUHYUP,
    @Schema(description = "새마을금고") SAEMAUL,
    @Schema(description = "신협") SHINHYEOP,
    @Schema(description = "우체국") POST,
    @Schema(description = "KEB하나은행 (구)") KEB_HANA
}
