package com.t3h.insurance_claim.service.impl;

import com.t3h.insurance_claim.dto.requests.ClaimCreationRequest;
import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.dto.responses.ClaimResponse;
import com.t3h.insurance_claim.dto.responses.ResponsePage;
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

import java.time.LocalDate;
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
    public ResponsePage<List<ClaimResponse>> getAllClaimsByConditions(ClaimRequestFilter filter, int page, int size) {

        Pageable pages = PageRequest.of(page, size);

//        Page<ClaimEntity> claims = claimCriteria.getAllClaimsByCondition(filter, pages);

        filter.setFromDateSearch(LocalDate.of(1900,1,1));
        Page<ClaimEntity> claims = claimRepository.findByCondition(filter, pages);

        List<ClaimResponse> claimResponses = claims.stream().map(ClaimMapper::mapToResponse).toList();

        return ResponsePage.<List<ClaimResponse>>builder()
                .content(claimResponses)
                .pageNumbers(claims.getNumber())
                .pageSize(claims.getSize())
                .totalPages(claims.getTotalPages())
                .totalElements((int) claims.getTotalElements())
                .build();
    }
}
