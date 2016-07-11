package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.f;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.kv;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzlb;
import com.google.android.gms.internal.zzlf;
import java.util.List;

@jw
public class zzf extends zzc implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzqs;

    public zzf(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzga, versionInfoParcel, c1077a);
    }

    private AdSizeParcel zzb(lc lcVar) {
        if (lcVar.f5353b.zzvw) {
            return this.zzpV.zzsB;
        }
        f fVar;
        String str = lcVar.f5353b.zzLS;
        if (str != null) {
            String[] split = str.split("[xX]");
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            fVar = new f(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            fVar = this.zzpV.zzsB.zzdD();
        }
        return new AdSizeParcel(this.zzpV.zzov, fVar);
    }

    private boolean zzb(@Nullable lb lbVar, lb lbVar2) {
        if (lbVar2.f5339n) {
            View a = C1098h.m6005a(lbVar2);
            if (a == null) {
                C1129c.m6192d("Could not get mediation view");
                return false;
            }
            View nextView = this.zzpV.zzsy.getNextView();
            if (nextView != null) {
                if (nextView instanceof no) {
                    ((no) nextView).destroy();
                }
                this.zzpV.zzsy.removeView(nextView);
            }
            if (!C1098h.m6025b(lbVar2)) {
                try {
                    zzb(a);
                } catch (Throwable th) {
                    C1129c.m6193d("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            }
        } else if (!(lbVar2.f5347v == null || lbVar2.f5327b == null)) {
            lbVar2.f5327b.m7239a(lbVar2.f5347v);
            this.zzpV.zzsy.removeAllViews();
            this.zzpV.zzsy.setMinimumWidth(lbVar2.f5347v.widthPixels);
            this.zzpV.zzsy.setMinimumHeight(lbVar2.f5347v.heightPixels);
            zzb(lbVar2.f5327b.m7247b());
        }
        if (this.zzpV.zzsy.getChildCount() > 1) {
            this.zzpV.zzsy.showNext();
        }
        if (lbVar != null) {
            View nextView2 = this.zzpV.zzsy.getNextView();
            if (nextView2 instanceof no) {
                ((no) nextView2).m7238a(this.zzpV.zzov, this.zzpV.zzsB, this.zzpQ);
            } else if (nextView2 != null) {
                this.zzpV.zzsy.removeView(nextView2);
            }
            this.zzpV.zzcI();
        }
        this.zzpV.zzsy.setVisibility(0);
        return true;
    }

    private void zzd(lb lbVar) {
        if (this.zzpV.zzcJ()) {
            if (lbVar.f5327b != null) {
                if (lbVar.f5335j != null) {
                    this.zzpX.m6350a(this.zzpV.zzsB, lbVar);
                }
                if (lbVar.m6989a()) {
                    this.zzpX.m6350a(this.zzpV.zzsB, lbVar).zza(lbVar.f5327b);
                } else {
                    lbVar.f5327b.m7262l().zza(new 1(this, lbVar));
                }
            }
        } else if (this.zzpV.zzsW != null && lbVar.f5335j != null) {
            this.zzpX.m6351a(this.zzpV.zzsB, lbVar, this.zzpV.zzsW);
        }
    }

    public void onGlobalLayout() {
        zze(this.zzpV.zzsC);
    }

    public void onScrollChanged() {
        zze(this.zzpV.zzsC);
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzaa.zzdc("setManualImpressionsEnabled must be called from the main thread.");
        this.zzqs = z;
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    protected no zza(lc lcVar, @Nullable C1078b c1078b, @Nullable kv kvVar) {
        if (this.zzpV.zzsB.zzvw) {
            this.zzpV.zzsB = zzb(lcVar);
        }
        return super.zza(lcVar, c1078b, kvVar);
    }

    protected void zza(@Nullable lb lbVar, boolean z) {
        super.zza(lbVar, z);
        if (C1098h.m6025b(lbVar)) {
            C1098h.m6014a(lbVar, new c(this));
        }
    }

    public boolean zza(@Nullable lb lbVar, lb lbVar2) {
        if (!super.zza(lbVar, lbVar2)) {
            return false;
        }
        if (!this.zzpV.zzcJ() || zzb(lbVar, lbVar2)) {
            if (lbVar2.f5336k) {
                zze(lbVar2);
                C1101o.m6061y().m7216a(this.zzpV.zzsy, (OnGlobalLayoutListener) this);
                C1101o.m6061y().m7217a(this.zzpV.zzsy, (OnScrollChangedListener) this);
            } else if (!this.zzpV.zzcK() || ((Boolean) dz.bf.m6433c()).booleanValue()) {
                zza(lbVar2, false);
            }
            zzlf com_google_android_gms_internal_zzlf = null;
            if (lbVar2.f5327b != null) {
                com_google_android_gms_internal_zzlf = lbVar2.f5327b.m7276z();
                zzlb l = lbVar2.f5327b.m7262l();
                if (l != null) {
                    l.zzjU();
                }
            }
            if (!(this.zzpV.zzsQ == null || com_google_android_gms_internal_zzlf == null)) {
                com_google_android_gms_internal_zzlf.zzL(this.zzpV.zzsQ.zzwN);
            }
            zzd(lbVar2);
            return true;
        }
        zzf(0);
        return false;
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        return super.zzb(zze(adRequestParcel));
    }

    @Nullable
    public zzab zzbl() {
        zzaa.zzdc("getVideoController must be called from the main thread.");
        return (this.zzpV.zzsC == null || this.zzpV.zzsC.f5327b == null) ? null : this.zzpV.zzsC.f5327b.m7276z();
    }

    protected boolean zzbr() {
        boolean z = true;
        if (!C1101o.m6041e().m7113a(this.zzpV.zzov.getPackageManager(), this.zzpV.zzov.getPackageName(), "android.permission.INTERNET")) {
            C1089r.m5951a().m6175a(this.zzpV.zzsy, this.zzpV.zzsB, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        if (!C1101o.m6041e().m7112a(this.zzpV.zzov)) {
            C1089r.m5951a().m6175a(this.zzpV.zzsy, this.zzpV.zzsB, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!(z || this.zzpV.zzsy == null)) {
            this.zzpV.zzsy.setVisibility(0);
        }
        return z;
    }

    AdRequestParcel zze(AdRequestParcel adRequestParcel) {
        if (adRequestParcel.zzuS == this.zzqs) {
            return adRequestParcel;
        }
        int i = adRequestParcel.versionCode;
        long j = adRequestParcel.zzuN;
        Bundle bundle = adRequestParcel.extras;
        int i2 = adRequestParcel.zzuO;
        List list = adRequestParcel.zzuP;
        boolean z = adRequestParcel.zzuQ;
        int i3 = adRequestParcel.zzuR;
        boolean z2 = adRequestParcel.zzuS || this.zzqs;
        return new AdRequestParcel(i, j, bundle, i2, list, z, i3, z2, adRequestParcel.zzuT, adRequestParcel.zzuU, adRequestParcel.zzuV, adRequestParcel.zzuW, adRequestParcel.zzuX, adRequestParcel.zzuY, adRequestParcel.zzuZ, adRequestParcel.zzva, adRequestParcel.zzvb, adRequestParcel.zzvc);
    }

    void zze(@Nullable lb lbVar) {
        if (lbVar != null && !lbVar.f5338m && this.zzpV.zzsy != null && C1101o.m6041e().m7114a(this.zzpV.zzsy, this.zzpV.zzov) && this.zzpV.zzsy.getGlobalVisibleRect(new Rect(), null)) {
            zza(lbVar, false);
            lbVar.f5338m = true;
        }
    }
}
