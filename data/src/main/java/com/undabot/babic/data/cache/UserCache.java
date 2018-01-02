package com.undabot.babic.data.cache;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.User;

import java.util.List;

import rx.Completable;
import rx.Single;

public interface UserCache {

    Completable cacheUsers(List<User> users);

    Single<Optional<User>> getCachedUser(String username);
}
