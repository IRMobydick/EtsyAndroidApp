package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.C1076h;
import com.google.android.gms.ads.C1131j;
import com.google.android.gms.ads.C1132l;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.doubleclick.c;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.internal.client.g.1;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.purchase.b;
import com.google.android.gms.ads.purchase.d;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzdh;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzhm;
import com.google.android.gms.internal.zzhq;
import java.util.concurrent.atomic.AtomicBoolean;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.g */
public class C1080g {
    final C1090s f4402a;
    private final zzfz f4403b;
    private final C1086n f4404c;
    private final AtomicBoolean f4405d;
    private final C1131j f4406e;
    private a f4407f;
    private a f4408g;
    private f[] f4409h;
    private com.google.android.gms.ads.doubleclick.a f4410i;
    private C1076h f4411j;
    private zzu f4412k;
    private b f4413l;
    private c f4414m;
    private d f4415n;
    private C1132l f4416o;
    private String f4417p;
    private String f4418q;
    private ViewGroup f4419r;
    private boolean f4420s;
    private boolean f4421t;

    public C1080g(ViewGroup viewGroup) {
        this(viewGroup, null, false, C1086n.m5927a(), false);
    }

    public C1080g(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, C1086n.m5927a(), false);
    }

    C1080g(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, C1086n c1086n, zzu com_google_android_gms_ads_internal_client_zzu, boolean z2) {
        this.f4403b = new zzfz();
        this.f4406e = new C1131j();
        this.f4402a = new 1(this);
        this.f4419r = viewGroup;
        this.f4404c = c1086n;
        this.f4412k = com_google_android_gms_ads_internal_client_zzu;
        this.f4405d = new AtomicBoolean(false);
        this.f4420s = z2;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                C1087o c1087o = new C1087o(context, attributeSet);
                this.f4409h = c1087o.m5931a(z);
                this.f4417p = c1087o.m5930a();
                if (viewGroup.isInEditMode()) {
                    C1089r.m5951a().m6174a(viewGroup, C1080g.m5874a(context, this.f4409h[0], this.f4420s), "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                C1089r.m5951a().m6175a(viewGroup, new AdSizeParcel(context, f.a), e.getMessage(), e.getMessage());
            }
        }
    }

    C1080g(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, C1086n c1086n, boolean z2) {
        this(viewGroup, attributeSet, z, c1086n, null, z2);
    }

    public C1080g(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, boolean z2) {
        this(viewGroup, attributeSet, z, C1086n.m5927a(), z2);
    }

    public C1080g(ViewGroup viewGroup, boolean z) {
        this(viewGroup, null, false, C1086n.m5927a(), z);
    }

    private static AdSizeParcel m5874a(Context context, f fVar, boolean z) {
        AdSizeParcel adSizeParcel = new AdSizeParcel(context, fVar);
        adSizeParcel.zzj(z);
        return adSizeParcel;
    }

    private static AdSizeParcel m5875a(Context context, f[] fVarArr, boolean z) {
        AdSizeParcel adSizeParcel = new AdSizeParcel(context, fVarArr);
        adSizeParcel.zzj(z);
        return adSizeParcel;
    }

    private void m5877s() {
        try {
            zzd zzbh = this.f4412k.zzbh();
            if (zzbh != null) {
                this.f4419r.addView((View) zze.zzx(zzbh));
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to get an ad frame.", e);
        }
    }

    public void m5878a() {
        try {
            if (this.f4412k != null) {
                this.f4412k.destroy();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to destroy AdView.", e);
        }
    }

    public void m5879a(a aVar) {
        this.f4408g = aVar;
        this.f4402a.m5957a(aVar);
    }

    public void m5880a(com.google.android.gms.ads.doubleclick.a aVar) {
        try {
            this.f4410i = aVar;
            if (this.f4412k != null) {
                this.f4412k.zza(aVar != null ? new zzj(aVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the AppEventListener.", e);
        }
    }

    public void m5881a(c cVar) {
        this.f4414m = cVar;
        try {
            if (this.f4412k != null) {
                this.f4412k.zza(cVar != null ? new zzdh(cVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the onCustomRenderedAdLoadedListener.", e);
        }
    }

    public void m5882a(C1076h c1076h) {
        this.f4411j = c1076h;
        try {
            if (this.f4412k != null) {
                this.f4412k.zza(this.f4411j == null ? null : this.f4411j.m5852a());
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set correlator.", e);
        }
    }

    public void m5883a(a aVar) {
        try {
            this.f4407f = aVar;
            if (this.f4412k != null) {
                this.f4412k.zza(aVar != null ? new zzb(aVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the AdClickListener.", e);
        }
    }

    public void m5884a(C1079e c1079e) {
        try {
            if (this.f4412k == null) {
                m5908q();
            }
            if (this.f4412k.zzb(this.f4404c.m5928a(this.f4419r.getContext(), c1079e))) {
                this.f4403b.zzh(c1079e.m5868j());
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to load ad.", e);
        }
    }

    public void m5885a(C1132l c1132l) {
        this.f4416o = c1132l;
        try {
            if (this.f4412k != null) {
                this.f4412k.zza(c1132l == null ? null : new VideoOptionsParcel(c1132l));
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set video options.", e);
        }
    }

    public void m5886a(b bVar) {
        if (this.f4415n != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.f4413l = bVar;
            if (this.f4412k != null) {
                this.f4412k.zza(bVar != null ? new zzhm(bVar) : null);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the InAppPurchaseListener.", e);
        }
    }

    public void m5887a(d dVar, String str) {
        if (this.f4413l != null) {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
        }
        try {
            this.f4415n = dVar;
            this.f4418q = str;
            if (this.f4412k != null) {
                this.f4412k.zza(dVar != null ? new zzhq(dVar) : null, str);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the play store purchase parameter.", e);
        }
    }

    public void m5888a(String str) {
        if (this.f4417p != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
        }
        this.f4417p = str;
    }

    public void m5889a(boolean z) {
        this.f4421t = z;
        try {
            if (this.f4412k != null) {
                this.f4412k.setManualImpressionsEnabled(this.f4421t);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set manual impressions.", e);
        }
    }

    public void m5890a(f... fVarArr) {
        if (this.f4409h != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        m5893b(fVarArr);
    }

    public boolean m5891a(AdSizeParcel adSizeParcel) {
        return "search_v2".equals(adSizeParcel.zzvs);
    }

    public a m5892b() {
        return this.f4408g;
    }

    public void m5893b(f... fVarArr) {
        this.f4409h = fVarArr;
        try {
            if (this.f4412k != null) {
                this.f4412k.zza(C1080g.m5875a(this.f4419r.getContext(), this.f4409h, this.f4420s));
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to set the ad size.", e);
        }
        this.f4419r.requestLayout();
    }

    public f m5894c() {
        try {
            if (this.f4412k != null) {
                AdSizeParcel zzbi = this.f4412k.zzbi();
                if (zzbi != null) {
                    return zzbi.zzdD();
                }
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to get the current AdSize.", e);
        }
        return this.f4409h != null ? this.f4409h[0] : null;
    }

    public f[] m5895d() {
        return this.f4409h;
    }

    public String m5896e() {
        return this.f4417p;
    }

    public com.google.android.gms.ads.doubleclick.a m5897f() {
        return this.f4410i;
    }

    public b m5898g() {
        return this.f4413l;
    }

    public c m5899h() {
        return this.f4414m;
    }

    public void m5900i() {
        try {
            if (this.f4412k != null) {
                this.f4412k.pause();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to call pause.", e);
        }
    }

    public void m5901j() {
        if (!this.f4405d.getAndSet(true)) {
            try {
                if (this.f4412k != null) {
                    this.f4412k.zzbk();
                }
            } catch (Throwable e) {
                C1129c.m6193d("Failed to record impression.", e);
            }
        }
    }

    public void m5902k() {
        try {
            if (this.f4412k != null) {
                this.f4412k.resume();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to call resume.", e);
        }
    }

    public String m5903l() {
        try {
            if (this.f4412k != null) {
                return this.f4412k.getMediationAdapterClassName();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to get the mediation adapter class name.", e);
        }
        return null;
    }

    public boolean m5904m() {
        try {
            if (this.f4412k != null) {
                return this.f4412k.isLoading();
            }
        } catch (Throwable e) {
            C1129c.m6193d("Failed to check if ad is loading.", e);
        }
        return false;
    }

    public C1131j m5905n() {
        return this.f4406e;
    }

    public zzab m5906o() {
        zzab com_google_android_gms_ads_internal_client_zzab = null;
        if (this.f4412k != null) {
            try {
                com_google_android_gms_ads_internal_client_zzab = this.f4412k.zzbl();
            } catch (Throwable e) {
                C1129c.m6193d("Failed to retrieve VideoController.", e);
            }
        }
        return com_google_android_gms_ads_internal_client_zzab;
    }

    public C1132l m5907p() {
        return this.f4416o;
    }

    void m5908q() {
        if ((this.f4409h == null || this.f4417p == null) && this.f4412k == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        this.f4412k = m5909r();
        this.f4412k.zza(new zzc(this.f4402a));
        if (this.f4407f != null) {
            this.f4412k.zza(new zzb(this.f4407f));
        }
        if (this.f4410i != null) {
            this.f4412k.zza(new zzj(this.f4410i));
        }
        if (this.f4413l != null) {
            this.f4412k.zza(new zzhm(this.f4413l));
        }
        if (this.f4415n != null) {
            this.f4412k.zza(new zzhq(this.f4415n), this.f4418q);
        }
        if (this.f4414m != null) {
            this.f4412k.zza(new zzdh(this.f4414m));
        }
        if (this.f4411j != null) {
            this.f4412k.zza(this.f4411j.m5852a());
        }
        if (this.f4416o != null) {
            this.f4412k.zza(new VideoOptionsParcel(this.f4416o));
        }
        this.f4412k.setManualImpressionsEnabled(this.f4421t);
        m5877s();
    }

    protected zzu m5909r() {
        Context context = this.f4419r.getContext();
        AdSizeParcel a = C1080g.m5875a(context, this.f4409h, this.f4420s);
        return m5891a(a) ? C1089r.m5953b().m5945a(context, a, this.f4417p) : C1089r.m5953b().m5946a(context, a, this.f4417p, this.f4403b);
    }
}
