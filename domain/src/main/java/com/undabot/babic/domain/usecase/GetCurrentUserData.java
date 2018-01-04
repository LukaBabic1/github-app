package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;

import rx.Single;

public interface GetCurrentUserData {

    Single<User> execute();
}
