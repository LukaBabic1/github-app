package com.undabot.babic.app.injection.application.module;

import android.content.Context;

import com.undabot.babic.app.injection.ForApplication;
import com.undabot.babic.app.utils.connectivity.ConnectivityManagerWrapper;
import com.undabot.babic.app.utils.connectivity.ConnectivityManagerWrapperImpl;
import com.undabot.babic.app.utils.connectivity.ConnectivityReceiver;
import com.undabot.babic.app.utils.connectivity.ConnectivityReceiverImpl;
import com.undabot.babic.app.utils.connectivity.NetworkUtils;
import com.undabot.babic.app.utils.connectivity.NetworkUtilsImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public final class ConnectivityModule {

    @Provides
    @Singleton
    ConnectivityManagerWrapper provideConnectivityManagerWrapper(@ForApplication final Context context) {
        return new ConnectivityManagerWrapperImpl(context);
    }

    @Provides
    @Singleton
    ConnectivityReceiver provideConnectivityReceiver(@ForApplication final Context context, final NetworkUtils networkUtils,
                                                     @Named(ThreadingModule.BACKGROUND_SCHEDULER) final Scheduler backgroundScheduler) {

        return new ConnectivityReceiverImpl(context, networkUtils, backgroundScheduler);
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(final ConnectivityManagerWrapper connectivityManagerWrapper) {
        return new NetworkUtilsImpl(connectivityManagerWrapper);
    }

    public interface Exposes {

        ConnectivityReceiver provideConnectivityReceiver();
    }
}
