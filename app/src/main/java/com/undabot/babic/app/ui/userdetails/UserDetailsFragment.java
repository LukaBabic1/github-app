package com.undabot.babic.app.ui.userdetails;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;
import com.undabot.babic.app.utils.ui.ImageLoader;
import com.undabot.babic.app.utils.view.ViewUtils;
import com.undabot.babic.domain.utils.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public final class UserDetailsFragment extends BaseFragment implements UserDetailsContract.View {

    public static final String TAG = "UserDetailsFragment";

    private static final String KEY_EXTRAS = "key_fragment_extras";

    private static final String NOT_AVAILABLE = "n/a";

    @BindView(R.id.fragment_user_detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.fragment_user_detail_avatar_image)
    ImageView avatarImageView;

    @BindView(R.id.fragment_user_detail_username)
    TextView usernameTextView;

    @BindView(R.id.fragment_user_detail_name)
    TextView nameTextView;

    @BindView(R.id.fragment_user_detail_type)
    TextView typeTextView;

    @BindView(R.id.fragment_user_detail_company_name)
    TextView companyNameTextView;

    @BindView(R.id.fragment_user_detail_location)
    TextView locationTextView;

    @BindView(R.id.fragment_user_detail_email)
    TextView emailTextView;

    @BindView(R.id.fragment_user_detail_site_admin)
    TextView siteAdminTextView;

    @BindView(R.id.fragment_user_detail_hireable)
    TextView hireableTextView;

    @BindView(R.id.fragment_user_detail_followers)
    TextView followersTextView;

    @BindView(R.id.fragment_user_detail_following)
    TextView followingTextView;

    @BindView(R.id.fragment_user_detail_created_at)
    TextView createdAtTextView;

    @BindView(R.id.fragment_user_detail_updated_at)
    TextView updatedAtTextView;

    @BindView(R.id.fragment_user_detail_public_repos)
    TextView publicReposTextView;

    @BindView(R.id.fragment_user_detail_public_gists)
    TextView publicGistsTextView;

    @BindView(R.id.fragment_user_detail_visit_blog_button)
    Button visitBlogButton;

    @Inject
    UserDetailsContract.Presenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Inject
    StringUtils stringUtils;

    @Inject
    ViewUtils viewUtils;

    private Optional<UserDetailViewModel> userDetailViewModel = Optional.empty();

    public static UserDetailsFragment newInstance(final String username) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_EXTRAS, new Extras(username));

        final UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void throwArgumentsMissingException() {
        throw new IllegalArgumentException("No arguments under " + KEY_EXTRAS + " found.");
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_user_details;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar();
        if (savedInstanceState == null) {
            userDetailViewModel.ifPresentOrElse(this::render,
                                                this::extractFromArguments);
        } else {
            extractFromSavedInstanceState(savedInstanceState);
        }
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.user_details_toolbar_title);
    }

    private void extractFromArguments() {
        extractBundle(getArguments());
    }

    private void extractFromSavedInstanceState(final Bundle savedInstanceState) {
        extractBundle(savedInstanceState);
    }

    private void extractBundle(final Bundle bundle) {
        Optional.ofNullable(bundle)
                .map(bundle1 -> (Extras) bundle1.getParcelable(KEY_EXTRAS))
                .map(extras -> extras.username)
                .ifPresentOrElse(presenter::init,
                                 this::throwArgumentsMissingException);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_EXTRAS, getArguments().getParcelable(KEY_EXTRAS));
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void render(final UserDetailViewModel viewModel) {
        loadImage(viewModel);

        nameTextView.setText(String.format(resources.getString(R.string.user_details_name_template), stringUtils.itOrDefault(viewModel.name, NOT_AVAILABLE)));
        usernameTextView.setText(String.format(resources.getString(R.string.user_details_username_template), stringUtils.itOrDefault(viewModel.username, NOT_AVAILABLE)));
        typeTextView.setText(String.format(resources.getString(R.string.user_details_type_template), stringUtils.itOrDefault(viewModel.type, NOT_AVAILABLE)));
        companyNameTextView.setText(String.format(resources.getString(R.string.user_details_company_name_template), stringUtils.itOrDefault(viewModel.companyName, NOT_AVAILABLE)));
        locationTextView.setText(String.format(resources.getString(R.string.user_details_location_template), stringUtils.itOrDefault(viewModel.location, NOT_AVAILABLE)));
        emailTextView.setText(String.format(resources.getString(R.string.user_details_email_template), stringUtils.itOrDefault(viewModel.email, NOT_AVAILABLE)));
        siteAdminTextView.setText(String.format(resources.getString(R.string.user_details_site_admin), viewModel.siteAdmin));
        hireableTextView.setText(String.format(resources.getString(R.string.user_details_hireable), viewModel.hireable));
        followersTextView.setText(String.format(resources.getString(R.string.user_details_followers_template), viewModel.following));
        followingTextView.setText(String.format(resources.getString(R.string.user_details_following_template), viewModel.followers));
        createdAtTextView.setText(String.format(resources.getString(R.string.user_details_created_at_template), stringUtils.itOrDefault(viewModel.createdAt, NOT_AVAILABLE)));
        updatedAtTextView.setText(String.format(resources.getString(R.string.user_details_updated_at_template), stringUtils.itOrDefault(viewModel.updatedAt, NOT_AVAILABLE)));
        publicReposTextView.setText(String.format(resources.getString(R.string.user_details_public_repos_template), viewModel.publicRepos));
        publicGistsTextView.setText(String.format(resources.getString(R.string.user_details_public_gists_template), viewModel.publicGists));

        visitBlogButton.setVisibility(viewModel.hasBlog ? View.VISIBLE : View.GONE);
    }

    private void loadImage(final UserDetailViewModel viewModel) {
        if (isImageMeasured()) {
            loadImageToMeasuredView(viewModel.avatarUrl);
        } else {
            loadImageAfterDrawn(viewModel);
        }
    }

    private boolean isImageMeasured() {
        return avatarImageView.getWidth() > 0 && avatarImageView.getHeight() > 0;
    }

    private void loadImageToMeasuredView(final String imageUrl) {
        imageLoader.loadImage(imageUrl, avatarImageView);
    }

    private void loadImageAfterDrawn(final UserDetailViewModel viewModel) {
        viewUtils.doOnPreDraw(avatarImageView, () -> {
            loadImageToMeasuredView(viewModel.avatarUrl);
            avatarImageView.requestLayout();
        }, true);
    }

    @Override
    public void showErrorMessage() {
        showShortToast(R.string.user_details_toast_error_message);
    }

    @Override
    public void showBrowserNotAvailableErrorMessage() {
        showShortToast(R.string.user_details_browser_not_available_error_message);
    }

    @OnClick(R.id.fragment_user_detail_visit_blog_button)
    void onVisitBlogButtonClicked() {
        presenter.visitBlog();
    }

    static final class Extras implements Parcelable {

        final String username;

        Extras(final String username) {
            this.username = username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.username);
        }

        protected Extras(Parcel in) {
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
