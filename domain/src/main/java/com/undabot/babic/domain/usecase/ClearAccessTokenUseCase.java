package com.undabot.babic.domain.usecase;

import rx.Completable;

public interface ClearAccessTokenUseCase {

    Completable execute();
}
