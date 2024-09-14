package com.t3h.insurance_claim.dto.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponsePage <T>{
    int pageNumbers;
    int pageSize;
    int totalPages;
    int totalElements;
    T content;

}
