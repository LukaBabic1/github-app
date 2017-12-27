package com.undabot.babic.app.injection.activity.module;

import com.undabot.babic.app.injection.activity.DaggerActivity;
import com.undabot.babic.app.ui.main.MainContract;
import com.undabot.babic.app.ui.main.MainPresenter;
import com.undabot.babic.app.ui.splash.SplashContract;
import com.undabot.babic.app.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityPresenterModule {

    private final DaggerActivity daggerActivity;

    public ActivityPresenterModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    SplashContract.Presenter provideSplashPresenter() {
        final SplashPresenter presenter = new SplashPresenter((SplashContract.View) daggerActivity);
        daggerActivity.getActivityComponent().inject(presenter);

        return presenter;
    }

    @Provides
    MainContract.Presenter provideMainPresenter() {
        final MainPresenter presenter = new MainPresenter((MainContract.View) daggerActivity);
        daggerActivity.getActivityComponent().inject(presenter);

        return presenter;
    }

    public interface Exposes {

    }
}
