package com.undabot.babic.data.repository;

import com.undabot.babic.data.network.client.CodeRepositoryClient;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import java.util.List;

import rx.Single;

public final class CodeRepositoryRepositoryImpl implements CodeRepositoryRepository {

    private static final int PER_PAGE_COUNT = 50;

    private final CodeRepositoryClient codeRepositoryClient;

    public CodeRepositoryRepositoryImpl(final CodeRepositoryClient codeRepositoryClient) {
        this.codeRepositoryClient = codeRepositoryClient;
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final SearchOrder searchOrder) {
        return codeRepositoryClient.searchRepositories(query, searchOrder, PER_PAGE_COUNT);
    }

    @Override
    public Single<List<CodeRepository>> searchMoreRepositories(final String query, final SearchOrder searchOrder, final int page) {
        return codeRepositoryClient.searchRepositories(query, searchOrder, PER_PAGE_COUNT, page);
    }
}
