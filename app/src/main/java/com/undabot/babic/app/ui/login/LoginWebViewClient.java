package com.undabot.babic.app.ui.login;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

public final class LoginWebViewClient extends WebViewClient {

    public interface CodeRedeemListener {

        void onCodeRedeemed(String code);

    }
    private final CodeRedeemListener listener;

    public LoginWebViewClient(final CodeRedeemListener listener) {
        this.listener = Objects.requireNonNull(listener);
    }

    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
        super.shouldOverrideUrlLoading(view, url);
        try {

            if (!url.contains("?code=")) {
                return false;
            }

            listener.onCodeRedeemed(extractCodeFromResponseUrl(url));

        } catch (final NullPointerException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String extractCodeFromResponseUrl(final String url) {
        final String code = url.substring(url.lastIndexOf("?code=") + 1);
        final String[] token_code = code.split("=");
        final String tokenFetchedIs = token_code[1];
        final String[] cleanToken = tokenFetchedIs.split("&");

        return cleanToken[0];
    }
}
