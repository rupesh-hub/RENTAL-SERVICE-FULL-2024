package com.rentme.app.security.model;

import java.util.Set;

public record ClientRegistrationRequest(
        String clientId,
        String secret,
        String name,
        TokenSettingRequest tokenSetting,
        Set<String> scopes,
        Set<String> redirectUris,
        Set<String> grantTypes,
        Set<String> authenticationMethods
) {

}