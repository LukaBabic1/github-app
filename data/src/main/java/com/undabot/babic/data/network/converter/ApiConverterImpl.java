package com.undabot.babic.data.network.converter;

import com.annimon.stream.Stream;
import com.undabot.babic.data.network.model.ApiAccessTokenResponse;
import com.undabot.babic.data.network.model.ApiCodeRepository;
import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.utils.DateUtils;
import com.undabot.babic.domain.utils.StringUtils;

import java.util.Collections;
import java.util.List;

public final class ApiConverterImpl implements ApiConverter {

    private static final String EMPTY = "";

    private final DateUtils dateUtils;
    private final StringUtils stringUtils;

    public ApiConverterImpl(final DateUtils dateUtils, final StringUtils stringUtils) {
        this.dateUtils = dateUtils;
        this.stringUtils = stringUtils;
    }

    @Override
    public AuthToken mapToAuthToken(final ApiAccessTokenResponse apiAccessTokenResponse) {
        if (apiAccessTokenResponse == null || stringUtils.isEmpty(apiAccessTokenResponse.accessToken)) {
            throw new InvalidAuthTokenException();
        }

        return new AuthToken(apiAccessTokenResponse.accessToken);
    }

    @Override
    public User mapToUser(final ApiUser apiUser) {
        if (apiUser == null) {
            throw new InvalidApiUserPayloadException();
        }

        return mapToUserInternal(apiUser);
    }

    private User mapToUserInternal(final ApiUser apiUser) {
        return new User(apiUser.id,
                        apiUser.username,
                        stringUtils.itOrDefault(apiUser.avatarUrl, EMPTY),
                        stringUtils.itOrDefault(apiUser.siteHtmlUrl, EMPTY),
                        stringUtils.itOrDefault(apiUser.name, EMPTY),
                        stringUtils.itOrDefault(apiUser.type, EMPTY),
                        stringUtils.itOrDefault(apiUser.companyName, EMPTY),
                        stringUtils.itOrDefault(apiUser.location, EMPTY),
                        stringUtils.itOrDefault(apiUser.email, EMPTY),
                        stringUtils.itOrDefault(apiUser.blog, EMPTY),
                        apiUser.siteAdmin,
                        Boolean.TRUE.toString().equalsIgnoreCase(stringUtils.itOrDefault(apiUser.hireable, EMPTY)),
                        apiUser.followers,
                        apiUser.following,
                        dateUtils.convertISO8601ToTimestamp(apiUser.createdAt).getTime(),
                        dateUtils.convertISO8601ToTimestamp(apiUser.updatedAt).getTime(),
                        apiUser.privateGists,
                        apiUser.publicRepos,
                        apiUser.publicGists,
                        apiUser.totalPrivateRepos,
                        apiUser.ownedPrivateRepos);
    }

    @Override
    public CodeRepository mapToCodeRepository(final ApiCodeRepository apiCodeRepository) {
        if (apiCodeRepository == null) {
            throw new InvalidCodeRepositoryPayloadException();
        }

        return mapToCodeRepositoryInternal(apiCodeRepository);
    }

    private CodeRepository mapToCodeRepositoryInternal(final ApiCodeRepository apiCodeRepository) {
        return new CodeRepository(apiCodeRepository.id,
                                  stringUtils.itOrDefault(apiCodeRepository.name, EMPTY),
                                  stringUtils.itOrDefault(apiCodeRepository.fullName, EMPTY),
                                  mapToUser(apiCodeRepository.owner),
                                  stringUtils.itOrDefault(apiCodeRepository.description, EMPTY),
                                  stringUtils.itOrDefault(apiCodeRepository.repositoryUrl, EMPTY),
                                  stringUtils.itOrDefault(apiCodeRepository.homepageUrl, EMPTY),
                                  stringUtils.itOrDefault(apiCodeRepository.language, EMPTY),
                                  dateUtils.convertISO8601ToTimestamp(apiCodeRepository.createdAt).getTime(),
                                  dateUtils.convertISO8601ToTimestamp(apiCodeRepository.updatedAt).getTime(),
                                  dateUtils.convertISO8601ToTimestamp(apiCodeRepository.pushedAt).getTime(),
                                  apiCodeRepository.stargazersCount,
                                  apiCodeRepository.watchersCount,
                                  apiCodeRepository.forksCount,
                                  apiCodeRepository.openIssuesCount,
                                  apiCodeRepository.score,
                                  apiCodeRepository.isPrivate,
                                  apiCodeRepository.hasIssues,
                                  apiCodeRepository.hasProjects,
                                  apiCodeRepository.hasWiki);
    }

    @Override
    public List<CodeRepository> mapToCodeRepositoryList(final List<ApiCodeRepository> apiCodeRepositories) {
        if (apiCodeRepositories == null) {
            throw new InvalidCodeRepositoryListPayloadException();
        }

        if (apiCodeRepositories.isEmpty()) {
            return Collections.emptyList();
        }

        return mapToCodeRepositoryListInternal(apiCodeRepositories);
    }

    private List<CodeRepository> mapToCodeRepositoryListInternal(final List<ApiCodeRepository> apiCodeRepositories) {
        return Stream.of(apiCodeRepositories)
                     .map(this::mapToCodeRepository)
                     .toList();
    }
}
