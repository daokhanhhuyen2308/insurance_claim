package com.t3h.insurance_claim.mapper;

import com.t3h.insurance_claim.dto.responses.ClaimResponse;
import com.t3h.insurance_claim.entity.ClaimEntity;

import java.time.LocalDate;

public class ClaimMapper {

    public static ClaimResponse mapToResponse(ClaimEntity claimEntity) {
        ClaimResponse claimResponse = ClaimResponse.builder()
                .id(claimEntity.getId())
                .amount(claimEntity.getAmount())
                .claimCode(claimEntity.getCode())
                .description(claimEntity.getDescription())
                .claimDate(LocalDate.now())
                .build();

        if (claimEntity.getCustomerEntity() != null) {
            claimResponse.setCustomerName(claimEntity.getCustomerEntity().getName());
        }

        if (claimEntity.getClaimStatusEntity() != null){
            claimResponse.setStatus(claimEntity.getClaimStatusEntity().getCode());
            claimResponse.setStatusDescription(claimEntity.getClaimStatusEntity().getDescription());

        }

        if (claimEntity.getInsuranceProductEntity() != null){
            claimResponse.setInsuranceProductName(claimEntity.getInsuranceProductEntity().getName());
        }

        return claimResponse;
    }
}
