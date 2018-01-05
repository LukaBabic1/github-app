package com.undabot.babic.app.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
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

    @BindView(R.id.activity_login_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_login_web_view)
    WebView webView;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        initToolbar();
        initWebView();

        presenter.init();
    }

    private void initToolbar() {
        toolbar.inflateMenu(R.menu.activity_login_menu);
        toolbar.setTitle(R.string.login_screen_toolbar_title);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.activity_main_menu_item_skip) {
                presenter.skipLogin();
                return true;
            }

            return false;
        });
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