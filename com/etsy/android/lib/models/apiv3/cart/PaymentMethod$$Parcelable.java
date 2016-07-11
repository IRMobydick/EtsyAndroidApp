package com.etsy.android.lib.models.apiv3.cart;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.cart.j */
final class PaymentMethod$$Parcelable implements Creator<PaymentMethod$$Parcelable> {
    private PaymentMethod$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2507a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2508a(i);
    }

    public PaymentMethod$$Parcelable m2507a(Parcel parcel) {
        return new PaymentMethod$$Parcelable(parcel);
    }

    public PaymentMethod$$Parcelable[] m2508a(int i) {
        return new PaymentMethod$$Parcelable[i];
    }
}
