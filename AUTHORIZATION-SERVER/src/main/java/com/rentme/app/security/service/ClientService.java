package com.rentme.app.security.service;

import com.rentme.app.security.ClientMapper;
import com.rentme.app.security.entity.*;
import com.rentme.app.security.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(clientMapper.toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        return clientRepository.findById(Long.parseLong(id))
                .map(Client::to)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .map(Client::to)
                .orElseThrow(() -> new RuntimeException(":("));
    }
}

