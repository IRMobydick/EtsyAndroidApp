package com.appboy.models.outgoing;

import bo.app.fj;
import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONObject;

public class AttributionData implements IPutIntoJson<JSONObject> {
    private static final String f996a;
    private final String f997b;
    private final String f998c;
    private final String f999d;
    private final String f1000e;

    static {
        f996a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AttributionData.class.getName()});
    }

    public AttributionData(String str, String str2, String str3, String str4) {
        this.f997b = str;
        this.f998c = str2;
        this.f999d = str3;
        this.f1000e = str4;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!fj.m354c(this.f997b)) {
                jSONObject.put("source", this.f997b);
            }
            if (!fj.m354c(this.f998c)) {
                jSONObject.put("campaign", this.f998c);
            }
            if (!fj.m354c(this.f999d)) {
                jSONObject.put("adgroup", this.f999d);
            }
            if (!fj.m354c(this.f1000e)) {
                jSONObject.put("ad", this.f1000e);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f996a, "Caught exception creating AttributionData Json.", e);
        }
        return jSONObject;
    }
}
