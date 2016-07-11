package com.google.android.gms.internal;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.formats.C1093a;
import com.google.android.gms.ads.internal.formats.e;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzd;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@jw
public class js implements jq<zzd> {
    private final boolean f5202a;
    private final boolean f5203b;

    public js(boolean z, boolean z2) {
        this.f5202a = z;
        this.f5203b = z2;
    }

    public /* synthetic */ e m6871a(jp jpVar, JSONObject jSONObject) {
        return m6872b(jpVar, jSONObject);
    }

    public zzd m6872b(jp jpVar, JSONObject jSONObject) {
        List<mz> a = jpVar.m6865a(jSONObject, FindsModule.FIELD_IMAGES, true, this.f5202a, this.f5203b);
        mz a2 = jpVar.m6864a(jSONObject, "app_icon", true, this.f5202a);
        mz b = jpVar.m6869b(jSONObject);
        List arrayList = new ArrayList();
        for (mz mzVar : a) {
            arrayList.add((zzc) mzVar.get());
        }
        return new zzd(jSONObject.getString(ResponseConstants.HEADLINE), arrayList, jSONObject.getString("body"), (zzdj) a2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble(ResponseConstants.RATING, -1.0d), jSONObject.optString("store"), jSONObject.optString(ResponseConstants.PRICE), (C1093a) b.get(), new Bundle());
    }
}
