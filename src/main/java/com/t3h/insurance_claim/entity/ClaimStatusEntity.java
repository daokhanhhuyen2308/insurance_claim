package com.t3h.insurance_claim.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "claim_status")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClaimStatusEntity extends BaseEntity{
    String description;
    String code;

    @OneToMany(mappedBy = "claimStatusEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ClaimEntity> claimEntities;

}
