package com.t3h.insurance_claim.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "insurance_product")
public class InsuranceProductEntity extends BaseEntity{
    String name;
    String description;
    String coverage;

    @OneToMany(mappedBy = "insuranceProductEntity")
    List<ClaimEntity> claimEntities;
}
