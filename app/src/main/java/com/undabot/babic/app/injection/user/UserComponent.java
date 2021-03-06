package com.undabot.babic.app.injection.user;

import com.undabot.babic.app.injection.application.ApplicationComponent;
import com.undabot.babic.app.injection.application.module.UseCaseModule;
import com.undabot.babic.app.injection.scope.UserScope;

import dagger.Component;

@UserScope
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                UseCaseModule.class,
                UserApiClientModule.class,
                UserRepositoryModule.class
        }
)
public interface UserComponent extends UserComponentExposes { }
