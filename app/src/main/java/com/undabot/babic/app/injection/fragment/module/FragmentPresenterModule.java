package com.undabot.babic.app.injection.fragment.module;

import com.undabot.babic.app.injection.fragment.DaggerFragment;
import com.undabot.babic.app.injection.scope.FragmentScope;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailContract;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailPresenter;
import com.undabot.babic.app.ui.search.RepositorySearchContract;
import com.undabot.babic.app.ui.search.RepositorySearchPresenter;
import com.undabot.babic.app.ui.userdetails.UserDetailsContract;
import com.undabot.babic.app.ui.userdetails.UserDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public final class FragmentPresenterModule {

    private final DaggerFragment fragment;

    public FragmentPresenterModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    RepositorySearchContract.Presenter provideSearchPresenter() {
        final RepositorySearchPresenter presenter = new RepositorySearchPresenter((RepositorySearchContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    @Provides
    @FragmentScope
    RepositoryDetailContract.Presenter provideRepositoryDetailPresenter() {
        final RepositoryDetailPresenter presenter = new RepositoryDetailPresenter((RepositoryDetailContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    @Provides
    @FragmentScope
    UserDetailsContract.Presenter provideUserDetailsPresenter() {
        final UserDetailsPresenter presenter = new UserDetailsPresenter((UserDetailsContract.View) fragment);
        fragment.getFragmentComponent().inject(presenter);

        return presenter;
    }

    public interface Exposes {

    }
}
