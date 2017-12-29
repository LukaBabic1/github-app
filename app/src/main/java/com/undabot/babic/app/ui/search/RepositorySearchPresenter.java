package com.undabot.babic.app.ui.search;

import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.usecase.SearchRepositoriesUseCase;
import com.undabot.babic.domain.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

public final class RepositorySearchPresenter extends BasePresenter<RepositorySearchContract.View> implements RepositorySearchContract.Presenter {

    @Inject
    SearchRepositoriesUseCase searchRepositoriesUseCase;

    @Inject
    ViewModelConverter viewModelConverter;

    @Inject
    StringUtils stringUtils;

    public RepositorySearchPresenter(final RepositorySearchContract.View view) {
        super(view);
    }

    @Override
    public void search(final String queryText) {
        if (stringUtils.isEmpty(queryText)) {
            return;
        }

        doIfViewNotNull(view -> {
            view.hideKeyboard();
            view.showLoading();
        });
        searchInternal(queryText);
    }

    private void searchInternal(final String queryText) {
        viewActionQueue.subscribeTo(searchRepositoriesUseCase.execute(new SearchRepositoriesUseCase.Request(queryText, CodeRepositoryRepository.SearchOrder.STARS))
                                                             .map(viewModelConverter::mapCodeRepositoriesToViewModels)
                                                             .map(this::mapToViewAction)
                                                             .subscribeOn(backgroundScheduler),
                                    this::processSearchError);
    }

    private Action1<RepositorySearchContract.View> mapToViewAction(final List<CodeRepositoryViewModel> codeRepositoryViewModels) {
        return view -> {
            view.hideLoading();
            view.render(new RepositorySearchScreenViewModel(codeRepositoryViewModels, true));
        };
    }

    private void processSearchError(final Throwable throwable) {
        logError(throwable);
        doIfViewNotNull(RepositorySearchContract.View::hideLoading);
        doIfViewNotNull(RepositorySearchContract.View::showErrorDialog);
    }

    @Override
    public void showRepositoryDetails(final int id) {

    }

    @Override
    public void showUserDetails(final int id) {

    }
}
