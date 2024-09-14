package com.t3h.insurance_claim.repository;

import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.entity.ClaimEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity, Long> {

    //:#{#filter.claimCode} viết kiểu này để access vào trong từng field của class ClaimRequestFilter
//    @Query("select c from ClaimEntity c left join c.claimStatusEntity status "+
//            "where (:#{#filter.claimCode} is null or lower(c.code) like lower(concat('%', :#{#filter.claimCode}, '%'))) and" +
//            "(:#{#filter.statusCode} is null or lower(status.code) like lower(concat('%', :#{#filter.statusCode}, '%'))) and" +
//            "(:#{#filter.fromDateSearch} is null or c.claimDate >= :#{filter.fromDateSearch}) and" +
//            "(:#{#filter.toDateSearch} is null or c.claimDate <= :#{filter.toDateSearch}) and "+
//            "(c.deleted = false)")
//    Page<ClaimEntity> findByCondition(@Param("filter") ClaimRequestFilter filter, Pageable pageable);

    @Query("select c from ClaimEntity c left join c.claimStatusEntity status "+
            "where (:#{#filter.claimCode} is null or lower(c.code) =:#{#filter.claimCode}) and" +
            "(:#{#filter.statusCode} is null or lower(status.code) = :#{#filter.statusCode}) and" +
            "(:#{#filter.fromDateSearch} is null or c.claimDate >= :#{filter.fromDateSearch}) and" +
//            "(:#{#filter.toDateSearch} is null or c.claimDate <= :#{filter.toDateSearch}) and "+
            "(c.deleted = false)")
    Page<ClaimEntity> findByCondition(@Param("filter") ClaimRequestFilter filter, Pageable pageable);
}
