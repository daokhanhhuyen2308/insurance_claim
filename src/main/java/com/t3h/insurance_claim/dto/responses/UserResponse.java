package com.t3h.insurance_claim.dto.responses;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String username;
    String code;
    String email;
    String phone;
    String address;
    String firstName;
    String lastName;
    Set<RoleResponse> roles;
    LocalDate createdDate;
    String createdBy;
    LocalDateTime lastModifiedDate;
    String lastModifiedBy;
    Boolean deleted;


}
