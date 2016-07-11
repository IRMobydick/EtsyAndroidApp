package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzgz.zza;

@jw
public final class io extends zzg<zzha> {
    public io() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public zzgz m6795a(Activity activity) {
        try {
            return zza.zzQ(((zzha) zzaI(activity)).zzm(zze.zzD(activity)));
        } catch (Throwable e) {
            C1129c.m6193d("Could not create remote AdOverlay.", e);
            return null;
        } catch (zzg.zza e2) {
            C1129c.m6193d("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    protected zzha m6796a(IBinder iBinder) {
        return zzha.zza.zzR(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return m6796a(iBinder);
    }
}
