package com.rentme.app.security.service;

import com.rentme.app.security.entity.*;
import com.rentme.app.security.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public void save(RegisteredClient registeredClient) {
        Client client = new Client();
        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());

        client.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(auth -> AuthenticationMethod.from(auth, client))
                        .toList()
        );

        client.setGrants(
                registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .map(grant-> GrantType.from(grant, client))
                        .collect(Collectors.toList())
        );

        client.setRedirectUris(
                registeredClient.getRedirectUris()
                        .stream()
                        .map(uri-> RedirectUri.from(uri, client))
                        .collect(Collectors.toList())
        );

        client.setScopes(
                registeredClient.getScopes()
                        .stream()
                        .map(scope-> Scope.from(scope, client))
                        .collect(Collectors.toList())
        );

        clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
       return clientRepository.findById(Long.parseLong(id))
               .map(Client::to)
               .orElseThrow(()-> new RuntimeException(""));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .map(Client::to)
                .orElseThrow(()-> new RuntimeException(":("));
    }


}
