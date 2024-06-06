package com.rentme.app.model;

public record Customer(
        String id,
        String username,
        String email,
        String firstName,
        String lastName
) {
}
