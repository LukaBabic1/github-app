package com.undabot.babic.app.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseActivity;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.activity.ActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void inject(final ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected ScopedPresenter getPresenter() {
        return presenter;
    }
}
