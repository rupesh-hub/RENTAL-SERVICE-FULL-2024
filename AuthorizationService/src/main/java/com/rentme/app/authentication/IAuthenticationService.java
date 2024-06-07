package com.rentme.app.authentication;

import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.util.GlobalResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface IAuthenticationService {

    GlobalResponse<Void> register(RegistrationRequest request) throws MethodArgumentNotValidException;

}
