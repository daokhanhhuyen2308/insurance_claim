package com.t3h.insurance_claim.dto.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClaimRequestFilter {
    String claimCode;
    String statusCode;
    LocalDate fromDateSearch;
    LocalDate toDateSearch;
    boolean deleted;
}
