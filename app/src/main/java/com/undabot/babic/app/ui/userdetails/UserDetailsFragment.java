package com.undabot.babic.app.ui.userdetails;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;

import javax.inject.Inject;

public final class UserDetailsFragment extends BaseFragment implements UserDetailsContract.View {

    public static final String TAG = "UserDetailsFragment";

    @Inject
    UserDetailsContract.Presenter presenter;

    public static UserDetailsFragment newInstance() {
        return new UserDetailsFragment();
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_user_details;
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }
}
