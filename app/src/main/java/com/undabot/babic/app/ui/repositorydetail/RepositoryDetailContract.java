package com.undabot.babic.app.ui.repositorydetail;

import com.undabot.babic.app.base.BaseView;
import com.undabot.babic.app.base.ScopedPresenter;

public final class RepositoryDetailContract {

    private RepositoryDetailContract() {

    }

    public interface View extends BaseView {

        void render(RepositoryDetailViewModel viewModel);
    }

    public interface Presenter extends ScopedPresenter {

        void init(String repositoryName, String username);

        void showUserDetails();

        void showRepositoryOnGithub();
    }
}
