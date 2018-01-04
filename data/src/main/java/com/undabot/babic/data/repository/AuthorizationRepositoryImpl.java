package com.undabot.babic.data.repository;

import android.content.res.Resources;

import com.undabot.babic.data.R;
import com.undabot.babic.domain.model.AuthorizationUrl;
import com.undabot.babic.domain.repository.AuthorizationRepository;

import rx.Single;

public final class AuthorizationRepositoryImpl implements AuthorizationRepository {

    private final Resources resources;

    public AuthorizationRepositoryImpl(final Resources resources) {
        this.resources = resources;
    }

    @Override
    public Single<AuthorizationUrl> getGithubAuthorizeUrl() {
        return Single.fromCallable(() -> String.format(resources.getString(R.string.github_authorization_url_template),
                                                       resources.getString(R.string.github_client_id)))
                     .map(AuthorizationUrl::new);
    }
}
