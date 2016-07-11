package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzdl.zza;

@jw
public class fc extends zzg<zzdm> {
    public fc() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    public zzdl m6512a(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        Throwable e;
        try {
            return zza.zzz(((zzdm) zzaI(context)).zza(zze.zzD(context), zze.zzD(frameLayout), zze.zzD(frameLayout2), 9080000));
        } catch (RemoteException e2) {
            e = e2;
            C1129c.m6193d("Could not create remote NativeAdViewDelegate.", e);
            return null;
        } catch (zzg.zza e3) {
            e = e3;
            C1129c.m6193d("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }

    protected zzdm m6513a(IBinder iBinder) {
        return zzdm.zza.zzA(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return m6513a(iBinder);
    }
}
