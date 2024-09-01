package com.t3h.insurance_claim.service.impl;

import com.t3h.insurance_claim.constant.DefaultRoles;
import com.t3h.insurance_claim.dto.requests.UserCreationRequest;
import com.t3h.insurance_claim.dto.responses.RoleResponse;
import com.t3h.insurance_claim.dto.responses.UserResponse;
import com.t3h.insurance_claim.entity.RoleEntity;
import com.t3h.insurance_claim.entity.UserEntity;
import com.t3h.insurance_claim.repository.*;
import com.t3h.insurance_claim.service.IUserService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements IUserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        boolean existingEmail = userRepository.existsByEmail(request.getEmail());
        boolean existingUsername = userRepository.existsByUsername(request.getUsername());
        boolean existingPhone = userRepository.existsByPhone(request.getPhone());

        if (existingEmail || existingUsername || existingPhone) {
            throw new IllegalArgumentException("The information exists already.");
        }

        UserEntity userEntity = modelMapper.map(request, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setCreatedDate(LocalDateTime.now());
        userEntity.setLastModifiedDate(LocalDateTime.now());

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName(DefaultRoles.USER_ROLE)
                .orElseThrow(() -> new IllegalArgumentException("No role found")));
        userEntity.setRoleEntities(roles);

        return buildUserResponse(userEntity);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(this::buildUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            throw new IllegalArgumentException("The user does not exist.");
        }
        return buildUserResponse(userEntity.get());
    }

    @Override
    public UserResponse getUserByUserName(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()){
            throw new IllegalArgumentException("The user does not exist.");
        }
        return buildUserResponse(userOptional.get());
    }

    private UserResponse buildUserResponse(UserEntity userEntity) {

        var roles = userEntity.getRoleEntities();

        UserResponse userResponse = modelMapper.map(userRepository.save(userEntity), UserResponse.class);
        Set<RoleResponse> roleResponses = new HashSet<>();
        roles.forEach(role -> {
            RoleResponse roleResponse = modelMapper.map(role, RoleResponse.class);
            roleResponses.add(roleResponse);
        });

        userResponse.setRoles(roleResponses);
        return userResponse;
    }


}
