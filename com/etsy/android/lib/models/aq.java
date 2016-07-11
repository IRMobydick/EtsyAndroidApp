package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Treasury$$Parcelable */
final class aq implements Creator<Treasury$$Parcelable> {
    private aq() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2571a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2572a(i);
    }

    public Treasury$$Parcelable m2571a(Parcel parcel) {
        return new Treasury$$Parcelable(parcel);
    }

    public Treasury$$Parcelable[] m2572a(int i) {
        return new Treasury$$Parcelable[i];
    }
}
