package com.undabot.babic.data.network.client;

import android.content.res.Resources;
import android.util.Base64;

import com.undabot.babic.data.R;
import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.User;

import java.nio.charset.StandardCharsets;

import rx.Completable;
import rx.Single;

public final class AuthorizedUserClient implements UserClient {

    private static final String BASIC_AUTHORIZATION_TEMPLATE = "Basic %s";
    private static final String AUTHORIZATION_TEMPLATE = "Token %s";

    private final ApiConverter apiConverter;
    private final GitHubService gitHubService;
    private final Resources resources;

    private final AuthToken authToken;

    public AuthorizedUserClient(final ApiConverter apiConverter, final GitHubService gitHubService, final Resources resources, final AuthToken authToken) {
        this.apiConverter = apiConverter;
        this.gitHubService = gitHubService;
        this.resources = resources;
        this.authToken = authToken;
    }

    @Override
    public Single<User> fetchCurrentUser() {
        return gitHubService.getCurrentUser(getTokenFormatted())
                            .map(apiConverter::mapToUser);
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return gitHubService.getUser(getTokenFormatted(), username)
                            .map(apiConverter::mapToUser);
    }

    private String getTokenFormatted() {
        return String.format(AUTHORIZATION_TEMPLATE, authToken.value);
    }

    @Override
    public Completable logOut() {
        return gitHubService.logOut(getBasicAuthorizationToken(), getClientId(), authToken.value)
                            .toCompletable();
    }

    public String getBasicAuthorizationToken() {
        return String.format(BASIC_AUTHORIZATION_TEMPLATE, Base64.encodeToString(getUsernameAndPassword().getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP));
    }

    private String getUsernameAndPassword() {
        return getClientId() + ":" + getClientSecret();
    }

    private String getClientId() {
        return resources.getString(R.string.github_client_id);
    }

    private String getClientSecret() {
        return resources.getString(R.string.github_secret);
    }
}
