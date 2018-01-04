package com.undabot.babic.app.injection.user;

import com.undabot.babic.app.injection.application.ApplicationComponentExposes;
import com.undabot.babic.app.injection.application.module.UseCaseModule;

public interface UserComponentExposes extends ApplicationComponentExposes,
                                              UserApiClientModule.Exposes,
                                              UserRepositoryModule.Exposes,
                                              UseCaseModule.Exposes { }
