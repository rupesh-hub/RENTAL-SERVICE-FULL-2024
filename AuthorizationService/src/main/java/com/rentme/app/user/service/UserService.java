package com.rentme.app.user.service;

import com.rentme.app.address.mapper.AddressMapper;
import com.rentme.app.exception.AuthorizationServiceException;
import com.rentme.app.role.entity.Role;
import com.rentme.app.role.mapper.RoleMapper;
import com.rentme.app.role.repository.RoleRepository;
import com.rentme.app.user.mapper.UserMapper;
import com.rentme.app.user.model.PrincipleUser;
import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.user.model.UserResponse;
import com.rentme.app.user.repository.UserRepository;
import com.rentme.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(PrincipleUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user not found with username %s", username)));
    }

    @Override
    public GlobalResponse<UserResponse> findByUsername(String username) {
        var response = userRepository.findByUsername(username)
                .map(user ->
                        UserMapper.toResponse(
                                user,
                                user.getRoles()
                                        .stream()
                                        .map(RoleMapper::toResponse)
                                        .collect(Collectors.toList()),
                                Optional.of(user.getAddress())
                                        .map(AddressMapper::toResponse)
                                        .orElse(null)
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));


        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<UserResponse> findByEmail(String email) {
        var response = userRepository.findByEmail(email)
                .map(user ->
                        UserMapper.toResponse(
                                user,
                                user.getRoles()
                                        .stream()
                                        .map(RoleMapper::toResponse)
                                        .collect(Collectors.toList()),
                                Optional.of(user.getAddress())
                                        .map(AddressMapper::toResponse)
                                        .orElse(null)
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));


        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<UserResponse> findByUserId(String userId) {
        var response = userRepository.findByUserId(userId)
                .map(user ->
                        UserMapper.toResponse(
                                user,
                                user.getRoles()
                                        .stream()
                                        .map(RoleMapper::toResponse)
                                        .collect(Collectors.toList()),
                                Optional.of(user.getAddress())
                                        .map(AddressMapper::toResponse)
                                        .orElse(null)
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));


        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<UserResponse> findById(Long id) {
        var response = userRepository.findById(id)
                .map(user ->
                        UserMapper.toResponse(
                                user,
                                user.getRoles()
                                        .stream()
                                        .map(RoleMapper::toResponse)
                                        .collect(Collectors.toList()),
                                Optional.of(user.getAddress())
                                        .map(AddressMapper::toResponse)
                                        .orElse(null)
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found by id."));


        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<Boolean> update(RegistrationRequest request, String userId) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));

        if (StringUtils.isNotBlank(request.firstName()) || StringUtils.isNotEmpty(request.firstName())) {
            user.setFirstName(request.firstName());
        }

        if (StringUtils.isNotBlank(request.lastName()) || StringUtils.isNotEmpty(request.lastName())) {
            user.setLastName(request.lastName());
        }
        userRepository.save(user);
        return GlobalResponse.success(Boolean.TRUE);
    }

    @Override
    public GlobalResponse<Boolean> delete(String userId) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));
        //keep user logs for future reference
        userRepository.delete(user);
        return GlobalResponse.success(Boolean.TRUE);
    }

    @Override
    public GlobalResponse<Boolean> assignRole(String userId, String[] roleIds) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));

        List<Role> roles = new ArrayList<>();
        for (String roleId : roleIds) {
            var role = roleRepository.findByRoleId(roleId)
                    .orElseThrow(() -> new AuthorizationServiceException("Role not found by role id: " + roleId));
            roles.add(role);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return GlobalResponse.success(Boolean.TRUE);
    }


    @Override
    public GlobalResponse<Boolean> removeRole(String userId, String[] roleIds) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));

        List<Role> rolesToRemove = new ArrayList<>();
        for (String roleId : roleIds) {
            var role = roleRepository.findByRoleId(roleId)
                    .orElseThrow(() -> new AuthorizationServiceException("Role not found by role id: " + roleId));
            rolesToRemove.add(role);
        }

        user.getRoles().removeAll(rolesToRemove);
        userRepository.save(user);

        return GlobalResponse.success(Boolean.TRUE);
    }

}
