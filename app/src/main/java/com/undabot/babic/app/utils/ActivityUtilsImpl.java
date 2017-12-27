package com.undabot.babic.app.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.annimon.stream.Optional;
import com.undabot.babic.app.base.BackPropagatingFragment;

import java.util.List;

import rx.functions.Func1;

public final class ActivityUtilsImpl implements ActivityUtils {

    @Override
    public void addFragmentToActivity(@NonNull final FragmentManager fragmentManager, @NonNull final Fragment fragment, final int frameId) {
        if (!fragment.isAdded()) {
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commit();
        }
    }

    @Override
    public void addFragmentWithTagToActivity(@NonNull final FragmentManager fragmentManager, @NonNull final Fragment fragment, final int frameId, @NonNull final String tag) {
        if (!fragment.isAdded()) {
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment, tag);
            transaction.commit();
        }
    }

    @Override
    public void addFragmentWithTagToActivity(@NonNull final FragmentManager fragmentManager, @NonNull final Fragment fragment, final String tag, final int frameId,
                                             final String backStackName) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(backStackName);
        transaction.commit();
    }


    /**
     * @return {@code true} is back is consumed, {@code false} otherwise
     */
    @Override
    public boolean propagateBackToTopFragment(@NonNull final FragmentManager fragmentManager) {
        return callIfPresent(findBackPropagatingFragment(fragmentManager), BackPropagatingFragment::onBack);
    }

    private boolean callIfPresent(final Optional<BackPropagatingFragment> backPropagatingFragmentOptional, final Func1<BackPropagatingFragment, Boolean> action) {
        if (backPropagatingFragmentOptional.isPresent()) {
            final BackPropagatingFragment backPropagatingFragment = backPropagatingFragmentOptional.get();
            return action.call(backPropagatingFragment);
        }
        return false;
    }

    private Optional<BackPropagatingFragment> findBackPropagatingFragment(final FragmentManager fragmentManager) {
        final List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.isEmpty()) {
            return Optional.empty();
        }
        for (int i = fragments.size() - 1; i >= 0; i--) {
            final Fragment fragment = fragments.get(i);
            if (fragment != null && fragment.isVisible() && fragment.getUserVisibleHint()) {
                if (fragment instanceof BackPropagatingFragment) {
                    return Optional.of(((BackPropagatingFragment) fragment));
                }
                break;
            }
        }
        return Optional.empty();
    }
}