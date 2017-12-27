package com.undabot.babic.app.injection.activity;

import com.undabot.babic.app.injection.activity.module.ActivityModule;
import com.undabot.babic.app.injection.activity.module.ActivityPresenterModule;
import com.undabot.babic.app.injection.application.ApplicationComponent;
import com.undabot.babic.app.injection.application.module.UseCaseModule;
import com.undabot.babic.app.injection.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class,
                ActivityPresenterModule.class,
                UseCaseModule.class
        }
)
public interface ActivityComponent extends ActivityComponentInjects, ActivityComponentExposes {}
