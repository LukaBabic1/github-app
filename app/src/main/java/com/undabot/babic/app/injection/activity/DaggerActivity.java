package com.undabot.babic.app.injection.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.undabot.babic.app.application.GithubApplication;
import com.undabot.babic.app.injection.ComponentFactory;

public abstract class DaggerActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getActivityComponent());
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getGithubApplication());
        }

        return activityComponent;
    }

    private GithubApplication getGithubApplication() {
        return GithubApplication.from(this);
    }

    protected abstract void inject(final ActivityComponent component);
}

