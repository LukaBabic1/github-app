package com.undabot.babic.data.network.client;

import com.undabot.babic.domain.model.User;

import rx.Completable;
import rx.Single;

public interface UserClient {

    Single<User> fetchCurrentUser();

    Single<User> fetchUser(String username);

    Completable logOut();

    final class UnauthorizedLogOutApiCallException extends RuntimeException {

    }
}
