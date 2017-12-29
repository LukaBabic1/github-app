package com.undabot.babic.app.injection.activity.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import com.undabot.babic.app.injection.ForActivity;
import com.undabot.babic.app.injection.activity.DaggerActivity;
import com.undabot.babic.app.injection.scope.ActivityScope;
import com.undabot.babic.app.ui.Router;
import com.undabot.babic.app.ui.RouterImpl;
import com.undabot.babic.app.utils.ActivityUtils;
import com.undabot.babic.app.utils.ActivityUtilsImpl;
import com.undabot.babic.app.utils.ui.ImageLoader;
import com.undabot.babic.app.utils.ui.ImageLoaderImpl;
import com.undabot.babic.app.utils.ui.KeyboardUtils;
import com.undabot.babic.app.utils.ui.KeyboardUtilsImpl;

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
    ImageLoader provideImageLoader(@ForActivity final Context context) {
        return new ImageLoaderImpl(context);
    }

    @Provides
    @ActivityScope
    InputMethodManager provideInputMethodManager(@ForActivity final Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Provides
    @ActivityScope
    KeyboardUtils provideKeyboardUtils(final InputMethodManager inputMethodManager) {
        return new KeyboardUtilsImpl(inputMethodManager);
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater(@ForActivity final Context context) {
        return LayoutInflater.from(context);
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

        ImageLoader imageLoader();

        KeyboardUtils keyboardUtils();

        LayoutInflater layoutInflater();

        Router router();
    }
}
