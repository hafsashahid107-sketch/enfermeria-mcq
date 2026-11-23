package com.enfermeriaconhafsa.mcq;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private static final String URL = "https://hafsashahid107-sketch.github.io/enfermeria-mcq/";
    private WebView web;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.parseColor("#0C447C"));
        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        web.setWebViewClient(new OfflineClient());
        setContentView(web);
        if (state != null) web.restoreState(state); else web.loadUrl(URL);
    }

    @Override
    protected void onSaveInstanceState(Bundle out) { super.onSaveInstanceState(out); web.saveState(out); }

    @Override
    public void onBackPressed() { if (web.canGoBack()) web.goBack(); else super.onBackPressed(); }

    static class OfflineClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView v, WebResourceRequest req, WebResourceError err) {
            // No internet and nothing cached yet: show the copy bundled inside the app.
            if (req.isForMainFrame()) v.loadUrl("file:///android_asset/index.html");
        }
    }
}
