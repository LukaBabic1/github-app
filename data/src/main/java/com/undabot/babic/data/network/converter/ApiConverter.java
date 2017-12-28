package com.undabot.babic.data.network.converter;

import com.undabot.babic.data.network.model.ApiCodeRepository;
import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;

public interface ApiConverter {

    User mapToUser(ApiUser apiUser);

    CodeRepository mapToCodeRepository(ApiCodeRepository apiCodeRepository);

    final class InvalidApiUserPayloadException extends RuntimeException {

    }

    final class InvalidCodeRepositoryPayloadException extends RuntimeException {

    }
}
