package com.undabot.babic.domain.repository;

import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.AuthorizationUrl;

import rx.Single;

public interface AuthorizationRepository {

    Single<AuthorizationUrl> getGithubAuthorizeUrl();

    Single<AuthToken> getAuthTokenFromCode(String code);
}
