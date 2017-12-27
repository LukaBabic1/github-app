package com.undabot.babic.data.network.converter;

import com.undabot.babic.domain.utils.StringUtils;

public final class ApiConverterImpl implements ApiConverter {

    private final StringUtils stringUtils;

    public ApiConverterImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }
}
