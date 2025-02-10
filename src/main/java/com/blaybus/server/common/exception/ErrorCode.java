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

    // 404 Not Found
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "등록되지 않은 유저입니다."),

    // 409 Conflict,
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 유저입니다.");

    private final HttpStatus status;
    private final String msg;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
