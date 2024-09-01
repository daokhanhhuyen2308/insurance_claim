package com.t3h.insurance_claim.service.impl;

import com.t3h.insurance_claim.dto.requests.CustomerCreationRequest;
import com.t3h.insurance_claim.dto.responses.CustomerResponse;
import com.t3h.insurance_claim.entity.CustomerEntity;
import com.t3h.insurance_claim.repository.CustomerRepository;
import com.t3h.insurance_claim.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream()
                .map(customerEntity -> modelMapper.map(customerEntity, CustomerResponse.class))
                .toList();
    }

    @Override
    public CustomerResponse createCustomer(CustomerCreationRequest request) {
        CustomerEntity customerEntity = modelMapper.map(request, CustomerEntity.class);
        return modelMapper.map(customerRepository.save(customerEntity), CustomerResponse.class);
    }
}
