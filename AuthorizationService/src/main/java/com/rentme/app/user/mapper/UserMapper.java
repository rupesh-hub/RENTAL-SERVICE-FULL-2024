package com.rentme.app.user.mapper;

import com.rentme.app.address.model.AddressResponse;
import com.rentme.app.role.model.RoleResponse;
import com.rentme.app.user.entity.User;
import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.user.model.UserResponse;

import java.util.List;
import java.util.UUID;

public final class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(RegistrationRequest request) {
        return User
                .builder()
                .userId(randomString())
                .username(request.username())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(request.password())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                null,
                null
        );
    }

    public static UserResponse toResponse(User user,
                                          List<RoleResponse> roles,
                                          List<AddressResponse> addresses) {
        return new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled(),
                roles,
                addresses
        );
    }


    private static String randomString() {
        return UUID.randomUUID().toString();
    }
}
