package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;

import rx.Completable;

public final class ClearAccessTokenUseCaseImpl implements ClearAccessTokenUseCase {

    private final StoreAuthTokenUseCase storeAuthTokenUseCase;

    public ClearAccessTokenUseCaseImpl(final StoreAuthTokenUseCase storeAuthTokenUseCase) {
        this.storeAuthTokenUseCase = storeAuthTokenUseCase;
    }

    @Override
    public Completable execute() {
        return storeAuthTokenUseCase.execute(AuthToken.EMPTY);
    }
}
