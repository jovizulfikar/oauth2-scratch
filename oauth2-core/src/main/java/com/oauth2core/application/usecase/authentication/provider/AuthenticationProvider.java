package com.oauth2core.application.usecase.authentication.provider;

import com.oauth2core.application.usecase.authentication.AccessTokenRequest;
import com.oauth2core.application.usecase.authentication.AccessTokenResponse;

public interface AuthenticationProvider {
    String ERROR_UNKNOWN_CLIENT = "AUTHENTICATION_PROVIDER.CLIENT_UNKNOWN_CLIENT";
    String ERROR_UNAUTHORIZED_CLIENT = "AUTHENTICATION_PROVIDER.UNAUTHORIZED_CLIENT";
    String ERROR_INVALID_RESOURCE_OWNER_CREDENTIALS = "AUTHENTICATION_PROVIDER.INVALID_RESOURCE_OWNER_CREDENTIALS";

    AccessTokenResponse authenticate(AccessTokenRequest accessTokenRequest);
}