package com.foresee.sdk.tracker.app;

import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.foresee.sdk.tracker.TrackingContext;

public class JsInterface {
    private ISurveyCallback callback;
    private TrackingContext trackingContext;

    public JsInterface(ISurveyCallback iSurveyCallback, TrackingContext trackingContext) {
        this.callback = iSurveyCallback;
        this.trackingContext = trackingContext;
    }

    @JavascriptInterface
    public void completeSurvey() {
        this.callback.surveyCompleted();
    }

    @JavascriptInterface
    public void abortSurvey() {
        this.callback.surveyAborted();
    }

    @JavascriptInterface
    public void showToast(String str) {
        Toast.makeText(this.callback.getContext(), str, 0).show();
    }
}
