package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.etsy.android.lib.models.apiv3.q */
final class ListingCard$$Parcelable implements Creator<ListingCard$$Parcelable> {
    private ListingCard$$Parcelable() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2551a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2552a(i);
    }

    public ListingCard$$Parcelable m2551a(Parcel parcel) {
        return new ListingCard$$Parcelable(parcel);
    }

    public ListingCard$$Parcelable[] m2552a(int i) {
        return new ListingCard$$Parcelable[i];
    }
}
