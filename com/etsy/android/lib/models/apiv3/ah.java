package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: OfferingPrice$$Parcelable */
final class ah implements Creator<OfferingPrice$$Parcelable> {
    private ah() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2403a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2404a(i);
    }

    public OfferingPrice$$Parcelable m2403a(Parcel parcel) {
        return new OfferingPrice$$Parcelable(parcel);
    }

    public OfferingPrice$$Parcelable[] m2404a(int i) {
        return new OfferingPrice$$Parcelable[i];
    }
}
