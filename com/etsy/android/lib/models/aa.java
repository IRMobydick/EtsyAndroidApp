package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Review$$Parcelable */
final class aa implements Creator<Review$$Parcelable> {
    private aa() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2330a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2331a(i);
    }

    public Review$$Parcelable m2330a(Parcel parcel) {
        return new Review$$Parcelable(parcel);
    }

    public Review$$Parcelable[] m2331a(int i) {
        return new Review$$Parcelable[i];
    }
}
