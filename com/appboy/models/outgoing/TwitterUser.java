package com.appboy.models.outgoing;

import bo.app.fj;
import com.appboy.Constants;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONObject;

public class TwitterUser implements IPutIntoJson<JSONObject> {
    private static final String f1026a;
    private final Integer f1027b;
    private final String f1028c;
    private final String f1029d;
    private final String f1030e;
    private final Integer f1031f;
    private final Integer f1032g;
    private final Integer f1033h;
    private final String f1034i;

    static {
        f1026a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, TwitterUser.class.getName()});
    }

    public TwitterUser(Integer num, String str, String str2, String str3, Integer num2, Integer num3, Integer num4, String str4) {
        this.f1027b = num;
        this.f1028c = str;
        this.f1029d = str2;
        this.f1030e = str3;
        this.f1031f = num2;
        this.f1032g = num3;
        this.f1033h = num4;
        this.f1034i = str4;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!fj.m354c(this.f1028c)) {
                jSONObject.put("screen_name", this.f1028c);
            }
            if (!fj.m354c(this.f1029d)) {
                jSONObject.put(ResponseConstants.NAME, this.f1029d);
            }
            if (!fj.m354c(this.f1030e)) {
                jSONObject.put(ResponseConstants.DESCRIPTION, this.f1030e);
            }
            if (!fj.m354c(this.f1034i)) {
                jSONObject.put("profile_image_url", this.f1034i);
            }
            jSONObject.put(ResponseConstants.ID, this.f1027b);
            jSONObject.put("followers_count", this.f1031f);
            jSONObject.put("friends_count", this.f1032g);
            jSONObject.put("statuses_count", this.f1033h);
        } catch (Throwable e) {
            AppboyLogger.m665e(f1026a, "Caught exception creating twitter user Json.", e);
        }
        return jSONObject;
    }
}
