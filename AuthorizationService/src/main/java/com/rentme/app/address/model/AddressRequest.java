package com.rentme.app.address.model;

public record AddressRequest(
         String country,
         String province,
         String district,
         String city,
         String street,
         long zip
) {
}
