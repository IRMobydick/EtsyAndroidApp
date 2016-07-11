package com.foresee.sdk.tracker;

import com.foresee.sdk.configuration.MeasureConfiguration;

public interface TrackerEventListener {
    void onInviteAccepted(MeasureConfiguration measureConfiguration);

    void onInviteDeclined(MeasureConfiguration measureConfiguration);

    void onInvitePresented(MeasureConfiguration measureConfiguration);

    void onReactivated();

    void onSamplingCheckCompleted(MeasureConfiguration measureConfiguration, boolean z);

    void onSurveyAborted(MeasureConfiguration measureConfiguration);

    void onSurveyCompleted(MeasureConfiguration measureConfiguration);
}
