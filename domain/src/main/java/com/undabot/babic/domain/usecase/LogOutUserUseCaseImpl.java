package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.repository.UserRepository;

import rx.Completable;

public final class LogOutUserUseCaseImpl implements LogOutUserUseCase {

    private final UserRepository userRepository;

    public LogOutUserUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Completable execute() {
        return userRepository.logOutUser();
    }
}
