package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Transaction$$Parcelable */
final class ap implements Creator<Transaction$$Parcelable> {
    private ap() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2360a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2361a(i);
    }

    public Transaction$$Parcelable m2360a(Parcel parcel) {
        return new Transaction$$Parcelable(parcel);
    }

    public Transaction$$Parcelable[] m2361a(int i) {
        return new Transaction$$Parcelable[i];
    }
}
