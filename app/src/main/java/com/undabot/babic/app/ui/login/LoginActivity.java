package com.undabot.babic.app.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseActivity;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.activity.ActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class LoginActivity extends BaseActivity implements LoginContract.View,
                                                                 LoginWebViewClient.CodeRedeemListener {

    @BindView(R.id.activity_login_web_view)
    WebView webView;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        initWebView();

        presenter.init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new LoginWebViewClient(this));
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

    @Override
    public void render(final LoginViewModel viewModel) {
        webView.loadUrl(viewModel.webViewAuthorizationUrl);
    }

    @Override
    public void onCodeRedeemed(final String code) {
        presenter.exchangeCodeForOAuthToken(code);
    }
}