package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.webkit.CookieManager;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.overlay.f;
import com.google.android.gms.ads.internal.purchase.C1113b;
import com.google.android.gms.ads.internal.purchase.C1115e;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.d;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzd;
import com.google.android.gms.ads.internal.purchase.zzg;
import com.google.android.gms.ads.internal.request.C1117a;
import com.google.android.gms.ads.internal.request.CapabilityParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.fq;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.no;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzhe;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;

@jw
public abstract class zzb extends zza implements l, f, d, fq, hl {
    private final Messenger mMessenger;
    protected final zzga zzqc;
    protected transient boolean zzqd;

    public zzb(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel, C1077a c1077a) {
        this(new zzv(context, adSizeParcel, str, versionInfoParcel), com_google_android_gms_internal_zzga, null, c1077a);
    }

    protected zzb(zzv com_google_android_gms_ads_internal_zzv, zzga com_google_android_gms_internal_zzga, @Nullable C1100j c1100j, C1077a c1077a) {
        super(com_google_android_gms_ads_internal_zzv, c1100j, c1077a);
        this.zzqc = com_google_android_gms_internal_zzga;
        this.mMessenger = new Messenger(new zzhe(this.zzpV.zzov));
        this.zzqd = false;
    }

    private C1117a zza(AdRequestParcel adRequestParcel, Bundle bundle, lf lfVar) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo = this.zzpV.zzov.getApplicationInfo();
        try {
            packageInfo = this.zzpV.zzov.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzpV.zzov.getResources().getDisplayMetrics();
        Bundle bundle2 = null;
        if (!(this.zzpV.zzsy == null || this.zzpV.zzsy.getParent() == null)) {
            int[] iArr = new int[2];
            this.zzpV.zzsy.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int width = this.zzpV.zzsy.getWidth();
            int height = this.zzpV.zzsy.getHeight();
            int i3 = 0;
            if (this.zzpV.zzsy.isShown() && i + width > 0 && i2 + height > 0 && i <= displayMetrics.widthPixels && i2 <= displayMetrics.heightPixels) {
                i3 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt(EtsyDialogFragment.OPT_X_BUTTON, i);
            bundle2.putInt("y", i2);
            bundle2.putInt(ResponseConstants.WIDTH, width);
            bundle2.putInt(ResponseConstants.HEIGHT, height);
            bundle2.putInt("visible", i3);
        }
        String c = C1101o.m6044h().m7027c();
        this.zzpV.zzsE = new ld(c, this.zzpV.zzsv);
        this.zzpV.zzsE.m6992a(adRequestParcel);
        String a = C1101o.m6041e().m7090a(this.zzpV.zzov, this.zzpV.zzsy, this.zzpV.zzsB);
        long j = 0;
        if (this.zzpV.zzsI != null) {
            try {
                j = this.zzpV.zzsI.getValue();
            } catch (RemoteException e2) {
                C1129c.m6192d("Cannot get correlation id, default to 0.");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Bundle a2 = C1101o.m6044h().m7009a(this.zzpV.zzov, this, c);
        List arrayList = new ArrayList();
        for (i = 0; i < this.zzpV.zzsO.size(); i++) {
            arrayList.add((String) this.zzpV.zzsO.keyAt(i));
        }
        boolean z = this.zzpV.zzsJ != null;
        boolean z2 = this.zzpV.zzsK != null && C1101o.m6044h().m7041p();
        boolean a3 = this.zzpY.f4380c.a();
        String str = StringUtils.EMPTY;
        if (((Boolean) dz.bM.m6433c()).booleanValue()) {
            C1129c.m6185a("Getting webview cookie from CookieManager.");
            CookieManager b = C1101o.m6043g().m7161b(this.zzpV.zzov);
            if (b != null) {
                str = b.getCookie("googleads.g.doubleclick.net");
            }
        }
        String str2 = null;
        if (lfVar != null) {
            str2 = lfVar.m7007c();
        }
        return new C1117a(bundle2, adRequestParcel, this.zzpV.zzsB, this.zzpV.zzsv, applicationInfo, packageInfo, c, C1101o.m6044h().m7011a(), this.zzpV.zzsx, a2, this.zzpV.zzsT, arrayList, bundle, C1101o.m6044h().m7032g(), this.mMessenger, displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.density, a, j, uuid, dz.m6443a(), this.zzpV.zzsu, this.zzpV.zzsP, new CapabilityParcel(z, z2, a3), this.zzpV.zzcN(), C1101o.m6041e().m7136g(), C1101o.m6041e().m7139h(), C1101o.m6041e().m7143k(this.zzpV.zzov), C1101o.m6041e().m7118b(this.zzpV.zzsy), this.zzpV.zzov instanceof Activity, C1101o.m6044h().m7036k(), str, str2, C1101o.m6044h().m7037l(), C1101o.m6060x().m6559a(), C1101o.m6041e().m7141i());
    }

    public String getMediationAdapterClassName() {
        return this.zzpV.zzsC == null ? null : this.zzpV.zzsC.f5342q;
    }

    public void onAdClicked() {
        if (this.zzpV.zzsC == null) {
            C1129c.m6192d("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzpV.zzsC.f5343r == null || this.zzpV.zzsC.f5343r.f5005c == null)) {
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC, this.zzpV.zzsv, false, this.zzpV.zzsC.f5343r.f5005c);
        }
        if (!(this.zzpV.zzsC.f5340o == null || this.zzpV.zzsC.f5340o.f4992f == null)) {
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC, this.zzpV.zzsv, false, this.zzpV.zzsC.f5340o.f4992f);
        }
        super.onAdClicked();
    }

    public void onPause() {
        this.zzpX.m6359d(this.zzpV.zzsC);
    }

    public void onResume() {
        this.zzpX.m6360e(this.zzpV.zzsC);
    }

    public void pause() {
        zzaa.zzdc("pause must be called on the main UI thread.");
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5327b == null || !this.zzpV.zzcJ())) {
            C1101o.m6043g().m7159a(this.zzpV.zzsC.f5327b);
        }
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5341p == null)) {
            try {
                this.zzpV.zzsC.f5341p.pause();
            } catch (RemoteException e) {
                C1129c.m6192d("Could not pause mediation adapter.");
            }
        }
        this.zzpX.m6359d(this.zzpV.zzsC);
        this.zzpU.m6033b();
    }

    public void recordImpression() {
        zza(this.zzpV.zzsC, false);
    }

    public void resume() {
        zzaa.zzdc("resume must be called on the main UI thread.");
        no noVar = null;
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5327b == null)) {
            noVar = this.zzpV.zzsC.f5327b;
        }
        if (noVar != null && this.zzpV.zzcJ()) {
            C1101o.m6043g().m7163b(this.zzpV.zzsC.f5327b);
        }
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5341p == null)) {
            try {
                this.zzpV.zzsC.f5341p.resume();
            } catch (RemoteException e) {
                C1129c.m6192d("Could not resume mediation adapter.");
            }
        }
        if (noVar == null || !noVar.m7271u()) {
            this.zzpU.m6034c();
        }
        this.zzpX.m6360e(this.zzpV.zzsC);
    }

    public void showInterstitial() {
        throw new IllegalStateException("showInterstitial is not supported for current ad type");
    }

    protected void zza(@Nullable lb lbVar, boolean z) {
        if (lbVar == null) {
            C1129c.m6192d("Ad state was null when trying to ping impression URLs.");
            return;
        }
        super.zzc(lbVar);
        if (!(lbVar.f5343r == null || lbVar.f5343r.f5006d == null)) {
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, lbVar, this.zzpV.zzsv, z, lbVar.f5343r.f5006d);
        }
        if (lbVar.f5340o != null && lbVar.f5340o.f4993g != null) {
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, lbVar, this.zzpV.zzsv, z, lbVar.f5340o.f4993g);
        }
    }

    public void zza(zzhh com_google_android_gms_internal_zzhh) {
        zzaa.zzdc("setInAppPurchaseListener must be called on the main UI thread.");
        this.zzpV.zzsJ = com_google_android_gms_internal_zzhh;
    }

    public void zza(zzhl com_google_android_gms_internal_zzhl, @Nullable String str) {
        zzaa.zzdc("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.zzpV.zzsU = new C1115e(str);
        this.zzpV.zzsK = com_google_android_gms_internal_zzhl;
        if (!C1101o.m6044h().m7031f() && com_google_android_gms_internal_zzhl != null) {
            Future future = (Future) new zzc(this.zzpV.zzov, this.zzpV.zzsK, this.zzpV.zzsU).zzhs();
        }
    }

    public void zza(String str, ArrayList<String> arrayList) {
        zzd com_google_android_gms_ads_internal_purchase_zzd = new zzd(str, arrayList, this.zzpV.zzov, this.zzpV.zzsx.afmaVersion);
        if (this.zzpV.zzsJ == null) {
            C1129c.m6192d("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (!C1089r.m5951a().m6181b(this.zzpV.zzov)) {
                C1129c.m6192d("Google Play Service unavailable, cannot launch default purchase flow.");
                return;
            } else if (this.zzpV.zzsK == null) {
                C1129c.m6192d("PlayStorePurchaseListener is not set.");
                return;
            } else if (this.zzpV.zzsU == null) {
                C1129c.m6192d("PlayStorePurchaseVerifier is not initialized.");
                return;
            } else if (this.zzpV.zzsY) {
                C1129c.m6192d("An in-app purchase request is already in progress, abort");
                return;
            } else {
                this.zzpV.zzsY = true;
                try {
                    if (this.zzpV.zzsK.isValidPurchase(str)) {
                        C1101o.m6051o().m6100a(this.zzpV.zzov, this.zzpV.zzsx.zzRE, new GInAppPurchaseManagerInfoParcel(this.zzpV.zzov, this.zzpV.zzsU, com_google_android_gms_ads_internal_purchase_zzd, this));
                        return;
                    } else {
                        this.zzpV.zzsY = false;
                        return;
                    }
                } catch (RemoteException e) {
                    C1129c.m6192d("Could not start In-App purchase.");
                    this.zzpV.zzsY = false;
                    return;
                }
            }
        }
        try {
            this.zzpV.zzsJ.zza(com_google_android_gms_ads_internal_purchase_zzd);
        } catch (RemoteException e2) {
            C1129c.m6192d("Could not start In-App purchase.");
        }
    }

    public void zza(String str, boolean z, int i, Intent intent, C1113b c1113b) {
        try {
            if (this.zzpV.zzsK != null) {
                this.zzpV.zzsK.zza(new zzg(this.zzpV.zzov, str, z, i, intent, c1113b));
            }
        } catch (RemoteException e) {
            C1129c.m6192d("Fail to invoke PlayStorePurchaseListener.");
        }
        lt.f5423a.postDelayed(new 1(this, intent), 500);
    }

    public boolean zza(AdRequestParcel adRequestParcel, ei eiVar) {
        String str = null;
        if (!zzbr()) {
            return false;
        }
        lf m;
        Bundle zza = zza(C1101o.m6044h().m7010a(this.zzpV.zzov));
        this.zzpU.m6030a();
        this.zzpV.zzsX = 0;
        if (((Boolean) dz.bs.m6433c()).booleanValue()) {
            m = C1101o.m6044h().m7038m();
            C1091d w = C1101o.m6059w();
            Context context = this.zzpV.zzov;
            VersionInfoParcel versionInfoParcel = this.zzpV.zzsx;
            if (m != null) {
                str = m.m7008d();
            }
            w.m5964a(context, versionInfoParcel, false, m, str, this.zzpV.zzsv);
        } else {
            m = null;
        }
        C1117a zza2 = zza(adRequestParcel, zza, m);
        eiVar.m6477a("seq_num", zza2.f4610g);
        eiVar.m6477a("request_id", zza2.f4625v);
        eiVar.m6477a("session_id", zza2.f4611h);
        if (zza2.f4609f != null) {
            eiVar.m6477a("app_version", String.valueOf(zza2.f4609f.versionCode));
        }
        this.zzpV.zzsz = C1101o.m6036a().m6118a(this.zzpV.zzov, zza2, this.zzpV.zzsw, this);
        return true;
    }

    protected boolean zza(AdRequestParcel adRequestParcel, lb lbVar, boolean z) {
        if (!z && this.zzpV.zzcJ()) {
            if (lbVar.f5333h > 0) {
                this.zzpU.m6032a(adRequestParcel, lbVar.f5333h);
            } else if (lbVar.f5343r != null && lbVar.f5343r.f5011i > 0) {
                this.zzpU.m6032a(adRequestParcel, lbVar.f5343r.f5011i);
            } else if (!lbVar.f5339n && lbVar.f5329d == 2) {
                this.zzpU.m6031a(adRequestParcel);
            }
        }
        return this.zzpU.m6035d();
    }

    boolean zza(lb lbVar) {
        AdRequestParcel adRequestParcel;
        boolean z = false;
        if (this.zzpW != null) {
            adRequestParcel = this.zzpW;
            this.zzpW = null;
        } else {
            adRequestParcel = lbVar.f5326a;
            if (adRequestParcel.extras != null) {
                z = adRequestParcel.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(adRequestParcel, lbVar, z);
    }

    protected boolean zza(@Nullable lb lbVar, lb lbVar2) {
        int i;
        int i2 = 0;
        if (!(lbVar == null || lbVar.f5344s == null)) {
            lbVar.f5344s.zza(null);
        }
        if (lbVar2.f5344s != null) {
            lbVar2.f5344s.zza((hl) this);
        }
        if (lbVar2.f5343r != null) {
            i = lbVar2.f5343r.f5017o;
            i2 = lbVar2.f5343r.f5018p;
        } else {
            i = 0;
        }
        this.zzpV.zzsV.m7050a(i, i2);
        return true;
    }

    public void zzb(lb lbVar) {
        super.zzb(lbVar);
        if (lbVar.f5340o != null) {
            C1129c.m6185a("Pinging network fill URLs.");
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, lbVar, this.zzpV.zzsv, false, lbVar.f5340o.f4994h);
            if (lbVar.f5343r.f5008f != null && lbVar.f5343r.f5008f.size() > 0) {
                C1129c.m6185a("Pinging urls remotely");
                C1101o.m6041e().m7108a(this.zzpV.zzov, lbVar.f5343r.f5008f);
            }
        }
        if (lbVar.f5329d == 3 && lbVar.f5343r != null && lbVar.f5343r.f5007e != null) {
            C1129c.m6185a("Pinging no fill URLs.");
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, lbVar, this.zzpV.zzsv, false, lbVar.f5343r.f5007e);
        }
    }

    public void zzbA() {
        C1101o.m6041e().m7109a(new 2(this));
    }

    public void zzbB() {
        C1101o.m6041e().m7109a(new 3(this));
    }

    protected boolean zzbr() {
        return C1101o.m6041e().m7113a(this.zzpV.zzov.getPackageManager(), this.zzpV.zzov.getPackageName(), "android.permission.INTERNET") && C1101o.m6041e().m7112a(this.zzpV.zzov);
    }

    public void zzbs() {
        this.zzpX.m6357b(this.zzpV.zzsC);
        this.zzqd = false;
        zzbm();
        this.zzpV.zzsE.m6997c();
    }

    public void zzbt() {
        this.zzqd = true;
        zzbo();
    }

    public void zzbu() {
        onAdClicked();
    }

    public void zzbv() {
        zzbs();
    }

    public void zzbw() {
        zzbj();
    }

    public void zzbx() {
        zzbt();
    }

    public void zzby() {
        if (this.zzpV.zzsC != null) {
            String str = this.zzpV.zzsC.f5342q;
            C1129c.m6192d(new StringBuilder(String.valueOf(str).length() + 74).append("Mediation adapter ").append(str).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        zza(this.zzpV.zzsC, true);
        zzbp();
    }

    public void zzbz() {
        recordImpression();
    }

    protected boolean zzc(AdRequestParcel adRequestParcel) {
        return super.zzc(adRequestParcel) && !this.zzqd;
    }
}
