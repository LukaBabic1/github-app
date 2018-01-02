package com.undabot.babic.app.ui;

public interface Router {

    void showRepositorySearchScreen();

    void showUserDetailsScreen(int userId);

    void showRepositoryDetailsScreen(int repositoryId);

    void goBack();
}
