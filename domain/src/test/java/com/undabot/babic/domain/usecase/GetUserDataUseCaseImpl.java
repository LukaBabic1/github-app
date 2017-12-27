package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Single;

public final class GetUserDataUseCaseImpl implements GetUserDataUseCase {

    private final UserRepository userRepository;

    public GetUserDataUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Single<User> execute(final String username) {
        return userRepository.fetchUser(username);
    }
}
