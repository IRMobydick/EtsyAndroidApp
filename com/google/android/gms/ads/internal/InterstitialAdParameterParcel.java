package com.google.android.gms.ads.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.jw;

@jw
public final class InterstitialAdParameterParcel extends AbstractSafeParcelable {
    public static final zzm CREATOR;
    public final int versionCode;
    public final boolean zzrf;
    public final boolean zzrg;
    public final String zzrh;
    public final boolean zzri;
    public final float zzrj;
    public final int zzrk;

    static {
        CREATOR = new zzm();
    }

    InterstitialAdParameterParcel(int i, boolean z, boolean z2, String str, boolean z3, float f, int i2) {
        this.versionCode = i;
        this.zzrf = z;
        this.zzrg = z2;
        this.zzrh = str;
        this.zzri = z3;
        this.zzrj = f;
        this.zzrk = i2;
    }

    public InterstitialAdParameterParcel(boolean z, boolean z2, boolean z3, float f, int i) {
        this(3, z, z2, null, z3, f, i);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
