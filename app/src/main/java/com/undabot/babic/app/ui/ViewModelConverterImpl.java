package com.undabot.babic.app.ui;

import com.annimon.stream.Stream;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailViewModel;
import com.undabot.babic.app.ui.search.CodeRepositoryViewModel;
import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;
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
    public CodeRepositoryViewModel mapCodeRepositoryToViewModel(final CodeRepository codeRepository) {
        return new CodeRepositoryViewModel(codeRepository.id,
                                           codeRepository.name,
                                           codeRepository.fullName,
                                           mapUserToRepositoryOwnerViewModel(codeRepository.owner),
                                           codeRepository.stargazersCount,
                                           codeRepository.watchersCount,
                                           codeRepository.forksCount,
                                           codeRepository.openIssuesCount,
                                           codeRepository.score);
    }

    @Override
    public List<CodeRepositoryViewModel> mapCodeRepositoriesToViewModels(final List<CodeRepository> codeRepositories) {
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
                                             String.valueOf(codeRepository.createdAt),
                                             String.valueOf(codeRepository.updatedAt),
                                             codeRepository.isPrivate,
                                             codeRepository.hasIssues,
                                             codeRepository.hasProjects,
                                             codeRepository.hasWiki);
    }
}
