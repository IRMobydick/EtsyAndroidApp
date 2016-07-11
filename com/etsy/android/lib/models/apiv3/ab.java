package com.etsy.android.lib.models.apiv3;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: MarketingBannerImage$$Parcelable */
final class ab implements Creator<MarketingBannerImage$$Parcelable> {
    private ab() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2391a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2392a(i);
    }

    public MarketingBannerImage$$Parcelable m2391a(Parcel parcel) {
        return new MarketingBannerImage$$Parcelable(parcel);
    }

    public MarketingBannerImage$$Parcelable[] m2392a(int i) {
        return new MarketingBannerImage$$Parcelable[i];
    }
}
