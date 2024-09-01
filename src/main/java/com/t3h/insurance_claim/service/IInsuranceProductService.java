package com.t3h.insurance_claim.service;

import com.t3h.insurance_claim.dto.responses.InsuranceResponse;

import java.util.List;

public interface IInsuranceProductService {
    List<InsuranceResponse> getAllInsurance();
}
