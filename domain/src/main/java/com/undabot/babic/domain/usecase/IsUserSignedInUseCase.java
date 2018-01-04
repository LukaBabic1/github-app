package com.undabot.babic.domain.usecase;

import rx.Single;

public interface IsUserSignedInUseCase {

    Single<Boolean> execute();
}
