package com.t3h.insurance_claim.repository;

import com.t3h.insurance_claim.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @Query("select u from UserEntity u where lower(u.username) =: username")
    Optional<UserEntity> findByUsername(String username);

    @Query("select distinct u from UserEntity u inner join u.roleEntities r where r.name = :name")
    Optional<UserEntity> findUserByRoleName(@Param("name") String name);

}
