package com.undabot.babic.app.application;

import android.app.Application;
import android.content.Context;

import com.annimon.stream.Optional;
import com.undabot.babic.app.injection.ComponentFactory;
import com.undabot.babic.app.injection.application.ApplicationComponent;
import com.undabot.babic.app.injection.user.UserComponent;
import com.undabot.babic.data.prefs.UserSharedPrefs;
import com.undabot.babic.data.prefs.UserSharedPrefsImpl;
import com.undabot.babic.domain.delegate.UserComponentDelegate;
import com.undabot.babic.domain.model.AuthToken;

public final class GithubApplication extends Application implements UserComponentDelegate {

    private ApplicationComponent applicationComponent;
    private UserComponent userComponent;

    private UserSharedPrefs userSharedPrefs;

    public static GithubApplication from(final Context context) {
        return (GithubApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        userSharedPrefs = UserSharedPrefsImpl.create(this);
        initUserComponent(userSharedPrefs.getAuthToken());
        injectMe();
    }

    private void initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }

    private void initUserComponent(final Optional<AuthToken> authToken) {
        userComponent = ComponentFactory.createUserComponent(applicationComponent, authToken.orElse(AuthToken.EMPTY));
    }

    private void injectMe() {
        applicationComponent.inject(this);
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    @Override
    public void initUserComponent(final AuthToken authToken) {
        initUserComponent(Optional.ofNullable(authToken));
    }
}
