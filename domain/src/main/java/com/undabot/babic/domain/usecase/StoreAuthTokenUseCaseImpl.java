package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Completable;

public final class StoreAuthTokenUseCaseImpl implements StoreAuthTokenUseCase {

    private final UserRepository userRepository;

    public StoreAuthTokenUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Completable execute(final AuthToken authToken) {
        return userRepository.saveAuthToken(authToken);
    }
}
