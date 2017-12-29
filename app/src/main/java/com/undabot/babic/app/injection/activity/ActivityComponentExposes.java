package com.undabot.babic.app.injection.activity;

import com.undabot.babic.app.injection.activity.module.ActivityModule;
import com.undabot.babic.app.injection.activity.module.ActivityPresenterModule;
import com.undabot.babic.app.injection.activity.module.UiAdapterModule;
import com.undabot.babic.app.injection.application.ApplicationComponentExposes;
import com.undabot.babic.app.injection.application.module.UseCaseModule;

public interface ActivityComponentExposes extends ApplicationComponentExposes,
                                                  ActivityModule.Exposes,
                                                  ActivityPresenterModule.Exposes,
                                                  UiAdapterModule.Exposes,
                                                  UseCaseModule.Exposes { }
