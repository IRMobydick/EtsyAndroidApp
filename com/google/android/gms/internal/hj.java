package com.google.android.gms.internal;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.google.android.gms.ads.internal.C1101o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@jw
public final class hj {
    public final String f4987a;
    public final String f4988b;
    public final List<String> f4989c;
    public final String f4990d;
    public final String f4991e;
    public final List<String> f4992f;
    public final List<String> f4993g;
    public final List<String> f4994h;
    public final String f4995i;
    public final List<String> f4996j;
    public final List<String> f4997k;
    public final String f4998l;
    public final String f4999m;
    public final String f5000n;
    public final List<String> f5001o;
    public final String f5002p;

    public hj(String str, String str2, List<String> list, String str3, String str4, List<String> list2, List<String> list3, String str5, String str6, List<String> list4, List<String> list5, String str7, String str8, String str9, List<String> list6, String str10, List<String> list7) {
        this.f4987a = str;
        this.f4988b = str2;
        this.f4989c = list;
        this.f4990d = str3;
        this.f4991e = str4;
        this.f4992f = list2;
        this.f4993g = list3;
        this.f4995i = str5;
        this.f4996j = list4;
        this.f4997k = list5;
        this.f4998l = str7;
        this.f4999m = str8;
        this.f5000n = str9;
        this.f5001o = list6;
        this.f5002p = str10;
        this.f4994h = list7;
    }

    public hj(JSONObject jSONObject) {
        String str = null;
        this.f4988b = jSONObject.getString(ResponseConstants.ID);
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        List arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.f4989c = Collections.unmodifiableList(arrayList);
        this.f4990d = jSONObject.optString("allocation_id", null);
        this.f4992f = C1101o.m6056t().m6687a(jSONObject, "clickurl");
        this.f4993g = C1101o.m6056t().m6687a(jSONObject, "imp_urls");
        this.f4994h = C1101o.m6056t().m6687a(jSONObject, "fill_urls");
        this.f4996j = C1101o.m6056t().m6687a(jSONObject, "video_start_urls");
        this.f4997k = C1101o.m6056t().m6687a(jSONObject, "video_complete_urls");
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        this.f4987a = optJSONObject != null ? optJSONObject.toString() : null;
        JSONObject optJSONObject2 = jSONObject.optJSONObject(ActivityFeedEntity.DATA);
        this.f4995i = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.f4991e = optJSONObject2 != null ? optJSONObject2.optString("class_name") : null;
        this.f4998l = jSONObject.optString("html_template", null);
        this.f4999m = jSONObject.optString("ad_base_url", null);
        optJSONObject = jSONObject.optJSONObject("assets");
        this.f5000n = optJSONObject != null ? optJSONObject.toString() : null;
        this.f5001o = C1101o.m6056t().m6687a(jSONObject, "template_ids");
        optJSONObject = jSONObject.optJSONObject("ad_loader_options");
        if (optJSONObject != null) {
            str = optJSONObject.toString();
        }
        this.f5002p = str;
    }
}
