package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.c */
final class CartGroupItem$$Parcelable implements Creator<CartGroupItem$$Parcelable> {
    private CartGroupItem$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2493a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2494a(i);
    }

    public CartGroupItem$$Parcelable m2493a(Parcel parcel) {
        return new CartGroupItem$$Parcelable(parcel);
    }

    public CartGroupItem$$Parcelable[] m2494a(int i) {
        return new CartGroupItem$$Parcelable[i];
    }
}
