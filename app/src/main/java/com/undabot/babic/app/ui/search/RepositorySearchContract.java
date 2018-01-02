package com.undabot.babic.app.ui.search;

import android.support.annotation.IntDef;

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

        void showNoInternetConnection();

        void hideNoInternetConnection();

        void enableSearchButton();

        void disableSearchButton();
    }

    public interface Presenter extends ScopedPresenter {

        @IntDef({STARS_SORT, FORKS_SORT, UPDATED_SORT})
        @interface SearchOrderInt {}

        int STARS_SORT = 1000;
        int FORKS_SORT = 2000;
        int UPDATED_SORT = 3000;

        void search(String queryText, @SearchOrderInt int searchOrder);

        void showRepositoryDetails(int id);

        void showUserDetails(String username);
    }
}
