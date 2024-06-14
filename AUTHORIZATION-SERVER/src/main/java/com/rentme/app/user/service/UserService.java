package com.rentme.app.user.service;

import com.rentme.app.exception.AuthorizationException;
import com.rentme.app.role.entity.Role;
import com.rentme.app.role.repository.RoleRepository;
import com.rentme.app.user.entity.User;
import com.rentme.app.user.mapper.UserMapper;
import com.rentme.app.user.model.PrincipleUser;
import com.rentme.app.user.model.UserRegistrationRequest;
import com.rentme.app.user.model.UserResponse;
import com.rentme.app.user.repository.UserRepository;
import com.rentme.app.util.GlobalResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
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
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User by username %s not found.", username)
                ));
    }

    @Override
    public GlobalResponse<Boolean> register(UserRegistrationRequest request) throws MethodArgumentNotValidException {
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

        if (!fieldErrors.isEmpty()) {
            BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
            for (FieldError fieldError : fieldErrors) {
                bindingResult.addError(fieldError);
            }

            // Get the method parameter for the request
            MethodParameter methodParameter = new MethodParameter(
                    this.getClass().getDeclaredMethods()[0],
                    0
            );

            throw new MethodArgumentNotValidException(methodParameter, bindingResult);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var role = roleRepository.findByName("user")
                .orElseThrow(() -> new RuntimeException("Role 'user' does not exist."));

        user.setRoles(List.of(role));

        userRepository.save(user);
        return GlobalResponse.success(Boolean.TRUE);
    }

    @Override
    public GlobalResponse<UserResponse> findByUsername(String username) {

        var response = userRepository.findByUsername(username)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));

        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<UserResponse> findByEmail(String email) {
        var response = userRepository.findByEmail(email)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email: " + email));

        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<UserResponse> findByUserId(String userId) {
        var response = userRepository.findByUserId(userId)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));

        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<UserResponse> findById(Long id) {
        var response = userRepository.findById(id)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by id."));

        return GlobalResponse.success(response);
    }

    @Override
    public GlobalResponse<Boolean> update(UserRegistrationRequest request, String userId) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));

        if (StringUtils.isNotBlank(request.firstName()) || StringUtils.isNotEmpty(request.lastName())) {
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
    public GlobalResponse<Boolean> assignRole(String userId, String[] names) {
        User user = userByUserId(userId);

        List<Role> roles = new ArrayList<>();
        for (String name : names) {
            var role = roleRepository.findByName(name)
                    .orElseThrow(() -> new AuthorizationException("Role not found by name: " + name));
            roles.add(role);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return GlobalResponse.success(Boolean.TRUE);
    }

    @Override
    public GlobalResponse<Boolean> removeRole(String userId, String[] names) {
        var user = userByUserId(userId);

        List<Role> rolesToRemove = new ArrayList<>();
        for (String name : names) {
            var role = roleRepository.findByName(name)
                    .orElseThrow(() -> new AuthorizationException("Role not found by name: " + name));
            rolesToRemove.add(role);
        }

        user.getRoles().removeAll(rolesToRemove);
        userRepository.save(user);

        return GlobalResponse.success(Boolean.TRUE);
    }

    private User userByUserId(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by user id: " + userId));
    }

}
