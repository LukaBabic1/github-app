package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;

import rx.Observable;

public interface GetUserDataUseCase {

    Observable<User> execute(String username);
}
