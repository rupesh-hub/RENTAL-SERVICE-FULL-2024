package com.rentme.app.client;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String username,
        String email
) {
}
