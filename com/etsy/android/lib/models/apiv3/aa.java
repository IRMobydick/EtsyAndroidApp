package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: MarketingBanner$$Parcelable */
final class aa implements Creator<MarketingBanner$$Parcelable> {
    private aa() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2389a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2390a(i);
    }

    public MarketingBanner$$Parcelable m2389a(Parcel parcel) {
        return new MarketingBanner$$Parcelable(parcel);
    }

    public MarketingBanner$$Parcelable[] m2390a(int i) {
        return new MarketingBanner$$Parcelable[i];
    }
}
