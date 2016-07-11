package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: UserAddress$$Parcelable */
final class as implements Creator<UserAddress$$Parcelable> {
    private as() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2575a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2576a(i);
    }

    public UserAddress$$Parcelable m2575a(Parcel parcel) {
        return new UserAddress$$Parcelable(parcel);
    }

    public UserAddress$$Parcelable[] m2576a(int i) {
        return new UserAddress$$Parcelable[i];
    }
}
