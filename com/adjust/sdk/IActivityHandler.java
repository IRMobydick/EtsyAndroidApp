package com.adjust.sdk;

import android.net.Uri;
import org.json.JSONObject;

public interface IActivityHandler {
    void finishedTrackingActivity(JSONObject jSONObject);

    ActivityPackage getAttributionPackage();

    void init(AdjustConfig adjustConfig);

    boolean isEnabled();

    void readOpenUrl(Uri uri, long j);

    void sendReferrer(String str, long j);

    void setAskingAttribution(boolean z);

    void setEnabled(boolean z);

    void setOfflineMode(boolean z);

    void trackEvent(AdjustEvent adjustEvent);

    void trackSubsessionEnd();

    void trackSubsessionStart();

    boolean tryUpdateAttribution(AdjustAttribution adjustAttribution);
}
