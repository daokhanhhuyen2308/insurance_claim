package com.t3h.insurance_claim.repository;

import com.t3h.insurance_claim.entity.ClaimDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimDocumentRepository extends JpaRepository<ClaimDocumentEntity, Long> {
}
