package com.t3h.insurance_claim.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class CustomExceptionHandler extends RuntimeException{
    HttpStatus status;
    CustomError error;

    public static CustomExceptionHandler notFoundException(String message) {
        return CustomExceptionHandler.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(CustomError.builder()
                        .message(message)
                        .details("The information that you provided for us could not be found. Please recheck your information!")
                        .build())
                .build();
    }

    public static CustomExceptionHandler badRequestException(String message) {
        return CustomExceptionHandler.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error(CustomError.builder()
                        .message(message)
                        .details("The information that you provided for us could be bad requested. Please recheck your information!")
                        .build())
                .build();
    }

    public static CustomExceptionHandler unauthorizedException(String message){
        return CustomExceptionHandler.builder()
                .error(CustomError.builder()
                        .message(message)
                        .build())
                .status(HttpStatus.UNAUTHORIZED).build();
    }
}
