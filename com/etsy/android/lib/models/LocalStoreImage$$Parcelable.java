package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.t */
final class LocalStoreImage$$Parcelable implements Creator<LocalStoreImage$$Parcelable> {
    private LocalStoreImage$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2962a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2963a(i);
    }

    public LocalStoreImage$$Parcelable m2962a(Parcel parcel) {
        return new LocalStoreImage$$Parcelable(parcel);
    }

    public LocalStoreImage$$Parcelable[] m2963a(int i) {
        return new LocalStoreImage$$Parcelable[i];
    }
}
