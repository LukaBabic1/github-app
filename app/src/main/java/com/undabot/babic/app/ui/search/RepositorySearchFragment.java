package com.undabot.babic.app.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @BindView(R.id.fragment_repository_search_progress_bar)
    ProgressBar progressBar;

    @Inject
    RepositorySearchContract.Presenter presenter;

    @Inject
    CodeRepositoriesAdapter codeRepositoriesAdapter;

    @Inject
    KeyboardUtils keyboardUtils;

    Optional<RepositorySearchScreenViewModel> repositorySearchScreenViewModel = Optional.empty();

    public static RepositorySearchFragment newInstance() {
        return new RepositorySearchFragment();
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
    }

    private void initAdapter() {
        codeRepositoriesAdapter.setListener(this);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(codeRepositoriesAdapter);
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void render(final RepositorySearchScreenViewModel viewModel) {
        repositorySearchScreenViewModel = Optional.of(viewModel);
        codeRepositoriesAdapter.setItems(viewModel.codeRepositoryViewModels);
    }

    @Override
    public void showLoading() {
        searchButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        searchButton.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
        keyboardUtils.hideSoftKeyboard(searchEditText);
    }

    @Override
    public void showErrorDialog() {
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
    public void onRepositoryClicked(final int id) {
        presenter.showRepositoryDetails(id);
    }

    @Override
    public void onUserAvatarClicked(final int id) {
        presenter.showUserDetails(id);
    }

    @OnClick(R.id.fragment_repository_search_search_button)
    void onSearchButtonClicked() {
        final int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == UNSELECTED_RADIO_BUTTON_ID) {
            showSortOrderNotSelectedPrompt();
            return;
        }

        @SearchOrderInt
        final int searchOrder = RADIO_BUTTON_ID_TO_SORT_ORDER.get(selectedRadioButtonId);
        presenter.search(searchEditText.getText().toString(), searchOrder);
    }

    private void showSortOrderNotSelectedPrompt() {
        showShortToast(R.string.repository_search_screen_sort_order_not_selected_text);
    }
}
