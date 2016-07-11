package com.etsy.android.lib.models.viewstate;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.viewstate.a */
final class CartViewState$$Parcelable implements Creator<CartViewState$$Parcelable> {
    private CartViewState$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2972a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2973a(i);
    }

    public CartViewState$$Parcelable m2972a(Parcel parcel) {
        return new CartViewState$$Parcelable(parcel);
    }

    public CartViewState$$Parcelable[] m2973a(int i) {
        return new CartViewState$$Parcelable[i];
    }
}
