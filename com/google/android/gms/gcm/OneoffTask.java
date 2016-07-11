package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR;
    private final long zzaTg;
    private final long zzaTh;

    static {
        CREATOR = new 1();
    }

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzaTg = parcel.readLong();
        this.zzaTh = parcel.readLong();
    }

    private OneoffTask(b bVar) {
        super((d) bVar);
        this.zzaTg = b.a(bVar);
        this.zzaTh = b.b(bVar);
    }

    public long getWindowEnd() {
        return this.zzaTh;
    }

    public long getWindowStart() {
        return this.zzaTg;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzaTg);
        bundle.putLong("window_end", this.zzaTh);
    }

    public String toString() {
        String valueOf = String.valueOf(super.toString());
        long windowStart = getWindowStart();
        return new StringBuilder(String.valueOf(valueOf).length() + 64).append(valueOf).append(" windowStart=").append(windowStart).append(" windowEnd=").append(getWindowEnd()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.zzaTg);
        parcel.writeLong(this.zzaTh);
    }
}
