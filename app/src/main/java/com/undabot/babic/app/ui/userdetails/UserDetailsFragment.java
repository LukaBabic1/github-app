package com.undabot.babic.app.ui.userdetails;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;
import com.undabot.babic.app.utils.ui.ImageLoader;
import com.undabot.babic.app.utils.view.ViewUtils;

import javax.inject.Inject;

import butterknife.BindView;

public final class UserDetailsFragment extends BaseFragment implements UserDetailsContract.View {

    public static final String TAG = "UserDetailsFragment";

    private static final String KEY_EXTRAS = "key_fragment_extras";

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

    @Inject
    UserDetailsContract.Presenter presenter;

    @Inject
    ImageLoader imageLoader;

    @Inject
    ViewUtils viewUtils;

    public static UserDetailsFragment newInstance(final String username) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_EXTRAS, new Extras(username));

        final UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractArguments();
    }

    private void extractArguments() {
        Optional.ofNullable(getArguments())
                .map(bundle -> (Extras) bundle.getParcelable(KEY_EXTRAS))
                .map(extras -> extras.username)
                .ifPresentOrElse(presenter::init,
                                 this::throwArgumentsMissingException);
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
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void render(final UserDetailViewModel viewModel) {
        loadImage(viewModel);

        nameTextView.setText(String.format(resources.getString(R.string.user_details_name_template), viewModel.name));
        usernameTextView.setText(String.format(resources.getString(R.string.user_details_username_template), viewModel.username));
        typeTextView.setText(String.format(resources.getString(R.string.user_details_type_template), viewModel.type));
        companyNameTextView.setText(String.format(resources.getString(R.string.user_details_company_name_template), viewModel.companyName));
        locationTextView.setText(String.format(resources.getString(R.string.user_details_location_template), viewModel.location));
        emailTextView.setText(String.format(resources.getString(R.string.user_details_email_template), viewModel.email));
        siteAdminTextView.setText(String.format(resources.getString(R.string.user_details_site_admin), viewModel.siteAdmin));
        hireableTextView.setText(String.format(resources.getString(R.string.user_details_hireable), viewModel.hireable));
        followersTextView.setText(String.format(resources.getString(R.string.user_details_followers_template), viewModel.following));
        followingTextView.setText(String.format(resources.getString(R.string.user_details_following_template), viewModel.followers));
        createdAtTextView.setText(String.format(resources.getString(R.string.user_details_created_at_template), viewModel.createdAt));
        updatedAtTextView.setText(String.format(resources.getString(R.string.user_details_updated_at_template), viewModel.updatedAt));
        publicReposTextView.setText(String.format(resources.getString(R.string.user_details_public_repos_template), viewModel.publicRepos));
        publicGistsTextView.setText(String.format(resources.getString(R.string.user_details_public_gists_template), viewModel.publicGists));
    }

    private void loadImage(final UserDetailViewModel viewModel) {
        if (isImageMeasured()) {
            loadImageToMeasuredView(viewModel.avatarUrl);
        } else {
            viewUtils.doOnPreDraw(avatarImageView, () -> {
                loadImageToMeasuredView(viewModel.avatarUrl);
                avatarImageView.requestLayout();
            }, true);
        }
    }

    private boolean isImageMeasured() {
        return avatarImageView.getWidth() > 0 && avatarImageView.getHeight() > 0;
    }

    private void loadImageToMeasuredView(final String imageUrl) {
        imageLoader.loadImage(imageUrl, avatarImageView);
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
