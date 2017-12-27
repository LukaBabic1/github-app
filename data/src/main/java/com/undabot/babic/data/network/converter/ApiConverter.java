package com.undabot.babic.data.network.converter;

import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.User;

public interface ApiConverter {

    User mapToUser(ApiUser apiUser);

    final class InvalidApiUserPayloadException extends RuntimeException {

    }
}
