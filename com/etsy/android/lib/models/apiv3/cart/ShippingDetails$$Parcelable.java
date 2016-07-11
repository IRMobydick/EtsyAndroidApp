package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.n */
final class ShippingDetails$$Parcelable implements Creator<ShippingDetails$$Parcelable> {
    private ShippingDetails$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2515a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2516a(i);
    }

    public ShippingDetails$$Parcelable m2515a(Parcel parcel) {
        return new ShippingDetails$$Parcelable(parcel);
    }

    public ShippingDetails$$Parcelable[] m2516a(int i) {
        return new ShippingDetails$$Parcelable[i];
    }
}
