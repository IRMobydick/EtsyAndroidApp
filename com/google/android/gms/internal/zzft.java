package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzgc.zza;

@jw
public final class zzft extends zza {
    private ho zzET;
    private hl zzEU;
    private final Object zzpp;

    public zzft() {
        this.zzpp = new Object();
    }

    public void onAdClicked() {
        synchronized (this.zzpp) {
            if (this.zzEU != null) {
                this.zzEU.zzbu();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.zzpp) {
            if (this.zzEU != null) {
                this.zzEU.zzbv();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.zzpp) {
            if (this.zzET != null) {
                this.zzET.a(i == 3 ? 1 : 2);
                this.zzET = null;
            }
        }
    }

    public void onAdImpression() {
        synchronized (this.zzpp) {
            if (this.zzEU != null) {
                this.zzEU.zzbz();
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.zzpp) {
            if (this.zzEU != null) {
                this.zzEU.zzbw();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.zzpp) {
            if (this.zzET != null) {
                this.zzET.a(0);
                this.zzET = null;
                return;
            }
            if (this.zzEU != null) {
                this.zzEU.zzby();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.zzpp) {
            if (this.zzEU != null) {
                this.zzEU.zzbx();
            }
        }
    }

    public void zza(@Nullable hl hlVar) {
        synchronized (this.zzpp) {
            this.zzEU = hlVar;
        }
    }

    public void zza(ho hoVar) {
        synchronized (this.zzpp) {
            this.zzET = hoVar;
        }
    }

    public void zza(zzgd com_google_android_gms_internal_zzgd) {
        synchronized (this.zzpp) {
            if (this.zzET != null) {
                this.zzET.a(0, com_google_android_gms_internal_zzgd);
                this.zzET = null;
                return;
            }
            if (this.zzEU != null) {
                this.zzEU.zzby();
            }
        }
    }
}
