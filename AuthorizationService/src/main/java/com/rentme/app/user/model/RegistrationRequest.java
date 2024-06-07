package com.rentme.app.user.model;

import com.rentme.app.address.model.AddressRequest;

public record RegistrationRequest(
         String firstName,
         String lastName,
         String username,
         String email,
         String password,
         String confirmPassword,
         AddressRequest address
) {
}
