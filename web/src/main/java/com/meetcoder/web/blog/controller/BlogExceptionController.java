package com.meetcoder.web.blog.controller;

import com.rometools.rome.io.FeedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BlogExceptionController {

    @ExceptionHandler(FeedException.class)
    public ResponseEntity<String> handleFeedException(final FeedException e) {
        log.error("Exception occurred : " + e);
        return ResponseEntity.badRequest().build();
    }

}
