package com.t3h.insurance_claim.dto.requests;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequest {
    String username;
    String password;
    String code;
    String email;
    String phone;
    String address;
    String firstName;
    String lastName;
}
