package com.undabot.babic.domain.usecase;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Single;

public final class IsUserSignedInUseCaseImpl implements IsUserSignedInUseCase {

    private final UserRepository userRepository;

    public IsUserSignedInUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<Boolean> execute() {
        return userRepository.getUserAuthToken()
                             .map(Optional::isPresent);
    }
}
