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

    private Optional<CodeRepository> codeRepository = Optional.empty();

    public RepositoryDetailPresenter(final RepositoryDetailContract.View view) {
        super(view);
    }

    @Override
    public void init(final String repositoryName, final String username) {
        getRepositoryData(repositoryName, username);
    }

    private void getRepositoryData(final String repositoryName, final String username) {
        viewActionQueue.subscribeTo(getRepositoryDetailsUseCase.execute(new GetRepositoryDetailsUseCase.Request(repositoryName, username))
                                                               .doOnNext(this::cacheData)
                                                               .map(viewModelConverter::mapToRepositoryDetailViewModel)
                                                               .map(this::mapToViewAction)
                                                               .subscribeOn(backgroundScheduler),
                                    Actions.noOpAction1(),
                                    this::processGetRepositoryDataError);
    }

    private void cacheData(final CodeRepository codeRepository) {
        this.codeRepository = Optional.of(codeRepository);
    }

    private Action1<RepositoryDetailContract.View> mapToViewAction(final RepositoryDetailViewModel viewModel) {
        return view -> view.render(viewModel);
    }

    private void processGetRepositoryDataError(final Throwable throwable) {
        logError(throwable);
        onViewAction(RepositoryDetailContract.View::showErrorScreen);
    }

    @Override
    public void showUserDetails() {
        codeRepository.ifPresent(codeRepository -> router.showUserDetailsScreen(codeRepository.owner.username));
    }

    @Override
    public void showRepositoryOnGithub() {
        codeRepository.ifPresent(codeRepository -> router.showPageInExternalBrowser(codeRepository.homepageUrl));
    }
}
