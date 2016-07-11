package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.C1067h;
import com.google.ads.mediation.C1073n;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.ads.mediation.b;
import com.google.android.gms.ads.mediation.customevent.C1133g;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.a;
import com.google.android.gms.internal.zzga.zza;
import java.util.Map;

@jw
public final class zzfz extends zza {
    private Map<Class<? extends Object>, Object> zzFz;

    private <NETWORK_EXTRAS extends C1073n, SERVER_PARAMETERS extends MediationServerParameters> zzgb zzan(String str) {
        try {
            Class cls = Class.forName(str, false, zzfz.class.getClassLoader());
            if (C1067h.class.isAssignableFrom(cls)) {
                C1067h c1067h = (C1067h) cls.newInstance();
                return new zzgm(c1067h, (C1073n) this.zzFz.get(c1067h.m5836b()));
            } else if (b.class.isAssignableFrom(cls)) {
                return new zzgh((b) cls.newInstance());
            } else {
                C1129c.m6192d(new StringBuilder(String.valueOf(str).length() + 64).append("Could not instantiate mediation adapter: ").append(str).append(" (not a valid adapter).").toString());
                throw new RemoteException();
            }
        } catch (Throwable th) {
            return zzao(str);
        }
    }

    private zzgb zzao(String str) {
        try {
            C1129c.m6185a("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzgh(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzgh(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzgh(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzgm(customEventAdapter, (C1133g) this.zzFz.get(customEventAdapter.b()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            C1129c.m6193d(new StringBuilder(String.valueOf(str).length() + 43).append("Could not instantiate mediation adapter: ").append(str).append(". ").toString(), th);
        }
    }

    public zzgb zzal(String str) {
        return zzan(str);
    }

    public boolean zzam(String str) {
        boolean z = false;
        try {
            z = a.class.isAssignableFrom(Class.forName(str, false, zzfz.class.getClassLoader()));
        } catch (Throwable th) {
            C1129c.m6192d(new StringBuilder(String.valueOf(str).length() + 80).append("Could not load custom event implementation class: ").append(str).append(", assuming old implementation.").toString());
        }
        return z;
    }

    public void zzh(Map<Class<? extends Object>, Object> map) {
        this.zzFz = map;
    }
}
