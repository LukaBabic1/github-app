package com.undabot.babic.app.injection.application.module;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.undabot.babic.app.BuildConfig;
import com.undabot.babic.data.network.client.AuthorizationClient;
import com.undabot.babic.data.network.client.AuthorizationClientImpl;
import com.undabot.babic.data.network.configuration.Urls;
import com.undabot.babic.data.network.configuration.UrlsImpl;
import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.converter.ApiConverterImpl;
import com.undabot.babic.data.network.interceptor.AcceptV3ApiRequestInterceptor;
import com.undabot.babic.data.network.service.ApiTokenProvider;
import com.undabot.babic.data.network.service.ApiTokenProviderImpl;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.utils.DateUtils;
import com.undabot.babic.domain.utils.StringUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class ApiModule {

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Provides
    AcceptV3ApiRequestInterceptor provideAcceptV3ApiRequestInterceptor() {
        return new AcceptV3ApiRequestInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor interceptor, final AcceptV3ApiRequestInterceptor acceptV3ApiRequestInterceptor) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(acceptV3ApiRequestInterceptor);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(interceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Urls provideUrls(final Resources resources) {
        return new UrlsImpl(resources);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final Urls urls, final OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(urls.getServerUrl().value)
                                     .client(okHttpClient)
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                     .build();
    }

    @Provides
    @Singleton
    ApiTokenProvider provideApiTokenProvider() {
        return new ApiTokenProviderImpl();
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService(final Retrofit retrofit) {
        return retrofit.create(GitHubService.class);
    }

    @Provides
    AuthorizationClient provideAuthorizationClient(final ApiConverter apiConverter, final Gson gson, final OkHttpClient okHttpClient, final Resources resources) {
        return new AuthorizationClientImpl(apiConverter, gson, okHttpClient, resources);
    }

    @Provides
    @Singleton
    ApiConverter provideApiConverter(final DateUtils dateUtils, final StringUtils stringUtils) {
        return new ApiConverterImpl(dateUtils, stringUtils);
    }

    public interface Exposes {

        ApiTokenProvider apiTokenProvider();

        GitHubService gitHubService();

        AuthorizationClient authorizationClient();

        ApiConverter apiConverter();
    }
}
