package com.undabot.babic.app.injection.fragment;

import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailFragment;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailPresenter;
import com.undabot.babic.app.ui.search.RepositorySearchFragment;
import com.undabot.babic.app.ui.search.RepositorySearchPresenter;
import com.undabot.babic.app.ui.userdetails.UserDetailsFragment;
import com.undabot.babic.app.ui.userdetails.UserDetailsPresenter;

public interface FragmentComponentInjects {

    void inject(RepositorySearchFragment fragment);

    void inject(RepositorySearchPresenter presenter);

    void inject(RepositoryDetailFragment fragment);

    void inject(RepositoryDetailPresenter presenter);

    void inject(UserDetailsFragment fragment);

    void inject(UserDetailsPresenter presenter);
}
