package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Segment$$Parcelable */
final class ad implements Creator<Segment$$Parcelable> {
    private ad() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2336a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2337a(i);
    }

    public Segment$$Parcelable m2336a(Parcel parcel) {
        return new Segment$$Parcelable(parcel);
    }

    public Segment$$Parcelable[] m2337a(int i) {
        return new Segment$$Parcelable[i];
    }
}
