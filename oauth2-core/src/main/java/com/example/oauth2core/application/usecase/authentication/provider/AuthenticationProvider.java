package com.example.oauth2core.application.usecase.authentication.provider;

import com.example.oauth2core.application.usecase.authentication.AccessTokenRequest;
import com.example.oauth2core.application.usecase.authentication.AccessTokenResponse;

public interface AuthenticationProvider {
    String ERROR_UNKNOWN_CLIENT = "AUTHENTICATION_PROVIDER.CLIENT_UNKNOWN_CLIENT";
    String ERROR_UNAUTHORIZED_AUTH_FLOW = "AUTHENTICATION_PROVIDER.UNAUTHORIZED_AUTH_FLOW";
    String ERROR_INVALID_CLIENT_SECRET = "AUTHENTICATION_PROVIDER.INVALID_CLIENT_SECRET";
    String ERROR_INVALID_RESOURCE_OWNER_CREDENTIALS = "AUTHENTICATION_PROVIDER.INVALID_RESOURCE_OWNER_CREDENTIALS";

    AccessTokenResponse authenticate(AccessTokenRequest accessTokenRequest);
}
