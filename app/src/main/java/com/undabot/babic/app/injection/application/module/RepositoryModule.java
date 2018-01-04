package com.undabot.babic.app.injection.application.module;

import android.content.res.Resources;

import com.undabot.babic.data.cache.CodeRepositoryCache;
import com.undabot.babic.data.cache.UserCache;
import com.undabot.babic.data.network.client.CodeRepositoryClient;
import com.undabot.babic.data.network.client.UserClient;
import com.undabot.babic.data.repository.AuthorizationRepositoryImpl;
import com.undabot.babic.data.repository.CodeRepositoryRepositoryImpl;
import com.undabot.babic.data.repository.UserRepositoryImpl;
import com.undabot.babic.domain.repository.AuthorizationRepository;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class RepositoryModule {

    @Provides
    AuthorizationRepository provideAuthorizationRepository(final Resources resources) {
        return new AuthorizationRepositoryImpl(resources);
    }

    @Provides
    @Singleton
    CodeRepositoryRepository provideCodeRepositoryRepository(final CodeRepositoryClient codeRepositoryClient, final CodeRepositoryCache codeRepositoryCache) {
        return new CodeRepositoryRepositoryImpl(codeRepositoryClient, codeRepositoryCache);
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(final UserClient userClient, final UserCache userCache) {
        return new UserRepositoryImpl(userClient, userCache);
    }

    public interface Exposes {

        AuthorizationRepository authorizationRepository();

        CodeRepositoryRepository codeRepositoryRepository();

        UserRepository userRepository();
    }
}
