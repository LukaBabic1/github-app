package com.undabot.babic.data.network.client;

import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.ApiTokenProvider;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.User;

import rx.Single;

public final class AuthorizedUserClient implements UserClient {

    private final ApiConverter apiConverter;
    private final ApiTokenProvider apiTokenProvider;
    private final GitHubService gitHubService;

    private final AuthToken authToken;

    public AuthorizedUserClient(final ApiConverter apiConverter, final ApiTokenProvider apiTokenProvider, final GitHubService gitHubService, final AuthToken authToken) {
        this.apiConverter = apiConverter;
        this.apiTokenProvider = apiTokenProvider;
        this.gitHubService = gitHubService;
        this.authToken = authToken;
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return gitHubService.getUser(authToken.value, username)
                            .map(apiConverter::mapToUser);
    }
}
