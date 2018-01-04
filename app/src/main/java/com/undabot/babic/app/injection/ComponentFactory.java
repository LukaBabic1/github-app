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
import com.undabot.babic.app.injection.user.DaggerUserComponent;
import com.undabot.babic.app.injection.user.UserApiClientModule;
import com.undabot.babic.app.injection.user.UserComponent;
import com.undabot.babic.domain.model.AuthToken;

public final class ComponentFactory {

    public static ApplicationComponent createApplicationComponent(final GithubApplication application) {
        return DaggerApplicationComponent.builder()
                                         .applicationModule(new ApplicationModule(application))
                                         .build();
    }

    public static UserComponent createUserComponent(final ApplicationComponent applicationComponent, final AuthToken authToken) {
        return DaggerUserComponent.builder().applicationComponent(applicationComponent)
                                  .userApiClientModule(new UserApiClientModule(authToken))
                                  .build();
    }

    public static ActivityComponent createActivityComponent(final DaggerActivity activity, final GithubApplication application) {
        return DaggerActivityComponent.builder()
                                      .userComponent(application.getUserComponent())
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
