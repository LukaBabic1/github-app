package com.undabot.babic.data.network.client;

import com.undabot.babic.domain.model.AuthToken;

import java.io.IOException;

import rx.Single;

public interface AuthorizationClient {

    Single<AuthToken> getAuthToken(String code);

    final class ApiOAuthAuthorizationException extends RuntimeException {

        public ApiOAuthAuthorizationException(final String message) {
            super(message);
        }

        public ApiOAuthAuthorizationException(final IOException e) {
            super(e);
        }
    }
}
