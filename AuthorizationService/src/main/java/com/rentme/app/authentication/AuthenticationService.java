package com.rentme.app.authentication;

import com.rentme.app.address.entity.Address;
import com.rentme.app.address.mapper.AddressMapper;
import com.rentme.app.exception.AuthorizationServiceException;
import com.rentme.app.role.repository.RoleRepository;
import com.rentme.app.user.mapper.UserMapper;
import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.user.repository.UserRepository;
import com.rentme.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
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
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        user.setAddress(address);

        // Set role
        var role = roleRepository.findByRole("USER")
                .orElseThrow(() -> new AuthorizationServiceException("Role 'user' does not exists."));

        user.setRoles(List.of(role));

        userRepository.save(user);
        return GlobalResponse.success();
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
