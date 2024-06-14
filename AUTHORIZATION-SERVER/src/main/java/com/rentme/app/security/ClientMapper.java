package com.rentme.app.security;

import com.rentme.app.security.entity.*;
import com.rentme.app.security.model.ClientRegistrationRequest;
import com.rentme.app.security.repository.AuthenticationMethodRepository;
import com.rentme.app.security.repository.GrantTypeRepository;
import com.rentme.app.security.repository.RedirectUriRepository;
import com.rentme.app.security.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class ClientMapper {

    private final AuthenticationMethodRepository authenticationMethodRepository;
    private final ScopeRepository scopeRepository;
    private final GrantTypeRepository grantTypeRepository;
    private final RedirectUriRepository redirectUriRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    * NOT REQUIRED CURRENTLY
    * */
    public Client toEntity(ClientRegistrationRequest request) {
        List<AuthenticationMethod> authenticationMethods = request.authenticationMethods()
                .stream()
                .map(this::getOrCreateAuthenticationMethod)
                .collect(Collectors.toList());

        List<Scope> scopes = request.scopes().stream()
                .map(this::getOrCreateScope)
                .collect(Collectors.toList());

        List<RedirectUri> redirectUris = request.redirectUris().stream()
                .map(this::getOrCreateRedirectUri)
                .collect(Collectors.toList());

        List<GrantType> grantTypes = request.grantTypes().stream()
                .map(this::getOrCreateGrantType)
                .collect(Collectors.toList());

        return Client.builder()
                .clientId(request.clientId())
                .name(request.name())
                .secret(request.secret())
                .issuedAt(new Date().toInstant())
                .authenticationMethods(authenticationMethods)
                .scopes(scopes)
                .redirectUris(redirectUris)
                .grantTypes(grantTypes)
                .tokenSetting(
                        TokenSetting.builder()
                                .tokenType(request.tokenSetting().tokenType())
                                .accessTokenTimeToLive(request.tokenSetting().accessTokenTimeToLive())
                                .refreshTokenTimeToLive(request.tokenSetting().refreshTokenTimeToLive())
                                .authenticationCodeTimeToLive(request.tokenSetting().authorizationCodeTimeToLive())
                                .reuseRefreshToken(request.tokenSetting().reuseRefreshToken())
                                .build()
                )
                .enabled(true)
                .build();
    }

    public Client toEntity(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(passwordEncoder.encode(registeredClient.getClientSecret()));
        client.setName(registeredClient.getClientName());
        client.setIssuedAt(registeredClient.getClientIdIssuedAt());

        client.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(auth -> getOrCreateAuthenticationMethod(auth.getValue()))
                        .toList()
        );

        client.setGrantTypes(
                registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .map(grant -> getOrCreateGrantType(grant.getValue()))
                        .collect(Collectors.toList())
        );

        client.setRedirectUris(
                registeredClient.getRedirectUris()
                        .stream()
                        .map(uri -> getOrCreateRedirectUri(uri))
                        .collect(Collectors.toList())
        );

        client.setScopes(
                registeredClient.getScopes()
                        .stream()
                        .map(scope -> getOrCreateScope(scope))
                        .collect(Collectors.toList())
        );

        client.setTokenSetting(
                TokenSetting
                        .builder()
                        .accessTokenTimeToLive((int) registeredClient.getTokenSettings().getAccessTokenTimeToLive().toHours())
                        .refreshTokenTimeToLive((int) registeredClient.getTokenSettings().getRefreshTokenTimeToLive().toHours())
                        .authenticationCodeTimeToLive((int) registeredClient.getTokenSettings().getAuthorizationCodeTimeToLive().toHours())
                        .client(client)
                        .build()
        );

        client.setEnabled(true);
        return client;
    }


    private AuthenticationMethod getOrCreateAuthenticationMethod(String method) {
        return authenticationMethodRepository.findByAuthenticationMethod(method)
                .orElseGet(() -> authenticationMethodRepository.save(
                        AuthenticationMethod
                                .builder()
                                .authenticationMethod(method)
                                .build()
                ));
    }

    private Scope getOrCreateScope(String scope) {
        return scopeRepository.findByScope(scope)
                .orElseGet(() -> scopeRepository.save(
                        Scope
                                .builder()
                                .scope(scope)
                                .build())
                );
    }

    private RedirectUri getOrCreateRedirectUri(String redirectUri) {
        return redirectUriRepository.findByUri(redirectUri)
                .orElseGet(() -> redirectUriRepository.save(
                        RedirectUri
                                .builder()
                                .uri(redirectUri)
                                .build()
                ));
    }

    private GrantType getOrCreateGrantType(String grantType) {
        return grantTypeRepository.findByGrantType(grantType)
                .orElseGet(() -> grantTypeRepository.save(
                        GrantType
                                .builder()
                                .grantType(grantType)
                                .build()
                ));
    }

}
