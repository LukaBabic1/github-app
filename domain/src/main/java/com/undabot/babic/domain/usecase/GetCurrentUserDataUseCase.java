package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;

import rx.Single;

public interface GetCurrentUserDataUseCase {

    Single<User> execute();
}
