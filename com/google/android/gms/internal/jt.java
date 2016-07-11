package com.google.android.gms.internal;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.finds.FindsModule;
import com.google.android.gms.ads.internal.formats.C1093a;
import com.google.android.gms.ads.internal.formats.e;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zze;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@jw
public class jt implements jq<zze> {
    private final boolean f5204a;
    private final boolean f5205b;

    public jt(boolean z, boolean z2) {
        this.f5204a = z;
        this.f5205b = z2;
    }

    public /* synthetic */ e m6873a(jp jpVar, JSONObject jSONObject) {
        return m6874b(jpVar, jSONObject);
    }

    public zze m6874b(jp jpVar, JSONObject jSONObject) {
        List<mz> a = jpVar.m6865a(jSONObject, FindsModule.FIELD_IMAGES, true, this.f5204a, this.f5205b);
        mz a2 = jpVar.m6864a(jSONObject, "secondary_image", false, this.f5204a);
        mz b = jpVar.m6869b(jSONObject);
        List arrayList = new ArrayList();
        for (mz mzVar : a) {
            arrayList.add((zzc) mzVar.get());
        }
        return new zze(jSONObject.getString(ResponseConstants.HEADLINE), arrayList, jSONObject.getString("body"), (zzdj) a2.get(), jSONObject.getString("call_to_action"), jSONObject.getString("advertiser"), (C1093a) b.get(), new Bundle());
    }
}
