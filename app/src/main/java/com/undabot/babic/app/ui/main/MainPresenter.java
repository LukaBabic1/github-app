package com.undabot.babic.app.ui.main;

import com.undabot.babic.app.base.BasePresenter;

public final class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(final MainContract.View view) {
        super(view);
    }

    @Override
    public void showSearchScreen() {
        router.showRepositorySearchScreen();
    }
}
