package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.repository.UserRepository;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCase;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCaseImpl;
import com.undabot.babic.domain.usecase.GetUserDataUseCase;
import com.undabot.babic.domain.usecase.GetUserDataUseCaseImpl;
import com.undabot.babic.domain.usecase.SearchMoreRepositoriesUseCase;
import com.undabot.babic.domain.usecase.SearchMoreRepositoriesUseCaseImpl;
import com.undabot.babic.domain.usecase.SearchRepositoriesUseCase;
import com.undabot.babic.domain.usecase.SearchRepositoriesUseCaseImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class UseCaseModule {

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

        GetUserDataUseCase getUserDataUseCase();

        GetRepositoryDetailsUseCase getRepositoryByIdUseCase();

        SearchRepositoriesUseCase searchRepositoriesUseCase();

        SearchMoreRepositoriesUseCase searchMoreRepositoriesUseCase();
    }
}
