package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1085m;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.client.ThinAdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.a;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.overlay.l;
import com.google.android.gms.ads.internal.request.c;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.dc;
import com.google.android.gms.internal.dg;
import com.google.android.gms.internal.di;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.fg;
import com.google.android.gms.internal.jh;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lb;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lk;
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhl;
import com.google.android.gms.internal.zziw;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.lang3.StringUtils;

@jw
public abstract class zza extends com.google.android.gms.ads.internal.client.zzu.zza implements a, l, c, fg, jh, lk {
    protected ei zzpQ;
    protected eg zzpR;
    protected eg zzpS;
    protected boolean zzpT;
    protected final C1100j zzpU;
    protected final zzv zzpV;
    @Nullable
    protected transient AdRequestParcel zzpW;
    protected final dc zzpX;
    protected final C1077a zzpY;

    zza(zzv com_google_android_gms_ads_internal_zzv, @Nullable C1100j c1100j, C1077a c1077a) {
        this.zzpT = false;
        this.zzpV = com_google_android_gms_ads_internal_zzv;
        if (c1100j == null) {
            c1100j = new C1100j(this);
        }
        this.zzpU = c1100j;
        this.zzpY = c1077a;
        C1101o.m6041e().m7123b(this.zzpV.zzov);
        C1101o.m6044h().m7015a(this.zzpV.zzov, this.zzpV.zzsx);
        this.zzpX = C1101o.m6044h().m7039n();
        zzbf();
    }

    private AdRequestParcel zza(AdRequestParcel adRequestParcel) {
        return (!zzi.zzaC(this.zzpV.zzov) || adRequestParcel.zzuV == null) ? adRequestParcel : new C1085m(adRequestParcel).m5925a(null).m5924a();
    }

    private TimerTask zza(Timer timer, CountDownLatch countDownLatch) {
        return new 1(this, countDownLatch, timer);
    }

    private void zzbf() {
        if (((Boolean) dz.bi.m6433c()).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(zza(timer, new CountDownLatch(((Integer) dz.bk.m6433c()).intValue())), 0, ((Long) dz.bj.m6433c()).longValue());
        }
    }

    public void destroy() {
        zzaa.zzdc("destroy must be called on the main UI thread.");
        this.zzpU.m6030a();
        this.zzpX.m6358c(this.zzpV.zzsC);
        this.zzpV.destroy();
    }

    public boolean isLoading() {
        return this.zzpT;
    }

    public boolean isReady() {
        zzaa.zzdc("isLoaded must be called on the main UI thread.");
        return this.zzpV.zzsz == null && this.zzpV.zzsA == null && this.zzpV.zzsC != null;
    }

    public void onAdClicked() {
        if (this.zzpV.zzsC == null) {
            C1129c.m6192d("Ad state was null when trying to ping click URLs.");
            return;
        }
        C1129c.m6185a("Pinging click URLs.");
        this.zzpV.zzsE.m6994b();
        if (this.zzpV.zzsC.f5328c != null) {
            C1101o.m6041e().m7105a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC.f5328c);
        }
        if (this.zzpV.zzsF != null) {
            try {
                this.zzpV.zzsF.onAdClicked();
            } catch (Throwable e) {
                C1129c.m6193d("Could not notify onAdClicked event.", e);
            }
        }
    }

    public void onAppEvent(String str, @Nullable String str2) {
        if (this.zzpV.zzsH != null) {
            try {
                this.zzpV.zzsH.onAppEvent(str, str2);
            } catch (Throwable e) {
                C1129c.m6193d("Could not call the AppEventListener.", e);
            }
        }
    }

    public void pause() {
        zzaa.zzdc("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzaa.zzdc("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) {
        throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public void setUserId(String str) {
        C1129c.m6192d("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void stopLoading() {
        zzaa.zzdc("stopLoading must be called on the main UI thread.");
        this.zzpT = false;
        this.zzpV.zzh(true);
    }

    Bundle zza(@Nullable di diVar) {
        if (diVar == null) {
            return null;
        }
        String b;
        String c;
        if (diVar.m6393f()) {
            diVar.m6391d();
        }
        dg c2 = diVar.m6390c();
        if (c2 != null) {
            b = c2.m6367b();
            c = c2.m6369c();
            String str = "In AdManager: loadAd, ";
            String valueOf = String.valueOf(c2.toString());
            C1129c.m6185a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (b != null) {
                C1101o.m6044h().m7014a(b);
            }
        } else {
            c = null;
            b = C1101o.m6044h().m7034i();
        }
        if (b == null) {
            return null;
        }
        Bundle bundle = new Bundle(1);
        bundle.putString("fingerprint", b);
        if (b.equals(c)) {
            return bundle;
        }
        bundle.putString("v_fp", c);
        return bundle;
    }

    public void zza(AdSizeParcel adSizeParcel) {
        zzaa.zzdc("setAdSize must be called on the main UI thread.");
        this.zzpV.zzsB = adSizeParcel;
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5327b == null || this.zzpV.zzsX != 0)) {
            this.zzpV.zzsC.f5327b.m7239a(adSizeParcel);
        }
        if (this.zzpV.zzsy != null) {
            if (this.zzpV.zzsy.getChildCount() > 1) {
                this.zzpV.zzsy.removeView(this.zzpV.zzsy.getNextView());
            }
            this.zzpV.zzsy.setMinimumWidth(adSizeParcel.widthPixels);
            this.zzpV.zzsy.setMinimumHeight(adSizeParcel.heightPixels);
            this.zzpV.zzsy.requestLayout();
        }
    }

    public void zza(@Nullable VideoOptionsParcel videoOptionsParcel) {
        zzaa.zzdc("setVideoOptions must be called on the main UI thread.");
        this.zzpV.zzsQ = videoOptionsParcel;
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) {
        zzaa.zzdc("setAdListener must be called on the main UI thread.");
        this.zzpV.zzsF = com_google_android_gms_ads_internal_client_zzp;
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) {
        zzaa.zzdc("setAdListener must be called on the main UI thread.");
        this.zzpV.zzsG = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) {
        zzaa.zzdc("setAppEventListener must be called on the main UI thread.");
        this.zzpV.zzsH = com_google_android_gms_ads_internal_client_zzw;
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) {
        zzaa.zzdc("setCorrelationIdProvider must be called on the main UI thread");
        this.zzpV.zzsI = com_google_android_gms_ads_internal_client_zzy;
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        zzaa.zzdc("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzpV.zzsS = com_google_android_gms_ads_internal_reward_client_zzd;
    }

    protected void zza(@Nullable RewardItemParcel rewardItemParcel) {
        if (this.zzpV.zzsS != null) {
            try {
                String str = StringUtils.EMPTY;
                int i = 0;
                if (rewardItemParcel != null) {
                    str = rewardItemParcel.type;
                    i = rewardItemParcel.zzOV;
                }
                this.zzpV.zzsS.zza(new zziw(str, i));
            } catch (Throwable e) {
                C1129c.m6193d("Could not call RewardedVideoAdListener.onRewarded().", e);
            }
        }
    }

    public void zza(lc lcVar) {
        if (!(lcVar.f5353b.zzLT == -1 || TextUtils.isEmpty(lcVar.f5353b.zzMd))) {
            long zzs = zzs(lcVar.f5353b.zzMd);
            if (zzs != -1) {
                eg a = this.zzpQ.m6474a(zzs + lcVar.f5353b.zzLT);
                this.zzpQ.m6479a(a, "stc");
            }
        }
        this.zzpQ.m6476a(lcVar.f5353b.zzMd);
        this.zzpQ.m6479a(this.zzpR, "arf");
        this.zzpS = this.zzpQ.m6473a();
        this.zzpQ.m6477a("gqi", lcVar.f5353b.zzMe);
        this.zzpV.zzsz = null;
        this.zzpV.zzsD = lcVar;
        zza(lcVar, this.zzpQ);
    }

    protected abstract void zza(lc lcVar, ei eiVar);

    public void zza(zzdg com_google_android_gms_internal_zzdg) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }

    public void zza(zzhh com_google_android_gms_internal_zzhh) {
        throw new IllegalStateException("setInAppPurchaseListener is not supported for current ad type");
    }

    public void zza(zzhl com_google_android_gms_internal_zzhl, String str) {
        throw new IllegalStateException("setPlayStorePurchaseParams is not supported for current ad type");
    }

    public void zza(HashSet<ld> hashSet) {
        this.zzpV.zza(hashSet);
    }

    protected abstract boolean zza(AdRequestParcel adRequestParcel, ei eiVar);

    boolean zza(lb lbVar) {
        return false;
    }

    protected abstract boolean zza(@Nullable lb lbVar, lb lbVar2);

    protected void zzb(View view) {
        this.zzpV.zzsy.addView(view, C1101o.m6043g().m7167d());
    }

    public void zzb(lb lbVar) {
        this.zzpQ.m6479a(this.zzpS, "awr");
        this.zzpV.zzsA = null;
        if (!(lbVar.f5329d == -2 || lbVar.f5329d == 3)) {
            C1101o.m6044h().m7022a(this.zzpV.zzcF());
        }
        if (lbVar.f5329d == -1) {
            this.zzpT = false;
            return;
        }
        if (zza(lbVar)) {
            C1129c.m6185a("Ad refresh scheduled.");
        }
        if (lbVar.f5329d != -2) {
            zzf(lbVar.f5329d);
            return;
        }
        if (this.zzpV.zzsV == null) {
            this.zzpV.zzsV = new ll(this.zzpV.zzsv);
        }
        this.zzpX.m6357b(this.zzpV.zzsC);
        if (zza(this.zzpV.zzsC, lbVar)) {
            this.zzpV.zzsC = lbVar;
            this.zzpV.zzcO();
            this.zzpQ.m6477a("is_mraid", this.zzpV.zzsC.m6989a() ? "1" : "0");
            this.zzpQ.m6477a("is_mediation", this.zzpV.zzsC.f5339n ? "1" : "0");
            if (!(this.zzpV.zzsC.f5327b == null || this.zzpV.zzsC.f5327b.m7262l() == null)) {
                this.zzpQ.m6477a("is_delay_pl", this.zzpV.zzsC.f5327b.m7262l().zzjS() ? "1" : "0");
            }
            this.zzpQ.m6479a(this.zzpR, "ttc");
            if (C1101o.m6044h().m7030e() != null) {
                C1101o.m6044h().m7030e().m6459a(this.zzpQ);
            }
            if (this.zzpV.zzcJ()) {
                zzbp();
            }
        }
        if (lbVar.f5325G != null) {
            C1101o.m6041e().m7108a(this.zzpV.zzov, lbVar.f5325G);
        }
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        zzaa.zzdc("loadAd must be called on the main UI thread.");
        AdRequestParcel zza = zza(adRequestParcel);
        if (this.zzpV.zzsz == null && this.zzpV.zzsA == null) {
            C1129c.m6190c("Starting ad request.");
            zzbg();
            this.zzpR = this.zzpQ.m6473a();
            if (!zza.zzuQ) {
                String valueOf = String.valueOf(C1089r.m5951a().m6168a(this.zzpV.zzov));
                C1129c.m6190c(new StringBuilder(String.valueOf(valueOf).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(valueOf).append("\") to get test ads on this device.").toString());
            }
            this.zzpT = zza(zza, this.zzpQ);
            return this.zzpT;
        }
        if (this.zzpW != null) {
            C1129c.m6192d("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            C1129c.m6192d("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzpW = zza;
        return false;
    }

    public void zzbg() {
        this.zzpQ = new ei(((Boolean) dz.f4808H.m6433c()).booleanValue(), "load_ad", this.zzpV.zzsB.zzvs);
        this.zzpR = new eg(-1, null, null);
        this.zzpS = new eg(-1, null, null);
    }

    public com.google.android.gms.dynamic.zzd zzbh() {
        zzaa.zzdc("getAdFrame must be called on the main UI thread.");
        return zze.zzD(this.zzpV.zzsy);
    }

    @Nullable
    public AdSizeParcel zzbi() {
        zzaa.zzdc("getAdSize must be called on the main UI thread.");
        return this.zzpV.zzsB == null ? null : new ThinAdSizeParcel(this.zzpV.zzsB);
    }

    public void zzbj() {
        zzbn();
    }

    public void zzbk() {
        zzaa.zzdc("recordManualImpression must be called on the main UI thread.");
        if (this.zzpV.zzsC == null) {
            C1129c.m6192d("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        C1129c.m6185a("Pinging manual tracking URLs.");
        if (this.zzpV.zzsC.f5331f != null && !this.zzpV.zzsC.f5324F) {
            C1101o.m6041e().m7105a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC.f5331f);
            this.zzpV.zzsC.f5324F = true;
        }
    }

    public zzab zzbl() {
        return null;
    }

    protected void zzbm() {
        C1129c.m6190c("Ad closing.");
        if (this.zzpV.zzsG != null) {
            try {
                this.zzpV.zzsG.onAdClosed();
            } catch (Throwable e) {
                C1129c.m6193d("Could not call AdListener.onAdClosed().", e);
            }
        }
        if (this.zzpV.zzsS != null) {
            try {
                this.zzpV.zzsS.onRewardedVideoAdClosed();
            } catch (Throwable e2) {
                C1129c.m6193d("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", e2);
            }
        }
    }

    protected void zzbn() {
        C1129c.m6190c("Ad leaving application.");
        if (this.zzpV.zzsG != null) {
            try {
                this.zzpV.zzsG.onAdLeftApplication();
            } catch (Throwable e) {
                C1129c.m6193d("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
        if (this.zzpV.zzsS != null) {
            try {
                this.zzpV.zzsS.onRewardedVideoAdLeftApplication();
            } catch (Throwable e2) {
                C1129c.m6193d("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", e2);
            }
        }
    }

    protected void zzbo() {
        C1129c.m6190c("Ad opening.");
        if (this.zzpV.zzsG != null) {
            try {
                this.zzpV.zzsG.onAdOpened();
            } catch (Throwable e) {
                C1129c.m6193d("Could not call AdListener.onAdOpened().", e);
            }
        }
        if (this.zzpV.zzsS != null) {
            try {
                this.zzpV.zzsS.onRewardedVideoAdOpened();
            } catch (Throwable e2) {
                C1129c.m6193d("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", e2);
            }
        }
    }

    protected void zzbp() {
        C1129c.m6190c("Ad finished loading.");
        this.zzpT = false;
        if (this.zzpV.zzsG != null) {
            try {
                this.zzpV.zzsG.onAdLoaded();
            } catch (Throwable e) {
                C1129c.m6193d("Could not call AdListener.onAdLoaded().", e);
            }
        }
        if (this.zzpV.zzsS != null) {
            try {
                this.zzpV.zzsS.onRewardedVideoAdLoaded();
            } catch (Throwable e2) {
                C1129c.m6193d("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", e2);
            }
        }
    }

    protected void zzbq() {
        if (this.zzpV.zzsS != null) {
            try {
                this.zzpV.zzsS.onRewardedVideoStarted();
            } catch (Throwable e) {
                C1129c.m6193d("Could not call RewardedVideoAdListener.onVideoStarted().", e);
            }
        }
    }

    protected void zzc(@Nullable lb lbVar) {
        if (lbVar == null) {
            C1129c.m6192d("Ad state was null when trying to ping impression URLs.");
            return;
        }
        C1129c.m6185a("Pinging Impression URLs.");
        this.zzpV.zzsE.m6990a();
        if (lbVar.f5330e != null && !lbVar.f5323E) {
            C1101o.m6041e().m7105a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, lbVar.f5330e);
            lbVar.f5323E = true;
        }
    }

    protected boolean zzc(AdRequestParcel adRequestParcel) {
        if (this.zzpV.zzsy == null) {
            return false;
        }
        ViewParent parent = this.zzpV.zzsy.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return C1101o.m6041e().m7114a(view, view.getContext());
    }

    public void zzd(AdRequestParcel adRequestParcel) {
        if (zzc(adRequestParcel)) {
            zzb(adRequestParcel);
            return;
        }
        C1129c.m6190c("Ad is not visible. Not refreshing ad.");
        this.zzpU.m6031a(adRequestParcel);
    }

    protected void zzf(int i) {
        C1129c.m6192d("Failed to load ad: " + i);
        this.zzpT = false;
        if (this.zzpV.zzsG != null) {
            try {
                this.zzpV.zzsG.onAdFailedToLoad(i);
            } catch (Throwable e) {
                C1129c.m6193d("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
        if (this.zzpV.zzsS != null) {
            try {
                this.zzpV.zzsS.onRewardedVideoAdFailedToLoad(i);
            } catch (Throwable e2) {
                C1129c.m6193d("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", e2);
            }
        }
    }

    long zzs(String str) {
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException e) {
            C1129c.m6192d("Invalid index for Url fetch time in CSI latency info.");
            return -1;
        } catch (NumberFormatException e2) {
            C1129c.m6192d("Cannot find valid format of Url fetch time in CSI latency info.");
            return -1;
        }
    }
}
