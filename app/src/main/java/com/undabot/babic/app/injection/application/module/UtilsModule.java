package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.app.ui.ViewModelConverterImpl;
import com.undabot.babic.domain.utils.DateUtils;
import com.undabot.babic.domain.utils.DateUtilsImpl;
import com.undabot.babic.domain.utils.StringUtils;
import com.undabot.babic.domain.utils.StringUtilsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    DateUtils provideDateUtils(final StringUtils stringUtils) {
        return new DateUtilsImpl(stringUtils);
    }

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtilsImpl();
    }

    @Provides
    @Singleton
    ViewModelConverter provideViewModelConverter(final StringUtils stringUtils) {
        return new ViewModelConverterImpl(stringUtils);
    }

    public interface Exposes {

        DateUtils dateUtils();

        StringUtils stringUtils();

        ViewModelConverter viewModelConverter();
    }
}
