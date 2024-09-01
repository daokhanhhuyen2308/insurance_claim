package com.t3h.insurance_claim.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClaimResponse {
    Long id;
    String claimCode;
    String customerName;
    String insuranceProductName;
    String description;
    LocalDate claimDate;
    String status;
    String statusDescription;
    Double amount;

}
