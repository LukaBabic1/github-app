package com.undabot.babic.app.injection.user;

import com.undabot.babic.app.injection.scope.UserScope;
import com.undabot.babic.data.network.client.AuthorizedCodeRepositoryClient;
import com.undabot.babic.data.network.client.AuthorizedUserClient;
import com.undabot.babic.data.network.client.CodeRepositoryClient;
import com.undabot.babic.data.network.client.CodeRepositoryClientImpl;
import com.undabot.babic.data.network.client.UserClient;
import com.undabot.babic.data.network.client.UserClientImpl;
import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.ApiTokenProvider;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.AuthToken;

import dagger.Module;
import dagger.Provides;

@Module
public final class UserApiClientModule {

    private final AuthToken authToken;

    public UserApiClientModule(final AuthToken authToken) {
        this.authToken = authToken;
    }

    @Provides
    @UserScope
    CodeRepositoryClient provideCodeRepositoryClient(final ApiConverter apiConverter, final GitHubService gitHubService) {
        if (AuthToken.EMPTY.equals(authToken)) {
            return new CodeRepositoryClientImpl(apiConverter, gitHubService);
        } else {
            return new AuthorizedCodeRepositoryClient(apiConverter, gitHubService, authToken);
        }
    }

    @Provides
    @UserScope
    UserClient provideUserClient(final ApiConverter apiConverter, final ApiTokenProvider apiTokenProvider, final GitHubService gitHubService) {
        if (AuthToken.EMPTY.equals(authToken)) {
            return new UserClientImpl(apiConverter, apiTokenProvider, gitHubService);
        } else {
            return new AuthorizedUserClient(apiConverter, apiTokenProvider, gitHubService, authToken);
        }
    }

    public interface Exposes {

    }
}
