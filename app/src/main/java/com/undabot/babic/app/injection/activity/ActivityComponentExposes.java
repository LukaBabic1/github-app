package com.undabot.babic.app.injection.activity;

import com.undabot.babic.app.injection.activity.module.ActivityModule;
import com.undabot.babic.app.injection.activity.module.ActivityPresenterModule;
import com.undabot.babic.app.injection.activity.module.UiAdapterModule;
import com.undabot.babic.app.injection.user.UserComponentExposes;

public interface ActivityComponentExposes extends UserComponentExposes,
                                                  ActivityModule.Exposes,
                                                  ActivityPresenterModule.Exposes,
                                                  UiAdapterModule.Exposes { }
