package com.blaybus.server.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
