package com.undabot.babic.data.network.client;

import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import java.util.List;

import rx.Single;

public final class AuthorizedCodeRepositoryClient implements CodeRepositoryClient {

    private static final String AUTHORIZATION_TEMPLATE = "Token %s";

    private static final int DEFAULT_PAGE = 0;
    private static final int ZERO_BASED_OFFSET_FIX = 1;

    private final ApiConverter apiConverter;
    private final GitHubService gitHubService;
    private final AuthToken authToken;

    public AuthorizedCodeRepositoryClient(final ApiConverter apiConverter, final GitHubService gitHubService, final AuthToken authToken) {
        this.apiConverter = apiConverter;
        this.gitHubService = gitHubService;
        this.authToken = authToken;
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final CodeRepositoryRepository.SearchOrder searchOrder, final int perPage) {
        return searchRepositories(query, searchOrder, perPage, DEFAULT_PAGE);
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final CodeRepositoryRepository.SearchOrder searchOrder, final int perPage, final int page) {
        return gitHubService.searchRepositories(getTokenFormatted(), query, searchOrder.value, page + ZERO_BASED_OFFSET_FIX, perPage)
                            .map(apiSearchRepositoriesResponse -> apiConverter.mapToCodeRepositoryList(apiSearchRepositoriesResponse.codeRepositories));
    }

    @Override
    public Single<CodeRepository> fetchRepository(final String repositoryName, final String username) {
        return gitHubService.getRepositoryDetails(getTokenFormatted(), username, repositoryName)
                            .map(apiConverter::mapToCodeRepository);
    }

    private String getTokenFormatted() {
        return String.format(AUTHORIZATION_TEMPLATE, authToken.value);
    }
}
