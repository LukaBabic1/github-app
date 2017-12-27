package com.undabot.babic.data.network.converter;

import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.utils.StringUtils;

public final class ApiConverterImpl implements ApiConverter {

    private final StringUtils stringUtils;

    public ApiConverterImpl(final StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public User mapToUser(final ApiUser apiUser) {
        if (apiUser == null) {
            throw new InvalidApiUserPayloadException();
        }

        return mapToUserInternal(apiUser);
    }

    private User mapToUserInternal(final ApiUser apiUser) {
        return new User();
    }
}
