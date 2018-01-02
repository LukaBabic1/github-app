package com.undabot.babic.data.cache;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.CodeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Completable;
import rx.Single;

public final class CodeRepositoryCacheImpl implements CodeRepositoryCache {

    private final Map<Integer, CodeRepository> codeRepositoryMap = new HashMap<>();

    @Override
    public Completable cacheRepositories(final List<CodeRepository> codeRepositories) {
        return Completable.fromAction(() -> cacheRepositoriesInternal(codeRepositories));
    }

    private void cacheRepositoriesInternal(final List<CodeRepository> codeRepositories) {
        synchronized (this.codeRepositoryMap) {
            for (final CodeRepository codeRepository : codeRepositories) {
                codeRepositoryMap.put(codeRepository.id, codeRepository);
            }
        }
    }

    @Override
    public Single<Optional<CodeRepository>> getCachedRepository(final int id) {
        return Single.fromCallable(() -> getCachedRepositoryInternal(id));
    }

    private Optional<CodeRepository> getCachedRepositoryInternal(final int id) {
        synchronized (codeRepositoryMap) {
            return Optional.ofNullable(codeRepositoryMap.get(id));
        }
    }
}
