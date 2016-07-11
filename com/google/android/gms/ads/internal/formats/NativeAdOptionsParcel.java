package com.google.android.gms.ads.internal.formats;

import android.os.Parcel;
import com.google.android.gms.ads.formats.c;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.jw;

@jw
public class NativeAdOptionsParcel extends AbstractSafeParcelable {
    public static final zzj CREATOR;
    public final int versionCode;
    public final boolean zzBl;
    public final int zzBm;
    public final boolean zzBn;

    static {
        CREATOR = new zzj();
    }

    public NativeAdOptionsParcel(int i, boolean z, int i2, boolean z2) {
        this.versionCode = i;
        this.zzBl = z;
        this.zzBm = i2;
        this.zzBn = z2;
    }

    public NativeAdOptionsParcel(c cVar) {
        this(1, cVar.a(), cVar.b(), cVar.c());
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }
}
