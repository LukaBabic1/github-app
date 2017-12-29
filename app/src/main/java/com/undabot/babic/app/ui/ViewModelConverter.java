package com.undabot.babic.app.ui;

import com.undabot.babic.app.ui.search.CodeRepositoryViewModel;
import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;

import java.util.List;

public interface ViewModelConverter {

    RepositoryOwnerViewModel mapUserToRepositoryOwnerViewModel(User user);

    CodeRepositoryViewModel mapCodeRepositoryToViewModel(CodeRepository codeRepository);

    List<CodeRepositoryViewModel> mapCodeRepositoriesToViewModels(List<CodeRepository> codeRepositories);
}
