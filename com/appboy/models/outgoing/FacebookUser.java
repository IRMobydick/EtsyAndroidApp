package com.appboy.models.outgoing;

import bo.app.fj;
import com.appboy.Constants;
import com.appboy.enums.Gender;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;

public class FacebookUser implements IPutIntoJson<JSONObject> {
    private static final String f1008a;
    private static String f1009b;
    private final String f1010c;
    private final String f1011d;
    private final String f1012e;
    private final String f1013f;
    private final String f1014g;
    private final String f1015h;
    private final Gender f1016i;
    private final Integer f1017j;
    private final Collection<String> f1018k;
    private final String f1019l;

    static {
        f1008a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, FacebookUser.class.getName()});
        f1009b = "birthday";
    }

    public FacebookUser(String str, String str2, String str3, String str4, String str5, String str6, Gender gender, Integer num, Collection<String> collection, String str7) {
        this.f1010c = str;
        this.f1011d = str2;
        this.f1012e = str3;
        this.f1013f = str4;
        this.f1014g = str5;
        this.f1015h = str6;
        this.f1016i = gender;
        this.f1017j = num;
        this.f1018k = collection;
        this.f1019l = str7;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!fj.m354c(this.f1010c)) {
                jSONObject.put(ResponseConstants.ID, this.f1010c);
            }
            if (!fj.m354c(this.f1011d)) {
                jSONObject.put(ResponseConstants.FIRST_NAME, this.f1011d);
            }
            if (!fj.m354c(this.f1012e)) {
                jSONObject.put(ResponseConstants.LAST_NAME, this.f1012e);
            }
            if (!fj.m354c(this.f1013f)) {
                jSONObject.put(ResponseConstants.EMAIL, this.f1013f);
            }
            if (!fj.m354c(this.f1014g)) {
                jSONObject.put(ResponseConstants.BIO, this.f1014g);
            }
            if (!fj.m354c(this.f1019l)) {
                jSONObject.put(f1009b, this.f1019l);
            }
            if (!fj.m354c(this.f1015h)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ResponseConstants.NAME, this.f1015h);
                jSONObject.put(ResponseConstants.LOCATION, jSONObject2);
            }
            if (this.f1016i != null) {
                jSONObject.put("gender", this.f1016i.forJsonPut());
            }
            jSONObject.put("num_friends", this.f1017j);
            if (!(this.f1018k == null || this.f1018k.isEmpty())) {
                jSONObject.put("likes", m657a());
            }
        } catch (Throwable e) {
            AppboyLogger.m665e(f1008a, "Caught exception creating facebook user Json.", e);
        }
        return jSONObject;
    }

    private JSONArray m657a() {
        JSONArray jSONArray = new JSONArray();
        for (String str : this.f1018k) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ResponseConstants.NAME, str);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
