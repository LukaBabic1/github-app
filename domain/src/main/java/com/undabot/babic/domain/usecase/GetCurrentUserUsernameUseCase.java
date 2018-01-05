package com.undabot.babic.domain.usecase;

import rx.Single;

public interface GetCurrentUserUsernameUseCase {

    Single<String> execute();
}
