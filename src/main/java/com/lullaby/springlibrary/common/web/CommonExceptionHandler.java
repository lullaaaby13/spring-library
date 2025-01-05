package com.lullaby.springlibrary.common.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {


    @ExceptionHandler(HttpClientErrorException.class)
    public CommonErrorResponse handleHttpClientErrorException(HttpServletRequest request, HttpClientErrorException e) {
        log.error(e.getMessage(), e);
        return new CommonErrorResponse(e.getStatusCode().value(), e.getStatusText(), request.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonErrorResponse handleAccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return new CommonErrorResponse(HttpStatus.FORBIDDEN.value(), "권한이 부족 합니다.", request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public CommonErrorResponse handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return new CommonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI());
    }

}
