package com.rentme.app.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_clients")
@DynamicUpdate
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_client_seq_generator")
    @SequenceGenerator(
            name = "_client_seq_generator",
            sequenceName = "_client_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String clientId;
    private String secret;
    private String authentication;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<GrantType> grants;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<RedirectUri> redirectUris;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Scope> scopes;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<AuthenticationMethod> authenticationMethods;

    @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
    private ClientTokenSetting clientTokenSetting;

    public static RegisteredClient to(Client client) {
        return RegisteredClient
                .withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                //.clientAuthenticationMethods(clientAuthMethods(client.getAuthenticationMethods()))
                .clientAuthenticationMethods(convert(client.getAuthenticationMethods(), auth -> new ClientAuthenticationMethod(auth.getAuthenticationMethod())))
                //.authorizationGrantTypes(authGrantTypes(client.getGrants()))
                .authorizationGrantTypes(convert(client.getGrants(), grant -> new AuthorizationGrantType(grant.getGrantType())))
                //.scopes(scopes(client.getScopes()))
                .scopes(convert(client.getScopes(), scope -> scope.getScope()))
                .redirectUris(convert(client.getRedirectUris(), uri -> uri.getUrl()))
                //.redirectUris(redirectUris(client.getRedirectUris()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(client.getClientTokenSetting().getAccessTokenTTL()))
                        .accessTokenFormat(new OAuth2TokenFormat(
                                client.getClientTokenSetting()
                                        .getTokenType()
                        ))
                        .build()

                )
                .build();
    }

    private static Consumer<Set<AuthorizationGrantType>> authGrantTypes(List<GrantType> grants) {
        return s -> {
            for (GrantType type : grants) {
                s.add(new AuthorizationGrantType(type.getGrantType()));
            }
        };
    }

    private static Consumer<Set<ClientAuthenticationMethod>> clientAuthMethods(List<AuthenticationMethod> authenticationMethods) {
        return r -> {
            for (AuthenticationMethod authMethod : authenticationMethods) {
                r.add(new ClientAuthenticationMethod(authMethod.getAuthenticationMethod()));
            }
        };
    }

    private static Consumer<Set<String>> scopes(List<Scope> scopes) {
        return s -> {
            for (Scope scope : scopes) {
                s.add(scope.getScope());
            }
        };
    }

    private static Consumer<Set<String>> redirectUris(List<RedirectUri> redirectUris) {
        return r -> {
            for (RedirectUri redirectUri : redirectUris) {
                r.add(redirectUri.getUrl());
            }
        };
    }

    private static <T, R> Consumer<Set<R>> convert(List<T> source, Function<T, R> mapper) {
        return target -> target.addAll(source.stream().map(mapper).collect(Collectors.toSet()));
    }

}
