package com.undabot.babic.data.network.converter;

import com.undabot.babic.domain.utils.DateUtilsImpl;
import com.undabot.babic.domain.utils.StringUtilsImpl;

import org.junit.Before;

public final class ApiConverterTest {

    private ApiConverter apiConverter;

    @Before
    public void setUp() throws Exception {
        apiConverter = new ApiConverterImpl(new DateUtilsImpl(new StringUtilsImpl()), new StringUtilsImpl());
    }
}
