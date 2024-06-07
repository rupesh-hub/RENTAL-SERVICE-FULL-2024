package com.rentme.app.user.service;

import ch.qos.logback.core.util.StringUtil;
import com.rentme.app.address.entity.Address;
import com.rentme.app.address.mapper.AddressMapper;
import com.rentme.app.exception.ApiException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(PrincipleUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user not found with username %s", username)));
    }

    @Override
    public GlobalResponse<Void> register(RegistrationRequest request) throws MethodArgumentNotValidException {
        var user = UserMapper.toEntity(request);
        List<FieldError> fieldErrors = new ArrayList<>();

        // Validate username
        var optionalUserByUsername = userRepository.findByUsername(request.username());
        if (optionalUserByUsername.isPresent()) {
            fieldErrors.add(new FieldError("user", "username", String.format("User with username '%s' already exists.", request.username())));
        }

        // Validate email
        var optionalUserByEmail = userRepository.findByEmail(request.email());
        if (optionalUserByEmail.isPresent()) {
            fieldErrors.add(new FieldError("user", "email", String.format("User with email '%s' already exists.", request.email())));
        }

        // Validate userId
        var optionalUserByUserId = userRepository.findByUserId(user.getUserId());
        if (optionalUserByUserId.isPresent()) {
            fieldErrors.add(new FieldError("user", "userId", String.format("User with user id '%s' already exists.", user.getUserId())));
        }

        // Validate password and confirmPassword
        if (!request.password().equals(request.confirmPassword())) {
            fieldErrors.add(new FieldError("user", "confirmPassword", "Password and confirm password do not match."));
        }

        // Throw MethodArgumentNotValidException if there are validation errors
        if (!fieldErrors.isEmpty()) {
            BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
            for (FieldError fieldError : fieldErrors) {
                bindingResult.addError(fieldError);
            }
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        user.setPassword(encodePassword(user.getPassword()));
        user.setEnabled(false);

        // Set address
        Address address = AddressMapper.toEntity(request.address());
        user.setAddressList(List.of(address));

        // Set role
        var role = roleRepository.findByRole("USER")
                .orElseThrow(() -> new ApiException("Role not exists."));
        user.setRoles(List.of(role));

        userRepository.save(user);
        return GlobalResponse.success();
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
                                user.getAddressList()
                                        .stream()
                                        .map(AddressMapper::toResponse)
                                        .collect(Collectors.toList())
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
                                user.getAddressList()
                                        .stream()
                                        .map(AddressMapper::toResponse)
                                        .collect(Collectors.toList())
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
                                user.getAddressList()
                                        .stream()
                                        .map(AddressMapper::toResponse)
                                        .collect(Collectors.toList())
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
                                user.getAddressList()
                                        .stream()
                                        .map(AddressMapper::toResponse)
                                        .collect(Collectors.toList())
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
                    .orElseThrow(() -> new ApiException("Role not found by role id: " + roleId));
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
                    .orElseThrow(() -> new ApiException("Role not found by role id: " + roleId));
            rolesToRemove.add(role);
        }

        user.getRoles().removeAll(rolesToRemove);
        userRepository.save(user);

        return GlobalResponse.success(Boolean.TRUE);
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
