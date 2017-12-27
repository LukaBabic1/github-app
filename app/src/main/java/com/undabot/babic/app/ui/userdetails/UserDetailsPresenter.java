package com.undabot.babic.app.ui.userdetails;

import com.undabot.babic.app.base.BasePresenter;

public final class UserDetailsPresenter extends BasePresenter<UserDetailsContract.View> implements UserDetailsContract.Presenter {

    public UserDetailsPresenter(final UserDetailsContract.View view) {
        super(view);
    }
}
