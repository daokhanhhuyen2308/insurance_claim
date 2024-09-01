package com.t3h.insurance_claim.service;

import com.t3h.insurance_claim.dto.requests.CustomerCreationRequest;
import com.t3h.insurance_claim.dto.responses.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    List<CustomerResponse> getAllCustomers();

    CustomerResponse createCustomer(CustomerCreationRequest request);
}
