package com.undabot.babic.data.network.client;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.undabot.babic.data.R;
import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.model.ApiAccessTokenResponse;
import com.undabot.babic.domain.model.AuthToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Single;
import rx.SingleEmitter;

public final class AuthorizationClientImpl implements AuthorizationClient {

    private static final String ACCEPT_HEADER = "Accept";
    private static final String ACCEPT_HEADER_VALUE = "application/json";

    private final ApiConverter apiConverter;
    private final Gson gson;
    private final OkHttpClient okHttpClient;
    private final Resources resources;

    public AuthorizationClientImpl(final ApiConverter apiConverter, final Gson gson, final OkHttpClient okHttpClient, final Resources resources) {
        this.apiConverter = apiConverter;
        this.gson = gson;
        this.okHttpClient = okHttpClient;
        this.resources = resources;
    }

    @Override
    public Single<AuthToken> getAuthToken(final String code) {
        return Single.fromEmitter(emitter -> getAuthTokenInternal(emitter, code));
    }

    private void getAuthTokenInternal(final SingleEmitter<AuthToken> emitter, final String code) {
        okHttpClient.newCall(getOAuthTokenRequest(code))
                    .enqueue(new Callback() {

                        @Override
                        public void onFailure(@NonNull final Call call, @NonNull final IOException e) {
                            emitter.onError(new ApiOAuthAuthorizationException(e));
                        }

                        @Override
                        public void onResponse(@NonNull final Call call, @NonNull final Response response) throws IOException {
                            if (response.isSuccessful()) {
                                emitter.onSuccess(getTokenFromResponse(response));
                            } else {
                                emitter.onError(new ApiOAuthAuthorizationException(response.message()));
                            }
                        }
                    });
    }

    private AuthToken getTokenFromResponse(final @NonNull Response response) throws IOException {
        return apiConverter.mapToAuthToken(gson.fromJson(response.body().string(), ApiAccessTokenResponse.class));
    }

    private Request getOAuthTokenRequest(final String code) {
        return new Request.Builder().header(ACCEPT_HEADER, ACCEPT_HEADER_VALUE)
                                    .url(getOAuthUrl(code))
                                    .build();
    }

    private HttpUrl getOAuthUrl(final String code) {
        final String url = String.format(resources.getString(R.string.github_oauth_url_template),
                                         resources.getString(R.string.github_client_id),
                                         resources.getString(R.string.github_secret),
                                         code);

        return HttpUrl.parse(url)
                      .newBuilder()
                      .build();
    }
}
