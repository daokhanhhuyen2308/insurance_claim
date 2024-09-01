package com.t3h.insurance_claim.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse <T>{
    @Builder.Default
    boolean success = true;
    @Builder.Default
    int statusCode = 200;
    T result;
}
