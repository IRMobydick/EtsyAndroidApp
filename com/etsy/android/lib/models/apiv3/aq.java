package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: RefundReason$$Parcelable */
final class aq implements Creator<RefundReason$$Parcelable> {
    private aq() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2421a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2422a(i);
    }

    public RefundReason$$Parcelable m2421a(Parcel parcel) {
        return new RefundReason$$Parcelable(parcel);
    }

    public RefundReason$$Parcelable[] m2422a(int i) {
        return new RefundReason$$Parcelable[i];
    }
}
