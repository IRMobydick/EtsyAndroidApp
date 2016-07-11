package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.a */
final class CartGroup$$Parcelable implements Creator<CartGroup$$Parcelable> {
    private CartGroup$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2489a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2490a(i);
    }

    public CartGroup$$Parcelable m2489a(Parcel parcel) {
        return new CartGroup$$Parcelable(parcel);
    }

    public CartGroup$$Parcelable[] m2490a(int i) {
        return new CartGroup$$Parcelable[i];
    }
}
