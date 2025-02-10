package com.blaybus.server.common.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class CareLinkException extends RuntimeException {

    private final ErrorCode errorCode;
    private Map<String, String> errorMap;

    /**
     * @param errorCode ErrorCode에 정의된 메시지 반환
     */
    public CareLinkException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    /**
     * @param message 정의되지 않은 예외 처리
     */
    public CareLinkException(String message) {
        super(message);
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }
}

