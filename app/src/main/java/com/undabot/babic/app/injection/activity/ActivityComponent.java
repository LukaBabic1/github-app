package com.undabot.babic.app.injection.activity;

import com.undabot.babic.app.injection.activity.module.ActivityModule;
import com.undabot.babic.app.injection.activity.module.ActivityPresenterModule;
import com.undabot.babic.app.injection.activity.module.UiAdapterModule;
import com.undabot.babic.app.injection.application.module.UseCaseModule;
import com.undabot.babic.app.injection.scope.ActivityScope;
import com.undabot.babic.app.injection.user.UserComponent;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {
                UserComponent.class
        },
        modules = {
                ActivityModule.class,
                ActivityPresenterModule.class,
                UiAdapterModule.class,
                UseCaseModule.class
        }
)
public interface ActivityComponent extends ActivityComponentInjects, ActivityComponentExposes {}
