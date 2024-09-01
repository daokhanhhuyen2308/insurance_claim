package com.t3h.insurance_claim.service.impl;

import com.t3h.insurance_claim.dto.responses.InsuranceResponse;
import com.t3h.insurance_claim.entity.InsuranceProductEntity;
import com.t3h.insurance_claim.repository.InsuranceProductRepository;
import com.t3h.insurance_claim.service.IInsuranceProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceProductServiceImpl implements IInsuranceProductService {
    private final InsuranceProductRepository insuranceProductRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InsuranceResponse> getAllInsurance() {
        List<InsuranceProductEntity> insuranceProductEntities = insuranceProductRepository.findAll();
        return insuranceProductEntities.stream()
                .map(insurance -> modelMapper.map(insurance, InsuranceResponse.class)).toList();
    }
}
