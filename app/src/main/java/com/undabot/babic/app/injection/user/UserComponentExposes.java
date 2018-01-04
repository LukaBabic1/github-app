package com.undabot.babic.app.injection.user;

import com.undabot.babic.app.injection.application.ApplicationComponentExposes;

public interface UserComponentExposes extends ApplicationComponentExposes,
                                              UserApiClientModule.Exposes,
                                              UserRepositoryModule.Exposes {

}
