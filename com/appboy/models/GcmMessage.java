package com.appboy.models;

import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.DeviceNotification;
import com.etsy.android.lib.models.finds.FindsModule;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class GcmMessage implements IPutIntoJson<JSONObject> {
    private static final String f899a;
    private final String f900b;
    private final String f901c;
    private final Map<String, String> f902d;
    private final String f903e;
    private final String f904f;
    private final Integer f905g;

    static {
        f899a = String.format("%s.%s", new Object[]{Constants.APPBOY, GcmMessage.class.getName()});
    }

    public GcmMessage(String str, String str2, Map<String, String> map, String str3, String str4, Integer num) {
        this.f900b = str;
        this.f901c = str2;
        if (map != null) {
            this.f902d = map;
        } else {
            this.f902d = new HashMap();
        }
        this.f903e = str3;
        this.f904f = str4;
        this.f905g = num;
    }

    public String getTitle() {
        return this.f900b;
    }

    public String getContent() {
        return this.f901c;
    }

    public String getCampaignId() {
        return this.f904f;
    }

    public Map<String, String> getExtras() {
        return this.f902d;
    }

    public Integer getNotificationId() {
        return this.f905g;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FindsModule.FIELD_TITLE, this.f900b);
            jSONObject.put("content", this.f901c);
            jSONObject.put("extras", new JSONObject(this.f902d));
            if (this.f903e != null) {
                jSONObject.put(Constants.APPBOY_GCM_MESSAGE_TYPE_KEY, this.f903e);
            }
            if (this.f904f != null) {
                jSONObject.put("campaign_id", this.f904f);
            }
            if (this.f905g != null) {
                jSONObject.put(DeviceNotification.FIELD_NOTIFICATION_ID, this.f905g);
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f899a, "Caught exception creating gcm message Json.", e);
        }
        return jSONObject;
    }
}
