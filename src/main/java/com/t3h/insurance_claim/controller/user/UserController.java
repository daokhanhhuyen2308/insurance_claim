package com.t3h.insurance_claim.controller.user;

import com.t3h.insurance_claim.dto.requests.UserCreationRequest;
import com.t3h.insurance_claim.dto.responses.ApiResponse;
import com.t3h.insurance_claim.dto.responses.UserResponse;
import com.t3h.insurance_claim.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request){
        return ApiResponse.<UserResponse>builder().statusCode(201)
                .result(userService.createUser(request)).build();
    }

    @GetMapping("/all-users")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") Long id){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .build();
    }

    @GetMapping("")
    public ApiResponse<UserResponse> getUserByUsername(@RequestParam(name = "username", required = false) String username){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserByUserName(username)).build();
    }
}
