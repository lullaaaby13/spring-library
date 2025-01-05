package com.lullaby.springlibrary.common.web;

import com.lullaby.springlibrary.common.feign.FeignClientException;
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

    @ExceptionHandler(FeignClientException.class)
    public CommonErrorResponse handleFeignClientException(HttpServletRequest request, FeignClientException e) {
        log.error(e.getMessage(), e);
        return new CommonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 오류가 발생 하였습니다. 잠시 후에 다시 시도해주세요.", request.getRequestURI());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public CommonErrorResponse handleHttpClientErrorException(HttpServletRequest request, HttpClientErrorException e) {
        log.error(e.getMessage(), e);
        return new CommonErrorResponse(e.getStatusCode().value(), e.getStatusText(), request.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonErrorResponse handleAccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return new CommonErrorResponse(HttpStatus.FORBIDDEN.value(), "해당 작업을 수행할 권한이 없습니다.", request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public CommonErrorResponse handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return new CommonErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 오류가 발생 하였습니다. 잠시 후에 다시 시도해주세요.", request.getRequestURI());
    }

}
