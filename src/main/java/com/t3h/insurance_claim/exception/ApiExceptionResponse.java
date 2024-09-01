package com.t3h.insurance_claim.exception;


import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiExceptionResponse <T>{
    @Builder.Default
    boolean success = false;
    int statusCode;
    T error;
}
