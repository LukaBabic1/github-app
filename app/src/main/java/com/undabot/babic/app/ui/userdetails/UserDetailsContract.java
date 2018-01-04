package com.undabot.babic.app.ui.userdetails;

import com.undabot.babic.app.base.BaseView;
import com.undabot.babic.app.base.ScopedPresenter;

public final class UserDetailsContract {

    private UserDetailsContract() {

    }

    public interface View extends BaseView {

        void render(UserDetailViewModel viewModel);

        void showErrorMessage();

        void showBrowserNotAvailableErrorMessage();
    }

    public interface Presenter extends ScopedPresenter {

        void init(String username);

        void visitBlog();
    }
}
