package com.undabot.babic.app.injection.application;

import android.content.Context;
import android.content.res.Resources;

import com.undabot.babic.app.application.GithubApplication;
import com.undabot.babic.app.injection.ForApplication;
import com.undabot.babic.domain.delegate.UserComponentDelegate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final GithubApplication githubApplication;

    public ApplicationModule(final GithubApplication githubApplication) {
        this.githubApplication = githubApplication;
    }

    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return githubApplication;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return githubApplication.getResources();
    }

    @Provides
    @Singleton
    UserComponentDelegate userComponentDelegate() {
        return githubApplication;
    }

    public interface Exposes {

        @ForApplication
        Context context();

        Resources resources();

        UserComponentDelegate userComponentDelegate();
    }
}
