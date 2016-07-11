package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1078b;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.client.a;
import com.google.android.gms.ads.internal.overlay.f;
import com.google.android.gms.ads.internal.overlay.l;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;

@jw
public class gw implements gs {
    private final no f4976a;

    public gw(Context context, VersionInfoParcel versionInfoParcel, bu buVar) {
        this.f4976a = C1101o.m6042f().m7277a(context, new AdSizeParcel(), false, false, buVar, versionInfoParcel);
        this.f4976a.m7235a().setWillNotDraw(true);
    }

    private void m6625a(Runnable runnable) {
        if (C1089r.m5951a().m6180b()) {
            runnable.run();
        } else {
            lt.f5423a.post(runnable);
        }
    }

    public void m6626a() {
        this.f4976a.destroy();
    }

    public void m6627a(a aVar, f fVar, fg fgVar, l lVar, boolean z, fq fqVar, fs fsVar, C1078b c1078b, in inVar) {
        this.f4976a.m7262l().zza(aVar, fVar, fgVar, lVar, z, fqVar, fsVar, new C1078b(this.f4976a.getContext(), false), inVar, null);
    }

    public void m6628a(gt gtVar) {
        this.f4976a.m7262l().zza(new 6(this, gtVar));
    }

    public void m6629a(String str) {
        m6625a(new 3(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public void m6630a(String str, fk fkVar) {
        this.f4976a.m7262l().zza(str, fkVar);
    }

    public void m6631a(String str, String str2) {
        m6625a(new 2(this, str, str2));
    }

    public void m6632a(String str, JSONObject jSONObject) {
        m6625a(new 1(this, str, jSONObject));
    }

    public he m6633b() {
        return new hf(this);
    }

    public void m6634b(String str) {
        m6625a(new 5(this, str));
    }

    public void m6635b(String str, fk fkVar) {
        this.f4976a.m7262l().zzb(str, fkVar);
    }

    public void m6636b(String str, JSONObject jSONObject) {
        this.f4976a.b(str, jSONObject);
    }

    public void m6637c(String str) {
        m6625a(new 4(this, str));
    }
}
