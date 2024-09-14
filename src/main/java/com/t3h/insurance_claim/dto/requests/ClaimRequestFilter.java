package com.t3h.insurance_claim.dto.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


public class ClaimRequestFilter {
    private String claimCode;
    private String statusCode;
    private LocalDate fromDateSearch;
    private LocalDate toDateSearch;

    public String getClaimCode() {
        return claimCode;
    }

    public void setClaimCode(String claimCode) {
        this.claimCode = claimCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDate getFromDateSearch() {
        return fromDateSearch;
    }

    public void setFromDateSearch(LocalDate fromDateSearch) {
        this.fromDateSearch = fromDateSearch;
    }

    public LocalDate getToDateSearch() {
        return toDateSearch;
    }

    public void setToDateSearch(LocalDate toDateSearch) {
        this.toDateSearch = toDateSearch;
    }
}
