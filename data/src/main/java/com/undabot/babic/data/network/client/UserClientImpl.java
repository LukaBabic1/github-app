package com.undabot.babic.data.network.client;

import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.User;

import rx.Completable;
import rx.Single;

public final class UserClientImpl implements UserClient {

    private final ApiConverter apiConverter;
    private final GitHubService gitHubService;

    public UserClientImpl(final ApiConverter apiConverter, final GitHubService gitHubService) {
        this.apiConverter = apiConverter;
        this.gitHubService = gitHubService;
    }

    @Override
    public Single<User> fetchUser(final String username) {
        return gitHubService.getUser(username)
                            .map(apiConverter::mapToUser);
    }

    @Override
    public Completable logOut() {
        return Completable.error(new UnauthorizedLogOutApiCallException());
    }
}
