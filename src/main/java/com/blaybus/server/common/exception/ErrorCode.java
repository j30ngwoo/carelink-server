package com.blaybus.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 500 Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "CareLink Server Error"),

    // 400 Bad Request
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다."),
    INVALID_TYPE(HttpStatus.BAD_REQUEST, "유저 타입이 잘못되었습니다."),
    INVALID_CERTIFICATE_TYPE(HttpStatus.BAD_REQUEST, "유효하지 않은 자격증 종류입니다."),
    INVALID_CERTIFICATE_NUMBER_CAREWORKER(HttpStatus.BAD_REQUEST,"요양보호사 자격증 번호는 9자리 숫자 + 대문자 1자리 형식이어야 합니다."),
    INVALID_CERTIFICATE_NUMBER_SOCIALWORKER(HttpStatus.BAD_REQUEST,"사회복지사 자격증 번호는 '1-123456' 또는 '2-123456' 형식이어야 합니다."),
    INVALID_CERTIFICATE_NUMBER_NURSINGASSISTANT(HttpStatus.BAD_REQUEST, "간호조무사 자격증 번호는 '1-123456' 또는 '2-123456' 형식이어야 합니다."),

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 유저입니다."),
    CENTER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 센터입니다."),

    // 409 Conflict,
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 유저입니다.");

    private final HttpStatus status;
    private final String msg;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
