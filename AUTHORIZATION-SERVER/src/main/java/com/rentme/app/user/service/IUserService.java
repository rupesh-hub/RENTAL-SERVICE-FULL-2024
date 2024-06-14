package com.rentme.app.user.service;

import com.rentme.app.user.model.UserRegistrationRequest;
import com.rentme.app.user.model.UserResponse;
import com.rentme.app.util.GlobalResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface IUserService {

    GlobalResponse<Boolean> register(UserRegistrationRequest request) throws MethodArgumentNotValidException;
    GlobalResponse<UserResponse> findByUsername(String username);
    GlobalResponse<UserResponse> findByEmail(String email);
    GlobalResponse<UserResponse> findByUserId(String userId);
    GlobalResponse<UserResponse> findById(Long id);
    GlobalResponse<Boolean> update(UserRegistrationRequest request, String userId);
    GlobalResponse<Boolean> delete(String userId);
    GlobalResponse<Boolean> assignRole(String userId, String[] names);
    GlobalResponse<Boolean> removeRole(String userId, String[] names);

}