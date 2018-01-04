package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;

import rx.Single;

public interface GetAuthTokenUseCase {

    Single<AuthToken> execute(String code);
}
