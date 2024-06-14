package com.rentme.app.security.model;

public record TokenSettingRequest(
        String tokenType,
        int accessTokenTimeToLive,
        int refreshTokenTimeToLive,
        int authorizationCodeTimeToLive,
        boolean reuseRefreshToken
) {

}
