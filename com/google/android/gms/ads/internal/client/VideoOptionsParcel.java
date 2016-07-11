package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.ads.C1132l;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.jw;

@jw
public class VideoOptionsParcel extends AbstractSafeParcelable {
    public static final zzaq CREATOR;
    public final int versionCode;
    public final boolean zzwN;

    static {
        CREATOR = new zzaq();
    }

    public VideoOptionsParcel(int i, boolean z) {
        this.versionCode = i;
        this.zzwN = z;
    }

    public VideoOptionsParcel(C1132l c1132l) {
        this(1, c1132l.m6197a());
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaq.zza(this, parcel, i);
    }
}
