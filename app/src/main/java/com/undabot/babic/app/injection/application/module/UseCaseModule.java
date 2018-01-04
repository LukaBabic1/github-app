package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.domain.repository.AuthorizationRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.repository.UserRepository;
import com.undabot.babic.domain.usecase.GetAuthTokenUseCase;
import com.undabot.babic.domain.usecase.GetAuthTokenUseCaseImpl;
import com.undabot.babic.domain.usecase.GetGithubAuthorizeUrl;
import com.undabot.babic.domain.usecase.GetGithubAuthorizeUrlImpl;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCase;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCaseImpl;
import com.undabot.babic.domain.usecase.GetUserDataUseCase;
import com.undabot.babic.domain.usecase.GetUserDataUseCaseImpl;
import com.undabot.babic.domain.usecase.SearchMoreRepositoriesUseCase;
import com.undabot.babic.domain.usecase.SearchMoreRepositoriesUseCaseImpl;
import com.undabot.babic.domain.usecase.SearchRepositoriesUseCase;
import com.undabot.babic.domain.usecase.SearchRepositoriesUseCaseImpl;
import com.undabot.babic.domain.usecase.StoreAuthTokenUseCase;
import com.undabot.babic.domain.usecase.StoreAuthTokenUseCaseImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class UseCaseModule {

    @Provides
    GetGithubAuthorizeUrl provideGetGithubAuthorizeUrl(final AuthorizationRepository authorizationRepository) {
        return new GetGithubAuthorizeUrlImpl(authorizationRepository);
    }

    @Provides
    GetAuthTokenUseCase provideGetAuthTokenUseCase(final AuthorizationRepository authorizationRepository) {
        return new GetAuthTokenUseCaseImpl(authorizationRepository);
    }

    @Provides
    StoreAuthTokenUseCase provideStoreAuthTokenUseCase() {
        return new StoreAuthTokenUseCaseImpl();
    }

    @Provides
    GetUserDataUseCase provideGetUserDataUseCase(final UserRepository userRepository) {
        return new GetUserDataUseCaseImpl(userRepository);
    }

    @Provides
    GetRepositoryDetailsUseCase provideGetRepositoryByIdUseCase(final CodeRepositoryRepository codeRepositoryRepository) {
        return new GetRepositoryDetailsUseCaseImpl(codeRepositoryRepository);
    }

    @Provides
    SearchRepositoriesUseCase provideSearchRepositoriesUseCase(final CodeRepositoryRepository codeRepositoryRepository) {
        return new SearchRepositoriesUseCaseImpl(codeRepositoryRepository);
    }

    @Provides
    SearchMoreRepositoriesUseCase provideSearchMoreRepositoriesUseCase(final CodeRepositoryRepository codeRepositoryRepository) {
        return new SearchMoreRepositoriesUseCaseImpl(codeRepositoryRepository);
    }

    public interface Exposes {

        GetGithubAuthorizeUrl getGithubAuthorizeUrl();

        GetAuthTokenUseCase getAuthTokenUseCase();

        StoreAuthTokenUseCase storeAuthTokenUseCase();

        GetUserDataUseCase getUserDataUseCase();

        GetRepositoryDetailsUseCase getRepositoryByIdUseCase();

        SearchRepositoriesUseCase searchRepositoriesUseCase();

        SearchMoreRepositoriesUseCase searchMoreRepositoriesUseCase();
    }
}
