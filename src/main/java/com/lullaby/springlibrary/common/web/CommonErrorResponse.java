package com.lullaby.springlibrary.common.web;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record CommonErrorResponse(int code, String message, String timestamp, String path) {
    public CommonErrorResponse(int code, String message, String path) {
        this(code, message, LocalDateTime.now().toString(), path);
    }

    public CommonErrorResponse(HttpStatus httpStatus, String message, String path) {
        this(httpStatus.value(), message, LocalDateTime.now().toString(), path);
    }
}
