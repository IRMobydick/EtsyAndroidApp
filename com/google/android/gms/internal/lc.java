package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import org.json.JSONObject;

@jw
public final class lc {
    public final AdRequestInfoParcel f5352a;
    public final AdResponseParcel f5353b;
    public final hk f5354c;
    public final AdSizeParcel f5355d;
    public final int f5356e;
    public final long f5357f;
    public final long f5358g;
    public final JSONObject f5359h;

    public lc(AdRequestInfoParcel adRequestInfoParcel, AdResponseParcel adResponseParcel, hk hkVar, AdSizeParcel adSizeParcel, int i, long j, long j2, JSONObject jSONObject) {
        this.f5352a = adRequestInfoParcel;
        this.f5353b = adResponseParcel;
        this.f5354c = hkVar;
        this.f5355d = adSizeParcel;
        this.f5356e = i;
        this.f5357f = j;
        this.f5358g = j2;
        this.f5359h = jSONObject;
    }
}
