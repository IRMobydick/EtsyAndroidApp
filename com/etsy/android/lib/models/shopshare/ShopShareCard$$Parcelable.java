package com.etsy.android.lib.models.shopshare;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.shopshare.b */
final class ShopShareCard$$Parcelable implements Creator<ShopShareCard$$Parcelable> {
    private ShopShareCard$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2958a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2959a(i);
    }

    public ShopShareCard$$Parcelable m2958a(Parcel parcel) {
        return new ShopShareCard$$Parcelable(parcel);
    }

    public ShopShareCard$$Parcelable[] m2959a(int i) {
        return new ShopShareCard$$Parcelable[i];
    }
}
