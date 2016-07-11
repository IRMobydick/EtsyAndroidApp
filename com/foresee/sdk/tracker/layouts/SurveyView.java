package com.foresee.sdk.tracker.layouts;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class SurveyView extends LinearLayout {
    private WebView webView;

    public SurveyView(Activity activity) {
        super(activity);
        setOrientation(1);
        this.webView = new WebView(activity);
        this.webView.setTag("fs_unmasked");
        this.webView.setLayoutParams(new LayoutParams(-1, -1));
        this.webView.getSettings().setJavaScriptEnabled(true);
        addView(this.webView);
        activity.getWindow().requestFeature(2);
        this.webView.setWebChromeClient(new 1(this, activity));
    }

    public WebView getWebView() {
        return this.webView;
    }
}
