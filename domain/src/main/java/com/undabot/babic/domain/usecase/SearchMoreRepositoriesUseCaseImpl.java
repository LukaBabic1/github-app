package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import java.util.List;

import rx.Single;

public final class SearchMoreRepositoriesUseCaseImpl implements SearchMoreRepositoriesUseCase {

    private final CodeRepositoryRepository codeRepositoryRepository;

    public SearchMoreRepositoriesUseCaseImpl(final CodeRepositoryRepository codeRepositoryRepository) {
        this.codeRepositoryRepository = codeRepositoryRepository;
    }

    @Override
    public Single<List<CodeRepository>> execute(final Request request) {
        return codeRepositoryRepository.searchRepositories(request.searchText, request.searchOrder, request.page);
    }
}
