package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;

@jw
public abstract class zzhu extends ln {
    protected final jh f5159a;
    protected final Context f5160b;
    protected final Object f5161c;
    protected final Object f5162d;
    protected final lc f5163e;
    protected AdResponseParcel f5164f;

    protected zzhu(Context context, lc lcVar, jh jhVar) {
        super(true);
        this.f5161c = new Object();
        this.f5162d = new Object();
        this.f5160b = context;
        this.f5163e = lcVar;
        this.f5164f = lcVar.f5353b;
        this.f5159a = jhVar;
    }

    protected abstract lb m6821a(int i);

    protected abstract void m6822a(long j);

    protected void m6823a(lb lbVar) {
        this.f5159a.zzb(lbVar);
    }

    public void onStop() {
    }

    public void zzbQ() {
        int errorCode;
        synchronized (this.f5161c) {
            C1129c.m6185a("AdRendererBackgroundTask started.");
            int i = this.f5163e.f5356e;
            try {
                m6822a(SystemClock.elapsedRealtime());
            } catch (zza e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    C1129c.m6190c(e.getMessage());
                } else {
                    C1129c.m6192d(e.getMessage());
                }
                if (this.f5164f == null) {
                    this.f5164f = new AdResponseParcel(errorCode);
                } else {
                    this.f5164f = new AdResponseParcel(errorCode, this.f5164f.zzEL);
                }
                lt.f5423a.post(new 1(this));
                i = errorCode;
            }
            lt.f5423a.post(new 2(this, m6821a(i)));
        }
    }
}
