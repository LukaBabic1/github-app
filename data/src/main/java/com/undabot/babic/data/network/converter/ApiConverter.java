package com.undabot.babic.data.network.converter;

import com.undabot.babic.data.network.model.ApiCodeRepository;
import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;

import java.util.List;

public interface ApiConverter {

    User mapToUser(ApiUser apiUser);

    CodeRepository mapToCodeRepository(ApiCodeRepository apiCodeRepository);

    List<CodeRepository> mapToCodeRepositoryList(List<ApiCodeRepository> apiCodeRepositories);

    final class InvalidApiUserPayloadException extends RuntimeException {

    }

    final class InvalidCodeRepositoryPayloadException extends RuntimeException {

    }

    final class InvalidCodeRepositoryListPayloadException extends RuntimeException {

    }
}
