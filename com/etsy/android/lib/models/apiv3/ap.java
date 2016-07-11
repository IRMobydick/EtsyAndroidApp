package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: ReceiptShipping$$Parcelable */
final class ap implements Creator<ReceiptShipping$$Parcelable> {
    private ap() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2419a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2420a(i);
    }

    public ReceiptShipping$$Parcelable m2419a(Parcel parcel) {
        return new ReceiptShipping$$Parcelable(parcel);
    }

    public ReceiptShipping$$Parcelable[] m2420a(int i) {
        return new ReceiptShipping$$Parcelable[i];
    }
}
