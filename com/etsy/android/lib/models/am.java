package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Source$$Parcelable */
final class am implements Creator<Source$$Parcelable> {
    private am() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2354a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2355a(i);
    }

    public Source$$Parcelable m2354a(Parcel parcel) {
        return new Source$$Parcelable(parcel);
    }

    public Source$$Parcelable[] m2355a(int i) {
        return new Source$$Parcelable[i];
    }
}
