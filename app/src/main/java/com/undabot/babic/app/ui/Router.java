package com.undabot.babic.app.ui;

public interface Router {

    void showRepositorySearchScreen();

    void showUserDetailsScreen(String username);

    void showRepositoryDetailsScreen(int repositoryId);

    void goBack();
}
