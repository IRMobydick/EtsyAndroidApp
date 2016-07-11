package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.n */
final class LocalDeliveryDetails$$Parcelable implements Creator<LocalDeliveryDetails$$Parcelable> {
    private LocalDeliveryDetails$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2798a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2799a(i);
    }

    public LocalDeliveryDetails$$Parcelable m2798a(Parcel parcel) {
        return new LocalDeliveryDetails$$Parcelable(parcel);
    }

    public LocalDeliveryDetails$$Parcelable[] m2799a(int i) {
        return new LocalDeliveryDetails$$Parcelable[i];
    }
}
