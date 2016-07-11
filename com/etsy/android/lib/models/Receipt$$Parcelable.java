package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.w */
final class Receipt$$Parcelable implements Creator<Receipt$$Parcelable> {
    private Receipt$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2974a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2975a(i);
    }

    public Receipt$$Parcelable m2974a(Parcel parcel) {
        return new Receipt$$Parcelable(parcel);
    }

    public Receipt$$Parcelable[] m2975a(int i) {
        return new Receipt$$Parcelable[i];
    }
}
