package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.e */
final class CountryToRegionMap$$Parcelable implements Creator<CountryToRegionMap$$Parcelable> {
    private CountryToRegionMap$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2523a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2524a(i);
    }

    public CountryToRegionMap$$Parcelable m2523a(Parcel parcel) {
        return new CountryToRegionMap$$Parcelable(parcel);
    }

    public CountryToRegionMap$$Parcelable[] m2524a(int i) {
        return new CountryToRegionMap$$Parcelable[i];
    }
}
