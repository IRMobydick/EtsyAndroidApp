package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.jw;

@jw
public final class VersionInfoParcel extends AbstractSafeParcelable {
    public static final zzd CREATOR;
    public String afmaVersion;
    public final int versionCode;
    public int zzRC;
    public int zzRD;
    public boolean zzRE;

    static {
        CREATOR = new zzd();
    }

    public VersionInfoParcel(int i, int i2, boolean z) {
        String valueOf = String.valueOf("afma-sdk-a-v");
        String str = z ? "0" : "1";
        this(1, new StringBuilder((String.valueOf(valueOf).length() + 24) + String.valueOf(str).length()).append(valueOf).append(i).append(".").append(i2).append(".").append(str).toString(), i, i2, z);
    }

    VersionInfoParcel(int i, String str, int i2, int i3, boolean z) {
        this.versionCode = i;
        this.afmaVersion = str;
        this.zzRC = i2;
        this.zzRD = i3;
        this.zzRE = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
