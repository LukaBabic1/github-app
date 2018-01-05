package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.repository.UserRepository;

import rx.Single;

public final class GetCurrentUserUsernameUseCaseImpl implements GetCurrentUserUsernameUseCase {

    private final UserRepository userRepository;

    public GetCurrentUserUsernameUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<String> execute() {
        return userRepository.getCurrentUserUsername();
    }
}
