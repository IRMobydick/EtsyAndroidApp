package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzga;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.k */
public final class C1083k extends zzg<zzt> {
    public C1083k() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public zzs m5920a(Context context, String str, zzga com_google_android_gms_internal_zzga) {
        try {
            return zza.zzl(((zzt) zzaI(context)).zza(zze.zzD(context), str, com_google_android_gms_internal_zzga, 9080000));
        } catch (Throwable e) {
            C1129c.m6193d("Could not create remote builder for AdLoader.", e);
            return null;
        } catch (zzg.zza e2) {
            C1129c.m6193d("Could not create remote builder for AdLoader.", e2);
            return null;
        }
    }

    protected zzt m5921a(IBinder iBinder) {
        return zzt.zza.zzm(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return m5921a(iBinder);
    }
}
