package com.t3h.insurance_claim.service.impl;

import com.t3h.insurance_claim.dto.requests.ClaimCreationRequest;
import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.dto.responses.ClaimResponse;
import com.t3h.insurance_claim.entity.ClaimEntity;
import com.t3h.insurance_claim.mapper.ClaimMapper;
import com.t3h.insurance_claim.repository.ClaimRepository;
import com.t3h.insurance_claim.repository.custom.ClaimCriteria;
import com.t3h.insurance_claim.service.IClaimService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClaimServiceImpl implements IClaimService {
    ClaimRepository claimRepository;
    ModelMapper modelMapper;
    ClaimCriteria claimCriteria;

    @Override
    public List<ClaimResponse> getAllClaims() {
        List<ClaimEntity> claims = claimRepository.findAll();
        return claims.stream().map(ClaimMapper::mapToResponse).toList();
    }

    @Override
    public ClaimResponse createClaim(ClaimCreationRequest request) {
        ClaimEntity claimEntity = modelMapper.map(request, ClaimEntity.class);
        return ClaimMapper.mapToResponse(claimRepository.save(claimEntity));
    }

    @Override
    public Page<ClaimResponse> getAllClaimsByConditions(ClaimRequestFilter requestFilter, int page, int size) {

        Pageable pages = PageRequest.of(page, size);

        Page<ClaimEntity> claims = claimCriteria.getAllClaimsByCondition(requestFilter, pages);

        return claims.map(ClaimMapper::mapToResponse);
    }
}
