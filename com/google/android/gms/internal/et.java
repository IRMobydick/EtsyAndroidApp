package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.ads.formats.b;
import com.google.android.gms.ads.formats.e;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdj.zza;
import java.util.ArrayList;
import java.util.List;

@jw
public class et extends e {
    private final zzdn f4891a;
    private final List<b> f4892b;
    private final ep f4893c;

    public et(zzdn com_google_android_gms_internal_zzdn) {
        ep epVar;
        this.f4892b = new ArrayList();
        this.f4891a = com_google_android_gms_internal_zzdn;
        try {
            List<Object> images = this.f4891a.getImages();
            if (images != null) {
                for (Object a : images) {
                    zzdj a2 = m6492a(a);
                    if (a2 != null) {
                        this.f4892b.add(new ep(a2));
                    }
                }
            }
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get image.", e);
        }
        try {
            zzdj zzeN = this.f4891a.zzeN();
            if (zzeN != null) {
                epVar = new ep(zzeN);
                this.f4893c = epVar;
            }
        } catch (Throwable e2) {
            C1129c.m6189b("Failed to get icon.", e2);
        }
        epVar = null;
        this.f4893c = epVar;
    }

    zzdj m6492a(Object obj) {
        return obj instanceof IBinder ? zza.zzy((IBinder) obj) : null;
    }

    protected /* synthetic */ Object m6493a() {
        return m6502j();
    }

    public CharSequence m6494b() {
        try {
            return this.f4891a.getHeadline();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get headline.", e);
            return null;
        }
    }

    public List<b> m6495c() {
        return this.f4892b;
    }

    public CharSequence m6496d() {
        try {
            return this.f4891a.getBody();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get body.", e);
            return null;
        }
    }

    public b m6497e() {
        return this.f4893c;
    }

    public CharSequence m6498f() {
        try {
            return this.f4891a.getCallToAction();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get call to action.", e);
            return null;
        }
    }

    public Double m6499g() {
        Double d = null;
        try {
            double starRating = this.f4891a.getStarRating();
            if (starRating != -1.0d) {
                d = Double.valueOf(starRating);
            }
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get star rating.", e);
        }
        return d;
    }

    public CharSequence m6500h() {
        try {
            return this.f4891a.getStore();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get store", e);
            return null;
        }
    }

    public CharSequence m6501i() {
        try {
            return this.f4891a.getPrice();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get price.", e);
            return null;
        }
    }

    protected zzd m6502j() {
        try {
            return this.f4891a.zzeO();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
