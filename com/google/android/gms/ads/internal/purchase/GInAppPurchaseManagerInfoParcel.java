package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.zzhg;

@jw
public final class GInAppPurchaseManagerInfoParcel extends AbstractSafeParcelable {
    public static final zza CREATOR;
    public final int versionCode;
    public final zzhg zzJe;
    public final Context zzJf;
    public final d zzJg;
    public final C1115e zzsU;

    static {
        CREATOR = new zza();
    }

    GInAppPurchaseManagerInfoParcel(int i, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4) {
        this.versionCode = i;
        this.zzsU = (C1115e) zze.zzx(zza.zzbz(iBinder));
        this.zzJe = (zzhg) zze.zzx(zza.zzbz(iBinder2));
        this.zzJf = (Context) zze.zzx(zza.zzbz(iBinder3));
        this.zzJg = (d) zze.zzx(zza.zzbz(iBinder4));
    }

    public GInAppPurchaseManagerInfoParcel(Context context, C1115e c1115e, zzhg com_google_android_gms_internal_zzhg, d dVar) {
        this.versionCode = 2;
        this.zzJf = context;
        this.zzsU = c1115e;
        this.zzJe = com_google_android_gms_internal_zzhg;
        this.zzJg = dVar;
    }

    public static void zza(Intent intent, GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", gInAppPurchaseManagerInfoParcel);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }

    public static GInAppPurchaseManagerInfoParcel zzc(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(GInAppPurchaseManagerInfoParcel.class.getClassLoader());
            return (GInAppPurchaseManagerInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    IBinder zzhh() {
        return zze.zzD(this.zzJg).asBinder();
    }

    IBinder zzhi() {
        return zze.zzD(this.zzsU).asBinder();
    }

    IBinder zzhj() {
        return zze.zzD(this.zzJe).asBinder();
    }

    IBinder zzhk() {
        return zze.zzD(this.zzJf).asBinder();
    }
}
