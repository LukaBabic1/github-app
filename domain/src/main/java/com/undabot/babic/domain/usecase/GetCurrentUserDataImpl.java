package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Single;

public final class GetCurrentUserDataImpl implements GetCurrentUserData {

    private final UserRepository userRepository;

    public GetCurrentUserDataImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<User> execute() {
        return userRepository.fetchCurrentUser();
    }
}
