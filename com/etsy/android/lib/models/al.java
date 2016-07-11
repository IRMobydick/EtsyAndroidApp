package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Snippet$$Parcelable */
final class al implements Creator<Snippet$$Parcelable> {
    private al() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2352a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2353a(i);
    }

    public Snippet$$Parcelable m2352a(Parcel parcel) {
        return new Snippet$$Parcelable(parcel);
    }

    public Snippet$$Parcelable[] m2353a(int i) {
        return new Snippet$$Parcelable[i];
    }
}
