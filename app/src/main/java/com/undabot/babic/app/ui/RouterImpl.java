package com.undabot.babic.app.ui;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

import com.undabot.babic.app.R;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailFragment;
import com.undabot.babic.app.ui.userdetails.UserDetailsFragment;

public final class RouterImpl implements Router {

    private static final int CONTAINER_ID = R.id.activity_main_fragment_container;

    private final Activity activity;
    private final FragmentManager fragmentManager;

    public RouterImpl(final Activity activity, final FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void showUserDetailsScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, UserDetailsFragment.newInstance(), UserDetailsFragment.TAG)
                       .commit();
    }

    @Override
    public void showRepositoryDetailsScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, RepositoryDetailFragment.newInstance(), UserDetailsFragment.TAG)
                       .commit();
    }

    @Override
    public void goBack() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            activity.finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
