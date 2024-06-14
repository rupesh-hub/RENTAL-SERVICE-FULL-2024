package com.rentme.app.security.resource;

import com.rentme.app.security.model.ClientRegistrationRequest;
import com.rentme.app.security.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> registerClient(@RequestBody final ClientRegistrationRequest request) {
        clientService.save(to(request));
        return ResponseEntity.accepted().build();
    }

    public static RegisteredClient to(ClientRegistrationRequest request) {

        return RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId(request.clientId())
                .clientSecret(request.secret())
                .clientName(request.name())
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(methods -> methods.addAll(
                        request.authenticationMethods()
                                .stream()
                                .map(ClientAuthenticationMethod::new)
                                .collect(Collectors.toSet())
                ))
                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(
                        request.grantTypes().stream()
                                .map(AuthorizationGrantType::new)
                                .collect(Collectors.toSet())
                ))
                .scopes(scopes -> scopes.addAll(request.scopes()))
                .redirectUris(redirectUris -> redirectUris.addAll(request.redirectUris()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(request.tokenSetting().accessTokenTimeToLive()))
                        .refreshTokenTimeToLive(Duration.ofHours(request.tokenSetting().refreshTokenTimeToLive()))
                        .authorizationCodeTimeToLive(Duration.ofHours(request.tokenSetting().authorizationCodeTimeToLive()))
                        .build()
                )
                .build();
    }

}
