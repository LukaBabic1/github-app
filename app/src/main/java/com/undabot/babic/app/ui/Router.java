package com.undabot.babic.app.ui;

public interface Router {

    void showRepositorySearchScreen();

    void showUserDetailsScreen(String username);

    void showRepositoryDetailsScreen(String repositoryName, String username);

    void goBack();

    void showPageInExternalBrowser(String url);
}
