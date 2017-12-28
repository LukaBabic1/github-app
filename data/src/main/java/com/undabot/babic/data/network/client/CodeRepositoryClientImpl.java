package com.undabot.babic.data.network.client;

import com.undabot.babic.data.network.converter.ApiConverter;
import com.undabot.babic.data.network.service.GitHubService;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository.SearchOrder;

import java.util.List;

import rx.Single;

public final class CodeRepositoryClientImpl implements CodeRepositoryClient {

    private static final int DEFAULT_PAGE = 1;
    private static final int ZERO_BASED_OFFSET_FIX = 1;

    private final ApiConverter apiConverter;
    private final GitHubService gitHubService;

    public CodeRepositoryClientImpl(final ApiConverter apiConverter, final GitHubService gitHubService) {
        this.apiConverter = apiConverter;
        this.gitHubService = gitHubService;
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final SearchOrder searchOrder, final int perPage) {
        return searchRepositories(query, searchOrder, perPage, DEFAULT_PAGE);
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final SearchOrder searchOrder, final int perPage, final int page) {
        return gitHubService.searchRepositories(query, searchOrder.value, perPage, page + ZERO_BASED_OFFSET_FIX)
                            .map(apiSearchRepositoriesResponse -> apiConverter.mapToCodeRepositoryList(apiSearchRepositoriesResponse.codeRepositories));
    }
}
