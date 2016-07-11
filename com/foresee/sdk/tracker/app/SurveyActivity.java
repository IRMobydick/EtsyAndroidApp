package com.foresee.sdk.tracker.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.foresee.sdk.configuration.MeasureConfiguration;
import com.foresee.sdk.instrumentation.SessionReplayPage;
import com.foresee.sdk.tracker.SurveyUrlBuilder;
import com.foresee.sdk.tracker.TrackingContext;
import com.foresee.sdk.tracker.layouts.SurveyView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyActivity extends Activity implements SessionReplayPage, ISurveyCallback {
    private JsInterface jsInterface;
    private Logger logger;
    private WebView webView;

    class SurveyWebClient extends WebViewClient {
        private SurveyWebClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (TrackingContext.Instance().getConfiguration().isDomainWhiteListed(str)) {
                SurveyActivity.this.webView.loadUrl(str);
            } else {
                SurveyActivity.this.logger.debug("Requested URL not white listed: {}", (Object) str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addFlags(268435456);
                SurveyActivity.this.startActivity(intent);
            }
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            switch (i) {
                case -14:
                case -8:
                case -6:
                case ListPopupWindow.WRAP_CONTENT /*-2*/:
                    SurveyActivity.this.onNetworkNotAvailable();
                default:
            }
        }

        public void onPageFinished(WebView webView, String str) {
            SurveyActivity.this.logger.debug("Finished loading URL: {}", (Object) str);
            super.onPageFinished(webView, str);
        }
    }

    public SurveyActivity() {
        this.logger = LoggerFactory.m7499a("FORESEE_TRIGGER");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getExtras().containsKey("IS_LOCAL_NOTFICATION") && getIntent().getExtras().getBoolean("IS_LOCAL_NOTFICATION")) {
            TrackingContext.Instance().acceptedLocalNotification();
        }
        MeasureConfiguration measureConfiguration = (MeasureConfiguration) getIntent().getSerializableExtra("MEASURE_CONFIG");
        View surveyView = new SurveyView(this);
        setContentView(surveyView);
        this.jsInterface = new JsInterface(this, TrackingContext.Instance());
        this.webView = surveyView.getWebView();
        this.webView.addJavascriptInterface(this.jsInterface, "fsrTracker");
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new SurveyWebClient());
        String surveyUrlBase = TrackingContext.Instance().getSurveyUrlBase();
        String userAgentString = this.webView.getSettings().getUserAgentString();
        try {
            TrackingContext Instance = TrackingContext.Instance();
            Object buildSurveyUrl = SurveyUrlBuilder.buildSurveyUrl(surveyUrlBase, userAgentString, Instance.getApiKey(), measureConfiguration.getSurveyId(), Instance.getConfiguration().getCpps(), Instance.getConfiguration().getQueryStringParams());
            this.logger.debug("Loading survey: {}", buildSurveyUrl);
            Log.d("FORESEE_TRIGGER", String.format("Loading survey: %s", new Object[]{buildSurveyUrl}));
            this.webView.loadUrl(buildSurveyUrl);
        } catch (Throwable e) {
            this.logger.error(e.getMessage(), e);
        }
    }

    protected void onResume() {
        super.onResume();
        TrackingContext.Instance().onActivityResumed(this);
    }

    protected void onPause() {
        super.onPause();
        TrackingContext.Instance().onActivityPaused(this);
    }

    protected void onStart() {
        super.onStart();
        TrackingContext.Instance().onActivityStarted(this);
    }

    public void surveyCompleted() {
        TrackingContext.Instance().completeSurvey();
        finish();
    }

    public void surveyAborted() {
        TrackingContext.Instance().abortSurvey();
        finish();
    }

    public Context getContext() {
        return this;
    }

    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            surveyAborted();
        }
    }

    protected void onNetworkNotAvailable() {
        TrackingContext.Instance().onNetworkDisconnected(this);
    }

    public String sessionReplayPageName() {
        return "Survey";
    }
}
