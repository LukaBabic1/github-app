package com.undabot.babic.app.ui.search;

import com.undabot.babic.app.base.BaseView;
import com.undabot.babic.app.base.ScopedPresenter;

public final class RepositorySearchContract {

    private RepositorySearchContract() {

    }

    public interface View extends BaseView {

        void render(RepositorySearchScreenViewModel viewModel);

        void showLoading();

        void hideLoading();

        void hideKeyboard();

        void showErrorDialog();
    }

    public interface Presenter extends ScopedPresenter {

        void search(String queryText);

        void showRepositoryDetails(int id);

        void showUserDetails(int id);
    }
}
