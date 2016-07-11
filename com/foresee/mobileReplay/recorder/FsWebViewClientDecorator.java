package com.foresee.mobileReplay.recorder;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class FsWebViewClientDecorator extends FSWebViewClient {
    private WebViewClient delegate;

    FsWebViewClientDecorator(String str, WebViewRegistry webViewRegistry, WebViewClient webViewClient) {
        super(str, webViewRegistry);
        this.delegate = webViewClient;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return this.delegate.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.delegate.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.delegate.onPageFinished(webView, str);
    }

    public void onLoadResource(WebView webView, String str) {
        this.delegate.onLoadResource(webView, str);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return this.delegate.shouldInterceptRequest(webView, str);
    }

    @Deprecated
    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        this.delegate.onTooManyRedirects(webView, message, message2);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        this.delegate.onReceivedError(webView, i, str, str2);
    }

    public void onFormResubmission(WebView webView, Message message, Message message2) {
        this.delegate.onFormResubmission(webView, message, message2);
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.delegate.doUpdateVisitedHistory(webView, str, z);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.delegate.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.delegate.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return this.delegate.shouldOverrideKeyEvent(webView, keyEvent);
    }

    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.delegate.onUnhandledKeyEvent(webView, keyEvent);
    }

    public void onScaleChanged(WebView webView, float f, float f2) {
        this.delegate.onScaleChanged(webView, f, f2);
    }

    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        this.delegate.onReceivedLoginRequest(webView, str, str2, str3);
    }
}
