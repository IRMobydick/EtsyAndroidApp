package com.google.android.gms.ads.internal.client;

import android.content.Context;
import com.google.android.gms.ads.C1076h;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.doubleclick.c;
import com.google.android.gms.ads.doubleclick.e;
import com.google.android.gms.ads.internal.reward.client.zzg;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.purchase.b;
import com.google.android.gms.ads.purchase.d;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdh;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzhm;
import com.google.android.gms.internal.zzhq;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.h */
public class C1081h {
    private final zzfz f4422a;
    private final Context f4423b;
    private final C1086n f4424c;
    private a f4425d;
    private a f4426e;
    private zzu f4427f;
    private String f4428g;
    private String f4429h;
    private com.google.android.gms.ads.doubleclick.a f4430i;
    private d f4431j;
    private b f4432k;
    private e f4433l;
    private c f4434m;
    private C1076h f4435n;
    private com.google.android.gms.ads.a.b f4436o;
    private boolean f4437p;

    public C1081h(Context context) {
        this(context, C1086n.m5927a(), null);
    }

    public C1081h(Context context, C1086n c1086n, e eVar) {
        this.f4422a = new zzfz();
        this.f4423b = context;
        this.f4424c = c1086n;
        this.f4433l = eVar;
    }

    private void m5910b(String str) {
        if (this.f4428g == null) {
            m5911c(str);
        }
        this.f4427f = C1089r.m5953b().m5949b(this.f4423b, this.f4437p ? AdSizeParcel.zzdC() : new AdSizeParcel(), this.f4428g, this.f4422a);
        if (this.f4425d != null) {
            this.f4427f.zza(new zzc(this.f4425d));
        }
        if (this.f4426e != null) {
            this.f4427f.zza(new zzb(this.f4426e));
        }
        if (this.f4430i != null) {
            this.f4427f.zza(new zzj(this.f4430i));
        }
        if (this.f4432k != null) {
            this.f4427f.zza(new zzhm(this.f4432k));
        }
        if (this.f4431j != null) {
            this.f4427f.zza(new zzhq(this.f4431j), this.f4429h);
        }
        if (this.f4434m != null) {
            this.f4427f.zza(new zzdh(this.f4434m));
        }
        if (this.f4435n != null) {
            this.f4427f.zza(this.f4435n.m5852a());
        }
        if (this.f4436o != null) {
            this.f4427f.zza(new zzg(this.f4436o));
        }
    }

    private void m5911c(String str) {
        if (this.f4427f == null) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 63).append("The ad unit ID must be set on InterstitialAd before ").append(str).append(" is called.").toString());
        }
    }

    public void m5912a() {
        try {
            m5911c("show");
            this.f4427f.showInterstitial();
        } catch (Throwable e) {
            C1129c.m6193d("Failed to show interstitial.", e);
        }
    }

    public void m5913a(com.google.android.gms.ads.a.b bVar) {
        try {
            this.f4436o = bVar;
            if (this.f4427f != null) {
                this.f4427f.zza(bVar != null ? new zzg(bVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the AdListener.", e);
        }
    }

    public void m5914a(a aVar) {
        try {
            this.f4425d = aVar;
            if (this.f4427f != null) {
                this.f4427f.zza(aVar != null ? new zzc(aVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the AdListener.", e);
        }
    }

    public void m5915a(a aVar) {
        try {
            this.f4426e = aVar;
            if (this.f4427f != null) {
                this.f4427f.zza(aVar != null ? new zzb(aVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the AdClickListener.", e);
        }
    }

    public void m5916a(C1079e c1079e) {
        try {
            if (this.f4427f == null) {
                m5910b("loadAd");
            }
            if (this.f4427f.zzb(this.f4424c.m5928a(this.f4423b, c1079e))) {
                this.f4422a.zzh(c1079e.m5868j());
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to load ad.", e);
        }
    }

    public void m5917a(String str) {
        if (this.f4428g != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.f4428g = str;
    }

    public void m5918a(boolean z) {
        this.f4437p = z;
    }
}
