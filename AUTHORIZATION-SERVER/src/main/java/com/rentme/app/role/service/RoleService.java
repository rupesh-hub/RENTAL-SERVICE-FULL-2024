package com.rentme.app.role.service;

import com.rentme.app.exception.AuthorizationException;
import com.rentme.app.role.entity.Role;
import com.rentme.app.role.repository.RoleRepository;
import com.rentme.app.util.GlobalResponse;
import com.rentme.app.util.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository repository;

    @Override
    public GlobalResponse<Void> save(Set<String> request) {

        Set<Role> roles = request.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return role;
                })
                .collect(Collectors.toSet());

        repository.saveAll(roles);

        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<Set<String>> getAll(int page, int size) {
        Page<Role> rolePage = repository.findAll(PageRequest.of(page, size));

        Set<String> roles = rolePage.getContent()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return GlobalResponse.success(
                roles,
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
    public GlobalResponse<Set<String>> getByName(String name) {
        var role = repository
                .findByName(name)
                .orElseThrow(() -> new AuthorizationException("Role by " + name + " not found."));

        return GlobalResponse.success(Set.of(role.getName()));
    }


}