package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.d */
final class Country$$Parcelable implements Creator<Country$$Parcelable> {
    private Country$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2611a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2612a(i);
    }

    public Country$$Parcelable m2611a(Parcel parcel) {
        return new Country$$Parcelable(parcel);
    }

    public Country$$Parcelable[] m2612a(int i) {
        return new Country$$Parcelable[i];
    }
}
