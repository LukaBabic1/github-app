package com.undabot.babic.app.injection.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.undabot.babic.app.injection.ComponentFactory;
import com.undabot.babic.app.injection.activity.DaggerActivity;

public abstract class DaggerFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getFragmentComponent());
    }

    protected abstract void inject(FragmentComponent component);

    public FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, getDaggerActivity().getActivityComponent());
        }

        return fragmentComponent;
    }

    public DaggerActivity getDaggerActivity() {
        return ((DaggerActivity) getActivity());
    }
}
