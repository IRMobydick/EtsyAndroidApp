package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.common.internal.zzaa;

@jw
public class nl {
    private final no f5481a;
    private final Context f5482b;
    private final ViewGroup f5483c;
    private zzk f5484d;

    public nl(Context context, ViewGroup viewGroup, no noVar) {
        this(context, viewGroup, noVar, null);
    }

    nl(Context context, ViewGroup viewGroup, no noVar, zzk com_google_android_gms_ads_internal_overlay_zzk) {
        this.f5482b = context;
        this.f5483c = viewGroup;
        this.f5481a = noVar;
        this.f5484d = com_google_android_gms_ads_internal_overlay_zzk;
    }

    public zzk m7227a() {
        zzaa.zzdc("getAdVideoUnderlay must be called from the UI thread.");
        return this.f5484d;
    }

    public void m7228a(int i, int i2, int i3, int i4) {
        zzaa.zzdc("The underlay may only be modified from the UI thread.");
        if (this.f5484d != null) {
            this.f5484d.zzd(i, i2, i3, i4);
        }
    }

    public void m7229a(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (this.f5484d == null) {
            ed.m6464a(this.f5481a.m7275y().m6470a(), this.f5481a.m7274x(), "vpr");
            eg a = ed.m6461a(this.f5481a.m7275y().m6470a());
            this.f5484d = new zzk(this.f5482b, this.f5481a, i5, z, this.f5481a.m7275y().m6470a(), a);
            this.f5483c.addView(this.f5484d, 0, new LayoutParams(-1, -1));
            this.f5484d.zzd(i, i2, i3, i4);
            this.f5481a.m7262l().zzJ(false);
        }
    }

    public void m7230b() {
        zzaa.zzdc("onPause must be called from the UI thread.");
        if (this.f5484d != null) {
            this.f5484d.pause();
        }
    }

    public void m7231c() {
        zzaa.zzdc("onDestroy must be called from the UI thread.");
        if (this.f5484d != null) {
            this.f5484d.destroy();
            this.f5483c.removeView(this.f5484d);
            this.f5484d = null;
        }
    }
}
