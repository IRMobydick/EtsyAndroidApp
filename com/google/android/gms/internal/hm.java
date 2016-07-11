package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.c;
import com.google.android.gms.ads.formats.d;
import com.google.android.gms.ads.internal.C1101o;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.C1085m;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.mediation.b;
import com.google.android.gms.dynamic.zze;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@jw
public class hm implements ho {
    private final String f5019a;
    private final zzga f5020b;
    private final long f5021c;
    private final hk f5022d;
    private final hj f5023e;
    private AdRequestParcel f5024f;
    private final AdSizeParcel f5025g;
    private final Context f5026h;
    private final Object f5027i;
    private final VersionInfoParcel f5028j;
    private final boolean f5029k;
    private final NativeAdOptionsParcel f5030l;
    private final List<String> f5031m;
    private final boolean f5032n;
    private zzgb f5033o;
    private int f5034p;
    private zzgd f5035q;

    public hm(Context context, String str, zzga com_google_android_gms_internal_zzga, hk hkVar, hj hjVar, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, VersionInfoParcel versionInfoParcel, boolean z, boolean z2, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) {
        this.f5027i = new Object();
        this.f5034p = -2;
        this.f5026h = context;
        this.f5020b = com_google_android_gms_internal_zzga;
        this.f5023e = hjVar;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.f5019a = m6669b();
        } else {
            this.f5019a = str;
        }
        this.f5022d = hkVar;
        this.f5021c = hkVar.f5004b != -1 ? hkVar.f5004b : 10000;
        this.f5024f = adRequestParcel;
        this.f5025g = adSizeParcel;
        this.f5028j = versionInfoParcel;
        this.f5029k = z;
        this.f5032n = z2;
        this.f5030l = nativeAdOptionsParcel;
        this.f5031m = list;
    }

    private long m6660a(long j, long j2, long j3, long j4) {
        while (this.f5034p == -2) {
            m6670b(j, j2, j3, j4);
        }
        return C1101o.m6045i().elapsedRealtime() - j;
    }

    private String m6663a(String str) {
        if (!(str == null || !m6678e() || m6671b(2))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                str = jSONObject.toString();
            } catch (JSONException e) {
                C1129c.m6192d("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private void m6665a(zzft com_google_android_gms_internal_zzft) {
        if ("com.google.ads.mediation.AdUrlAdapter".equals(this.f5019a)) {
            if (this.f5024f.zzuX == null) {
                this.f5024f = new C1085m(this.f5024f).m5926a(new Bundle()).m5924a();
            }
            Bundle bundle = this.f5024f.zzuX.getBundle(this.f5019a);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("sdk_less_network_id", this.f5023e.f4988b);
            this.f5024f.zzuX.putBundle(this.f5019a, bundle);
        }
        String a = m6663a(this.f5023e.f4995i);
        try {
            if (this.f5028j.zzRD < 4100000) {
                if (this.f5025g.zzvt) {
                    this.f5033o.zza(zze.zzD(this.f5026h), this.f5024f, a, com_google_android_gms_internal_zzft);
                } else {
                    this.f5033o.zza(zze.zzD(this.f5026h), this.f5025g, this.f5024f, a, com_google_android_gms_internal_zzft);
                }
            } else if (this.f5029k) {
                this.f5033o.zza(zze.zzD(this.f5026h), this.f5024f, a, this.f5023e.f4987a, com_google_android_gms_internal_zzft, this.f5030l, this.f5031m);
            } else if (this.f5025g.zzvt) {
                this.f5033o.zza(zze.zzD(this.f5026h), this.f5024f, a, this.f5023e.f4987a, com_google_android_gms_internal_zzft);
            } else if (!this.f5032n) {
                this.f5033o.zza(zze.zzD(this.f5026h), this.f5025g, this.f5024f, a, this.f5023e.f4987a, com_google_android_gms_internal_zzft);
            } else if (this.f5023e.f4998l != null) {
                this.f5033o.zza(zze.zzD(this.f5026h), this.f5024f, a, this.f5023e.f4987a, com_google_android_gms_internal_zzft, new NativeAdOptionsParcel(m6668b(this.f5023e.f5002p)), this.f5023e.f5001o);
            } else {
                this.f5033o.zza(zze.zzD(this.f5026h), this.f5025g, this.f5024f, a, this.f5023e.f4987a, com_google_android_gms_internal_zzft);
            }
        } catch (Throwable e) {
            C1129c.m6193d("Could not request ad from mediation adapter.", e);
            m6685a(5);
        }
    }

    private static c m6668b(String str) {
        d dVar = new d();
        if (str == null) {
            return dVar.a();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            dVar.b(jSONObject.optBoolean("multiple_images", false));
            dVar.a(jSONObject.optBoolean("only_urls", false));
            dVar.a(m6672c(jSONObject.optString("native_image_orientation", "any")));
        } catch (Throwable e) {
            C1129c.m6193d("Exception occurred when creating native ad options", e);
        }
        return dVar.a();
    }

    private String m6669b() {
        try {
            if (!TextUtils.isEmpty(this.f5023e.f4991e)) {
                return this.f5020b.zzam(this.f5023e.f4991e) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            C1129c.m6192d("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private void m6670b(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        elapsedRealtime = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || elapsedRealtime <= 0) {
            C1129c.m6190c("Timed out waiting for adapter.");
            this.f5034p = 3;
            return;
        }
        try {
            this.f5027i.wait(Math.min(j5, elapsedRealtime));
        } catch (InterruptedException e) {
            this.f5034p = -1;
        }
    }

    private boolean m6671b(int i) {
        try {
            Bundle zzfL = this.f5029k ? this.f5033o.zzfL() : this.f5025g.zzvt ? this.f5033o.getInterstitialAdapterInfo() : this.f5033o.zzfK();
            if (zzfL == null) {
                return false;
            }
            return (zzfL.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException e) {
            C1129c.m6192d("Could not get adapter info. Returning false");
            return false;
        }
    }

    private static int m6672c(String str) {
        return "landscape".equals(str) ? 2 : "portrait".equals(str) ? 1 : 0;
    }

    private zzgd m6674c() {
        if (this.f5034p != 0 || !m6678e()) {
            return null;
        }
        try {
            if (!(!m6671b(4) || this.f5035q == null || this.f5035q.zzfG() == 0)) {
                return this.f5035q;
            }
        } catch (RemoteException e) {
            C1129c.m6192d("Could not get cpm value from MediationResponseMetadata");
        }
        return m6675c(m6680f());
    }

    private static zzgd m6675c(int i) {
        return new 2(i);
    }

    private zzgb m6676d() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.f5019a);
        C1129c.m6190c(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!this.f5029k) {
            if (((Boolean) dz.aH.m6433c()).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.f5019a)) {
                return m6683a(new AdMobAdapter());
            }
            if (((Boolean) dz.aI.m6433c()).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.f5019a)) {
                return m6683a(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.f5019a)) {
                return new zzgh(new ie());
            }
        }
        try {
            return this.f5020b.zzal(this.f5019a);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Could not instantiate mediation adapter: ";
            valueOf = String.valueOf(this.f5019a);
            C1129c.m6186a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    private boolean m6678e() {
        return this.f5022d.f5014l != -1;
    }

    private int m6680f() {
        if (this.f5023e.f4995i == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f5023e.f4995i);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.f5019a)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = m6671b(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException e) {
            C1129c.m6192d("Could not convert to json. Returning 0");
            return 0;
        }
    }

    public hn m6682a(long j, long j2) {
        hn hnVar;
        synchronized (this.f5027i) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzft com_google_android_gms_internal_zzft = new zzft();
            lt.f5423a.post(new 1(this, com_google_android_gms_internal_zzft));
            zzft com_google_android_gms_internal_zzft2 = com_google_android_gms_internal_zzft;
            hnVar = new hn(this.f5023e, this.f5033o, this.f5019a, com_google_android_gms_internal_zzft2, this.f5034p, m6674c(), m6660a(elapsedRealtime, this.f5021c, j, j2));
        }
        return hnVar;
    }

    protected zzgb m6683a(b bVar) {
        return new zzgh(bVar);
    }

    public void m6684a() {
        synchronized (this.f5027i) {
            try {
                if (this.f5033o != null) {
                    this.f5033o.destroy();
                }
            } catch (Throwable e) {
                C1129c.m6193d("Could not destroy mediation adapter.", e);
            }
            this.f5034p = -1;
            this.f5027i.notify();
        }
    }

    public void m6685a(int i) {
        synchronized (this.f5027i) {
            this.f5034p = i;
            this.f5027i.notify();
        }
    }

    public void m6686a(int i, zzgd com_google_android_gms_internal_zzgd) {
        synchronized (this.f5027i) {
            this.f5034p = i;
            this.f5035q = com_google_android_gms_internal_zzgd;
            this.f5027i.notify();
        }
    }
}
