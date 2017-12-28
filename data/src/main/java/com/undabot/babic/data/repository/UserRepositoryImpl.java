package com.undabot.babic.data.repository;

import com.undabot.babic.data.network.client.UserClient;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;
import com.undabot.babic.domain.utils.exception.UnimplementedMethodException;

import rx.Single;

public final class UserRepositoryImpl implements UserRepository {

    private final UserClient userClient;

    public UserRepositoryImpl(final UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return userClient.fetchUser(username);
    }

    @Override
    public Single<User> getUser(final String username) {
        throw new UnimplementedMethodException();
    }
}
