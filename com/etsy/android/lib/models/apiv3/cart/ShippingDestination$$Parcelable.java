package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.m */
final class ShippingDestination$$Parcelable implements Creator<ShippingDestination$$Parcelable> {
    private ShippingDestination$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2513a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2514a(i);
    }

    public ShippingDestination$$Parcelable m2513a(Parcel parcel) {
        return new ShippingDestination$$Parcelable(parcel);
    }

    public ShippingDestination$$Parcelable[] m2514a(int i) {
        return new ShippingDestination$$Parcelable[i];
    }
}
