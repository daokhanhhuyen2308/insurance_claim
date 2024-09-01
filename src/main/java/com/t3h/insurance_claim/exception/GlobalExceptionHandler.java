package com.t3h.insurance_claim.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ApiExceptionResponse<CustomError>> handleCustomException(CustomExceptionHandler handler, HttpServletRequest request) {

        ApiExceptionResponse<CustomError> apiExceptionResponse = ApiExceptionResponse.<CustomError>builder()
                .error(CustomError.builder()
                        .path(request.getRequestURI())
                        .timestamp(new Date())
                        .build())
                .build();


        return ResponseEntity.status(handler.status).body(apiExceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse<CustomError>> handleGeneralException(Exception exception, HttpServletRequest request) {

        ApiExceptionResponse<CustomError> response = ApiExceptionResponse.<CustomError>builder()
                .error(CustomError.builder()
                        .path(request.getRequestURI())
                        .timestamp(new Date())
                        .details("An unexpected error occurred. Please try again later!")
                        .message(exception.getMessage())
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
