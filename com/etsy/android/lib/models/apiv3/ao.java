package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Rating$$Parcelable */
final class ao implements Creator<Rating$$Parcelable> {
    private ao() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2417a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2418a(i);
    }

    public Rating$$Parcelable m2417a(Parcel parcel) {
        return new Rating$$Parcelable(parcel);
    }

    public Rating$$Parcelable[] m2418a(int i) {
        return new Rating$$Parcelable[i];
    }
}
