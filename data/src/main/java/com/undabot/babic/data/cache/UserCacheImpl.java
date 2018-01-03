package com.undabot.babic.data.cache;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Completable;
import rx.Single;

public final class UserCacheImpl implements UserCache {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public Completable cacheUser(final User user) {
        return cacheUsers(Collections.singletonList(user));
    }

    @Override
    public Completable cacheUsers(final List<User> users) {
        return Completable.fromAction(() -> cacheUsersInternal(users));
    }

    private void cacheUsersInternal(final List<User> users) {
        synchronized (userMap) {
            for (final User user : users) {
                userMap.put(user.username, user);
            }
        }
    }

    @Override
    public Single<Optional<User>> getCachedUser(final String username) {
        return Single.fromCallable(() -> getCachedUserInternal(username));
    }

    private Optional<User> getCachedUserInternal(final String id) {
        synchronized (userMap) {
            return Optional.ofNullable(userMap.get(id));
        }
    }
}
