package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1089r;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.cr;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ls;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhl;
import java.util.Map;
import java.util.concurrent.Future;

@jw
public class zzt extends zza {
    private final Context mContext;
    private zzq zzqG;
    private final VersionInfoParcel zzqP;
    private final AdSizeParcel zzrJ;
    private final Future<cr> zzrK;
    private final n zzrL;
    private WebView zzrM;
    private cr zzrN;
    private AsyncTask<Void, Void, Void> zzrO;

    public zzt(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzqP = versionInfoParcel;
        this.zzrJ = adSizeParcel;
        this.zzrM = new WebView(this.mContext);
        this.zzrK = zzca();
        this.zzrL = new n(str);
        zzbX();
    }

    private void zzbX() {
        zzh(0);
        this.zzrM.setVerticalScrollBarEnabled(false);
        this.zzrM.getSettings().setJavaScriptEnabled(true);
        this.zzrM.setWebViewClient(new 1(this));
        this.zzrM.setOnTouchListener(new 2(this));
    }

    private Future<cr> zzca() {
        return ls.m7071a(new 3(this));
    }

    private String zzx(String str) {
        if (this.zzrN == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzrN.b(parse, this.mContext);
        } catch (Throwable e) {
            C1129c.m6193d("Unable to process ad data", e);
        } catch (zzbp e2) {
            C1129c.m6193d("Unable to parse ad click url", e2);
        }
        return parse.toString();
    }

    private void zzy(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    public void destroy() {
        zzaa.zzdc("destroy must be called on the main UI thread.");
        this.zzrO.cancel(true);
        this.zzrK.cancel(true);
        this.zzrM.destroy();
        this.zzrM = null;
    }

    public String getMediationAdapterClassName() {
        return null;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public void pause() {
        zzaa.zzdc("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzaa.zzdc("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) {
    }

    public void setUserId(String str) {
        throw new IllegalStateException("Unused method");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Unused method");
    }

    public void stopLoading() {
    }

    public void zza(AdSizeParcel adSizeParcel) {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzqG = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzdg com_google_android_gms_internal_zzdg) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzhh com_google_android_gms_internal_zzhh) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzhl com_google_android_gms_internal_zzhl, String str) {
        throw new IllegalStateException("Unused method");
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        zzaa.zzb(this.zzrM, (Object) "This Search Ad has already been torn down");
        this.zzrL.a(adRequestParcel);
        this.zzrO = new m(this, null).execute(new Void[0]);
        return true;
    }

    String zzbY() {
        String valueOf;
        Uri a;
        Throwable e;
        String valueOf2;
        Builder builder = new Builder();
        builder.scheme("https://").appendEncodedPath((String) dz.bA.m6433c());
        builder.appendQueryParameter(ResponseConstants.QUERY, this.zzrL.b());
        builder.appendQueryParameter("pubId", this.zzrL.c());
        Map d = this.zzrL.d();
        for (String valueOf3 : d.keySet()) {
            builder.appendQueryParameter(valueOf3, (String) d.get(valueOf3));
        }
        Uri build = builder.build();
        if (this.zzrN != null) {
            try {
                a = this.zzrN.a(build, this.mContext);
            } catch (zzbp e2) {
                e = e2;
                C1129c.m6193d("Unable to process ad data", e);
                a = build;
                valueOf2 = String.valueOf(zzbZ());
                valueOf3 = String.valueOf(a.getEncodedQuery());
                return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
            } catch (RemoteException e3) {
                e = e3;
                C1129c.m6193d("Unable to process ad data", e);
                a = build;
                valueOf2 = String.valueOf(zzbZ());
                valueOf3 = String.valueOf(a.getEncodedQuery());
                return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
            }
            valueOf2 = String.valueOf(zzbZ());
            valueOf3 = String.valueOf(a.getEncodedQuery());
            return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
        }
        a = build;
        valueOf2 = String.valueOf(zzbZ());
        valueOf3 = String.valueOf(a.getEncodedQuery());
        return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
    }

    String zzbZ() {
        String str;
        CharSequence a = this.zzrL.a();
        if (TextUtils.isEmpty(a)) {
            str = "www.google.com";
        } else {
            CharSequence charSequence = a;
        }
        String valueOf = String.valueOf("https://");
        String str2 = (String) dz.bA.m6433c();
        return new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(str2).length()).append(valueOf).append(str).append(str2).toString();
    }

    public com.google.android.gms.dynamic.zzd zzbh() {
        zzaa.zzdc("getAdFrame must be called on the main UI thread.");
        return zze.zzD(this.zzrM);
    }

    public AdSizeParcel zzbi() {
        return this.zzrJ;
    }

    public void zzbk() {
        throw new IllegalStateException("Unused method");
    }

    public zzab zzbl() {
        return null;
    }

    void zzh(int i) {
        if (this.zzrM != null) {
            this.zzrM.setLayoutParams(new LayoutParams(-1, i));
        }
    }

    int zzw(String str) {
        int i = 0;
        Object queryParameter = Uri.parse(str).getQueryParameter(ResponseConstants.HEIGHT);
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                i = C1089r.m5951a().m6166a(this.mContext, Integer.parseInt(queryParameter));
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }
}
