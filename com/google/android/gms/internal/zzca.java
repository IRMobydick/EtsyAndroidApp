package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;

@jw
public class zzca extends zzbv {
    private final hd zztQ;

    public zzca(Context context, AdSizeParcel adSizeParcel, lb lbVar, VersionInfoParcel versionInfoParcel, df dfVar, hd hdVar) {
        super(context, adSizeParcel, lbVar, versionInfoParcel, dfVar);
        this.zztQ = hdVar;
        zzc(this.zztQ);
        zzcQ();
        zzi(3);
        String str = "Tracking ad unit: ";
        String valueOf = String.valueOf(this.zztl.m6347d());
        C1129c.m6185a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected void destroy() {
        synchronized (this.zzpp) {
            super.destroy();
            zzd(this.zztQ);
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zztQ.a("AFMA_updateActiveView", jSONObject);
    }

    public void zzcS() {
        destroy();
    }

    protected boolean zzcY() {
        return true;
    }
}
