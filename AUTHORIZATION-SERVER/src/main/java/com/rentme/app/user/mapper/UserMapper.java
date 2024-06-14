package com.rentme.app.user.mapper;

import com.rentme.app.role.entity.Role;
import com.rentme.app.user.entity.User;
import com.rentme.app.user.model.UserRegistrationRequest;
import com.rentme.app.user.model.UserResponse;

import java.util.UUID;
import java.util.stream.Collectors;

public final class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserRegistrationRequest request) {
        return User
                .builder()
                .userId(randomString())
                .username(request.username())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .password(request.password())
                .confirmPassword(request.confirmPassword())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse
                .builder()
                .userId(UUID.randomUUID().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .enabled(user.isEnabled())
                .roles(
                        user
                                .getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toSet())
                )
                .build();
    }

    private static String randomString() {
        return UUID.randomUUID().toString();
    }
}
