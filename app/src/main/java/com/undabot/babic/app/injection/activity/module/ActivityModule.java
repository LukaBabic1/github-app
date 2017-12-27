package com.undabot.babic.app.injection.activity.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.undabot.babic.app.injection.ForActivity;
import com.undabot.babic.app.injection.activity.DaggerActivity;
import com.undabot.babic.app.injection.scope.ActivityScope;
import com.undabot.babic.app.ui.Router;
import com.undabot.babic.app.ui.RouterImpl;
import com.undabot.babic.app.utils.ActivityUtils;
import com.undabot.babic.app.utils.ActivityUtilsImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private final DaggerActivity activity;

    public ActivityModule(final DaggerActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    ActivityUtils provideActivityUtils() {
        return new ActivityUtilsImpl();
    }

    @Provides
    @ForActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    Router provideRouter(final FragmentManager fragmentManager) {
        return new RouterImpl(activity, fragmentManager);
    }

    public interface Exposes {

        ActivityUtils activityUtils();

        @ForActivity
        Context provideActivityContext();

        Router router();
    }
}
