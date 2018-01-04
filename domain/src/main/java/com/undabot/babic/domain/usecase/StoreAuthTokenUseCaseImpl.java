package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;

import rx.Completable;

public final class StoreAuthTokenUseCaseImpl implements StoreAuthTokenUseCase {

    @Override
    public Completable execute(final AuthToken authToken) {
        return Completable.complete();
    }
}
