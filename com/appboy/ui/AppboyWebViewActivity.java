package com.appboy.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.WebAction;
import com.appboy.ui.activities.AppboyBaseActivity;

public class AppboyWebViewActivity extends AppboyBaseActivity {
    private static final String TAG;
    public static final String URL_EXTRA = "url";

    /* renamed from: com.appboy.ui.AppboyWebViewActivity.1 */
    class C03911 extends WebChromeClient {
        C03911() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i < 100) {
                AppboyWebViewActivity.this.setProgressBarVisibility(true);
            } else {
                AppboyWebViewActivity.this.setProgressBarVisibility(false);
            }
        }
    }

    /* renamed from: com.appboy.ui.AppboyWebViewActivity.2 */
    class C03922 implements DownloadListener {
        C03922() {
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            AppboyWebViewActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.appboy.ui.AppboyWebViewActivity.3 */
    class C03933 extends WebViewClient {
        C03933() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                if (!WebAction.getSupportedSchemes().contains(Uri.parse(str).getScheme())) {
                    ActionFactory.createViewUriAction(str, AppboyWebViewActivity.this.getIntent().getExtras()).execute(webView.getContext());
                    AppboyWebViewActivity.this.finish();
                    return true;
                }
            } catch (Throwable e) {
                AppboyLogger.m667i(AppboyWebViewActivity.TAG, String.format("Unexpected exception while processing url %s. Passing url back to WebView.", new Object[]{str}), e);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyWebViewActivity.class.getName()});
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(2);
        requestWindowFeature(5);
        setContentView(C0401R.layout.com_appboy_webview_activity);
        setProgressBarVisibility(true);
        WebView webView = (WebView) findViewById(C0401R.id.com_appboy_webview_activity_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setPluginState(PluginState.OFF);
        setZoomSafe(settings);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        webView.setLayoutParams(new LayoutParams(-1, -1));
        webView.setWebChromeClient(new C03911());
        webView.setDownloadListener(new C03922());
        webView.getSettings().setCacheMode(2);
        setWebLayerTypeSafe(webView);
        webView.setWebViewClient(new C03933());
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(URL_EXTRA)) {
            webView.loadUrl(extras.getString(URL_EXTRA));
        }
    }

    @TargetApi(11)
    private void setZoomSafe(WebSettings webSettings) {
        if (VERSION.SDK_INT >= 11) {
            webSettings.setDisplayZoomControls(false);
        }
    }

    @TargetApi(11)
    private void setWebLayerTypeSafe(WebView webView) {
        if (VERSION.SDK_INT >= 11) {
            webView.setLayerType(1, null);
        }
    }
}
