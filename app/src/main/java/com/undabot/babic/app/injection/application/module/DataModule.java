package com.undabot.babic.app.injection.application.module;

import android.content.Context;

import com.google.gson.Gson;
import com.undabot.babic.app.injection.ForApplication;
import com.undabot.babic.data.cache.CodeRepositoryCache;
import com.undabot.babic.data.cache.CodeRepositoryCacheImpl;
import com.undabot.babic.data.cache.UserCache;
import com.undabot.babic.data.cache.UserCacheImpl;
import com.undabot.babic.data.prefs.UserSharedPrefs;
import com.undabot.babic.data.prefs.UserSharedPrefsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    UserSharedPrefs provideUserSharedPreferences(@ForApplication final Context context) {
        return UserSharedPrefsImpl.create(context);
    }

    @Provides
    @Singleton
    CodeRepositoryCache provideRepositoriesCache() {
        return new CodeRepositoryCacheImpl();
    }

    @Provides
    @Singleton
    UserCache provideUserCache() {
        return new UserCacheImpl();
    }

    public interface Exposes {

        CodeRepositoryCache repositoriesCache();

        UserCache userCache();
    }
}
