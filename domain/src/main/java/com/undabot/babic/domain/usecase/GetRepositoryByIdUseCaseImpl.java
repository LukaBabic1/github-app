package com.undabot.babic.domain.usecase;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import rx.Observable;
import rx.Single;

public final class GetRepositoryByIdUseCaseImpl implements GetRepositoryByIdUseCase {

    private final CodeRepositoryRepository codeRepositoryRepository;

    public GetRepositoryByIdUseCaseImpl(final CodeRepositoryRepository codeRepositoryRepository) {
        this.codeRepositoryRepository = codeRepositoryRepository;
    }

    @Override
    public Observable<CodeRepository> execute(final int id) {
        return getCachedRepository(id).concatWith(fetchCodeRepository(id));
    }

    private Observable<CodeRepository> getCachedRepository(final int id) {
        return codeRepositoryRepository.getCachedRepository(id)
                                       .toObservable()
                                       .filter(Optional::isPresent)
                                       .map(Optional::get);
    }

    private Observable<CodeRepository> fetchCodeRepository(final int id) {
        return codeRepositoryRepository.getRepository(id)
                                       .flatMap(this::cacheRepository)
                                       .toObservable();
    }

    private Single<CodeRepository> cacheRepository(final CodeRepository codeRepository) {
        return codeRepositoryRepository.cacheRepository(codeRepository)
                                       .andThen(Single.just(codeRepository));
    }
}
