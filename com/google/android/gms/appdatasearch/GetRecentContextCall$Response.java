package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

public class GetRecentContextCall$Response extends AbstractSafeParcelable implements Result {
    public static final zzg CREATOR;
    final int mVersionCode;
    public Status zzZR;
    public List<UsageInfo> zzZS;
    @Deprecated
    public String[] zzZT;

    static {
        CREATOR = new zzg();
    }

    public GetRecentContextCall$Response() {
        this.mVersionCode = 1;
    }

    GetRecentContextCall$Response(int i, Status status, List<UsageInfo> list, String[] strArr) {
        this.mVersionCode = i;
        this.zzZR = status;
        this.zzZS = list;
        this.zzZT = strArr;
    }

    public Status getStatus() {
        return this.zzZR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg com_google_android_gms_appdatasearch_zzg = CREATOR;
        zzg.zza(this, parcel, i);
    }
}
