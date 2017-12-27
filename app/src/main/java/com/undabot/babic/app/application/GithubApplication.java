package com.undabot.babic.app.application;

import android.app.Application;
import android.content.Context;

import com.undabot.babic.app.injection.ComponentFactory;
import com.undabot.babic.app.injection.application.ApplicationComponent;

public final class GithubApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static GithubApplication from(final Context context) {
        return (GithubApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
