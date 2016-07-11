package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ShopTask$$Parcelable */
final class ak implements Creator<ShopTask$$Parcelable> {
    private ak() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2350a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2351a(i);
    }

    public ShopTask$$Parcelable m2350a(Parcel parcel) {
        return new ShopTask$$Parcelable(parcel);
    }

    public ShopTask$$Parcelable[] m2351a(int i) {
        return new ShopTask$$Parcelable[i];
    }
}
