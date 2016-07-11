package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.s */
final class LocalStore$$Parcelable implements Creator<LocalStore$$Parcelable> {
    private LocalStore$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2812a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2813a(i);
    }

    public LocalStore$$Parcelable m2812a(Parcel parcel) {
        return new LocalStore$$Parcelable(parcel);
    }

    public LocalStore$$Parcelable[] m2813a(int i) {
        return new LocalStore$$Parcelable[i];
    }
}
