package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.C1077a;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

@jw
public class zziy extends zzb implements kq {
    private static final zzfz zzOy;
    private boolean zzOA;
    private final Map<String, kt> zzOz;

    static {
        zzOy = new zzfz();
    }

    public zziy(Context context, C1077a c1077a, AdSizeParcel adSizeParcel, zzga com_google_android_gms_internal_zzga, VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, null, com_google_android_gms_internal_zzga, versionInfoParcel, c1077a);
        this.zzOz = new HashMap();
    }

    private lc zze(lc lcVar) {
        lo.m7056e("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            String jSONObject = jy.m6897a(lcVar.f5353b).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("pubid", lcVar.f5352a.zzsv);
            hj hjVar = new hj(jSONObject, null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList());
            return new lc(lcVar.f5352a, lcVar.f5353b, new hk(Arrays.asList(new hj[]{hjVar}), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, StringUtils.EMPTY, -1, 0, 1, null, 0, -1, -1, false), lcVar.f5355d, lcVar.f5356e, lcVar.f5357f, lcVar.f5358g, lcVar.f5359h);
        } catch (Throwable e) {
            C1129c.m6189b("Unable to generate ad state for non-mediated rewarded video.", e);
            return zzf(lcVar);
        }
    }

    private lc zzf(lc lcVar) {
        return new lc(lcVar.f5352a, lcVar.f5353b, null, lcVar.f5355d, 0, lcVar.f5357f, lcVar.f5358g, lcVar.f5359h);
    }

    public void destroy() {
        zzaa.zzdc("destroy must be called on the main UI thread.");
        for (String str : this.zzOz.keySet()) {
            String str2;
            try {
                kt ktVar = (kt) this.zzOz.get(str2);
                if (!(ktVar == null || ktVar.m6984a() == null)) {
                    ktVar.m6984a().destroy();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to destroy adapter: ";
                str2 = String.valueOf(str2);
                C1129c.m6192d(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public boolean isLoaded() {
        zzaa.zzdc("isLoaded must be called on the main UI thread.");
        return this.zzpV.zzsz == null && this.zzpV.zzsA == null && this.zzpV.zzsC != null && !this.zzOA;
    }

    public void onContextChanged(@NonNull Context context) {
        for (kt a : this.zzOz.values()) {
            try {
                a.m6984a().zzj(zze.zzD(context));
            } catch (Throwable e) {
                C1129c.m6189b("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public void onRewardedVideoAdClosed() {
        zzbm();
    }

    public void onRewardedVideoAdLeftApplication() {
        zzbn();
    }

    public void onRewardedVideoAdOpened() {
        zza(this.zzpV.zzsC, false);
        zzbo();
    }

    public void onRewardedVideoStarted() {
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5340o == null)) {
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC, this.zzpV.zzsv, false, this.zzpV.zzsC.f5340o.f4996j);
        }
        zzbq();
    }

    public void pause() {
        String valueOf;
        zzaa.zzdc("pause must be called on the main UI thread.");
        for (String valueOf2 : this.zzOz.keySet()) {
            try {
                kt ktVar = (kt) this.zzOz.get(valueOf2);
                if (!(ktVar == null || ktVar.m6984a() == null)) {
                    ktVar.m6984a().pause();
                }
            } catch (RemoteException e) {
                String str = "Fail to pause adapter: ";
                valueOf2 = String.valueOf(valueOf2);
                C1129c.m6192d(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
            }
        }
    }

    public void resume() {
        zzaa.zzdc("resume must be called on the main UI thread.");
        for (String str : this.zzOz.keySet()) {
            String str2;
            try {
                kt ktVar = (kt) this.zzOz.get(str2);
                if (!(ktVar == null || ktVar.m6984a() == null)) {
                    ktVar.m6984a().resume();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to resume adapter: ";
                str2 = String.valueOf(str2);
                C1129c.m6192d(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        zzaa.zzdc("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(rewardedVideoAdRequestParcel.zzsv)) {
            C1129c.m6192d("Invalid ad unit id. Aborting.");
            return;
        }
        this.zzOA = false;
        this.zzpV.zzsv = rewardedVideoAdRequestParcel.zzsv;
        super.zzb(rewardedVideoAdRequestParcel.zzLi);
    }

    public void zza(lc lcVar, ei eiVar) {
        if (lcVar.f5356e != -2) {
            lt.f5423a.post(new 1(this, lcVar));
            return;
        }
        this.zzpV.zzsD = lcVar;
        if (lcVar.f5354c == null) {
            this.zzpV.zzsD = zze(lcVar);
        }
        this.zzpV.zzsX = 0;
        this.zzpV.zzsA = C1101o.m6040d().m6815a(this.zzpV.zzov, this.zzpV.zzsD, this);
    }

    protected boolean zza(AdRequestParcel adRequestParcel, lb lbVar, boolean z) {
        return false;
    }

    public boolean zza(lb lbVar, lb lbVar2) {
        return true;
    }

    @Nullable
    public kt zzaE(String str) {
        kt ktVar;
        Throwable th;
        String str2;
        String valueOf;
        kt ktVar2 = (kt) this.zzOz.get(str);
        if (ktVar2 != null) {
            return ktVar2;
        }
        try {
            ktVar = new kt(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzOy : this.zzqc).zzal(str), this);
            try {
                this.zzOz.put(str, ktVar);
                return ktVar;
            } catch (Throwable e) {
                th = e;
                str2 = "Fail to instantiate adapter ";
                valueOf = String.valueOf(str);
                C1129c.m6193d(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
                return ktVar;
            }
        } catch (Throwable e2) {
            th = e2;
            ktVar = ktVar2;
            str2 = "Fail to instantiate adapter ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            C1129c.m6193d(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
            return ktVar;
        }
    }

    public void zzc(@Nullable RewardItemParcel rewardItemParcel) {
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5340o == null)) {
            C1101o.m6056t().m6688a(this.zzpV.zzov, this.zzpV.zzsx.afmaVersion, this.zzpV.zzsC, this.zzpV.zzsv, false, this.zzpV.zzsC.f5340o.f4997k);
        }
        if (!(this.zzpV.zzsC == null || this.zzpV.zzsC.f5343r == null || TextUtils.isEmpty(this.zzpV.zzsC.f5343r.f5012j))) {
            rewardItemParcel = new RewardItemParcel(this.zzpV.zzsC.f5343r.f5012j, this.zzpV.zzsC.f5343r.f5013k);
        }
        zza(rewardItemParcel);
    }

    public void zzil() {
        zzaa.zzdc("showAd must be called on the main UI thread.");
        if (isLoaded()) {
            this.zzOA = true;
            kt zzaE = zzaE(this.zzpV.zzsC.f5342q);
            if (zzaE != null && zzaE.m6984a() != null) {
                try {
                    zzaE.m6984a().showVideo();
                    return;
                } catch (Throwable e) {
                    C1129c.m6193d("Could not call showVideo.", e);
                    return;
                }
            }
            return;
        }
        C1129c.m6192d("The reward video has not loaded.");
    }

    public void zzim() {
        onAdClicked();
    }
}
