package com.rentme.app.address.model;

public record AddressResponse(
        Long id,
        String country,
        String province,
        String district,
        String city,
        String street,
        long zip
) {
}
