package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.o */
final class LocalMarket$$Parcelable implements Creator<LocalMarket$$Parcelable> {
    private LocalMarket$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2800a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2801a(i);
    }

    public LocalMarket$$Parcelable m2800a(Parcel parcel) {
        return new LocalMarket$$Parcelable(parcel);
    }

    public LocalMarket$$Parcelable[] m2801a(int i) {
        return new LocalMarket$$Parcelable[i];
    }
}
