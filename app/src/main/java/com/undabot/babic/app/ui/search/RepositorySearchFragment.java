package com.undabot.babic.app.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class RepositorySearchFragment extends BaseFragment implements RepositorySearchContract.View {

    public static final String TAG = "SearchFragment";

    @BindView(R.id.fragment_repository_search_edit_text)
    EditText searchEditText;

    @BindView(R.id.fragment_repository_search_search_button)
    Button searchButton;

    @BindView(R.id.fragment_repository_search_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.fragment_repository_search_progress_bar)
    ProgressBar progressBar;

    @Inject
    RepositorySearchContract.Presenter presenter;

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
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.fragment_repository_search_search_button)
    void onSearchButtonClicked() {
        presenter.search(searchEditText.getText().toString());
    }
}
