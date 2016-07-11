package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: User$$Parcelable */
final class ar implements Creator<User$$Parcelable> {
    private ar() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2573a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2574a(i);
    }

    public User$$Parcelable m2573a(Parcel parcel) {
        return new User$$Parcelable(parcel);
    }

    public User$$Parcelable[] m2574a(int i) {
        return new User$$Parcelable[i];
    }
}
