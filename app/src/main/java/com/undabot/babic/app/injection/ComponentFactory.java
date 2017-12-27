package com.undabot.babic.app.injection;

import com.undabot.babic.app.application.GithubApplication;
import com.undabot.babic.app.injection.activity.ActivityComponent;
import com.undabot.babic.app.injection.activity.DaggerActivity;
import com.undabot.babic.app.injection.activity.DaggerActivityComponent;
import com.undabot.babic.app.injection.activity.module.ActivityModule;
import com.undabot.babic.app.injection.activity.module.ActivityPresenterModule;
import com.undabot.babic.app.injection.application.ApplicationComponent;
import com.undabot.babic.app.injection.application.ApplicationModule;
import com.undabot.babic.app.injection.application.DaggerApplicationComponent;
import com.undabot.babic.app.injection.application.module.UseCaseModule;
import com.undabot.babic.app.injection.fragment.DaggerFragment;
import com.undabot.babic.app.injection.fragment.DaggerFragmentComponent;
import com.undabot.babic.app.injection.fragment.FragmentComponent;
import com.undabot.babic.app.injection.fragment.module.FragmentPresenterModule;

public final class ComponentFactory {

    public static ApplicationComponent createApplicationComponent(final GithubApplication application) {
        return DaggerApplicationComponent.builder()
                                         .applicationModule(new ApplicationModule(application))
                                         .build();
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity activity, final GithubApplication application) {
        return DaggerActivityComponent.builder()
                                      .applicationComponent(application.getApplicationComponent())
                                      .activityModule(new ActivityModule(activity))
                                      .activityPresenterModule(new ActivityPresenterModule(activity))
                                      .useCaseModule(new UseCaseModule())
                                      .build();
    }

    public static FragmentComponent createFragmentComponent(final DaggerFragment fragment, final ActivityComponent component) {
        return DaggerFragmentComponent.builder()
                                      .activityComponent(component)
                                      .fragmentPresenterModule(new FragmentPresenterModule(fragment))
                                      .build();
    }
}
