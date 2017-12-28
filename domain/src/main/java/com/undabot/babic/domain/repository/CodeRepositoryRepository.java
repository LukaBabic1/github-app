package com.undabot.babic.domain.repository;

import com.undabot.babic.domain.model.User;

import rx.Single;

public interface CodeRepositoryRepository {

    Single<User> getUser(String username);

    Single<User> fetchUser(String username);
}
