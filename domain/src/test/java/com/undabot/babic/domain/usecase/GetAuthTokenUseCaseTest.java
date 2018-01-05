package com.undabot.babic.domain.usecase;

import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.repository.AuthorizationRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import rx.Single;

/**
 * An example of use case test.
 */
public final class GetAuthTokenUseCaseTest {

    private AuthorizationRepository mockAuthorizationRepository;

    private GetAuthTokenUseCase getAuthTokenUseCase;

    @Before
    public void setUp() throws Exception {
        mockAuthorizationRepository = Mockito.mock(AuthorizationRepository.class);
        getAuthTokenUseCase = new GetAuthTokenUseCaseImpl(mockAuthorizationRepository);

        Mockito.when(mockAuthorizationRepository.getAuthTokenFromCode(Mockito.anyString())).thenReturn(Single.just(AuthToken.EMPTY));
    }

    @Test
    public void testUseCaseExecuting() throws Exception {
        getAuthTokenUseCase.execute("code").toBlocking();

        Mockito.verify(mockAuthorizationRepository, Mockito.times(1)).getAuthTokenFromCode("code");
    }
}
