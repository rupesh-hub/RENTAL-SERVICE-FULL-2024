package com.rentme.app.role.service;

import com.rentme.app.exception.AuthorizationServiceException;
import com.rentme.app.role.entity.Role;
import com.rentme.app.role.mapper.RoleMapper;
import com.rentme.app.role.model.RoleRequest;
import com.rentme.app.role.model.RoleResponse;
import com.rentme.app.role.repository.RoleRepository;
import com.rentme.app.util.GlobalResponse;
import com.rentme.app.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository repository;

    @Override
    public GlobalResponse<Void> save(RoleRequest request) {
        var role = RoleMapper.toEntity(request);
        repository.save(role);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<List<RoleResponse>> getAll(int page, int size) {
        Page<Role> rolePage = repository.findAll(PageRequest.of(page, size));

        List<RoleResponse> responseList = rolePage.getContent()
                .stream()
                .map(RoleMapper::toResponse)
                .collect(Collectors.toList());

        return GlobalResponse.success(
                responseList,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(rolePage.getTotalElements())
                        .totalPage(rolePage.getTotalPages())
                        .first(rolePage.isFirst())
                        .last(rolePage.isLast())
                        .build()
        );
    }

    @Override
    public GlobalResponse<RoleResponse> getByRole(String role) {
        var response = repository
                .findByRole(role)
                .map(RoleMapper::toResponse)
                .orElseThrow(() -> new AuthorizationServiceException("Role by " + role + " not found."));
        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<RoleResponse> getByRoleId(String roleId) {
        var response = repository
                .findByRoleId(roleId)
                .map(RoleMapper::toResponse)
                .orElseThrow(() -> new AuthorizationServiceException("Role by " + roleId + " not found."));
        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<Boolean> update(RoleRequest request, String roleId) {
        var response = repository
                .findByRoleId(roleId)
                .orElseThrow(() -> new AuthorizationServiceException("Role by " + roleId + " not found."));

        response.setRole(response.getRole());
        repository.save(response);
        return GlobalResponse.success(Boolean.TRUE);
    }

    @Override
    public GlobalResponse<Boolean> delete(String roleId) {
        var response = repository
                .findByRoleId(roleId)
                .orElseThrow(() -> new AuthorizationServiceException("Role by " + roleId + " not found."));
        repository.delete(response);
        return GlobalResponse.success(Boolean.TRUE);
    }
}
