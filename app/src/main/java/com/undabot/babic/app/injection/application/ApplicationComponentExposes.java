package com.undabot.babic.app.injection.application;

import com.undabot.babic.app.injection.application.module.ApiModule;
import com.undabot.babic.app.injection.application.module.ConnectivityModule;
import com.undabot.babic.app.injection.application.module.DataModule;
import com.undabot.babic.app.injection.application.module.ThreadingModule;
import com.undabot.babic.app.injection.application.module.UtilsModule;

public interface ApplicationComponentExposes extends ApplicationModule.Exposes,
                                                     ApiModule.Exposes,
                                                     ConnectivityModule.Exposes,
                                                     DataModule.Exposes,
                                                     ThreadingModule.Exposes,
                                                     UtilsModule.Exposes { }
