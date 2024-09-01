package com.t3h.insurance_claim.controller.cms;

import com.t3h.insurance_claim.config.Admin;
import com.t3h.insurance_claim.dto.requests.CustomerCreationRequest;
import com.t3h.insurance_claim.dto.responses.ApiResponse;
import com.t3h.insurance_claim.dto.responses.CustomerResponse;
import com.t3h.insurance_claim.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("/all-customers")
    @Admin
    public ApiResponse<List<CustomerResponse>> getAllCustomers() {
        return ApiResponse.<List<CustomerResponse>>builder()
                .result(customerService.getAllCustomers())
                .build();
    }

    @PostMapping("")
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerCreationRequest request){
        return ApiResponse.<CustomerResponse>builder()
                .statusCode(201)
                .result(customerService.createCustomer(request))
                .build();
    }
}
