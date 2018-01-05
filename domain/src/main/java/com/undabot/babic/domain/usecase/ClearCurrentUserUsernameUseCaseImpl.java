package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Completable;

public final class ClearCurrentUserUsernameUseCaseImpl implements ClearCurrentUserUsernameUseCase {

    private final UserRepository userRepository;

    public ClearCurrentUserUsernameUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Completable execute() {
        return userRepository.saveCurrentUserUsername(User.EMPTY.username);
    }
}
