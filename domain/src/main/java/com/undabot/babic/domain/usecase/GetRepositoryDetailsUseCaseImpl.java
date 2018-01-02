package com.undabot.babic.domain.usecase;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;

import rx.Observable;
import rx.Single;

public final class GetRepositoryDetailsUseCaseImpl implements GetRepositoryDetailsUseCase {

    private final CodeRepositoryRepository codeRepositoryRepository;

    public GetRepositoryDetailsUseCaseImpl(final CodeRepositoryRepository codeRepositoryRepository) {
        this.codeRepositoryRepository = codeRepositoryRepository;
    }

    @Override
    public Observable<CodeRepository> execute(final Request request) {
        return getCachedRepository(request).concatWith(fetchCodeRepository(request))
                                           .distinct();
    }

    private Observable<CodeRepository> getCachedRepository(final Request request) {
        return codeRepositoryRepository.getCachedRepository(request.repositoryName, request.username)
                                       .toObservable()
                                       .filter(Optional::isPresent)
                                       .map(Optional::get);
    }

    private Observable<CodeRepository> fetchCodeRepository(final Request request) {
        return codeRepositoryRepository.getRepository(request.repositoryName, request.username)
                                       .flatMap(this::cacheRepository)
                                       .toObservable();
    }

    private Single<CodeRepository> cacheRepository(final CodeRepository codeRepository) {
        return codeRepositoryRepository.cacheRepository(codeRepository)
                                       .andThen(Single.just(codeRepository));
    }
}
