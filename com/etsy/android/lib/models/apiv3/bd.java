package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ShopV3$$Parcelable */
final class bd implements Creator<ShopV3$$Parcelable> {
    private bd() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2449a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2450a(i);
    }

    public ShopV3$$Parcelable m2449a(Parcel parcel) {
        return new ShopV3$$Parcelable(parcel);
    }

    public ShopV3$$Parcelable[] m2450a(int i) {
        return new ShopV3$$Parcelable[i];
    }
}
