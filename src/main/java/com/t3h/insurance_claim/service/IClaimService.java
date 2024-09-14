package com.t3h.insurance_claim.service;

import com.t3h.insurance_claim.dto.requests.ClaimCreationRequest;
import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.dto.responses.ClaimResponse;
import com.t3h.insurance_claim.dto.responses.ResponsePage;

import java.util.List;

public interface IClaimService {
    List<ClaimResponse> getAllClaims();

    ClaimResponse createClaim(ClaimCreationRequest request);

    ResponsePage<List<ClaimResponse>> getAllClaimsByConditions(ClaimRequestFilter filter, int page, int size);
}
