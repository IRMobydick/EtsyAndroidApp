package com.adjust.sdk;

import android.content.Context;
import org.json.JSONObject;

public interface IPackageHandler {
    void addPackage(ActivityPackage activityPackage);

    void closeFirstPackage();

    void finishedTrackingActivity(JSONObject jSONObject);

    void init(IActivityHandler iActivityHandler, Context context, boolean z);

    void pauseSending();

    void resumeSending();

    void sendFirstPackage();

    void sendNextPackage();
}
