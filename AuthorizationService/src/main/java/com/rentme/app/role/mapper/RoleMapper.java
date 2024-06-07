package com.rentme.app.role.mapper;

import com.rentme.app.role.entity.Role;
import com.rentme.app.role.model.RoleRequest;
import com.rentme.app.role.model.RoleResponse;

import java.util.UUID;

public final class RoleMapper {

    private RoleMapper(){

    }

    public static Role toEntity(RoleRequest request){
        return Role.builder()
                .roleId(randomString())
                .role(request.role())
                .build();
    }

    public static RoleResponse toResponse(Role role){
        return new RoleResponse(role.getRoleId(), role.getRole());
    }

    private static String randomString(){
        return UUID.randomUUID().toString();
    }

}
