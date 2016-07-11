package com.etsy.android.lib.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.m */
final class ListingImage$$Parcelable implements Creator<ListingImage$$Parcelable> {
    private ListingImage$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2796a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2797a(i);
    }

    public ListingImage$$Parcelable m2796a(Parcel parcel) {
        return new ListingImage$$Parcelable(parcel);
    }

    public ListingImage$$Parcelable[] m2797a(int i) {
        return new ListingImage$$Parcelable[i];
    }
}
