package com.jinq.widgetsx.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.jinq.widgetsx.MainActivity;
import com.jinq.widgetsx.R;
import com.jinq.widgetsx.base.BaseActivity;
import com.jinq.widgetsx.presenter.BasePresenter;
import com.jinq.widgetsx.util.PrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity{
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        this.setTheme(R.style.SplashFullScreen);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        SplashActivity.JSKit jsKit = new SplashActivity.JSKit(this);
        mWebView.addJavascriptInterface(jsKit, "Widgets");
        mWebView.loadUrl("file:///android_asset/web_page/page.html");
    }

    @Override
    protected void dismissData() {

    }

    class JSKit {
        private SplashActivity context;

        public JSKit(SplashActivity context) {
            this.context = context;
        }

        @JavascriptInterface
        public void jumpMainActivity() {
            boolean loginState = PrefUtil.getLoginState(context);
            if(!loginState) {
//                Intent intent = new Intent(context, LoginActivity.class);
//                startActivity(intent);
//                //TODO transition
//                finish();
            }else{
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
