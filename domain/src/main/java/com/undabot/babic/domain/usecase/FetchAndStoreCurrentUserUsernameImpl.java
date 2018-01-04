package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.repository.UserRepository;

import rx.Completable;

public final class FetchAndStoreCurrentUserUsernameImpl implements FetchAndStoreCurrentUserUsername {

    private final GetCurrentUserData getCurrentUserData;
    private final UserRepository userRepository;

    public FetchAndStoreCurrentUserUsernameImpl(final GetCurrentUserData getCurrentUserData, final UserRepository userRepository) {
        this.getCurrentUserData = getCurrentUserData;
        this.userRepository = userRepository;
    }

    @Override
    public Completable execute() {
        return getCurrentUserData.execute()
                                 .map(user -> user.username)
                                 .flatMapCompletable(userRepository::saveCurrentUserUsername);
    }
}
