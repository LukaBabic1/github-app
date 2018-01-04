package com.undabot.babic.app.injection.application;

import com.undabot.babic.app.injection.application.module.ApiModule;
import com.undabot.babic.app.injection.application.module.ConnectivityModule;
import com.undabot.babic.app.injection.application.module.DataModule;
import com.undabot.babic.app.injection.user.UserRepositoryModule;
import com.undabot.babic.app.injection.application.module.ThreadingModule;
import com.undabot.babic.app.injection.application.module.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                ConnectivityModule.class,
                DataModule.class,
                UserRepositoryModule.class,
                ThreadingModule.class,
                UtilsModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentInjects,
                                              ApplicationComponentExposes { }
