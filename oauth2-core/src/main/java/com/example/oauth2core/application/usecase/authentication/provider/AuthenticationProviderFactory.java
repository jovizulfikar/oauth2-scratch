package com.example.oauth2core.application.usecase.authentication.provider;

import com.example.oauth2core.common.exception.AppException;
import com.example.oauth2core.domain.oauth2.AuthorizationGrantType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class AuthenticationProviderFactory {

    private final ResourceOwnerPasswordCredentials resourceOwnerPasswordCredentials;
    private final ClientCredentials clientCredentials;

    public static final String ERROR_UNSUPPORTED_GRANT_TYPE = "AUTHENTICATION_PROVIDER_FACTORY.UNSUPPORTED_GRANT_TYPE";

    @SneakyThrows
    public AuthenticationProvider getByGrantType(String grantType) {
        if (AuthorizationGrantType.PASSWORD.getGranType().equals(grantType)) {
            return resourceOwnerPasswordCredentials;
        } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getGranType().equals(grantType))
            return clientCredentials;
        else {
            throw AppException.build(ERROR_UNSUPPORTED_GRANT_TYPE);
        }
    }
}
