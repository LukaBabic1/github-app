package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Single;

public final class GetCurrentUserDataUseCaseImpl implements GetCurrentUserDataUseCase {

    private final UserRepository userRepository;

    public GetCurrentUserDataUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<User> execute() {
        return userRepository.fetchCurrentUser();
    }
}
