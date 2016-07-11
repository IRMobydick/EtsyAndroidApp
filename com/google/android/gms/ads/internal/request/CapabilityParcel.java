package com.google.android.gms.ads.internal.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.jw;

@jw
public class CapabilityParcel extends AbstractSafeParcelable {
    public static final Creator<CapabilityParcel> CREATOR;
    public final int versionCode;
    public final boolean zzMn;
    public final boolean zzMo;
    public final boolean zzMp;

    static {
        CREATOR = new zzj();
    }

    CapabilityParcel(int i, boolean z, boolean z2, boolean z3) {
        this.versionCode = i;
        this.zzMn = z;
        this.zzMo = z2;
        this.zzMp = z3;
    }

    public CapabilityParcel(boolean z, boolean z2, boolean z3) {
        this(2, z, z2, z3);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("iap_supported", this.zzMn);
        bundle.putBoolean("default_iap_supported", this.zzMo);
        bundle.putBoolean("app_streaming_supported", this.zzMp);
        return bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }
}
