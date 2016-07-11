package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: StatsRevenues$$Parcelable */
final class an implements Creator<StatsRevenues$$Parcelable> {
    private an() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2356a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2357a(i);
    }

    public StatsRevenues$$Parcelable m2356a(Parcel parcel) {
        return new StatsRevenues$$Parcelable(parcel);
    }

    public StatsRevenues$$Parcelable[] m2357a(int i) {
        return new StatsRevenues$$Parcelable[i];
    }
}
