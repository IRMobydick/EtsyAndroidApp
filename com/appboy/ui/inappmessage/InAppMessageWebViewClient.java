package com.appboy.ui.inappmessage;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.Constants;
import com.appboy.models.IInAppMessage;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.support.StringUtils;
import com.appboy.ui.support.UriUtils;
import java.util.Map;

public class InAppMessageWebViewClient extends WebViewClient {
    private static final String APPBOY_INAPP_MESSAGE_SCHEME = "appboy";
    private static final String AUTHORITY_NAME_CLOSE = "close";
    private static final String AUTHORITY_NAME_CUSTOM_EVENT = "customEvent";
    private static final String AUTHORITY_NAME_NEWSFEED = "feed";
    public static final String QUERY_NAME_BUTTON_ID = "abButtonId";
    public static final String QUERY_NAME_EXTERNAL_OPEN = "abExternalOpen";
    private static final String TAG;
    private final IInAppMessage mInAppMessage;
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, InAppMessageWebViewClient.class.getName()});
    }

    public InAppMessageWebViewClient(IInAppMessage iInAppMessage, IInAppMessageWebViewClientListener iInAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = iInAppMessageWebViewClientListener;
        this.mInAppMessage = iInAppMessage;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.mInAppMessageWebViewClientListener == null) {
            AppboyLogger.m666i(TAG, "InAppMessageWebViewClient was given null IInAppMessageWebViewClientListener listener. Returning true.");
        } else if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m666i(TAG, "InAppMessageWebViewClient.shouldOverrideUrlLoading was given null or blank url. Returning true.");
        } else {
            Uri parse = Uri.parse(str);
            Bundle bundleFromUrl = getBundleFromUrl(str);
            if (parse.getScheme().equals(APPBOY_INAPP_MESSAGE_SCHEME)) {
                String authority = parse.getAuthority();
                if (authority.equals(AUTHORITY_NAME_CLOSE)) {
                    this.mInAppMessageWebViewClientListener.onCloseAction(this.mInAppMessage, str, bundleFromUrl);
                } else if (authority.equals(AUTHORITY_NAME_NEWSFEED)) {
                    this.mInAppMessageWebViewClientListener.onNewsfeedAction(this.mInAppMessage, str, bundleFromUrl);
                } else if (authority.equals(AUTHORITY_NAME_CUSTOM_EVENT)) {
                    this.mInAppMessageWebViewClientListener.onCustomEventAction(this.mInAppMessage, str, bundleFromUrl);
                }
            } else {
                this.mInAppMessageWebViewClientListener.onOtherUrlAction(this.mInAppMessage, str, bundleFromUrl);
            }
        }
        return true;
    }

    static Bundle getBundleFromUrl(String str) {
        Bundle bundle = new Bundle();
        if (StringUtils.isNullOrBlank(str)) {
            return bundle;
        }
        Map queryParameters = UriUtils.getQueryParameters(Uri.parse(str));
        for (String str2 : queryParameters.keySet()) {
            bundle.putString(str2, (String) queryParameters.get(str2));
        }
        return bundle;
    }
}
