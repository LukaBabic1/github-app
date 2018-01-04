package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.delegate.UserComponentDelegate;
import com.undabot.babic.domain.model.AuthToken;

import rx.Completable;

public final class InitUserComponentUseCaseImpl implements InitUserComponentUseCase {

    private final UserComponentDelegate userComponentDelegate;

    public InitUserComponentUseCaseImpl(final UserComponentDelegate userComponentDelegate) {
        this.userComponentDelegate = userComponentDelegate;
    }

    @Override
    public Completable execute(final AuthToken authToken) {
        return Completable.fromAction(() -> userComponentDelegate.initUserComponent(authToken));
    }
}
