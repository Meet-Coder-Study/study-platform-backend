package com.meetcoder.web.common.controller;

import com.meetcoder.web.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class CommonExceptionController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleBindException(final BindException e) {
        final String exceptionMessage = ExceptionUtil.createValidExceptionMessage(e);
        log.error("Exception occurred : " + exceptionMessage);
        return ResponseEntity.badRequest().body(exceptionMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(final IllegalArgumentException e) {
        log.error("Exception occurred : " + e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException (final IOException e) {
        log.error("Exception occurred : " + e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
