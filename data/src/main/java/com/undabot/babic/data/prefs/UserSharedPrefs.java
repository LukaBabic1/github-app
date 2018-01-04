package com.undabot.babic.data.prefs;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.AuthToken;

public interface UserSharedPrefs {

    void saveAuthToken(AuthToken authToken);

    Optional<AuthToken> getAuthToken();
}
