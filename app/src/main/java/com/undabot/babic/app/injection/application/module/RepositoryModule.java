package com.undabot.babic.app.injection.application.module;

import com.undabot.babic.data.network.client.CodeRepositoryClient;
import com.undabot.babic.data.network.client.UserClient;
import com.undabot.babic.data.repository.CodeRepositoryRepositoryImpl;
import com.undabot.babic.data.repository.UserRepositoryImpl;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class RepositoryModule {

    @Provides
    @Singleton
    CodeRepositoryRepository provideCodeRepositoryRepository(final CodeRepositoryClient codeRepositoryClient) {
        return new CodeRepositoryRepositoryImpl(codeRepositoryClient);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(final UserClient userClient) {
        return new UserRepositoryImpl(userClient);
    }

    public interface Exposes {

        CodeRepositoryRepository codeRepositoryRepository();

        UserRepository userRepository();
    }
}
