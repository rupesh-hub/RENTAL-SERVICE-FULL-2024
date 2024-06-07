package com.rentme.app.user.service;

import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.user.model.UserResponse;
import com.rentme.app.util.GlobalResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface IUserService {

    GlobalResponse<UserResponse> findByUsername(String username);
    GlobalResponse<UserResponse> findByEmail(String email);
    GlobalResponse<UserResponse> findByUserId(String userId);
    GlobalResponse<UserResponse> findById(Long id);
    GlobalResponse<Boolean> update(RegistrationRequest request, String userId);
    GlobalResponse<Boolean> delete(String userId);
    GlobalResponse<Boolean> assignRole(String userId, String[] roleId);
    GlobalResponse<Boolean> removeRole(String userId, String[] roleId);

}
