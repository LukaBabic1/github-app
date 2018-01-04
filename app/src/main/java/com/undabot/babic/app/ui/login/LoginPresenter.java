package com.undabot.babic.app.ui.login;

import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.domain.usecase.GetAuthTokenUseCase;
import com.undabot.babic.domain.usecase.GetGithubAuthorizeUrl;
import com.undabot.babic.domain.usecase.StoreAuthTokenUseCase;

import javax.inject.Inject;

import rx.Single;
import rx.functions.Action1;

public final class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    GetGithubAuthorizeUrl getGithubAuthorizeUrl;

    @Inject
    GetAuthTokenUseCase getAuthTokenUseCase;

    @Inject
    StoreAuthTokenUseCase storeAuthTokenUseCase;

    @Inject
    ViewModelConverter viewModelConverter;

    public LoginPresenter(final LoginContract.View view) {
        super(view);
    }

    @Override
    public void init() {
        getAuthorizationUrl();
    }

    private void getAuthorizationUrl() {
        viewActionQueue.subscribeTo(getGithubAuthorizeUrl.execute()
                                                         .subscribeOn(backgroundScheduler)
                                                         .map(viewModelConverter::mapToLoginViewModel)
                                                         .map(this::mapToViewAction),
                                    this::processGetAuthorizationUrlError);
    }

    private Action1<LoginContract.View> mapToViewAction(final LoginViewModel viewModel) {
        return view -> view.render(viewModel);
    }

    private void processGetAuthorizationUrlError(final Throwable throwable) {
        logError(throwable);
    }

    @Override
    public void exchangeCodeForOAuthToken(final String code) {
        viewActionQueue.subscribeTo(getAuthTokenUseCase.execute(code)
                                                       .flatMapCompletable(storeAuthTokenUseCase::execute)
                                                       .andThen(Single.fromCallable(this::mapToViewAction))
                                                       .subscribeOn(backgroundScheduler),
                                    this::getAuthTokenUseCaseError);
    }

    private Action1<LoginContract.View> mapToViewAction() {
        return view -> router.showMainScreen();
    }

    private void getAuthTokenUseCaseError(final Throwable throwable) {
        logError(throwable);
    }

    @Override
    public void skipLogin() {
        router.showMainScreen();
    }
}
