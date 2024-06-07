package com.rentme.app.user.model;

public record RegistrationResponse(
        String userId,
        String firstName,
        String lastName,
        String username,
        String email,
        String password,
        String confirmPassword,
        boolean enabled
) {
}
