package com.blaybus.server.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class CareLinkExceptionController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = CareLinkException.class)
    public ResponseEntity<?> serviceException(CareLinkException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(
                        new ErrorResponse(
                                e.getErrorCode().getStatus(),
                                e.getMessage(),
                                e.getErrorMap()
                        )
                );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> BindException(BindException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                                null
                        )
                );
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse<String>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(
                        new ErrorResponse(
                                HttpStatus.PAYLOAD_TOO_LARGE,
                                "파일 크기가 너무 큽니다. 최대 업로드 가능 크기를 확인해주세요.",
                                null
                        ));
    }
}
