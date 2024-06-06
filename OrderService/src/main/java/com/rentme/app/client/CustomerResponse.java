package com.rentme.app.client;

public record CustomerResponse(
        String id,
        String username,
        String firstName,
        String lastName,
        String email
) {
}
