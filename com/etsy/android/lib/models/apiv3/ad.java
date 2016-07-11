package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Money$$Parcelable */
final class ad implements Creator<Money$$Parcelable> {
    private ad() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2395a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2396a(i);
    }

    public Money$$Parcelable m2395a(Parcel parcel) {
        return new Money$$Parcelable(parcel);
    }

    public Money$$Parcelable[] m2396a(int i) {
        return new Money$$Parcelable[i];
    }
}
