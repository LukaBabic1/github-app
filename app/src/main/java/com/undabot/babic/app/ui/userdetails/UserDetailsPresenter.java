package com.undabot.babic.app.ui.userdetails;

import com.annimon.stream.Optional;
import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.app.utils.Actions;
import com.undabot.babic.domain.model.User;
import com.undabot.babic.domain.usecase.GetUserDataUseCase;

import javax.inject.Inject;

import rx.functions.Action1;

public final class UserDetailsPresenter extends BasePresenter<UserDetailsContract.View> implements UserDetailsContract.Presenter {

    @Inject
    GetUserDataUseCase getUserDataUseCase;

    @Inject
    ViewModelConverter viewModelConverter;

    private Optional<String> username = Optional.empty();
    private Optional<User> user = Optional.empty();

    public UserDetailsPresenter(final UserDetailsContract.View view) {
        super(view);
    }

    @Override
    public void init(final String username) {
        this.username = Optional.of(username);
        getUserData(username);
    }

    private void getUserData(final String username) {
        viewActionQueue.subscribeTo(getUserDataUseCase.execute(username)
                                                      .doOnNext(this::cacheUser)
                                                      .map(viewModelConverter::mapToUserDetailViewModel)
                                                      .map(this::mapToViewAction)
                                                      .subscribeOn(backgroundScheduler),
                                    Actions.noOpAction1(),
                                    this::processGetUserDataError);
    }

    private void cacheUser(final User user) {
        this.user = Optional.of(user);
    }

    private Action1<UserDetailsContract.View> mapToViewAction(final UserDetailViewModel viewModel) {
        return view -> view.render(viewModel);
    }

    private void processGetUserDataError(final Throwable throwable) {
        logError(throwable);
        onViewAction(UserDetailsContract.View::showErrorMessage);
    }

    @Override
    public void visitBlog() {
        user.filter(user -> !user.blog.isEmpty())
            .ifPresent(user -> router.showPageInExternalBrowser(user.blog));
    }
}
