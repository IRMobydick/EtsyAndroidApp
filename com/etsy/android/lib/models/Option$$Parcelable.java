package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.v */
final class Option$$Parcelable implements Creator<Option$$Parcelable> {
    private Option$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2968a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2969a(i);
    }

    public Option$$Parcelable m2968a(Parcel parcel) {
        return new Option$$Parcelable(parcel);
    }

    public Option$$Parcelable[] m2969a(int i) {
        return new Option$$Parcelable[i];
    }
}
