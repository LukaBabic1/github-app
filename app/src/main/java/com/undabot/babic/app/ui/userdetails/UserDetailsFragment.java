package com.undabot.babic.app.ui.userdetails;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.annimon.stream.Optional;
import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseFragment;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.fragment.FragmentComponent;

import javax.inject.Inject;

public final class UserDetailsFragment extends BaseFragment implements UserDetailsContract.View {

    public static final String TAG = "UserDetailsFragment";

    private static final String KEY_EXTRAS = "key_fragment_extras";

    @Inject
    UserDetailsContract.Presenter presenter;

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

    static final class Extras implements Parcelable {

        final String username;

        public Extras(final String username) {
            this.username = username;
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {dest.writeString(this.username);}

        protected Extras(Parcel in) {this.username = in.readString();}

        public static final Creator<Extras> CREATOR = new Creator<Extras>() {

            @Override
            public Extras createFromParcel(Parcel source) {return new Extras(source);}

            @Override
            public Extras[] newArray(int size) {return new Extras[size];}
        };
    }
}
