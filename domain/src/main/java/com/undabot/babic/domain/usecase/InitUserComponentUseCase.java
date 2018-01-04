package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;

import rx.Completable;

public interface InitUserComponentUseCase {

    Completable execute(AuthToken authToken);
}
