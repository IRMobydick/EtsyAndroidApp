package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.fs;
import com.google.android.gms.internal.fx;
import com.google.android.gms.internal.fy;
import com.google.android.gms.internal.hd;
import com.google.android.gms.internal.hj;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kv;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzlb;
import java.util.Collections;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

@jw
public class zzl extends zzc implements fs, fy {
    protected transient boolean zzqX;
    private int zzqY;
    private boolean zzqZ;
    private float zzra;

    public zzl(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzga, versionInfoParcel, c1077a);
        this.zzqY = -1;
        this.zzqX = false;
    }

    private void zzb(Bundle bundle) {
        C1101o.m6041e().m7122b(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, "gmob-apps", bundle, false);
    }

    private lc zzc(lc lcVar) {
        try {
            String jSONObject = jy.m6897a(lcVar.f5353b).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("pubid", lcVar.f5352a.zzsv);
            hk hkVar = new hk(Collections.singletonList(new hj(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList())), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, StringUtils.EMPTY, -1, 0, 1, null, 0, -1, -1, false);
            AdResponseParcel adResponseParcel = lcVar.f5353b;
            return new lc(lcVar.f5352a, new AdResponseParcel(lcVar.f5352a, adResponseParcel.zzHH, adResponseParcel.body, adResponseParcel.zzEF, adResponseParcel.zzEG, adResponseParcel.zzLO, true, adResponseParcel.zzLQ, adResponseParcel.zzLR, adResponseParcel.zzEL, adResponseParcel.orientation, adResponseParcel.zzLS, adResponseParcel.zzLT, adResponseParcel.zzLU, adResponseParcel.zzLV, adResponseParcel.zzLW, adResponseParcel.zzLX, adResponseParcel.zzLY, adResponseParcel.zzvv, adResponseParcel.zzLq, adResponseParcel.zzLZ, adResponseParcel.zzMa, adResponseParcel.zzMb, adResponseParcel.zzMe, adResponseParcel.zzvw, adResponseParcel.zzvx, adResponseParcel.zzMf, adResponseParcel.zzMg, adResponseParcel.zzMh, adResponseParcel.zzMi, adResponseParcel.zzMj, adResponseParcel.zzLH, adResponseParcel.zzLI, adResponseParcel.zzEI, adResponseParcel.zzMk, adResponseParcel.zzEJ), hkVar, lcVar.f5355d, lcVar.f5356e, lcVar.f5357f, lcVar.f5358g, lcVar.f5359h);
        } catch (Throwable e) {
            C1129c.m6189b("Unable to generate ad state for an interstitial ad with pooling.", e);
            return lcVar;
        }
    }

    public void showInterstitial() {
        zzaa.zzdc("showInterstitial must be called on the main UI thread.");
        if (this.zzpV.zzsC == null) {
            C1129c.m6192d("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) dz.ax.m6433c()).booleanValue()) {
            Bundle bundle;
            String packageName = this.zzpV.zzov.getApplicationContext() != null ? this.zzpV.zzov.getApplicationContext().getPackageName() : this.zzpV.zzov.getPackageName();
            if (!this.zzqX) {
                C1129c.m6192d("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString("action", "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            if (!C1101o.m6041e().m7137g(this.zzpV.zzov)) {
                C1129c.m6192d("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString("action", "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzpV.zzcK()) {
            if (this.zzpV.zzsC.f5339n && this.zzpV.zzsC.f5341p != null) {
                try {
                    this.zzpV.zzsC.f5341p.showInterstitial();
                } catch (Throwable e) {
                    C1129c.m6193d("Could not show interstitial.", e);
                    zzbO();
                }
            } else if (this.zzpV.zzsC.f5327b == null) {
                C1129c.m6192d("The interstitial failed to load.");
            } else if (this.zzpV.zzsC.f5327b.m7266p()) {
                C1129c.m6192d("The interstitial is already showing.");
            } else {
                this.zzpV.zzsC.f5327b.m7246a(true);
                if (this.zzpV.zzsC.f5335j != null) {
                    this.zzpX.m6350a(this.zzpV.zzsB, this.zzpV.zzsC);
                }
                Bitmap h = this.zzpV.zzrf ? C1101o.m6041e().m7138h(this.zzpV.zzov) : null;
                this.zzqY = C1101o.m6058v().m7185a(h);
                if (!((Boolean) dz.bb.m6433c()).booleanValue() || h == null) {
                    InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.zzpV.zzrf, zzbN(), false, 0.0f, -1);
                    int q = this.zzpV.zzsC.f5327b.m7267q();
                    if (q == -1) {
                        q = this.zzpV.zzsC.f5332g;
                    }
                    C1101o.m6039c().m6066a(this.zzpV.zzov, new AdOverlayInfoParcel(this, this, this, this.zzpV.zzsC.f5327b, q, this.zzpV.zzsx, this.zzpV.zzsC.f5321C, interstitialAdParameterParcel));
                    return;
                }
                Future future = (Future) new C1097g(this, this.zzqY).zzhs();
            }
        }
    }

    protected no zza(lc lcVar, @Nullable C1078b c1078b, @Nullable kv kvVar) {
        no a = C1101o.m6042f().m7278a(this.zzpV.zzov, this.zzpV.zzsB, false, false, this.zzpV.zzsw, this.zzpV.zzsx, this.zzpQ, this, this.zzpY);
        a.m7262l().zza(this, null, this, this, ((Boolean) dz.f4823W.m6433c()).booleanValue(), this, this, c1078b, null, kvVar);
        zza((hd) a);
        a.m7250b(lcVar.f5352a.zzLx);
        fx.m6550a(a, (fy) this);
        return a;
    }

    public void zza(lc lcVar, ei eiVar) {
        Object obj = 1;
        if (!((Boolean) dz.ah.m6433c()).booleanValue()) {
            super.zza(lcVar, eiVar);
        } else if (lcVar.f5356e != -2) {
            super.zza(lcVar, eiVar);
        } else {
            Bundle bundle = lcVar.f5352a.zzLi.zzuX.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            Object obj2 = (bundle == null || !bundle.containsKey("gw")) ? 1 : null;
            if (lcVar.f5353b.zzLP) {
                obj = null;
            }
            if (!(obj2 == null || r2 == null)) {
                this.zzpV.zzsD = zzc(lcVar);
            }
            super.zza(this.zzpV.zzsD, eiVar);
        }
    }

    public void zza(boolean z, float f) {
        this.zzqZ = z;
        this.zzra = f;
    }

    public boolean zza(AdRequestParcel adRequestParcel, ei eiVar) {
        if (this.zzpV.zzsC == null) {
            return super.zza(adRequestParcel, eiVar);
        }
        C1129c.m6192d("An interstitial is already loading. Aborting.");
        return false;
    }

    protected boolean zza(AdRequestParcel adRequestParcel, lb lbVar, boolean z) {
        if (this.zzpV.zzcJ() && lbVar.f5327b != null) {
            C1101o.m6043g().m7159a(lbVar.f5327b);
        }
        return this.zzpU.m6035d();
    }

    public boolean zza(@Nullable lb lbVar, lb lbVar2) {
        if (!super.zza(lbVar, lbVar2)) {
            return false;
        }
        if (!(this.zzpV.zzcJ() || this.zzpV.zzsW == null || lbVar2.f5335j == null)) {
            this.zzpX.m6351a(this.zzpV.zzsB, lbVar2, this.zzpV.zzsW);
        }
        return true;
    }

    public void zzb(RewardItemParcel rewardItemParcel) {
        if (this.zzpV.zzsC != null) {
            if (this.zzpV.zzsC.f5351z != null) {
                C1101o.m6041e().m7105a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC.f5351z);
            }
            if (this.zzpV.zzsC.f5349x != null) {
                rewardItemParcel = this.zzpV.zzsC.f5349x;
            }
        }
        zza(rewardItemParcel);
    }

    protected boolean zzbN() {
        if (!(this.zzpV.zzov instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzpV.zzov).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        boolean z = (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
        return z;
    }

    public void zzbO() {
        C1101o.m6058v().m7187b(Integer.valueOf(this.zzqY));
        if (this.zzpV.zzcJ()) {
            this.zzpV.zzcG();
            this.zzpV.zzsC = null;
            this.zzpV.zzrf = false;
            this.zzqX = false;
        }
    }

    public void zzbP() {
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5350y == null)) {
            C1101o.m6041e().m7105a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC.f5350y);
        }
        zzbq();
    }

    protected void zzbm() {
        zzbO();
        super.zzbm();
    }

    protected void zzbp() {
        super.zzbp();
        this.zzqX = true;
    }

    public void zzbt() {
        recordImpression();
        super.zzbt();
        if (this.zzpV.zzsC != null && this.zzpV.zzsC.f5327b != null) {
            zzlb l = this.zzpV.zzsC.f5327b.m7262l();
            if (l != null) {
                l.zzjU();
            }
        }
    }

    public void zzf(boolean z) {
        this.zzpV.zzrf = z;
    }
}
