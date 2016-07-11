package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.z */
final class RevenueItem$$Parcelable implements Creator<RevenueItem$$Parcelable> {
    private RevenueItem$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2980a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2981a(i);
    }

    public RevenueItem$$Parcelable m2980a(Parcel parcel) {
        return new RevenueItem$$Parcelable(parcel);
    }

    public RevenueItem$$Parcelable[] m2981a(int i) {
        return new RevenueItem$$Parcelable[i];
    }
}
