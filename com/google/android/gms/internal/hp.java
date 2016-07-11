package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1101o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

@jw
public class hp {
    public List<String> m6687a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        List arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public void m6688a(Context context, String str, lb lbVar, String str2, boolean z, List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str3 = z ? "1" : "0";
            for (String replaceAll : list) {
                String replaceAll2 = replaceAll2.replaceAll("@gw_adlocid@", str2).replaceAll("@gw_adnetrefresh@", str3).replaceAll("@gw_qdata@", lbVar.f5343r.f5010h).replaceAll("@gw_sdkver@", str).replaceAll("@gw_sessid@", C1101o.m6044h().m7011a()).replaceAll("@gw_seqnum@", lbVar.f5334i).replaceAll("@gw_adnetstatus@", lbVar.f5346u);
                if (lbVar.f5340o != null) {
                    replaceAll2 = replaceAll2.replaceAll("@gw_adnetid@", lbVar.f5340o.f4988b).replaceAll("@gw_allocid@", lbVar.f5340o.f4990d);
                }
                Future future = (Future) new mq(context, str, replaceAll2).zzhs();
            }
        }
    }
}
