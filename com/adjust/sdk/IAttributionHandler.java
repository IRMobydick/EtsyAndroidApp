package com.adjust.sdk;

import org.json.JSONObject;

public interface IAttributionHandler {
    void checkAttribution(JSONObject jSONObject);

    void getAttribution();

    void init(IActivityHandler iActivityHandler, ActivityPackage activityPackage, boolean z, boolean z2);

    void pauseSending();

    void resumeSending();
}
