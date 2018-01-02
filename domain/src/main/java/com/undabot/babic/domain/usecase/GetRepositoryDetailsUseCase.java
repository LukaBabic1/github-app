package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.CodeRepository;

import rx.Observable;

public interface GetRepositoryDetailsUseCase {

    Observable<CodeRepository> execute(Request request);

    final class Request {

        public final String repositoryName;
        public final String username;

        public Request(final String repositoryName, final String username) {
            this.repositoryName = repositoryName;
            this.username = username;
        }
    }
}
