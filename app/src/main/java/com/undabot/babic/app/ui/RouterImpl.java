package com.undabot.babic.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;

import com.undabot.babic.app.R;
import com.undabot.babic.app.ui.main.MainActivity;
import com.undabot.babic.app.ui.repositorydetail.RepositoryDetailFragment;
import com.undabot.babic.app.ui.search.RepositorySearchFragment;
import com.undabot.babic.app.ui.userdetails.UserDetailsFragment;
import com.undabot.babic.domain.utils.ListUtils;

public final class RouterImpl implements Router {

    private static final int CONTAINER_ID = R.id.activity_main_fragment_container;

    private final Activity activity;
    private final FragmentManager fragmentManager;
    private final ListUtils listUtils;

    public RouterImpl(final Activity activity, final FragmentManager fragmentManager, final ListUtils listUtils) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.listUtils = listUtils;
    }

    @Override
    public void showMainScreen() {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
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
    public boolean showPageInExternalBrowser(final String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));

        if (canIntentBeResolved(intent)) {
            activity.startActivity(intent);
            return true;
        }

        return false;
    }

    private boolean canIntentBeResolved(final Intent intent) {
        return !listUtils.isEmpty(activity.getPackageManager().queryIntentActivities(intent, 0));
    }

    public void goBack() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            activity.finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
