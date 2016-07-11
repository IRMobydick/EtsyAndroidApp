package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.o */
final class ShippingOption$$Parcelable implements Creator<ShippingOption$$Parcelable> {
    private ShippingOption$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2517a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2518a(i);
    }

    public ShippingOption$$Parcelable m2517a(Parcel parcel) {
        return new ShippingOption$$Parcelable(parcel);
    }

    public ShippingOption$$Parcelable[] m2518a(int i) {
        return new ShippingOption$$Parcelable[i];
    }
}
