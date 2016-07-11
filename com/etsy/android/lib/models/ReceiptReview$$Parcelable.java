package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.x */
final class ReceiptReview$$Parcelable implements Creator<ReceiptReview$$Parcelable> {
    private ReceiptReview$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2976a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2977a(i);
    }

    public ReceiptReview$$Parcelable m2976a(Parcel parcel) {
        return new ReceiptReview$$Parcelable(parcel);
    }

    public ReceiptReview$$Parcelable[] m2977a(int i) {
        return new ReceiptReview$$Parcelable[i];
    }
}
