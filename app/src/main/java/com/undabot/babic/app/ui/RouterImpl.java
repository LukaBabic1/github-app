package com.undabot.babic.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;

import com.undabot.babic.app.R;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailFragment;
import com.undabot.babic.app.ui.search.RepositorySearchFragment;
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
    public void showRepositorySearchScreen() {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, RepositorySearchFragment.newInstance(), RepositorySearchFragment.TAG)
                       .commit();
    }

    @Override
    public void showUserDetailsScreen(final String username) {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, UserDetailsFragment.newInstance(username), UserDetailsFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    @Override
    public void showRepositoryDetailsScreen(final String repositoryName, final String username) {
        fragmentManager.beginTransaction()
                       .replace(CONTAINER_ID, RepositoryDetailFragment.newInstance(repositoryName, username), RepositoryDetailFragment.TAG)
                       .addToBackStack(null)
                       .commit();
    }

    @Override
    public void showPageInExternalBrowser(final String url) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
    }

    public void goBack() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            activity.finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
