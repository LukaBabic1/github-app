package com.undabot.babic.app.ui.splash;

import com.undabot.babic.app.base.BaseActivity;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.activity.ActivityComponent;

import javax.inject.Inject;

public final class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter presenter;

    @Override
    protected void inject(final ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected ScopedPresenter getPresenter() {
        return presenter;
    }
}
