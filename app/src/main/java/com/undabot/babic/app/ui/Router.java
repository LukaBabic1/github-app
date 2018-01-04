package com.undabot.babic.app.ui;

public interface Router {

    void showMainScreen();

    void showRepositorySearchScreen();

    void showUserDetailsScreen(String username);

    void showRepositoryDetailsScreen(String repositoryName, String username);

    void goBack();

    boolean showPageInExternalBrowser(String url);
}
