package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.k */
final class PaymentOptions$$Parcelable implements Creator<PaymentOptions$$Parcelable> {
    private PaymentOptions$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2509a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2510a(i);
    }

    public PaymentOptions$$Parcelable m2509a(Parcel parcel) {
        return new PaymentOptions$$Parcelable(parcel);
    }

    public PaymentOptions$$Parcelable[] m2510a(int i) {
        return new PaymentOptions$$Parcelable[i];
    }
}
