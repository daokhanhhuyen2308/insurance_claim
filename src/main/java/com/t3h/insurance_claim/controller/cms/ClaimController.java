package com.t3h.insurance_claim.controller.cms;

import com.t3h.insurance_claim.dto.requests.ClaimCreationRequest;
import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.dto.responses.ApiResponse;
import com.t3h.insurance_claim.dto.responses.ClaimResponse;
import com.t3h.insurance_claim.dto.responses.ResponsePage;
import com.t3h.insurance_claim.service.IClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/claim")
@RequiredArgsConstructor
public class ClaimController {

    private final IClaimService claimService;

    @GetMapping("/claim-manager")
    public String claimManager() {
        return "cms/claim-manager";
    }

    @GetMapping("/claim-detail")
    public String claimDetail() {
        return "cms/claim-detail";
    }

    @GetMapping("/all-claims")
    public ApiResponse<List<ClaimResponse>> getAllClaims() {
        return ApiResponse.<List<ClaimResponse>>builder()
                .result(claimService.getAllClaims())
                .build();
    }

    @PostMapping("")
    public ApiResponse<ClaimResponse> createClaim(@RequestBody ClaimCreationRequest request){
        return ApiResponse.<ClaimResponse>builder()
                .statusCode(201)
                .result(claimService.createClaim(request))
                .build();
    }

    @GetMapping("/conditions")
    public ApiResponse<ResponsePage<List<ClaimResponse>>> getAllClaimsByConditions
    (@RequestParam(name = "claimCode", required = false) String claimCode,
     @RequestParam(name = "statusCode", required = false) String statusCode,
//     @RequestParam(name = "fromDateSearch", required = false)LocalDate fromDateSearch,
//     @RequestParam(name = "toDateSearch", required = false) LocalDate toDateSearch,
     @RequestParam(name = "page", defaultValue = "0") int page,
     @RequestParam(name = "size", defaultValue = "10") int size) {

        ClaimRequestFilter filter = new ClaimRequestFilter();
        filter.setClaimCode(claimCode);
        filter.setStatusCode(statusCode);
//        filter.setFromDateSearch(fromDateSearch);
//        filter.setToDateSearch(toDateSearch);

         return ApiResponse.<ResponsePage<List<ClaimResponse>>>builder()
                 .result(claimService.getAllClaimsByConditions(filter, page, size))
                 .build();
    }

}
