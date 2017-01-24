package com.hatten.hatten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView web_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        web_view = (WebView)findViewById(R.id.web_view);
        web_view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web_view.getSettings().setDomStorageEnabled(true);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(mWebViewClient);
        web_view.addJavascriptInterface(new MyJavaScriptInterface(), "android");
        web_view.loadUrl("http://www.hatten.dk");
    }

    WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
        };
    };

    class MyJavaScriptInterface {
        @JavascriptInterface
        public void onUrlChange(String url) {
            Log.d("hydrated", "onUrlChange" + url);
        }
    }
}
