package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.ads.formats.b;
import com.google.android.gms.ads.formats.g;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdj.zza;
import java.util.ArrayList;
import java.util.List;

@jw
public class ev extends g {
    private final zzdp f4894a;
    private final List<b> f4895b;
    private final ep f4896c;

    public ev(zzdp com_google_android_gms_internal_zzdp) {
        ep epVar;
        this.f4895b = new ArrayList();
        this.f4894a = com_google_android_gms_internal_zzdp;
        try {
            List<Object> images = this.f4894a.getImages();
            if (images != null) {
                for (Object a : images) {
                    zzdj a2 = m6503a(a);
                    if (a2 != null) {
                        this.f4895b.add(new ep(a2));
                    }
                }
            }
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get image.", e);
        }
        try {
            zzdj zzeR = this.f4894a.zzeR();
            if (zzeR != null) {
                epVar = new ep(zzeR);
                this.f4896c = epVar;
            }
        } catch (Throwable e2) {
            C1129c.m6189b("Failed to get icon.", e2);
        }
        epVar = null;
        this.f4896c = epVar;
    }

    zzdj m6503a(Object obj) {
        return obj instanceof IBinder ? zza.zzy((IBinder) obj) : null;
    }

    protected /* synthetic */ Object m6504a() {
        return m6511h();
    }

    public CharSequence m6505b() {
        try {
            return this.f4894a.getHeadline();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get headline.", e);
            return null;
        }
    }

    public List<b> m6506c() {
        return this.f4895b;
    }

    public CharSequence m6507d() {
        try {
            return this.f4894a.getBody();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get body.", e);
            return null;
        }
    }

    public b m6508e() {
        return this.f4896c;
    }

    public CharSequence m6509f() {
        try {
            return this.f4894a.getCallToAction();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get call to action.", e);
            return null;
        }
    }

    public CharSequence m6510g() {
        try {
            return this.f4894a.getAdvertiser();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to get attribution.", e);
            return null;
        }
    }

    protected zzd m6511h() {
        try {
            return this.f4894a.zzeO();
        } catch (Throwable e) {
            C1129c.m6189b("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
