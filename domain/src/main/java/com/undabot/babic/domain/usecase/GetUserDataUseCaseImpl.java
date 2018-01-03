package com.undabot.babic.domain.usecase;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.repository.UserRepository;

import rx.Observable;
import rx.Single;

public final class GetUserDataUseCaseImpl implements GetUserDataUseCase {

    private final UserRepository userRepository;

    public GetUserDataUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Observable<User> execute(final String username) {
        return getCachedUser(username).concatWith(fetchCodeRepository(username))
                                      .distinct();
    }

    private Observable<User> getCachedUser(final String username) {
        return userRepository.getCachedUser(username)
                             .toObservable()
                             .filter(Optional::isPresent)
                             .map(Optional::get);
    }

    private Observable<User> fetchCodeRepository(final String username) {
        return userRepository.fetchUser(username)
                             .flatMap(this::cacheRepository)
                             .toObservable();
    }

    private Single<User> cacheRepository(final User user) {
        return userRepository.cacheUser(user)
                             .andThen(Single.just(user));
    }
}
