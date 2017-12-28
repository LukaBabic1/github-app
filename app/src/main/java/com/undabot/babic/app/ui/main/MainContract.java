package com.undabot.babic.app.ui.main;

import com.undabot.babic.app.base.BaseView;
import com.undabot.babic.app.base.ScopedPresenter;

public final class MainContract {

    private MainContract() {

    }

    public interface View extends BaseView {

    }

    public interface Presenter extends ScopedPresenter {

        void showSearchScreen();
    }
}
