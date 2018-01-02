package com.undabot.babic.data.cache;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.CodeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Completable;
import rx.Single;

public final class CodeRepositoryCacheImpl implements CodeRepositoryCache {

    private final Map<Key, CodeRepository> codeRepositoryMap = new HashMap<>();

    @Override
    public Completable cacheRepositories(final List<CodeRepository> codeRepositories) {
        return Completable.fromAction(() -> cacheRepositoriesInternal(codeRepositories));
    }

    private void cacheRepositoriesInternal(final List<CodeRepository> codeRepositories) {
        synchronized (this.codeRepositoryMap) {
            for (final CodeRepository codeRepository : codeRepositories) {
                codeRepositoryMap.put(new Key(codeRepository.name, codeRepository.owner.username), codeRepository);
            }
        }
    }

    @Override
    public Single<Optional<CodeRepository>> getCachedRepository(final String repositoryName, final String username) {
        return Single.fromCallable(() -> getCachedRepositoryInternal(repositoryName, username));
    }

    private Optional<CodeRepository> getCachedRepositoryInternal(final String repositoryName, final String username) {
        synchronized (codeRepositoryMap) {
            return Optional.ofNullable(codeRepositoryMap.get(new Key(repositoryName, username)));
        }
    }

    private static final class Key {

        final String repositoryName;
        final String username;

        private Key(final String repositoryName, final String username) {
            this.repositoryName = repositoryName;
            this.username = username;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Key key = (Key) o;

            if (repositoryName != null ? !repositoryName.equals(key.repositoryName) : key.repositoryName != null) {
                return false;
            }
            return username != null ? username.equals(key.username) : key.username == null;
        }

        @Override
        public int hashCode() {
            int result = repositoryName != null ? repositoryName.hashCode() : 0;
            result = 31 * result + (username != null ? username.hashCode() : 0);
            return result;
        }
    }
}
