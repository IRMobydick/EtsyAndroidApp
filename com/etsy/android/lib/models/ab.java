package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ReviewResponse$$Parcelable */
final class ab implements Creator<ReviewResponse$$Parcelable> {
    private ab() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2332a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2333a(i);
    }

    public ReviewResponse$$Parcelable m2332a(Parcel parcel) {
        return new ReviewResponse$$Parcelable(parcel);
    }

    public ReviewResponse$$Parcelable[] m2333a(int i) {
        return new ReviewResponse$$Parcelable[i];
    }
}
