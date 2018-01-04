package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthorizationUrl;

import rx.Single;

public interface GetGithubAuthorizeUrl {

    Single<AuthorizationUrl> execute();
}
