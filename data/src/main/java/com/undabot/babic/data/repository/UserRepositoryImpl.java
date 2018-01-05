package com.undabot.babic.data.repository;

import com.annimon.stream.Optional;
import com.undabot.babic.data.cache.UserCache;
import com.undabot.babic.data.network.client.UserClient;
import com.undabot.babic.data.prefs.UserSharedPrefs;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Completable;
import rx.Single;

public final class UserRepositoryImpl implements UserRepository {

    private final UserClient userClient;
    private final UserCache userCache;
    private final UserSharedPrefs userSharedPrefs;

    public UserRepositoryImpl(final UserClient userClient, final UserCache userCache, final UserSharedPrefs userSharedPrefs) {
        this.userClient = userClient;
        this.userCache = userCache;
        this.userSharedPrefs = userSharedPrefs;
    }

    @Override
    public Completable saveAuthToken(final AuthToken authToken) {
        return Completable.fromAction(() -> userSharedPrefs.saveAuthToken(authToken));
    }

    @Override
    public Single<User> fetchCurrentUser() {
        return userClient.fetchCurrentUser();
    }

    @Override
    public Single<String> getCurrentUserUsername() {
        return Single.fromCallable(userSharedPrefs::getCurrentUserUsername);
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return userClient.fetchUser(username);
    }

    @Override
    public Single<Optional<User>> getCachedUser(final String username) {
        return userCache.getCachedUser(username);
    }

    @Override
    public Completable cacheUser(final User user) {
        return userCache.cacheUser(user);
    }

    @Override
    public Completable saveCurrentUserUsername(final String username) {
        return Completable.fromAction(() -> userSharedPrefs.saveCurrentUserUsername(username));
    }

    @Override
    public Single<Optional<AuthToken>> getUserAuthToken() {
        return Single.fromCallable(userSharedPrefs::getAuthToken);
    }

    @Override
    public Completable logOutUser() {
        return userClient.logOut();
    }
}
