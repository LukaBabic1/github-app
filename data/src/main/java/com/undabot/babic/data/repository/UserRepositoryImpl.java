package com.undabot.babic.data.repository;

import com.annimon.stream.Optional;
import com.undabot.babic.data.cache.UserCache;
import com.undabot.babic.data.network.client.UserClient;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Single;

public final class UserRepositoryImpl implements UserRepository {

    private final UserClient userClient;
    private final UserCache userCache;

    public UserRepositoryImpl(final UserClient userClient, final UserCache userCache) {
        this.userClient = userClient;
        this.userCache = userCache;
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return userClient.fetchUser(username);
    }

    @Override
    public Single<Optional<User>> getUser(final String username) {
        return userCache.getCachedUser(username);
    }
}
