package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.domain.utils.StringUtils;
import com.undabot.babic.domain.utils.StringUtilsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtilsImpl();
    }

    public interface Exposes {

        StringUtils stringUtils();
    }
}
