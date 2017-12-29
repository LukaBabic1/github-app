package com.undabot.babic.app.utils.ui;

import android.view.View;

public interface KeyboardUtils {

    void showSoftKeyboard();

    void hideSoftKeyboard(View v);

    boolean isKeyboardShown();
}
