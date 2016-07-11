package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.d;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@jw
public class dc implements dd {
    private final Object f4746a;
    private final WeakHashMap<lb, zzbv> f4747b;
    private final ArrayList<zzbv> f4748c;
    private final Context f4749d;
    private final VersionInfoParcel f4750e;
    private final gx f4751f;

    public dc(Context context, VersionInfoParcel versionInfoParcel, gx gxVar) {
        this.f4746a = new Object();
        this.f4747b = new WeakHashMap();
        this.f4748c = new ArrayList();
        this.f4749d = context.getApplicationContext();
        this.f4750e = versionInfoParcel;
        this.f4751f = gxVar;
    }

    public zzbv m6350a(AdSizeParcel adSizeParcel, lb lbVar) {
        return m6351a(adSizeParcel, lbVar, lbVar.f5327b.m7247b());
    }

    public zzbv m6351a(AdSizeParcel adSizeParcel, lb lbVar, View view) {
        return m6354a(adSizeParcel, lbVar, new cz(view, lbVar), null);
    }

    public zzbv m6352a(AdSizeParcel adSizeParcel, lb lbVar, View view, hd hdVar) {
        return m6354a(adSizeParcel, lbVar, new cz(view, lbVar), hdVar);
    }

    public zzbv m6353a(AdSizeParcel adSizeParcel, lb lbVar, d dVar) {
        return m6354a(adSizeParcel, lbVar, new cw(dVar), null);
    }

    public zzbv m6354a(AdSizeParcel adSizeParcel, lb lbVar, df dfVar, hd hdVar) {
        zzbv com_google_android_gms_internal_zzbv;
        synchronized (this.f4746a) {
            if (m6356a(lbVar)) {
                com_google_android_gms_internal_zzbv = (zzbv) this.f4747b.get(lbVar);
            } else {
                if (hdVar != null) {
                    com_google_android_gms_internal_zzbv = new zzca(this.f4749d, adSizeParcel, lbVar, this.f4750e, dfVar, hdVar);
                } else {
                    com_google_android_gms_internal_zzbv = new zzcb(this.f4749d, adSizeParcel, lbVar, this.f4750e, dfVar, this.f4751f);
                }
                com_google_android_gms_internal_zzbv.zza((dd) this);
                this.f4747b.put(lbVar, com_google_android_gms_internal_zzbv);
                this.f4748c.add(com_google_android_gms_internal_zzbv);
            }
        }
        return com_google_android_gms_internal_zzbv;
    }

    public void m6355a(zzbv com_google_android_gms_internal_zzbv) {
        synchronized (this.f4746a) {
            if (!com_google_android_gms_internal_zzbv.zzcU()) {
                this.f4748c.remove(com_google_android_gms_internal_zzbv);
                Iterator it = this.f4747b.entrySet().iterator();
                while (it.hasNext()) {
                    if (((Entry) it.next()).getValue() == com_google_android_gms_internal_zzbv) {
                        it.remove();
                    }
                }
            }
        }
    }

    public boolean m6356a(lb lbVar) {
        boolean z;
        synchronized (this.f4746a) {
            zzbv com_google_android_gms_internal_zzbv = (zzbv) this.f4747b.get(lbVar);
            z = com_google_android_gms_internal_zzbv != null && com_google_android_gms_internal_zzbv.zzcU();
        }
        return z;
    }

    public void m6357b(lb lbVar) {
        synchronized (this.f4746a) {
            zzbv com_google_android_gms_internal_zzbv = (zzbv) this.f4747b.get(lbVar);
            if (com_google_android_gms_internal_zzbv != null) {
                com_google_android_gms_internal_zzbv.zzcS();
            }
        }
    }

    public void m6358c(lb lbVar) {
        synchronized (this.f4746a) {
            zzbv com_google_android_gms_internal_zzbv = (zzbv) this.f4747b.get(lbVar);
            if (com_google_android_gms_internal_zzbv != null) {
                com_google_android_gms_internal_zzbv.stop();
            }
        }
    }

    public void m6359d(lb lbVar) {
        synchronized (this.f4746a) {
            zzbv com_google_android_gms_internal_zzbv = (zzbv) this.f4747b.get(lbVar);
            if (com_google_android_gms_internal_zzbv != null) {
                com_google_android_gms_internal_zzbv.pause();
            }
        }
    }

    public void m6360e(lb lbVar) {
        synchronized (this.f4746a) {
            zzbv com_google_android_gms_internal_zzbv = (zzbv) this.f4747b.get(lbVar);
            if (com_google_android_gms_internal_zzbv != null) {
                com_google_android_gms_internal_zzbv.resume();
            }
        }
    }
}
