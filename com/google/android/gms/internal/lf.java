package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@jw
public class lf {
    private final long f5374a;
    private final List<String> f5375b;
    private final Map<String, lh> f5376c;
    private String f5377d;
    private String f5378e;
    private boolean f5379f;

    public lf(String str, long j) {
        this.f5375b = new ArrayList();
        this.f5376c = new HashMap();
        this.f5379f = false;
        this.f5378e = str;
        this.f5374a = j;
        m7003a(str);
    }

    private void m7003a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt(ResponseConstants.STATUS, -1) != 1) {
                    this.f5379f = false;
                    C1129c.m6192d("App settings could not be fetched successfully.");
                    return;
                }
                this.f5379f = true;
                this.f5377d = jSONObject.optString("app_id");
                JSONArray optJSONArray = jSONObject.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        m7004a(optJSONArray.getJSONObject(i));
                    }
                }
            } catch (Throwable e) {
                C1129c.m6193d("Exception occurred while processing app setting json", e);
                C1101o.m6044h().m7021a(e, true);
            }
        }
    }

    private void m7004a(JSONObject jSONObject) {
        String optString = jSONObject.optString(ResponseConstants.FORMAT);
        CharSequence optString2 = jSONObject.optString("ad_unit_id");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            if ("interstitial".equalsIgnoreCase(optString)) {
                this.f5375b.add(optString2);
            } else if ("rewarded".equalsIgnoreCase(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("mediation_config");
                if (optJSONObject != null) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("ad_networks");
                    if (optJSONArray != null) {
                        int i = 0;
                        while (i < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            JSONArray optJSONArray2 = jSONObject2.optJSONArray("adapters");
                            if (optJSONArray2 != null) {
                                List arrayList = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    arrayList.add(optJSONArray2.getString(i2));
                                }
                                jSONObject2 = jSONObject2.optJSONObject(ActivityFeedEntity.DATA);
                                if (jSONObject2 != null) {
                                    Bundle bundle = new Bundle();
                                    Iterator keys = jSONObject2.keys();
                                    while (keys.hasNext()) {
                                        optString = (String) keys.next();
                                        bundle.putString(optString, jSONObject2.getString(optString));
                                    }
                                    lg lgVar = new lg(this, arrayList, bundle);
                                    lh lhVar = this.f5376c.containsKey(optString2) ? (lh) this.f5376c.get(optString2) : new lh(this);
                                    lhVar.a(lgVar);
                                    this.f5376c.put(optString2, lhVar);
                                    i++;
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    public long m7005a() {
        return this.f5374a;
    }

    public boolean m7006b() {
        return this.f5379f;
    }

    public String m7007c() {
        return this.f5378e;
    }

    public String m7008d() {
        return this.f5377d;
    }
}
