package com.undabot.babic.app.ui;

import com.annimon.stream.Stream;
import com.undabot.babic.app.ui.search.CodeRepositoryViewModel;
import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;

import java.util.List;

public final class ViewModelConverterImpl implements ViewModelConverter {

    @Override
    public RepositoryOwnerViewModel mapUserToRepositoryOwnerViewModel(final User user) {
        return new RepositoryOwnerViewModel(user.id, user.username, user.avatarUrl, user.name);
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
}
