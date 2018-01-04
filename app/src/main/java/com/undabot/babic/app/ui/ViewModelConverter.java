package com.undabot.babic.app.ui;

import com.undabot.babic.app.ui.login.LoginViewModel;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailViewModel;
import com.undabot.babic.app.ui.search.RepositoryViewModel;
import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;
import com.undabot.babic.app.ui.userdetails.UserDetailViewModel;
import com.undabot.babic.domain.model.AuthorizationUrl;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.model.User;

import java.util.List;

public interface ViewModelConverter {

    LoginViewModel mapToLoginViewModel(AuthorizationUrl authorizationUrl);

    RepositoryOwnerViewModel mapUserToRepositoryOwnerViewModel(User user);

    RepositoryViewModel mapCodeRepositoryToViewModel(CodeRepository codeRepository);

    List<RepositoryViewModel> mapCodeRepositoriesToViewModels(List<CodeRepository> codeRepositories);

    RepositoryDetailViewModel mapToRepositoryDetailViewModel(CodeRepository codeRepository);

    UserDetailViewModel mapToUserDetailViewModel(User user);
}
