package com.undabot.babic.domain.repository;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.CodeRepository;

import java.util.List;

import rx.Completable;
import rx.Single;

public interface CodeRepositoryRepository {

    enum SearchOrder {

        STARS("stars"),
        FORKS("forks"),
        UPDATED("updated");

        public final String value;

        SearchOrder(final String value) {
            this.value = value;
        }
    }

    Single<List<CodeRepository>> searchRepositories(String query, SearchOrder searchOrder);

    Single<List<CodeRepository>> searchRepositories(String query, SearchOrder searchOrder, int page);

    Completable cacheRepository(CodeRepository codeRepository);

    Completable cacheRepositories(List<CodeRepository> codeRepositories);

    Single<CodeRepository> getRepository(int repositoryId);

    Single<Optional<CodeRepository>> getCachedRepository(int repositoryId);
}
