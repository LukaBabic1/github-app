package com.undabot.babic.domain.repository;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.User;

import rx.Single;

public interface UserRepository {

    Single<User> fetchUser(String username);

    Single<Optional<User>> getUser(String username);
}
