package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.appboy.ui.C0401R;

public class AppboyInAppMessageHtmlFullView extends AppboyInAppMessageHtmlBaseView {
    private WebView mMessageWebView;

    public AppboyInAppMessageHtmlFullView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WebView getMessageWebView() {
        if (this.mMessageWebView == null) {
            this.mMessageWebView = (WebView) findViewById(C0401R.id.com_appboy_inappmessage_html_full_webview);
            if (this.mMessageWebView != null) {
                this.mMessageWebView.getSettings().setJavaScriptEnabled(true);
                this.mMessageWebView.setBackgroundColor(0);
            }
        }
        return this.mMessageWebView;
    }
}
