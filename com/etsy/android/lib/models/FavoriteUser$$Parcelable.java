package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.i */
final class FavoriteUser$$Parcelable implements Creator<FavoriteUser$$Parcelable> {
    private FavoriteUser$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2788a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2789a(i);
    }

    public FavoriteUser$$Parcelable m2788a(Parcel parcel) {
        return new FavoriteUser$$Parcelable(parcel);
    }

    public FavoriteUser$$Parcelable[] m2789a(int i) {
        return new FavoriteUser$$Parcelable[i];
    }
}
