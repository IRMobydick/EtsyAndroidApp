package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public class zzcb extends zzbv {
    private ha zztR;
    private boolean zztS;

    public zzcb(Context context, AdSizeParcel adSizeParcel, lb lbVar, VersionInfoParcel versionInfoParcel, df dfVar, gx gxVar) {
        super(context, adSizeParcel, lbVar, versionInfoParcel, dfVar);
        this.zztR = gxVar.m6649a();
        try {
            this.zztR.a(new 1(this, zzd(dfVar.c().a())), new 2(this));
        } catch (JSONException e) {
        } catch (Throwable e2) {
            C1129c.m6189b("Failure while processing active view data.", e2);
        }
        this.zztR.a(new 3(this), new 4(this));
        String str = "Tracking ad unit: ";
        String valueOf = String.valueOf(this.zztl.m6347d());
        C1129c.m6185a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected void destroy() {
        synchronized (this.zzpp) {
            super.destroy();
            this.zztR.a(new 6(this), new nd());
            this.zztR.c_();
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zztR.a(new 5(this, jSONObject), new nd());
    }

    protected boolean zzcY() {
        return this.zztS;
    }
}
