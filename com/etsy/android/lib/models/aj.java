package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ShopSection$$Parcelable */
final class aj implements Creator<ShopSection$$Parcelable> {
    private aj() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2348a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2349a(i);
    }

    public ShopSection$$Parcelable m2348a(Parcel parcel) {
        return new ShopSection$$Parcelable(parcel);
    }

    public ShopSection$$Parcelable[] m2349a(int i) {
        return new ShopSection$$Parcelable[i];
    }
}
