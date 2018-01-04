package com.undabot.babic.app.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.undabot.babic.app.R;
import com.undabot.babic.app.base.BaseActivity;
import com.undabot.babic.app.base.ScopedPresenter;
import com.undabot.babic.app.injection.activity.ActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class LoginActivity extends BaseActivity implements LoginContract.View {

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
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                super.shouldOverrideUrlLoading(view, url);
                // Try catch to allow in app browsing without crashing.
                try {
                    if (!url.contains("?code=")) {
                        return false;
                    }

                    String code = url.substring(url.lastIndexOf("?code=") + 1);
                    String[] token_code = code.split("=");
                    String tokenFetchedIs = token_code[1];
                    String[] cleanToken = tokenFetchedIs.split("&");

                    fetchOauthTokenWithCode(cleanToken[0]);

                } catch (final NullPointerException | ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
    }

    private void fetchOauthTokenWithCode(final String code) {

//        HttpUrl.Builder url = HttpUrl.parse(GITHUB_OAUTH)
//                                     .newBuilder();
//
//        url.addQueryParameter("client_id", CLIENT_ID);
//        url.addQueryParameter("client_secret", CLIENT_SECRET);
//        url.addQueryParameter("code", code);
//
//        String url_oauth = url.build().toString();
//
//        final Request request = new Request.Builder().header("Accept", "application/json")
//                                                     .url(url_oauth)
//                                                     .build();
//
//        OkHttpClient client = new OkHttpClient();
//        client.newCall(request)
//              .enqueue(new Callback() {
//
//                  @Override
//                  public void onFailure(Call call, IOException e) {
//                      System.out.println(e);
//                  }
//
//                  @Override
//                  public void onResponse(Call call, Response response) throws IOException {
//
//                      if (response.isSuccessful()) {
//                          String JsonData = response.body().string();
//
//                          try {
//                              JSONObject jsonObject = new JSONObject(JsonData);
//                              String auth_token = jsonObject.getString("access_token");
//
//                              System.out.println("Token: " + auth_token);
//                          } catch (final JSONException exception) {
//                              System.out.println(exception);
//                          }
//
//                          startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                      } else {
//                          System.out.println("No success - " + response.message());
//                      }
//                  }
//              });
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
}
