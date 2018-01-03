package com.undabot.babic.app.ui.search;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;
import com.undabot.babic.app.ui.search.RepositorySearchContract.Presenter.SearchOrderInt;
import com.undabot.babic.app.utils.ui.KeyboardUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.undabot.babic.app.ui.search.RepositorySearchContract.Presenter.FORKS_SORT;
import static com.undabot.babic.app.ui.search.RepositorySearchContract.Presenter.STARS_SORT;
import static com.undabot.babic.app.ui.search.RepositorySearchContract.Presenter.UPDATED_SORT;

public final class RepositorySearchFragment extends BaseFragment implements RepositorySearchContract.View,
                                                                            CodeRepositoriesAdapter.CodeRepositoriesAdapterListener {

    public static final String TAG = "SearchFragment";

    private static final String KEY_SEARCH_TERM = "key_search_params";

    private static final int UNSELECTED_RADIO_BUTTON_ID = -1;
    private static final int SPARSE_ARRAY_INIT_LENGTH = 3;

    private static final SparseIntArray RADIO_BUTTON_ID_TO_SORT_ORDER;

    static {
        RADIO_BUTTON_ID_TO_SORT_ORDER = new SparseIntArray(SPARSE_ARRAY_INIT_LENGTH);

        RADIO_BUTTON_ID_TO_SORT_ORDER.put(R.id.fragment_repository_search_stars_sort, STARS_SORT);
        RADIO_BUTTON_ID_TO_SORT_ORDER.put(R.id.fragment_repository_search_forks_sort, FORKS_SORT);
        RADIO_BUTTON_ID_TO_SORT_ORDER.put(R.id.fragment_repository_search_updated_sort, UPDATED_SORT);
    }

    @BindView(R.id.fragment_repository_search_no_internet_connection_text_view)
    TextView noInternetConnectionTextView;

    @BindView(R.id.fragment_repository_search_edit_text)
    EditText searchEditText;

    @BindView(R.id.fragment_repository_search_search_button)
    Button searchButton;

    @BindView(R.id.fragment_repository_search_radio_group)
    RadioGroup radioGroup;

    @BindView(R.id.fragment_repository_search_stars_sort)
    RadioButton starsSortRadioButton;

    @BindView(R.id.fragment_repository_search_forks_sort)
    RadioButton forksSortRadioButton;

    @BindView(R.id.fragment_repository_search_updated_sort)
    RadioButton updatedSortRadioButton;

    @BindView(R.id.fragment_repository_search_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.fragment_repository_search_loading_fading_view)
    FrameLayout loadingFadingView;

    @BindView(R.id.fragment_repository_search_progress_bar)
    ProgressBar progressBar;

    @Inject
    RepositorySearchContract.Presenter presenter;

    @Inject
    CodeRepositoriesAdapter codeRepositoriesAdapter;

    @Inject
    KeyboardUtils keyboardUtils;

    Optional<RepositorySearchScreenViewModel> repositorySearchScreenViewModel = Optional.empty();

    private LastSearchParams lastSearchParams;

    public static RepositorySearchFragment newInstance() {
        return new RepositorySearchFragment();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreFromStateIfPresent(savedInstanceState);
    }

    private void restoreFromStateIfPresent(final Bundle savedInstanceState) {
        Optional.ofNullable(savedInstanceState)
                .map(bundle -> (LastSearchParams) bundle.getParcelable(KEY_SEARCH_TERM))
                .filter(lastSearchParams -> !lastSearchParams.query.isEmpty())
                .ifPresent(this::restoreSearch);
    }

    private void restoreSearch(final LastSearchParams params) {
        this.lastSearchParams = params;
        presenter.search(params.query, params.searchOrder);
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_repository_search;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initRecyclerView();
        initSearchEditText();
    }

    private void initAdapter() {
        codeRepositoriesAdapter.setListener(this);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(codeRepositoriesAdapter);
    }

    private void initSearchEditText() {
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchInternal();
                return true;
            }

            return false;
        });
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_SEARCH_TERM, lastSearchParams);
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void render(final RepositorySearchScreenViewModel viewModel) {
        repositorySearchScreenViewModel = Optional.of(viewModel);
        codeRepositoriesAdapter.setItems(viewModel.repositoryViewModels, viewModel.canLoadMore);
    }

    @Override
    public void renderMoreItems(final RepositorySearchScreenViewModel viewModel) {
        repositorySearchScreenViewModel = Optional.of(viewModel);
        codeRepositoriesAdapter.setMoreItems(viewModel.repositoryViewModels, viewModel.canLoadMore);
    }

    @Override
    public void showLoading() {
        searchButton.setEnabled(false);
        loadingFadingView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        searchButton.setEnabled(true);
        loadingFadingView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
        keyboardUtils.hideSoftKeyboard(searchEditText);
    }

    @Override
    public void showErrorMessage() {
        showShortToast(R.string.repository_search_screen_data_fetch_error_message);
    }

    @Override
    public void showNoInternetConnection() {
        noInternetConnectionTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoInternetConnection() {
        noInternetConnectionTextView.setVisibility(View.GONE);
    }

    @Override
    public void enableSearchButton() {
        searchButton.setEnabled(true);
    }

    @Override
    public void disableSearchButton() {
        searchButton.setEnabled(false);
    }

    @Override
    public void onRepositoryClicked(final String repositoryName, final String username) {
        if (!isLoading()) {
            presenter.showRepositoryDetails(repositoryName, username);
        }
    }

    @Override
    public void onUserAvatarClicked(final String username) {
        if (!isLoading()) {
            presenter.showUserDetails(username);
        }
    }

    @Override
    public void loadMoreItems() {
        if (isLoading()) {
            return;
        }

        repositorySearchScreenViewModel.ifPresent(viewModel -> presenter.searchMoreItems(viewModel.searchTerm, viewModel.searchModeInt, viewModel.lastLoadedPage));
    }

    private boolean isLoading() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    @OnClick(R.id.fragment_repository_search_search_button)
    void onSearchButtonClicked() {
        searchInternal();
    }

    private void searchInternal() {
        final int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == UNSELECTED_RADIO_BUTTON_ID) {
            showSortOrderNotSelectedPrompt();
            return;
        }

        @SearchOrderInt final int searchOrder = RADIO_BUTTON_ID_TO_SORT_ORDER.get(selectedRadioButtonId);
        final String searchTerm = searchEditText.getText().toString();
        lastSearchParams = new LastSearchParams(searchTerm, searchOrder);
        presenter.search(searchTerm, searchOrder);
    }

    private void showSortOrderNotSelectedPrompt() {
        showShortToast(R.string.repository_search_screen_sort_order_not_selected_text);
    }

    static final class LastSearchParams implements Parcelable {

        final String query;

        @SearchOrderInt final int searchOrder;

        LastSearchParams(final String query, final int searchOrder) {
            this.query = query;
            this.searchOrder = searchOrder;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.query);
            dest.writeInt(this.searchOrder);
        }

        protected LastSearchParams(Parcel in) {
            this.query = in.readString();
            this.searchOrder = in.readInt();
        }

        public static final Parcelable.Creator<LastSearchParams> CREATOR = new Parcelable.Creator<LastSearchParams>() {

            @Override
            public LastSearchParams createFromParcel(Parcel source) {
                return new LastSearchParams(source);
            }

            @Override
            public LastSearchParams[] newArray(int size) {
                return new LastSearchParams[size];
            }
        };
    }
}
