package com.undabot.babic.app.ui.repositorydetail;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;

import javax.inject.Inject;

public final class RepositoryDetailFragment extends BaseFragment implements RepositoryDetailContract.View {

    public static final String TAG = "RepositoryDetailFragment";

    @Inject
    RepositoryDetailContract.Presenter presenter;

    public static RepositoryDetailFragment newInstance(final int repositoryId) {
        return new RepositoryDetailFragment();
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_repository_details;
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }
}
