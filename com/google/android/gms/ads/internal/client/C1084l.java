package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzga;

@jw
/* renamed from: com.google.android.gms.ads.internal.client.l */
public class C1084l extends zzg<zzv> {
    public C1084l() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public zzu m5922a(Context context, AdSizeParcel adSizeParcel, String str, zzga com_google_android_gms_internal_zzga, int i) {
        Throwable e;
        try {
            return zza.zzn(((zzv) zzaI(context)).zza(zze.zzD(context), adSizeParcel, str, com_google_android_gms_internal_zzga, 9080000, i));
        } catch (RemoteException e2) {
            e = e2;
            C1129c.m6186a("Could not create remote AdManager.", e);
            return null;
        } catch (zzg.zza e3) {
            e = e3;
            C1129c.m6186a("Could not create remote AdManager.", e);
            return null;
        }
    }

    protected zzv m5923a(IBinder iBinder) {
        return zzv.zza.zzo(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return m5923a(iBinder);
    }
}
