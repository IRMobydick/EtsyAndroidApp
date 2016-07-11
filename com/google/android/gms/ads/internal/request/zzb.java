package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.bu;
import com.google.android.gms.internal.dz;
import com.google.android.gms.internal.hk;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lc;
import com.google.android.gms.internal.ln;
import com.google.android.gms.internal.ls;
import com.google.android.gms.internal.lt;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.nb;
import com.google.android.gms.internal.nf;
import org.json.JSONObject;

@jw
public class zzb extends ln implements e {
    me f4654a;
    AdResponseParcel f4655b;
    hk f4656c;
    private final c f4657d;
    private final C1117a f4658e;
    private final Object f4659f;
    private final Context f4660g;
    private final bu f4661h;
    private AdRequestInfoParcel f4662i;
    private Runnable f4663j;

    @jw
    final class zza extends Exception {
        private final int zzKa;

        public zza(String str, int i) {
            super(str);
            this.zzKa = i;
        }

        public int getErrorCode() {
            return this.zzKa;
        }
    }

    public zzb(Context context, C1117a c1117a, bu buVar, c cVar) {
        this.f4659f = new Object();
        this.f4657d = cVar;
        this.f4660g = context;
        this.f4658e = c1117a;
        this.f4661h = buVar;
    }

    private void m6146a(int i, String str) {
        if (i == 3 || i == -1) {
            C1129c.m6190c(str);
        } else {
            C1129c.m6192d(str);
        }
        if (this.f4655b == null) {
            this.f4655b = new AdResponseParcel(i);
        } else {
            this.f4655b = new AdResponseParcel(i, this.f4655b.zzEL);
        }
        this.f4657d.zza(new lc(this.f4662i != null ? this.f4662i : new AdRequestInfoParcel(this.f4658e, null, -1), this.f4655b, this.f4656c, null, i, -1, this.f4655b.zzLT, null));
    }

    protected AdSizeParcel m6150a(AdRequestInfoParcel adRequestInfoParcel) {
        if (this.f4655b.zzLS == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.f4655b.zzLS.split(EtsyDialogFragment.OPT_X_BUTTON);
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.f4655b.zzLS);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (AdSizeParcel adSizeParcel : adRequestInfoParcel.zzsB.zzvu) {
                float f = this.f4660g.getResources().getDisplayMetrics().density;
                int i = adSizeParcel.width == -1 ? (int) (((float) adSizeParcel.widthPixels) / f) : adSizeParcel.width;
                int i2 = adSizeParcel.height == -2 ? (int) (((float) adSizeParcel.heightPixels) / f) : adSizeParcel.height;
                if (parseInt == i && parseInt2 == i2) {
                    return new AdSizeParcel(adSizeParcel, adRequestInfoParcel.zzsB.zzvu);
                }
            }
            str = "The ad size from the ad response was not one of the requested sizes: ";
            valueOf = String.valueOf(this.f4655b.zzLS);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (NumberFormatException e) {
            str = "Invalid ad size number from the ad response: ";
            valueOf = String.valueOf(this.f4655b.zzLS);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
    }

    me m6151a(VersionInfoParcel versionInfoParcel, nb<AdRequestInfoParcel> nbVar) {
        return C1119d.m6119a(this.f4660g, versionInfoParcel, nbVar, this);
    }

    protected void m6152a() {
        if (this.f4655b.errorCode != -3) {
            if (TextUtils.isEmpty(this.f4655b.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            C1101o.m6044h().m7013a(this.f4660g, this.f4655b.zzLq);
            if (this.f4655b.zzLP) {
                try {
                    this.f4656c = new hk(this.f4655b.body);
                    C1101o.m6044h().m7025b(this.f4656c.f5009g);
                } catch (Throwable e) {
                    C1129c.m6189b("Could not parse mediation config.", e);
                    String str = "Could not parse mediation config: ";
                    String valueOf = String.valueOf(this.f4655b.body);
                    throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
                }
            }
            C1101o.m6044h().m7025b(this.f4655b.zzEJ);
            if (!TextUtils.isEmpty(this.f4655b.zzLI) && ((Boolean) dz.bM.m6433c()).booleanValue()) {
                C1129c.m6185a("Received cookie from server. Setting webview cookie in CookieManager.");
                CookieManager b = C1101o.m6043g().m7161b(this.f4660g);
                if (b != null) {
                    b.setCookie("googleads.g.doubleclick.net", this.f4655b.zzLI);
                }
            }
        }
    }

    public void m6153a(@NonNull AdResponseParcel adResponseParcel) {
        C1129c.m6185a("Received ad response.");
        this.f4655b = adResponseParcel;
        long elapsedRealtime = C1101o.m6045i().elapsedRealtime();
        synchronized (this.f4659f) {
            this.f4654a = null;
        }
        C1101o.m6044h().m7024b(this.f4660g, this.f4655b.zzLH);
        try {
            if (this.f4655b.errorCode == -2 || this.f4655b.errorCode == -3) {
                JSONObject jSONObject;
                m6152a();
                AdSizeParcel a = this.f4662i.zzsB.zzvu != null ? m6150a(this.f4662i) : null;
                C1101o.m6044h().m7023a(this.f4655b.zzLZ);
                if (!TextUtils.isEmpty(this.f4655b.zzLX)) {
                    try {
                        jSONObject = new JSONObject(this.f4655b.zzLX);
                    } catch (Throwable e) {
                        C1129c.m6189b("Error parsing the JSON for Active View.", e);
                    }
                    this.f4657d.zza(new lc(this.f4662i, this.f4655b, this.f4656c, a, -2, elapsedRealtime, this.f4655b.zzLT, jSONObject));
                    lt.f5423a.removeCallbacks(this.f4663j);
                    return;
                }
                jSONObject = null;
                this.f4657d.zza(new lc(this.f4662i, this.f4655b, this.f4656c, a, -2, elapsedRealtime, this.f4655b.zzLT, jSONObject));
                lt.f5423a.removeCallbacks(this.f4663j);
                return;
            }
            throw new zza("There was a problem getting an ad response. ErrorCode: " + this.f4655b.errorCode, this.f4655b.errorCode);
        } catch (zza e2) {
            m6146a(e2.getErrorCode(), e2.getMessage());
            lt.f5423a.removeCallbacks(this.f4663j);
        }
    }

    public void onStop() {
        synchronized (this.f4659f) {
            if (this.f4654a != null) {
                this.f4654a.cancel();
            }
        }
    }

    public void zzbQ() {
        C1129c.m6185a("AdLoaderBackgroundTask started.");
        this.f4663j = new 1(this);
        lt.f5423a.postDelayed(this.f4663j, ((Long) dz.aJ.m6433c()).longValue());
        nf nfVar = new nf();
        long elapsedRealtime = C1101o.m6045i().elapsedRealtime();
        ls.m7070a(new 2(this, nfVar));
        this.f4662i = new AdRequestInfoParcel(this.f4658e, this.f4661h.a().a(this.f4660g), elapsedRealtime);
        nfVar.a(this.f4662i);
    }
}
