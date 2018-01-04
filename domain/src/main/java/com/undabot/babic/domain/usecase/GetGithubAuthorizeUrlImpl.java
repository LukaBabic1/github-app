package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthorizationUrl;
import com.undabot.babic.domain.repository.AuthorizationRepository;

import rx.Single;

public final class GetGithubAuthorizeUrlImpl implements GetGithubAuthorizeUrl {

    private final AuthorizationRepository authorizationRepository;

    public GetGithubAuthorizeUrlImpl(final AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Single<AuthorizationUrl> execute() {
        return authorizationRepository.getGithubAuthorizeUrl();
    }
}
