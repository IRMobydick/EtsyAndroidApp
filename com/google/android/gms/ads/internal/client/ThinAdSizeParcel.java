package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.internal.jw;

@jw
public class ThinAdSizeParcel extends AdSizeParcel {
    public ThinAdSizeParcel(AdSizeParcel adSizeParcel) {
        super(adSizeParcel.versionCode, adSizeParcel.zzvs, adSizeParcel.height, adSizeParcel.heightPixels, adSizeParcel.zzvt, adSizeParcel.width, adSizeParcel.widthPixels, adSizeParcel.zzvu, adSizeParcel.zzvv, adSizeParcel.zzvw, adSizeParcel.zzvx);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, this.versionCode);
        zzb.zza(parcel, 2, this.zzvs, false);
        zzb.zzc(parcel, 3, this.height);
        zzb.zzc(parcel, 6, this.width);
        zzb.zzJ(parcel, zzar);
    }
}
