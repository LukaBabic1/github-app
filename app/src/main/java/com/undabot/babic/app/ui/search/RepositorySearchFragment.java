package com.undabot.babic.app.ui.search;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;

import javax.inject.Inject;

public final class RepositorySearchFragment extends BaseFragment implements RepositorySearchContract.View {

    public static final String TAG = "SearchFragment";

    @Inject
    RepositorySearchContract.Presenter presenter;

    public static RepositorySearchFragment newInstance() {
        return new RepositorySearchFragment();
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_repository_search;
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }
}
