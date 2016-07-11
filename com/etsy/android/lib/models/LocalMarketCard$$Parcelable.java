package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.r */
final class LocalMarketCard$$Parcelable implements Creator<LocalMarketCard$$Parcelable> {
    private LocalMarketCard$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2810a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2811a(i);
    }

    public LocalMarketCard$$Parcelable m2810a(Parcel parcel) {
        return new LocalMarketCard$$Parcelable(parcel);
    }

    public LocalMarketCard$$Parcelable[] m2811a(int i) {
        return new LocalMarketCard$$Parcelable[i];
    }
}
