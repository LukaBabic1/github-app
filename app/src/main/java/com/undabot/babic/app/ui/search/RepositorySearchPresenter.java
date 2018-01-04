package com.undabot.babic.app.ui.search;

import com.undabot.babic.app.base.BasePresenter;
import com.undabot.babic.app.ui.ViewModelConverter;
import com.undabot.babic.app.utils.Actions;
import com.undabot.babic.data.repository.CodeRepositoryRepositoryImpl;
import com.undabot.babic.domain.model.AuthToken;
import com.undabot.babic.domain.repository.CodeRepositoryRepository;
import com.undabot.babic.domain.usecase.ClearAccessTokenUseCase;
import com.undabot.babic.domain.usecase.InitUserComponentUseCase;
import com.undabot.babic.domain.usecase.IsUserSignedInUseCase;
import com.undabot.babic.domain.usecase.LogOutUserUseCase;
import com.undabot.babic.domain.usecase.SearchMoreRepositoriesUseCase;
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

    private static final int PER_PAGE_COUNT = CodeRepositoryRepositoryImpl.PER_PAGE_COUNT;

    private static final int DEFAULT_PAGE_INDEX = 0;
    private static final int NEXT_PAGE_INDEX_OFFSET = 1;

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
    SearchMoreRepositoriesUseCase searchMoreRepositoriesUseCase;

    @Inject
    LogOutUserUseCase logOutUserUseCase;

    @Inject
    ClearAccessTokenUseCase clearAccessTokenUseCase;

    @Inject
    InitUserComponentUseCase initUserComponentUseCase;

    @Inject
    IsUserSignedInUseCase isUserSignedInUseCase;

    @Inject
    ViewModelConverter viewModelConverter;

    @Inject
    StringUtils stringUtils;

    public RepositorySearchPresenter(final RepositorySearchContract.View view) {
        super(view);
    }

    @Override
    public void init() {
        viewActionQueue.subscribeTo(isUserSignedInUseCase.execute()
                                                         .map(this::mapToSignedInAction)
                                                         .subscribeOn(backgroundScheduler),
                                    this::logError);
    }

    private Action1<RepositorySearchContract.View> mapToSignedInAction(final boolean isSignedIn) {
        return view -> {
            if (isSignedIn) {
                view.showMenuInToolbar();
            }
        };
    }

    @Override
    public void activate() {
        super.activate();
        observeInternetConnection(this::hideNoInternetConnection,
                                  this::showNoInternetConnection);
    }

    private void hideNoInternetConnection() {
        onViewAction(view -> {
            view.hideNoInternetConnection();
            view.enableSearchButton();
        });
    }

    private void showNoInternetConnection() {
        onViewAction(view -> {
            view.showNoInternetConnection();
            view.disableSearchButton();
        });
    }

    @Override
    public void search(final String queryText, @SearchOrderInt final int searchOrderInt) {
        if (stringUtils.isEmpty(queryText)) {
            return;
        }

        onViewAction(view -> {
            view.hideKeyboard();
            view.showLoading();
        });

        searchInternal(queryText, searchOrderInt);
    }

    private void searchInternal(final String queryText, @SearchOrderInt final int searchOrderInt) {
        viewActionQueue.subscribeTo(searchRepositoriesUseCase.execute(new SearchRepositoriesUseCase.Request(queryText, mapSearchOrder(searchOrderInt)))
                                                             .map(viewModelConverter::mapCodeRepositoriesToViewModels)
                                                             .map(viewModels -> mapToViewAction(viewModels, DEFAULT_PAGE_INDEX, queryText, searchOrderInt))
                                                             .subscribeOn(backgroundScheduler),
                                    this::processSearchError);
    }

    private CodeRepositoryRepository.SearchOrder mapSearchOrder(@SearchOrderInt final int searchOrder) {
        return SEARCH_ORDER_INT_TO_SEARCH_ORDER_MAP.get(searchOrder);
    }

    private Action1<RepositorySearchContract.View> mapToViewAction(final List<RepositoryViewModel> viewModels, final int lastLoadedPage, final String searchTerm,
                                                                   final int searchOrderInt) {
        return view -> {
            view.hideLoading();
            view.render(new RepositorySearchScreenViewModel(viewModels, lastLoadedPage, searchTerm, searchOrderInt, evaluateIfMoreCanBeLoaded(viewModels), viewModels.isEmpty()));
        };
    }

    private boolean evaluateIfMoreCanBeLoaded(final List<RepositoryViewModel> repositoryViewModels) {
        return repositoryViewModels.size() == PER_PAGE_COUNT;
    }

    private void processSearchError(final Throwable throwable) {
        logError(throwable);
        onViewAction(view -> {
            view.hideLoading();
            view.showErrorMessage();
        });
    }

    @Override
    public void searchMoreItems(final String searchTerm, final int searchOrderInt, final int lastLoadedPage) {
        onViewAction(view -> {
            view.hideKeyboard();
            view.showLoading();
        });

        searchMoreItemsInternal(searchTerm, searchOrderInt, lastLoadedPage + NEXT_PAGE_INDEX_OFFSET);
    }

    private void searchMoreItemsInternal(final String searchTerm, final int searchOrderInt, final int nextPage) {
        viewActionQueue.subscribeTo(searchMoreRepositoriesUseCase.execute(new SearchMoreRepositoriesUseCase.Request(searchTerm, mapSearchOrder(searchOrderInt), nextPage))
                                                                 .map(viewModelConverter::mapCodeRepositoriesToViewModels)
                                                                 .map(viewModels -> mapToViewActionForMoreItemsLoaded(viewModels, nextPage, searchTerm, searchOrderInt))
                                                                 .subscribeOn(backgroundScheduler),
                                    this::processSearchMoreItemsError);
    }

    private Action1<RepositorySearchContract.View> mapToViewActionForMoreItemsLoaded(final List<RepositoryViewModel> viewModels, final int lastLoadedPage, final String searchTerm,
                                                                                     final int searchOrderInt) {
        return view -> {
            view.hideLoading();
            view.renderMoreItems(new RepositorySearchScreenViewModel(viewModels, lastLoadedPage, searchTerm, searchOrderInt, evaluateIfMoreCanBeLoaded(viewModels), false));
        };
    }

    private void processSearchMoreItemsError(final Throwable throwable) {
        logError(throwable);
        onViewAction(view -> {
            view.hideLoading();
            view.showErrorMessage();
        });
    }

    @Override
    public void showRepositoryDetails(final String repositoryName, final String username) {
        router.showRepositoryDetailsScreen(repositoryName, username);
    }

    @Override
    public void showUserDetails(final String username) {
        router.showUserDetailsScreen(username);
    }

    @Override
    public void logOut() {
        doIfConnectedToInternet(this::logOutInternal, Actions.noOpAction0());
    }

    private void logOutInternal() {
        onViewAction(view -> {
            view.showLoading();
            view.hideKeyboard();
        });

        viewActionQueue.subscribeTo(logOutUserUseCase.execute()
                                                     .andThen(clearAccessTokenUseCase.execute())
                                                     .andThen(initUserComponentUseCase.execute(AuthToken.EMPTY))
                                                     .subscribeOn(backgroundScheduler),
                                    view -> router.showLoginScreen(),
                                    this::processLogOutError);
    }

    private void processLogOutError(final Throwable throwable) {
        logError(throwable);
        onViewAction(RepositorySearchContract.View::hideLoading);
    }
}
