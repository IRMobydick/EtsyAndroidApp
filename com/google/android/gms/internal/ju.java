package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.formats.C1093a;
import com.google.android.gms.ads.internal.formats.e;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.util.client.C1129c;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

@jw
public class ju implements jq<zzf> {
    private final boolean f5206a;

    public ju(boolean z) {
        this.f5206a = z;
    }

    private <K, V> SimpleArrayMap<K, V> m6875a(SimpleArrayMap<K, Future<V>> simpleArrayMap) {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), ((Future) simpleArrayMap.valueAt(i)).get());
        }
        return simpleArrayMap2;
    }

    private void m6876a(jp jpVar, JSONObject jSONObject, SimpleArrayMap<String, Future<zzc>> simpleArrayMap) {
        simpleArrayMap.put(jSONObject.getString(ResponseConstants.NAME), jpVar.m6866a(jSONObject, "image_value", this.f5206a));
    }

    private void m6877a(JSONObject jSONObject, SimpleArrayMap<String, String> simpleArrayMap) {
        simpleArrayMap.put(jSONObject.getString(ResponseConstants.NAME), jSONObject.getString("string_value"));
    }

    public /* synthetic */ e m6878a(jp jpVar, JSONObject jSONObject) {
        return m6879b(jpVar, jSONObject);
    }

    public zzf m6879b(jp jpVar, JSONObject jSONObject) {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        mz b = jpVar.m6869b(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(FindsModule.FIELD_TYPE);
            if ("string".equals(string)) {
                m6877a(jSONObject2, simpleArrayMap2);
            } else if (ResponseConstants.IMAGE.equals(string)) {
                m6876a(jpVar, jSONObject2, simpleArrayMap);
            } else {
                String str = "Unknown custom asset type: ";
                String valueOf = String.valueOf(string);
                C1129c.m6192d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        return new zzf(jSONObject.getString("custom_template_id"), m6875a(simpleArrayMap), simpleArrayMap2, (C1093a) b.get());
    }
}
