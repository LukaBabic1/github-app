package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;

import rx.Completable;

public interface StoreAuthTokenUseCase {

    Completable execute(AuthToken authToken);
}
