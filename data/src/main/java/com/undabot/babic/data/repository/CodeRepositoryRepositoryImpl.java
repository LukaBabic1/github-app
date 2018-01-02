package com.undabot.babic.data.repository;

import com.annimon.stream.Optional;
import com.undabot.babic.data.cache.CodeRepositoryCache;
import com.undabot.babic.data.network.client.CodeRepositoryClient;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import java.util.Collections;
import java.util.List;

import rx.Completable;
import rx.Single;

public final class CodeRepositoryRepositoryImpl implements CodeRepositoryRepository {

    private static final int PER_PAGE_COUNT = 50;

    private final CodeRepositoryClient codeRepositoryClient;
    private final CodeRepositoryCache codeRepositoryCache;

    public CodeRepositoryRepositoryImpl(final CodeRepositoryClient codeRepositoryClient, final CodeRepositoryCache codeRepositoryCache) {
        this.codeRepositoryClient = codeRepositoryClient;
        this.codeRepositoryCache = codeRepositoryCache;
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final SearchOrder searchOrder) {
        return codeRepositoryClient.searchRepositories(query, searchOrder, PER_PAGE_COUNT);
    }

    @Override
    public Single<List<CodeRepository>> searchRepositories(final String query, final SearchOrder searchOrder, final int page) {
        return codeRepositoryClient.searchRepositories(query, searchOrder, PER_PAGE_COUNT, page);
    }

    @Override
    public Completable cacheRepository(final CodeRepository codeRepository) {
        return cacheRepositories(Collections.singletonList(codeRepository));
    }

    @Override
    public Completable cacheRepositories(final List<CodeRepository> codeRepositories) {
        return codeRepositoryCache.cacheRepositories(codeRepositories);
    }

    @Override
    public Single<CodeRepository> getRepository(final int repositoryId) {
        return codeRepositoryClient.fetchRepository(repositoryId);
    }

    @Override
    public Single<Optional<CodeRepository>> getCachedRepository(final int repositoryId) {
        return codeRepositoryCache.getCachedRepository(repositoryId);
    }
}
