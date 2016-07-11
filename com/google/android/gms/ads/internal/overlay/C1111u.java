package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lt;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.u */
class C1111u implements Runnable {
    private zzk f4579a;
    private boolean f4580b;

    C1111u(zzk com_google_android_gms_ads_internal_overlay_zzk) {
        this.f4580b = false;
        this.f4579a = com_google_android_gms_ads_internal_overlay_zzk;
    }

    public void m6088a() {
        this.f4580b = true;
        lt.f5423a.removeCallbacks(this);
    }

    public void m6089b() {
        lt.f5423a.postDelayed(this, 250);
    }

    public void run() {
        if (!this.f4580b) {
            this.f4579a.zzgI();
            m6089b();
        }
    }
}
