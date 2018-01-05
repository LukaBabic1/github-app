package com.undabot.babic.data.network.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public final class AcceptV3ApiRequestInterceptor implements Interceptor {

    private static final String ACCEPT_HEADER_KEY = "Accept";
    private static final String ACCEPT_HEADER_VALUE = "application/vnd.github.v3+json";

    @Override
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request()
                                  .newBuilder()
                                  .addHeader(ACCEPT_HEADER_KEY, ACCEPT_HEADER_VALUE)
                                  .build());
    }
}
