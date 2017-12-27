package com.undabot.babic.app.injection.activity.module;

import com.undabot.babic.app.injection.activity.DaggerActivity;

import dagger.Module;

@Module
public final class ActivityPresenterModule {

    private final DaggerActivity daggerActivity;

    public ActivityPresenterModule(final DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

}
