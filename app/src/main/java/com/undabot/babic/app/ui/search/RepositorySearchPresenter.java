package com.undabot.babic.app.ui.search;

import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.usecase.SearchRepositoriesUseCase;
import com.undabot.babic.domain.utils.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.functions.Action1;

public final class RepositorySearchPresenter extends BasePresenter<RepositorySearchContract.View> implements RepositorySearchContract.Presenter {

    private static final Map<Integer, CodeRepositoryRepository.SearchOrder> SEARCH_ORDER_INT_TO_SEARCH_ORDER_MAP;

    static {
        final Map<Integer, CodeRepositoryRepository.SearchOrder> map = new HashMap<>();

        map.put(RepositorySearchContract.Presenter.FORKS_SORT, CodeRepositoryRepository.SearchOrder.FORKS);
        map.put(RepositorySearchContract.Presenter.STARS_SORT, CodeRepositoryRepository.SearchOrder.STARS);
        map.put(RepositorySearchContract.Presenter.UPDATED_SORT, CodeRepositoryRepository.SearchOrder.UPDATED);

        SEARCH_ORDER_INT_TO_SEARCH_ORDER_MAP = Collections.unmodifiableMap(map);
    }

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
    public void activate() {
        super.activate();
        observeInternetConnection(this::hideNoInternetConnection,
                                  this::showNoInternetConnection);
    }

    private void hideNoInternetConnection() {
        doIfViewNotNull(view -> {
            view.hideNoInternetConnection();
            view.enableSearchButton();
        });
    }

    private void showNoInternetConnection() {
        doIfViewNotNull(view -> {
            view.showNoInternetConnection();
            view.disableSearchButton();
        });
    }

    @Override
    public void search(final String queryText, @SearchOrderInt final int searchOrder) {
        if (stringUtils.isEmpty(queryText)) {
            return;
        }

        doIfViewNotNull(view -> {
            view.hideKeyboard();
            view.showLoading();
        });

        searchInternal(queryText, searchOrder);
    }

    private void searchInternal(final String queryText, @SearchOrderInt final int searchOrderInt) {
        viewActionQueue.subscribeTo(searchRepositoriesUseCase.execute(new SearchRepositoriesUseCase.Request(queryText, mapSearchOrder(searchOrderInt)))
                                                             .map(viewModelConverter::mapCodeRepositoriesToViewModels)
                                                             .map(this::mapToViewAction)
                                                             .subscribeOn(backgroundScheduler),
                                    this::processSearchError);
    }

    private CodeRepositoryRepository.SearchOrder mapSearchOrder(@SearchOrderInt final int searchOrder) {
        return SEARCH_ORDER_INT_TO_SEARCH_ORDER_MAP.get(searchOrder);
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
    public void showRepositoryDetails(final String repositoryName, final String username) {
        router.showRepositoryDetailsScreen(repositoryName, username);
    }

    @Override
    public void showUserDetails(final String username) {
        router.showUserDetailsScreen(username);
    }
}
