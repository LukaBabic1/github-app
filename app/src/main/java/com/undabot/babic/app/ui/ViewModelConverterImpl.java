package com.undabot.babic.app.ui;

import com.annimon.stream.Stream;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailViewModel;
import com.undabot.babic.app.ui.search.RepositoryViewModel;
import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;
import com.undabot.babic.app.ui.userdetails.UserDetailViewModel;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.utils.DateUtils;
import com.undabot.babic.domain.utils.StringUtils;

import java.util.List;

public final class ViewModelConverterImpl implements ViewModelConverter {

    private final DateUtils dateUtils;
    private final StringUtils stringUtils;

    public ViewModelConverterImpl(final DateUtils dateUtils, final StringUtils stringUtils) {
        this.dateUtils = dateUtils;
        this.stringUtils = stringUtils;
    }

    @Override
    public RepositoryOwnerViewModel mapUserToRepositoryOwnerViewModel(final User user) {
        return new RepositoryOwnerViewModel(user.id, user.username, user.avatarUrl, stringUtils.itOrDefault(user.name, user.username));
    }

    @Override
    public RepositoryViewModel mapCodeRepositoryToViewModel(final CodeRepository codeRepository) {
        return new RepositoryViewModel(codeRepository.id,
                                       codeRepository.name,
                                       codeRepository.fullName,
                                       mapUserToRepositoryOwnerViewModel(codeRepository.owner),
                                       codeRepository.stargazersCount,
                                       codeRepository.watchersCount,
                                       codeRepository.forksCount,
                                       codeRepository.openIssuesCount
        );
    }

    @Override
    public List<RepositoryViewModel> mapCodeRepositoriesToViewModels(final List<CodeRepository> codeRepositories) {
        return Stream.of(codeRepositories)
                     .map(this::mapCodeRepositoryToViewModel)
                     .toList();
    }

    @Override
    public RepositoryDetailViewModel mapToRepositoryDetailViewModel(final CodeRepository codeRepository) {
        return new RepositoryDetailViewModel(codeRepository.id,
                                             codeRepository.name,
                                             codeRepository.fullName,
                                             mapUserToRepositoryOwnerViewModel(codeRepository.owner),
                                             codeRepository.stargazersCount,
                                             codeRepository.watchersCount,
                                             codeRepository.forksCount,
                                             codeRepository.openIssuesCount,
                                             codeRepository.score,
                                             codeRepository.language,
                                             dateUtils.convertToUserReadableTimestamp(codeRepository.createdAt),
                                             dateUtils.convertToUserReadableTimestamp(codeRepository.updatedAt),
                                             codeRepository.isPrivate,
                                             codeRepository.hasIssues,
                                             codeRepository.hasProjects,
                                             codeRepository.hasWiki);
    }

    @Override
    public UserDetailViewModel mapToUserDetailViewModel(final User user) {
        return new UserDetailViewModel(user.id,
                                       user.username,
                                       user.name,
                                       user.type,
                                       user.companyName,
                                       user.location,
                                       user.email,
                                       user.avatarUrl,
                                       user.siteAdmin,
                                       user.hireable,
                                       !stringUtils.isEmpty(user.blog),
                                       user.followers,
                                       user.following,
                                       dateUtils.convertToUserReadableTimestamp(user.createdAt),
                                       dateUtils.convertToUserReadableTimestamp(user.updatedAt),
                                       user.privateGists,
                                       user.publicRepos,
                                       user.publicGists,
                                       user.totalPrivateRepos,
                                       user.ownedPrivateRepos);
    }
}
