package com.undabot.babic.app.injection.activity.module;

import android.view.LayoutInflater;

import com.undabot.babic.app.ui.search.CodeRepositoriesAdapter;
import com.undabot.babic.app.utils.ui.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public final class UiAdapterModule {

    @Provides
    CodeRepositoriesAdapter provideCodeRepositoriesAdapter(final LayoutInflater inflater, final ImageLoader imageLoader) {
        return new CodeRepositoriesAdapter(inflater, imageLoader);
    }

    public interface Exposes {

        CodeRepositoriesAdapter codeRepositoriesAdapter();
    }
}
