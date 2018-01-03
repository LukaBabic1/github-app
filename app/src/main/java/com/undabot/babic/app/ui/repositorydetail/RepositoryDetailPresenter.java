package com.undabot.babic.app.ui.repositorydetail;

import com.annimon.stream.Optional;
import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.app.utils.Actions;
import com.undabot.babic.domain.model.CodeRepository;
import com.undabot.babic.domain.usecase.GetRepositoryDetailsUseCase;

import javax.inject.Inject;

import rx.functions.Action1;

public final class RepositoryDetailPresenter extends BasePresenter<RepositoryDetailContract.View> implements RepositoryDetailContract.Presenter {

    @Inject
    GetRepositoryDetailsUseCase getRepositoryDetailsUseCase;

    @Inject
    ViewModelConverter viewModelConverter;

    private Optional<InitData> initData = Optional.empty();

    public RepositoryDetailPresenter(final RepositoryDetailContract.View view) {
        super(view);
    }

    @Override
    public void init(final String repositoryName, final String username) {
        initData = Optional.of(new InitData(repositoryName, username));
        getRepositoryData(repositoryName, username);
    }

    private void getRepositoryData(final String repositoryName, final String username) {
        viewActionQueue.subscribeTo(getRepositoryDetailsUseCase.execute(new GetRepositoryDetailsUseCase.Request(repositoryName, username))
                                                               .map(viewModelConverter::mapToRepositoryDetailViewModel)
                                                               .map(this::mapToViewAction)
                                                               .subscribeOn(backgroundScheduler),
                                    Actions.noOpAction1(),
                                    this::processGetRepositoryDataError);
    }

    private Action1<RepositoryDetailContract.View> mapToViewAction(final RepositoryDetailViewModel viewModel) {
        return view -> view.render(viewModel);
    }

    private void processGetRepositoryDataError(final Throwable throwable) {
        logError(throwable);
    }

    @Override
    public void showRepositoryOnGithub() {
        initData.ifPresent(this::showRepositoryOnGithubInternal);
    }

    private void showRepositoryOnGithubInternal(final InitData initData) {
        addSubscription(getRepositoryDetailsUseCase.execute(new GetRepositoryDetailsUseCase.Request(initData.repositoryName, initData.username))
                                                   .first()
                                                   .subscribeOn(backgroundScheduler)
                                                   .observeOn(mainThreadScheduler)
                                                   .subscribe(this::processGetRepositoryDetailsSuccess,
                                                              this::processGetRepositoryDetailsError));
    }

    private void processGetRepositoryDetailsSuccess(final CodeRepository codeRepository) {
        router.showPageInExternalBrowser(codeRepository.homepageUrl);
    }

    private void processGetRepositoryDetailsError(final Throwable throwable) {
        logError(throwable);
    }

    private static final class InitData {

        private final String repositoryName;
        private final String username;

        private InitData(final String repositoryName, final String username) {
            this.repositoryName = repositoryName;
            this.username = username;
        }
    }
}
