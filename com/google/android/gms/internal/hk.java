package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@jw
public final class hk {
    public final List<hj> f5003a;
    public final long f5004b;
    public final List<String> f5005c;
    public final List<String> f5006d;
    public final List<String> f5007e;
    public final List<String> f5008f;
    public final boolean f5009g;
    public final String f5010h;
    public final long f5011i;
    public final String f5012j;
    public final int f5013k;
    public final int f5014l;
    public final long f5015m;
    public final boolean f5016n;
    public int f5017o;
    public int f5018p;

    public hk(String str) {
        JSONObject jSONObject = new JSONObject(str);
        if (C1129c.m6187a(2)) {
            String str2 = "Mediation Response JSON: ";
            String valueOf = String.valueOf(jSONObject.toString(2));
            lo.m7056e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        List arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            hj hjVar = new hj(jSONArray.getJSONObject(i2));
            arrayList.add(hjVar);
            if (i < 0 && m6659a(hjVar)) {
                i = i2;
            }
        }
        this.f5017o = i;
        this.f5018p = jSONArray.length();
        this.f5003a = Collections.unmodifiableList(arrayList);
        this.f5010h = jSONObject.getString("qdata");
        this.f5014l = jSONObject.optInt("fs_model_type", -1);
        this.f5015m = jSONObject.optLong("timeout_ms", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.f5004b = optJSONObject.optLong("ad_network_timeout_millis", -1);
            this.f5005c = C1101o.m6056t().m6687a(optJSONObject, "click_urls");
            this.f5006d = C1101o.m6056t().m6687a(optJSONObject, "imp_urls");
            this.f5007e = C1101o.m6056t().m6687a(optJSONObject, "nofill_urls");
            this.f5008f = C1101o.m6056t().m6687a(optJSONObject, "remote_ping_urls");
            this.f5009g = optJSONObject.optBoolean("render_in_browser", false);
            long optLong = optJSONObject.optLong("refresh", -1);
            this.f5011i = optLong > 0 ? optLong * 1000 : -1;
            RewardItemParcel zza = RewardItemParcel.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.f5012j = null;
                this.f5013k = 0;
            } else {
                this.f5012j = zza.type;
                this.f5013k = zza.zzOV;
            }
            this.f5016n = optJSONObject.optBoolean("use_displayed_impression", false);
            return;
        }
        this.f5004b = -1;
        this.f5005c = null;
        this.f5006d = null;
        this.f5007e = null;
        this.f5008f = null;
        this.f5011i = -1;
        this.f5012j = null;
        this.f5013k = 0;
        this.f5016n = false;
        this.f5009g = false;
    }

    public hk(List<hj> list, long j, List<String> list2, List<String> list3, List<String> list4, List<String> list5, boolean z, String str, long j2, int i, int i2, String str2, int i3, int i4, long j3, boolean z2) {
        this.f5003a = list;
        this.f5004b = j;
        this.f5005c = list2;
        this.f5006d = list3;
        this.f5007e = list4;
        this.f5008f = list5;
        this.f5009g = z;
        this.f5010h = str;
        this.f5011i = j2;
        this.f5017o = i;
        this.f5018p = i2;
        this.f5012j = str2;
        this.f5013k = i3;
        this.f5014l = i4;
        this.f5015m = j3;
        this.f5016n = z2;
    }

    private boolean m6659a(hj hjVar) {
        for (String equals : hjVar.f4989c) {
            if (equals.equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
