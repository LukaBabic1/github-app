package com.undabot.babic.domain.repository;

import com.undabot.babic.domain.model.User;

import rx.Single;

public interface UserRepository {

    Single<User> fetchUser(String username);

    Single<User> getUser(String username);
}
