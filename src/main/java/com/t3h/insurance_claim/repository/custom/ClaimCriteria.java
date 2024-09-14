package com.t3h.insurance_claim.repository.custom;

import com.t3h.insurance_claim.dto.requests.ClaimRequestFilter;
import com.t3h.insurance_claim.entity.ClaimEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ClaimCriteria {
    private final EntityManager em;

    public Page<ClaimEntity> getAllClaimsByCondition(ClaimRequestFilter requestFilter, Pageable pages) {
        StringBuilder sql = new StringBuilder("select c from ClaimEntity c left join c.claimStatusEntity cs where 1=1");

        Map<String, Object> params = new HashMap<>();

        if (requestFilter.getClaimCode() != null) {
            sql.append(" and lower(c.code) like :claimCode");
            params.put("claimCode", "%" + requestFilter.getClaimCode().toLowerCase() + "%");
        }

        if (requestFilter.getStatusCode() != null){
            sql.append(" and lower(cs.code) like :statusCode");
            params.put("statusCode", "%" + requestFilter.getStatusCode() + "%");
        }

        if (requestFilter.getFromDateSearch() != null){
            sql.append(" and c.claimDate >= :fromDateSearch");
            params.put("fromDateSearch", requestFilter.getFromDateSearch());
        }

        if (requestFilter.getToDateSearch() != null){
            sql.append(" and c.claimDate <= :toDateSearch");
            params.put("toDateSearch", requestFilter.getToDateSearch());
        }

        TypedQuery<ClaimEntity> tQuery = em.createQuery(sql.toString(), ClaimEntity.class);

        params.forEach(tQuery::setParameter);

        tQuery.setFirstResult(pages.getPageNumber());
        tQuery.setMaxResults(pages.getPageSize());

        List<ClaimEntity> claimEntities = tQuery.getResultList();

        return new PageImpl<>(claimEntities, pages, claimEntities.size());

    }
}
