package com.undabot.babic.data.network.converter;

import com.undabot.babic.data.network.model.ApiUser;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.utils.StringUtils;

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
}
