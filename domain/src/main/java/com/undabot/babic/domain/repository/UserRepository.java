package com.undabot.babic.domain.repository;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.User;

import rx.Completable;
import rx.Single;

public interface UserRepository {

    Completable saveAuthToken(AuthToken authToken);

    Single<User> fetchCurrentUser();

    Single<String> getCurrentUserUsername();

    Single<User> fetchUser(String username);

    Single<Optional<User>> getCachedUser(String username);

    Completable cacheUser(User user);

    Completable saveCurrentUserUsername(String username);

    Single<Optional<AuthToken>> getUserAuthToken();

    Completable logOutUser();
}
