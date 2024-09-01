package com.t3h.insurance_claim.service;

import com.t3h.insurance_claim.dto.requests.UserCreationRequest;
import com.t3h.insurance_claim.dto.responses.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getAllUser();

    UserResponse getUserById(Long id);

    UserResponse getUserByUserName(String username);
}
