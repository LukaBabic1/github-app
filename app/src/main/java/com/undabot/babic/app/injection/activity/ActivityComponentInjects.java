package com.undabot.babic.app.injection.activity;

import com.undabot.babic.app.ui.main.MainActivity;
import com.undabot.babic.app.ui.main.MainPresenter;
import com.undabot.babic.app.ui.splash.SplashActivity;
import com.undabot.babic.app.ui.splash.SplashPresenter;

public interface ActivityComponentInjects {

    void inject(SplashActivity activity);

    void inject(SplashPresenter presenter);

    void inject(MainActivity activity);

    void inject(MainPresenter presenter);
}
