package com.foresee.sdk.tracker.app;

import android.content.Context;

public interface ISurveyCallback {
    Context getContext();

    void surveyAborted();

    void surveyCompleted();
}
