package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.app.ui.ViewModelConverterImpl;
import com.undabot.babic.app.utils.view.ViewUtils;
import com.undabot.babic.app.utils.view.ViewUtilsImpl;
import com.undabot.babic.domain.utils.DateUtils;
import com.undabot.babic.domain.utils.DateUtilsImpl;
import com.undabot.babic.domain.utils.ListUtils;
import com.undabot.babic.domain.utils.ListUtilsImpl;
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
    ListUtils provideListUtils() {
        return new ListUtilsImpl();
    }

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtilsImpl();
    }

    @Provides
    @Singleton
    ViewModelConverter provideViewModelConverter(final DateUtils dateUtils, final StringUtils stringUtils) {
        return new ViewModelConverterImpl(dateUtils, stringUtils);
    }

    @Provides
    @Singleton
    ViewUtils provideViewUtils() {
        return new ViewUtilsImpl();
    }

    public interface Exposes {

        DateUtils dateUtils();

        ListUtils listUtils();

        StringUtils stringUtils();

        ViewModelConverter viewModelConverter();

        ViewUtils viewUtils();
    }
}
