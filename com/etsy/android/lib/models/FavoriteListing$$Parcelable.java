package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.h */
final class FavoriteListing$$Parcelable implements Creator<FavoriteListing$$Parcelable> {
    private FavoriteListing$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2771a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2772a(i);
    }

    public FavoriteListing$$Parcelable m2771a(Parcel parcel) {
        return new FavoriteListing$$Parcelable(parcel);
    }

    public FavoriteListing$$Parcelable[] m2772a(int i) {
        return new FavoriteListing$$Parcelable[i];
    }
}
