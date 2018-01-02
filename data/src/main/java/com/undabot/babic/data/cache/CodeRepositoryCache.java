package com.undabot.babic.data.cache;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.CodeRepository;

import java.util.List;

import rx.Completable;
import rx.Single;

public interface CodeRepositoryCache {

    Completable cacheRepositories(List<CodeRepository> codeRepositories);

    Single<Optional<CodeRepository>> getCachedRepository(String repositoryName, String username);
}
