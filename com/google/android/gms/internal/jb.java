package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzhi.zza;

@jw
public final class jb extends zzg<zzhj> {
    public jb() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    public zzhi m6803a(Activity activity) {
        try {
            return zza.zzV(((zzhj) zzaI(activity)).zzn(zze.zzD(activity)));
        } catch (Throwable e) {
            C1129c.m6193d("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (zzg.zza e2) {
            C1129c.m6193d("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }

    protected zzhj m6804a(IBinder iBinder) {
        return zzhj.zza.zzW(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return m6804a(iBinder);
    }
}
