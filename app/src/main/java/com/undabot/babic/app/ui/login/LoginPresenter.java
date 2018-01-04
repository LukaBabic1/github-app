package com.undabot.babic.app.ui.login;

import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.domain.usecase.GetGithubAuthorizeUrl;

import javax.inject.Inject;

import rx.functions.Action1;

public final class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    GetGithubAuthorizeUrl getGithubAuthorizeUrl;

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
                                    this::logError);
    }

    private Action1<LoginContract.View> mapToViewAction(final LoginViewModel viewModel) {
        return view -> view.render(viewModel);
    }
}
