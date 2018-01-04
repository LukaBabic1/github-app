package com.undabot.babic.app.ui.repositorydetail;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;
import com.undabot.babic.app.ui.search.RepositoryOwnerViewModel;
import com.undabot.babic.app.utils.ui.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class RepositoryDetailFragment extends BaseFragment implements RepositoryDetailContract.View {

    public static final String TAG = "RepositoryDetailFragment";

    private static final String KEY_EXTRA = "key_fragment_extra";

    @BindView(R.id.fragment_repository_detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.fragment_repository_detail_repository_name)
    TextView repositoryName;

    @BindView(R.id.fragment_repository_detail_repository_full_name)
    TextView repositoryFullName;

    @BindView(R.id.fragment_repository_detail_stars_count)
    TextView starsCountTextView;

    @BindView(R.id.fragment_repository_detail_watchers_count)
    TextView watchersCountTextView;

    @BindView(R.id.fragment_repository_detail_forks_count)
    TextView forksCountTextView;

    @BindView(R.id.fragment_repository_detail_open_issues_count)
    TextView openIssuesCountTextView;

    @BindView(R.id.fragment_repository_detail_language_value)
    TextView languageTextView;

    @BindView(R.id.fragment_repository_detail_created_at_value)
    TextView createdAtTextView;

    @BindView(R.id.fragment_repository_detail_updated_at_value)
    TextView updatedAtTextView;

    @BindView(R.id.fragment_repository_detail_is_private_value)
    TextView isPrivateRepoTextView;

    @BindView(R.id.fragment_repository_detail_has_issues_value)
    TextView hasIssuesTextView;

    @BindView(R.id.fragment_repository_detail_has_wiki_value)
    TextView hasWikiTextView;

    @BindView(R.id.fragment_repository_detail_user_image)
    ImageView userImage;

    @BindView(R.id.fragment_repository_detail_author_name_value)
    TextView authorNameTextView;

    @Inject
    RepositoryDetailContract.Presenter presenter;

    @Inject
    ImageLoader imageLoader;

    private Optional<RepositoryDetailViewModel> viewModel = Optional.empty();

    public static RepositoryDetailFragment newInstance(final String repositoryName, final String username) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_EXTRA, new Extras(repositoryName, username));

        final RepositoryDetailFragment fragment = new RepositoryDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_repository_details;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar();
        if (savedInstanceState == null) {
            viewModel.ifPresentOrElse(this::render,
                                      this::extractFromArguments);
        } else {
            extractFromSavedInstanceState(savedInstanceState);
        }
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.repository_details_toolbar_title);
    }

    private void extractFromArguments() {
        extractBundle(getArguments());
    }

    private void extractFromSavedInstanceState(final Bundle bundle) {
        extractBundle(bundle);
    }

    private void extractBundle(final Bundle bundle) {
        Optional.ofNullable(bundle)
                .map(bundle1 -> (Extras) bundle1.getParcelable(KEY_EXTRA))
                .ifPresentOrElse(this::initPresenter,
                                 this::throwMissingArgumentsException);
    }

    private void initPresenter(final Extras extras) {
        presenter.init(extras.repositoryName, extras.username);
    }

    private void throwMissingArgumentsException() {
        throw new IllegalArgumentException("Missing extras for fragment under \"" + KEY_EXTRA + "\" key");
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_EXTRA, getArguments().getParcelable(KEY_EXTRA));
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void render(final RepositoryDetailViewModel viewModel) {
        this.viewModel = Optional.of(viewModel);

        repositoryName.setText(viewModel.name);
        repositoryFullName.setText(viewModel.fullName);
        starsCountTextView.setText(String.valueOf(viewModel.stargazersCount));
        watchersCountTextView.setText(String.valueOf(viewModel.watchersCount));
        forksCountTextView.setText(String.valueOf(viewModel.forksCount));
        openIssuesCountTextView.setText(String.valueOf(viewModel.openIssuesCount));
        languageTextView.setText(viewModel.language);
        createdAtTextView.setText(viewModel.createdAt);
        updatedAtTextView.setText(viewModel.updatedAt);
        isPrivateRepoTextView.setText(String.valueOf(viewModel.isPrivate));
        hasIssuesTextView.setText(String.valueOf(viewModel.hasIssued));
        hasWikiTextView.setText(String.valueOf(viewModel.hasWiki));

        renderOwnerDetails(viewModel.repositoryOwnerViewModel);
    }

    private void renderOwnerDetails(final RepositoryOwnerViewModel viewModel) {
        imageLoader.loadImage(viewModel.avatarUrl, userImage);
        authorNameTextView.setText(viewModel.name);
    }

    @Override
    public void showErrorScreen() {
        showShortToast(R.string.repository_details_toast_error_message);
    }

    @Override
    public void showBrowserNotAvailableErrorMessage() {
        showShortToast(R.string.repository_details_browser_not_available_error_message);
    }

    @OnClick(R.id.fragment_repository_detail_user_image)
    void onUserImageClicked() {
        presenter.showUserDetails();
    }

    @OnClick(R.id.fragment_repository_detail_view_repository_on_github_button)
    void onViewOnGithubButtonClicked() {
        presenter.showRepositoryOnGithub();
    }

    static final class Extras implements Parcelable {

        final String repositoryName;
        final String username;

        Extras(final String repositoryName, final String username) {
            this.repositoryName = repositoryName;
            this.username = username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.repositoryName);
            dest.writeString(this.username);
        }

        protected Extras(Parcel in) {
            this.repositoryName = in.readString();
            this.username = in.readString();
        }

        public static final Creator<Extras> CREATOR = new Creator<Extras>() {

            @Override
            public Extras createFromParcel(Parcel source) {
                return new Extras(source);
            }

            @Override
            public Extras[] newArray(int size) {
                return new Extras[size];
            }
        };
    }
}
