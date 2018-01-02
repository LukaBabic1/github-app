package com.undabot.babic.app.ui.repositorydetail;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;

import javax.inject.Inject;

public final class RepositoryDetailFragment extends BaseFragment implements RepositoryDetailContract.View {

    public static final String TAG = "RepositoryDetailFragment";

    private static final String KEY_EXTRA = "key_fragment_extra";

    @Inject
    RepositoryDetailContract.Presenter presenter;

    public static RepositoryDetailFragment newInstance(final String repositoryName, final String username) {

        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_EXTRA, new Extras(repositoryName, username));

        final RepositoryDetailFragment fragment = new RepositoryDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        extractArguments();
    }

    @Override
    protected void inject(final FragmentComponent component) {
        component.inject(this);
    }

    private void extractArguments() {
        Optional.ofNullable(getArguments())
                .map(bundle -> (Extras) bundle.getParcelable(KEY_EXTRA))
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
    protected int getLayoutResourceId() {
        return R.layout.fragment_repository_details;
    }

    @Override
    public ScopedPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void render(final RepositoryDetailViewModel viewModel) {
        System.out.println(viewModel);
    }

    static final class Extras implements Parcelable {

        final String repositoryName;
        final String username;

        Extras(final String repositoryName, final String username) {
            this.repositoryName = repositoryName;
            this.username = username;
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.repositoryName);
            dest.writeString(this.username);
        }

        protected Extras(Parcel in) {
            this.repositoryName = in.readString();
            this.username = in.readString();
        }

        public static final Parcelable.Creator<Extras> CREATOR = new Parcelable.Creator<Extras>() {

            @Override
            public Extras createFromParcel(Parcel source) {return new Extras(source);}

            @Override
            public Extras[] newArray(int size) {return new Extras[size];}
        };
    }
}
