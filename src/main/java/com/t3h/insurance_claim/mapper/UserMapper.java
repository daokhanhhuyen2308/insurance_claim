package com.t3h.insurance_claim.mapper;

import com.t3h.insurance_claim.dto.responses.RoleResponse;
import com.t3h.insurance_claim.dto.responses.UserResponse;
import com.t3h.insurance_claim.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserResponse mapToUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .code(user.getCode())
                .address(user.getAddress())
                .createdDate(LocalDate.now())
                .createdBy(user.getUsername())
                .lastModifiedDate(LocalDateTime.now())
                .lastModifiedBy(user.getUsername())
                .roles(user.getRoleEntities().stream()
                        .map(role -> RoleResponse.builder().id(role.getId())
                                .name(role.getName())
                                .code(role.getCode()).build())
                        .collect(Collectors.toSet()))
                .deleted(user.getDeleted())
                .build();
    }
}
