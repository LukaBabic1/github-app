package com.undabot.babic.data.repository;

import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.utils.exception.UnimplementedMethodException;

import rx.Single;

public final class CodeRepositoryRepositoryImpl implements CodeRepositoryRepository {

    @Override
    public Single<User> getUser(final String username) {
        throw new UnimplementedMethodException();
    }

    @Override
    public Single<User> fetchUser(final String username) {
        throw new UnimplementedMethodException();
    }
}
