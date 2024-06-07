package com.rentme.app.role.service;

import com.rentme.app.role.model.RoleRequest;
import com.rentme.app.role.model.RoleResponse;
import com.rentme.app.util.GlobalResponse;

import java.util.List;

public interface IRoleService {

    GlobalResponse<Void> save(RoleRequest request);
    GlobalResponse<List<RoleResponse>> getAll(int page, int size);
    GlobalResponse<RoleResponse> getByRole(String role);
    GlobalResponse<RoleResponse> getByRoleId(String roleId);
    GlobalResponse<Boolean> update(RoleRequest request, String roleId);
    GlobalResponse<Boolean> delete(String roleId);

}
