package com.t3h.insurance_claim.controller;


import com.t3h.insurance_claim.dto.responses.ApiResponse;
import com.t3h.insurance_claim.dto.responses.InsuranceResponse;
import com.t3h.insurance_claim.service.IInsuranceProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final IInsuranceProductService insuranceProductService;

    @GetMapping("/all-insurances")
    public ApiResponse<List<InsuranceResponse>> getAllInsurances(){
        return ApiResponse.<List<InsuranceResponse>>builder()
                .result(insuranceProductService.getAllInsurance())
                .build();
    }
}
