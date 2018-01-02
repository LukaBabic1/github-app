package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import java.util.List;

import rx.Single;

public final class SearchRepositoriesUseCaseImpl implements SearchRepositoriesUseCase {

    private final CodeRepositoryRepository codeRepositoryRepository;

    public SearchRepositoriesUseCaseImpl(final CodeRepositoryRepository codeRepositoryRepository) {
        this.codeRepositoryRepository = codeRepositoryRepository;
    }

    @Override
    public Single<List<CodeRepository>> execute(final Request request) {
        return codeRepositoryRepository.searchRepositories(request.searchText, request.searchOrder)
                                       .flatMap(this::cacheRepositories);
    }

    private Single<List<CodeRepository>> cacheRepositories(final List<CodeRepository> codeRepositories) {
        return codeRepositoryRepository.cacheRepositories(codeRepositories)
                                                             .andThen(Single.just(codeRepositories));
    }
}
