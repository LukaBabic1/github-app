package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.repository.AuthorizationRepository;

import rx.Single;

public final class GetAuthTokenUseCaseImpl implements GetAuthTokenUseCase {

    private final AuthorizationRepository authorizationRepository;

    public GetAuthTokenUseCaseImpl(final AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Single<AuthToken> execute(final String code) {
        return authorizationRepository.getAuthTokenFromCode(code);
    }
}
