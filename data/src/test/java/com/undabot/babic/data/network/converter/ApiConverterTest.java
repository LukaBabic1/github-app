package com.undabot.babic.data.network.converter;

import com.undabot.babic.data.network.model.ApiAccessTokenResponse;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.utils.DateUtilsImpl;
import com.undabot.babic.domain.utils.StringUtilsImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * An example how would ApiConverter methods be tested.
 */
public final class ApiConverterTest {

    private ApiConverter apiConverter;

    @Before
    public void setUp() throws Exception {
        apiConverter = new ApiConverterImpl(new DateUtilsImpl(new StringUtilsImpl()), new StringUtilsImpl());
    }

    @Test(expected = ApiConverter.InvalidAuthTokenException.class)
    public void testNullAuthTokenResponseParsingThrowingException() throws Exception {
        apiConverter.mapToAuthToken(null);
    }

    @Test(expected = ApiConverter.InvalidAuthTokenException.class)
    public void testEmptyAuthTokenResponseParsingThrowingException() throws Exception {
        final ApiAccessTokenResponse response = new ApiAccessTokenResponse();
        response.accessToken = "";

        apiConverter.mapToAuthToken(response);
    }

    @Test
    public void testParsingTokenFromNormalResponse() throws Exception {
        final ApiAccessTokenResponse response = new ApiAccessTokenResponse();
        response.accessToken = "auth_token";

        Assert.assertEquals(apiConverter.mapToAuthToken(response), new AuthToken("auth_token"));
    }
}
