package com.undabot.babic.domain.usecase;

import rx.Completable;

public interface FetchAndStoreCurrentUserUsernameUseCase {

    Completable execute();
}
