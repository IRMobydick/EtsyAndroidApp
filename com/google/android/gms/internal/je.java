package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.atomic.AtomicBoolean;

@jw
public abstract class je implements me<Void>, np {
    protected final jh f5147a;
    protected final Context f5148b;
    protected final no f5149c;
    protected final lc f5150d;
    protected AdResponseParcel f5151e;
    protected final Object f5152f;
    private Runnable f5153g;
    private AtomicBoolean f5154h;

    protected je(Context context, lc lcVar, no noVar, jh jhVar) {
        this.f5152f = new Object();
        this.f5154h = new AtomicBoolean(true);
        this.f5148b = context;
        this.f5150d = lcVar;
        this.f5151e = this.f5150d.f5353b;
        this.f5149c = noVar;
        this.f5147a = jhVar;
    }

    private lb m6806b(int i) {
        AdRequestInfoParcel adRequestInfoParcel = this.f5150d.f5352a;
        return new lb(adRequestInfoParcel.zzLi, this.f5149c, this.f5151e.zzEF, i, this.f5151e.zzEG, this.f5151e.zzLR, this.f5151e.orientation, this.f5151e.zzEL, adRequestInfoParcel.zzLl, this.f5151e.zzLP, null, null, null, null, null, this.f5151e.zzLQ, this.f5150d.f5355d, this.f5151e.zzLO, this.f5150d.f5357f, this.f5151e.zzLT, this.f5151e.zzLU, this.f5150d.f5359h, null, this.f5151e.zzMf, this.f5151e.zzMg, this.f5151e.zzMh, this.f5151e.zzMi, this.f5151e.zzMj, null, this.f5151e.zzEI);
    }

    public final Void m6807a() {
        zzaa.zzdc("Webview render task needs to be called on UI thread.");
        this.f5153g = new 1(this);
        lt.f5423a.postDelayed(this.f5153g, ((Long) dz.aK.m6433c()).longValue());
        m6810b();
        return null;
    }

    protected void m6808a(int i) {
        if (i != -2) {
            this.f5151e = new AdResponseParcel(i, this.f5151e.zzEL);
        }
        this.f5149c.m7255e();
        this.f5147a.zzb(m6806b(i));
    }

    public void m6809a(no noVar, boolean z) {
        int i = 0;
        C1129c.m6185a("WebView finished loading.");
        if (this.f5154h.getAndSet(false)) {
            if (z) {
                i = m6811c();
            }
            m6808a(i);
            lt.f5423a.removeCallbacks(this.f5153g);
        }
    }

    protected abstract void m6810b();

    protected int m6811c() {
        return -2;
    }

    public void cancel() {
        if (this.f5154h.getAndSet(false)) {
            this.f5149c.stopLoading();
            C1101o.m6043g().m7159a(this.f5149c);
            m6808a(-1);
            lt.f5423a.removeCallbacks(this.f5153g);
        }
    }

    public /* synthetic */ Object zzhs() {
        return m6807a();
    }
}
