package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.domain.delegate.UserComponentDelegate;
import com.undabot.babic.domain.repository.AuthorizationRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.repository.UserRepository;
import com.undabot.babic.domain.usecase.ClearAccessTokenUseCase;
import com.undabot.babic.domain.usecase.ClearAccessTokenUseCaseImpl;
import com.undabot.babic.domain.usecase.GetAuthTokenUseCase;
import com.undabot.babic.domain.usecase.GetAuthTokenUseCaseImpl;
import com.undabot.babic.domain.usecase.GetGithubAuthorizeUrl;
import com.undabot.babic.domain.usecase.GetGithubAuthorizeUrlImpl;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCase;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCaseImpl;
import com.undabot.babic.domain.usecase.GetUserDataUseCase;
import com.undabot.babic.domain.usecase.GetUserDataUseCaseImpl;
import com.undabot.babic.domain.usecase.InitUserComponentUseCase;
import com.undabot.babic.domain.usecase.InitUserComponentUseCaseImpl;
import com.undabot.babic.domain.usecase.IsUserSignedInUseCase;
import com.undabot.babic.domain.usecase.IsUserSignedInUseCaseImpl;
import com.undabot.babic.domain.usecase.LogOutUserUseCase;
import com.undabot.babic.domain.usecase.LogOutUserUseCaseImpl;
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
    StoreAuthTokenUseCase provideStoreAuthTokenUseCase(final UserRepository userRepository) {
        return new StoreAuthTokenUseCaseImpl(userRepository);
    }

    @Provides
    ClearAccessTokenUseCase provideClearAccessTokenUseCase(final StoreAuthTokenUseCase storeAuthTokenUseCase) {
        return new ClearAccessTokenUseCaseImpl(storeAuthTokenUseCase);
    }

    @Provides
    IsUserSignedInUseCase provideIsUserSignedInUseCase(final UserRepository userRepository) {
        return new IsUserSignedInUseCaseImpl(userRepository);
    }

    @Provides
    LogOutUserUseCase provideLogOutUserUseCase(final UserRepository userRepository) {
        return new LogOutUserUseCaseImpl(userRepository);
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

    @Provides
    InitUserComponentUseCase provideInitUserComponentUseCase(final UserComponentDelegate userComponentDelegate) {
        return new InitUserComponentUseCaseImpl(userComponentDelegate);
    }

    public interface Exposes {

        GetGithubAuthorizeUrl getGithubAuthorizeUrl();

        GetAuthTokenUseCase getAuthTokenUseCase();

        StoreAuthTokenUseCase storeAuthTokenUseCase();

        ClearAccessTokenUseCase clearAccessTokenUseCase();

        IsUserSignedInUseCase isUserSignedInUseCase();

        LogOutUserUseCase logOutUserUseCase();

        GetUserDataUseCase getUserDataUseCase();

        GetRepositoryDetailsUseCase getRepositoryByIdUseCase();

        SearchRepositoriesUseCase searchRepositoriesUseCase();

        SearchMoreRepositoriesUseCase searchMoreRepositoriesUseCase();

        InitUserComponentUseCase initUserComponentUseCase();
    }
}
