package com.t3h.insurance_claim.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "claim_document")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClaimDocumentEntity extends BaseEntity{
    String documentName;
    String documentType;
    String filePath;
    Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "claim_id", referencedColumnName = "id")
    ClaimEntity claimEntity;
}
