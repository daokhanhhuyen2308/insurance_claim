package com.t3h.insurance_claim.repository;

import com.t3h.insurance_claim.entity.InsuranceProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceProductRepository extends JpaRepository<InsuranceProductEntity, Long> {
}
