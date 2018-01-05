package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.repository.UserRepository;

import rx.Completable;

public final class FetchAndStoreCurrentUserUsernameUseCaseImpl implements FetchAndStoreCurrentUserUsernameUseCase {

    private final GetCurrentUserDataUseCase getCurrentUserDataUseCase;
    private final UserRepository userRepository;

    public FetchAndStoreCurrentUserUsernameUseCaseImpl(final GetCurrentUserDataUseCase getCurrentUserDataUseCase, final UserRepository userRepository) {
        this.getCurrentUserDataUseCase = getCurrentUserDataUseCase;
        this.userRepository = userRepository;
    }

    @Override
    public Completable execute() {
        return getCurrentUserDataUseCase.execute()
                                        .map(user -> user.username)
                                        .flatMapCompletable(userRepository::saveCurrentUserUsername);
    }
}
