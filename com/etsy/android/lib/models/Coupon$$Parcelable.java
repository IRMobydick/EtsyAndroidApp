package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.e */
final class Coupon$$Parcelable implements Creator<Coupon$$Parcelable> {
    private Coupon$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2621a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2622a(i);
    }

    public Coupon$$Parcelable m2621a(Parcel parcel) {
        return new Coupon$$Parcelable(parcel);
    }

    public Coupon$$Parcelable[] m2622a(int i) {
        return new Coupon$$Parcelable[i];
    }
}
