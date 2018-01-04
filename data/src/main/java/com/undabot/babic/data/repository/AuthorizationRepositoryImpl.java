package com.undabot.babic.data.repository;

import android.content.res.Resources;

import com.undabot.babic.data.R;
import com.undabot.babic.data.network.client.AuthorizationClient;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.AuthorizationUrl;
import com.undabot.babic.domain.repository.AuthorizationRepository;

import rx.Single;

public final class AuthorizationRepositoryImpl implements AuthorizationRepository {

    private final AuthorizationClient authorizationClient;
    private final Resources resources;

    public AuthorizationRepositoryImpl(final AuthorizationClient authorizationClient, final Resources resources) {
        this.authorizationClient = authorizationClient;
        this.resources = resources;
    }

    @Override
    public Single<AuthorizationUrl> getGithubAuthorizeUrl() {
        return Single.fromCallable(this::getUrlAsString)
                     .map(AuthorizationUrl::new);
    }

    private String getUrlAsString() {
        return String.format(resources.getString(R.string.github_authorization_url_template),
                             resources.getString(R.string.github_client_id));
    }

    @Override
    public Single<AuthToken> getAuthTokenFromCode(final String code) {
        return authorizationClient.getAuthToken(code);
    }
}
