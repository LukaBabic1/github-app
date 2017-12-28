package com.undabot.babic.data.network.client;

import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.ApiTokenProvider;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.User;

import rx.Single;

public final class UserClientImpl implements UserClient {

    private final ApiConverter apiConverter;
    private final ApiTokenProvider apiTokenProvider;
    private final GitHubService gitHubService;

    public UserClientImpl(final ApiConverter apiConverter, final ApiTokenProvider apiTokenProvider, final GitHubService gitHubService) {
        this.apiConverter = apiConverter;
        this.apiTokenProvider = apiTokenProvider;
        this.gitHubService = gitHubService;
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return gitHubService.getUser(apiTokenProvider.getApiToken(), username)
                            .map(apiConverter::mapToUser);
    }
}
