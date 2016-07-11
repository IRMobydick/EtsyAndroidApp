package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: OfferingOption$$Parcelable */
final class ag implements Creator<OfferingOption$$Parcelable> {
    private ag() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2401a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2402a(i);
    }

    public OfferingOption$$Parcelable m2401a(Parcel parcel) {
        return new OfferingOption$$Parcelable(parcel);
    }

    public OfferingOption$$Parcelable[] m2402a(int i) {
        return new OfferingOption$$Parcelable[i];
    }
}
