package com.t3h.insurance_claim.service;

import com.t3h.insurance_claim.dto.requests.ClaimCreationRequest;
import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.dto.responses.ApiResponse;
import com.t3h.insurance_claim.dto.responses.ClaimResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClaimService {
    List<ClaimResponse> getAllClaims();

    ClaimResponse createClaim(ClaimCreationRequest request);

    Page<ClaimResponse> getAllClaimsByConditions(ClaimRequestFilter requestFilter, int page, int size);
}
