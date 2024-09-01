package com.t3h.insurance_claim.entity;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "claim")
public class ClaimEntity extends BaseEntity{
    Double amount;
    LocalDate claimDate;
    String description;
    String code;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private InsuranceProductEntity insuranceProductEntity;

    @OneToMany(mappedBy = "claimEntity")
    List<ClaimDocumentEntity> claimDocumentEntities;

    @ManyToOne
    @JoinColumn(name = "status_id")
    ClaimStatusEntity claimStatusEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customerEntity;

}
