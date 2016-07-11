package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.l */
final class Listing$$Parcelable implements Creator<Listing$$Parcelable> {
    private Listing$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2794a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2795a(i);
    }

    public Listing$$Parcelable m2794a(Parcel parcel) {
        return new Listing$$Parcelable(parcel);
    }

    public Listing$$Parcelable[] m2795a(int i) {
        return new Listing$$Parcelable[i];
    }
}
