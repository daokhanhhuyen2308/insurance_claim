package com.t3h.insurance_claim.repository;

import com.t3h.insurance_claim.entity.ClaimStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatusEntity, Long> {
}
