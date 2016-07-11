package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Order$$Parcelable */
final class am implements Creator<Order$$Parcelable> {
    private am() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2413a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2414a(i);
    }

    public Order$$Parcelable m2413a(Parcel parcel) {
        return new Order$$Parcelable(parcel);
    }

    public Order$$Parcelable[] m2414a(int i) {
        return new Order$$Parcelable[i];
    }
}
