package com.t3h.insurance_claim.dto.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerCreationRequest {
    String address;
    String bankName;
    String bankNumber;
    String phoneNumber;
    String email;
    String name;
    Set<Long> claimIds;
}
