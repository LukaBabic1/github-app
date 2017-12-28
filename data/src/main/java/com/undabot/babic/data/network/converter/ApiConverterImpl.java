package com.undabot.babic.data.network.converter;

import com.annimon.stream.Stream;
import com.undabot.babic.data.network.model.ApiCodeRepository;
import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.utils.StringUtils;

import java.util.Collections;
import java.util.List;

public final class ApiConverterImpl implements ApiConverter {

    private static final String EMPTY = "";

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
        return new User(apiUser.id,
                        apiUser.username,
                        stringUtils.itOrDefault(apiUser.avatarUrl, EMPTY),
                        stringUtils.itOrDefault(apiUser.siteHtmlUrl, EMPTY),
                        stringUtils.itOrDefault(apiUser.name, EMPTY),
                        stringUtils.itOrDefault(apiUser.type, EMPTY),
                        stringUtils.itOrDefault(apiUser.companyName, EMPTY),
                        stringUtils.itOrDefault(apiUser.companyName, EMPTY),
                        stringUtils.itOrDefault(apiUser.email, EMPTY),
                        apiUser.siteAdmin,
                        Boolean.TRUE.toString().equalsIgnoreCase(stringUtils.itOrDefault(apiUser.hireable, EMPTY)),
                        apiUser.followers,
                        apiUser.following,
                        // TODO
                        0L,
                        0L,
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
                                  // TODO
                                  0L,
                                  0L,
                                  0L,
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
