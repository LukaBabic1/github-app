package com.undabot.babic.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.annimon.stream.Optional;
import com.undabot.babic.domain.model.AuthToken;

public final class UserSharedPrefsImpl implements UserSharedPrefs {

    private static final String PREFS_FILE_NAME = "user_shared_preferences";

    private static final String KEY_ACCESS_TOKEN = "key_access_token";
    private static final String KEY_CURRENT_USER_USRENAME = "key_current_user_username";

    private final SharedPreferences preferences;

    public static UserSharedPrefsImpl create(final Context context) {
        return new UserSharedPrefsImpl(context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE));
    }

    private UserSharedPrefsImpl(final SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void saveAuthToken(final AuthToken authToken) {
        preferences.edit()
                   .putString(KEY_ACCESS_TOKEN, authToken.value)
                   .apply();
    }

    @Override
    public Optional<AuthToken> getAuthToken() {
        return Optional.ofNullable(preferences.getString(KEY_ACCESS_TOKEN, null))
                       .filter(value -> !AuthToken.EMPTY.value.equals(value))
                       .map(AuthToken::new);
    }

    @Override
    public void saveCurrentUserUsername(final String username) {
        preferences.edit()
                   .putString(KEY_CURRENT_USER_USRENAME, username)
                   .apply();
    }

    @Override
    public String getCurrentUserUsername() {
        return preferences.getString(KEY_CURRENT_USER_USRENAME, "");
    }
}
