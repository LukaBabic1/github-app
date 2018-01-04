package com.undabot.babic.app.ui.splash;

import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.domain.usecase.IsUserSignedInUseCase;

import javax.inject.Inject;

import rx.functions.Action1;

public final class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    IsUserSignedInUseCase isUserSignedInUseCase;

    public SplashPresenter(final SplashContract.View view) {
        super(view);
    }

    @Override
    public void init() {
        viewActionQueue.subscribeTo(isUserSignedInUseCase.execute()
                                                         .map(this::mapToViewAction)
                                                         .subscribeOn(backgroundScheduler),
                                    this::logError);
    }

    private Action1<SplashContract.View> mapToViewAction(final boolean isSignedIn) {
        if (isSignedIn) {
            return view -> router.showMainScreen();
        } else {
            return view -> router.showLoginScreen();
        }
    }
}
