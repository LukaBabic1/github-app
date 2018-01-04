package com.undabot.babic.app.ui.login;

import com.undabot.babic.app.base.BaseView;
import com.undabot.babic.app.base.ScopedPresenter;

public final class LoginContract {

    private LoginContract() {

    }

    public interface View extends BaseView {

        void render(LoginViewModel viewModel);
    }

    public interface Presenter extends ScopedPresenter {

        void init();

        void exchangeCodeForOAuthToken(String code);
    }
}
