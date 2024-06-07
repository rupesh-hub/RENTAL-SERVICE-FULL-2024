package com.rentme.app.user.resource;

import com.rentme.app.authentication.IAuthenticationService;
import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "authentication")
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> save(@RequestBody @Valid RegistrationRequest request)
            throws MethodArgumentNotValidException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
