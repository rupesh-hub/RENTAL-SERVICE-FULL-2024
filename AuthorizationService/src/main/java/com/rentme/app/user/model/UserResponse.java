package com.rentme.app.user.model;

import com.rentme.app.address.model.AddressResponse;
import com.rentme.app.role.model.RoleResponse;

import java.util.List;

public record UserResponse(
        String userId,
        String firstName,
        String lastName,
        String username,
        String email,
        boolean enabled,
        List<RoleResponse> roles,
        AddressResponse address
) {
}
